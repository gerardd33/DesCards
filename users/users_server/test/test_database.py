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
            except psycopg2.OperationalError:
                pass

    def test_create_delete_user(self):
        self.database.create_user("test", "test")

        cur = self.database.conn.cursor()
        cur.execute("SELECT * FROM users WHERE username=%s", ('test',))

        test_user = cur.fetchone()
        self.assertIsNotNone("test")

        self.database.delete_user("test")
        cur.execute("SELECT * FROM users WHERE username=%s", ('test',))

        test_user = cur.fetchone()
        self.assertIsNone(test_user)

    def test_verify_user(self):
        self.database.create_user("test", "correct_password")

        result = self.database.validate_user("test", "wrong_pasword")
        self.assertFalse(result)

        result = self.database.validate_user("test", "correct_password")
        self.assertTrue(result)

        self.database.delete_user("test")

    def test_create_delete_validate_session(self):
        result = self.database.validate_session("test")
        self.assertFalse(result)

        self.database.create_session("test")

        result = self.database.validate_session("test")
        self.assertTrue("test")

        self.database.delete_session("test")

        result = self.database.validate_session("test")
        self.assertFalse("test")
