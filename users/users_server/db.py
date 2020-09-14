import psycopg2
from flask import g
import datetime
import os
import bcrypt


SESSION_VALID_TIME = datetime.timedelta(minutes=15)

DATABASE_NAME = os.environ['POSTGRES_DB']
DATABASE_USER = os.environ['POSTGRES_USER']
DATABASE_PASSWORD = os.environ['POSTGRES_PASSWORD']
DATABASE_HOST = "database"


class Database():

    def __init__(self, database_name):
        self.conn = psycopg2.connect(dbname=database_name,
                                     user=DATABASE_USER,
                                     password=DATABASE_PASSWORD,
                                     host=DATABASE_HOST)

    def create_user(self, username, password):
        cur = self.conn.cursor()
        password = password.encode('utf-8')
        password = bcrypt.hashpw(password, bcrypt.gensalt())
        cur.execute("INSERT INTO users (username, password) VALUES (%s, %s) RETURNING id",
                    (username, password))
        user_id = cur.fetchone()[0]
        self.conn.commit()
        return user_id

    def delete_user(self, userid):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM users WHERE id = %s", (userid,))
        self.conn.commit()

    def validate_user(self, username, password):
        cur = self.conn.cursor()
        cur.execute("SELECT id, username, password FROM users WHERE username = %s",
                    (username,))

        user = cur.fetchone()

        if user is None:
            return None

        if bcrypt.checkpw(password.encode('utf-8'), user[2].tobytes()):
            return user[0]
        else:
            return None

    def create_session(self, userid):
        self.delete_session(userid)
        cur = self.conn.cursor()
        cur.execute("INSERT INTO sessions (userid, expires) VALUES (%s, %s) RETURNING id",
                    (userid, datetime.datetime.now() + SESSION_VALID_TIME))
        session_id = cur.fetchone()[0]
        self.conn.commit()
        return session_id

    def validate_session(self, userid):
        cur = self.conn.cursor()
        cur.execute("SELECT id, userid, expires FROM sessions WHERE userid=%s",
                    (userid,))
        session = cur.fetchone()

        if session is None:
            return False

        if session[2] < datetime.datetime.now():
            return False

        return True

    def delete_session(self, userid):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM sessions WHERE userid = %s", (userid,))
        self.conn.commit()

    def __del__(self):
        if hasattr(self, 'conn'):
            self.conn.close()


def get_db(db_name=DATABASE_NAME):
    if 'db' not in g:
        g.db = Database(db_name)

    return g.db


def close_db(error=None):
    db = g.pop('db', None)

    if db is not None:
        del db
