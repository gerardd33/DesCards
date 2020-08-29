import unittest
from .. import db
import psycopg2


class TestDatabase(unittest.TestCase):

    @classmethod
    def setUpClass(self):
        while True:
            try:
                self.database = db.Database("develop")
                break
            except Exception:
                pass

    def test_create_user(self):
        self.database.create_user("test", "test")

        cur = self.database.conn.cursor()
        cur.execute("SELECT * FROM user WHERE username=test")

        test_user = cur.fetchone()
        print(test_user)
