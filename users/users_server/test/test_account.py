import unittest
from .. import create_app
from . import test_database


class TestUserRegistration(unittest.TestCase):

    def setUp(self):
        test_database.wait_for_db()
        self.app = create_app()
        self.app.testing = True

    def test_add_user(self):
        user = 'new_test_user'
        password = 'pass'

        with self.app.test_client() as client:
            json = {'username': user, 'password': password}
            res = client.post('/account/register', json=json)

            self.assertEqual(res.status_code, 200)

    def test_add_same_user_twice(self):
        pass

    def test_remove_user(self):
        pass

    def test_remove_nonexisting_user(self):
        pass

    def test_incomplete_register_request(self):
        pass
