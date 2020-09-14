import unittest
from .. import create_app
from . import test_database


class TestUserRegistration(unittest.TestCase):

    def setUp(self):
        test_database.wait_for_db()
        self.app = create_app()
        self.app.testing = True

    def test_add_remove_user(self):
        user = 'new_test_user'
        password = 'pass'

        with self.app.test_client() as client:
            json = {'username': user, 'password': password}
            res = client.post('/account/register', json=json)
            self.assertEqual(res.status_code, 200)

            res = client.post('/auth/login', json=json)
            self.assertEqual(res.status_code, 200)

            token = res.json['token']

            # test deleting
            res = client.post('/account/delete',
                              json={'token': token})
            self.assertEqual(res.status_code, 200)

            # test double deletion of the same account
            res = client.post('/account/delete',
                              json={'token': token})
            self.assertEqual(res.status_code, 200)

            # after deletion, account should be logged out
            res = client.get('/auth/validate',
                              json={'token': token})
            self.assertEqual(res.status_code, 200)
            self.assertFalse(res.json['is_valid'])

    def test_add_same_user_twice(self):
        user = 'same_user'
        password = 'pass'

        with self.app.test_client() as client:
            json = {'username': user, 'password': password}
            res = client.post('/account/register', json=json)
            self.assertEqual(res.status_code, 200)

            res = client.post('/account/register', json=json)
            self.assertEqual(res.status_code, 409)
