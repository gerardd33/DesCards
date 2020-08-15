import psycopg2
from flask import g


class Database():

    def __init__(self):
        print("database connection opened", flush=True)
        self.conn = psycopg2.connect(dbname="test",
                                     user="postgres",
                                     password="postgres",
                                     host="database")

    def create_user(self, username, password):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM users")
        users = cur.fetchall()
        return users

    def delete_user(self, username):
        pass

    def create_session(self, username):
        pass

    def get_session(self, ):
        pass

    def __del__(self):
        print("database connection closed", flush=True)
        self.conn.close()


def get_db():
    if 'db' not in g:
        g.db = Database()

    return g.db


def close_db(error=None):
    db = g.pop('db', None)

    if db is not None:
        del db
