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
        cur.execute("INSERT INTO users (username, password) VALUES (%s, %s)",
                    (username, password))
        self.conn.commit()

    def delete_user(self, username):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM users WHERE username = %s", (username,))
        self.conn.commit()

    def validate_user(self, username, password):
        cur = self.conn.cursor()
        cur.execute("SELECT username, password FROM users WHERE username = %s",
                    (username,))

        user = cur.fetchone()
        # if not PyMemoryView_Check(hashed_passwd):
        #    return None
#        hashed_passwd = PyMemoryView_GET_BUFFER(hashed_passwd)

        return bcrypt.checkpw(password.encode('utf-8'), user[1].tobytes())

    def create_session(self, username):
        cur = self.conn.cursor()
        cur.execute("INSERT INTO sessions (username, expires) VALUES (%s, %s)",
                    (username, datetime.datetime.now() + SESSION_VALID_TIME))
        self.conn.commit()

    def validate_session(self, username):
        pass

    def delete_session(self, username):
        pass

    def __del__(self):
        if hasattr(self, 'conn'):
            self.conn.close()


def get_db(db_name="db"):
    if 'db' not in g:
        g.db = Database(db_name)

    return g.db


def close_db(error=None):
    db = g.pop('db', None)

    if db is not None:
        del db
