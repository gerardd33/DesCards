import unittest
from .. import create_app
from . import test_database


class TestAuth(unittest.TestCase):

    def setUp(self):
        test_database.wait_for_db()
        self.app = create_app()
        self.app.testing = True

    def test_login_logout_validate(self):
        with self.app.test_client() as client:
            username = 'admin'
            creds = {'username': username,
                     'password': 'admin'}
            # Invalid JWT with 'admin' encoded in it
            token = 'eyJhGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9'

            r = client.get('/auth/validate',
                           json={'token': token})
            self.assertFalse(r.json['is_valid'])
            self.assertEqual(r.status_code, 200)

            r = client.post('/auth/login', json=creds)
            self.assertEqual(r.status_code, 200)
            token = r.json['token']

            r = client.get('/auth/validate',
                           json={'token': token})
            self.assertTrue(r.json['is_valid'])
            self.assertEqual(r.status_code, 200)

            r = client.post('/auth/logout',
                            json={'token': token})
            self.assertEqual(r.status_code, 200)

            r = client.get('/auth/validate',
                           json={'token': token})
            self.assertFalse(r.json['is_valid'])
            self.assertEqual(r.status_code, 200)

    def test_wrong_password(self):
        with self.app.test_client() as client:
            creds = {'username': 'admin',
                     'password': 'pass'}
            r = client.post('/auth/login', json=creds)

            self.assertEqual(r.status_code, 403)

    def test_login_nonexisting_user(self):
        with self.app.test_client() as client:
            creds = {'username': 'nonexisting_user',
                     'password': 'pass'}
            r = client.post('/auth/login', json=creds)

            self.assertEqual(r.status_code, 403)

    def test_double_logout(self):
        with self.app.test_client() as client:
            creds = {'username': 'admin',
                     'password': 'admin'}
            r = client.post('/auth/login', json=creds)
            self.assertEqual(r.status_code, 200)
            token = r.json['token']

            r = client.post('/auth/logout', json={'token': token})
            self.assertEqual(r.status_code, 200)

            r = client.post('/auth/logout', json={'token': token})
            self.assertEqual(r.status_code, 200)

    def tearDown(self):
        pass
