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
            token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIn0.Xs1l2H7ui_yqE-GlQ2GARQ5ZpjuS8B8xQaooy89Q8y8'  # Invalid JWT with 'admin' encoded in it
            r = client.get('/auth/validate',
                           json={'token': token})
            self.assertFalse(r.json['is_validated'])

            r = client.post('/auth/login', json=creds)
            token = r.json['token']

            r = client.get('/auth/validate',
                           json={'token': token})
            self.assertTrue(r.json['is_validated'])

            r = client.post('/auth/logout', json={'username': username})

            r = client.get('/auth/validate',
                           json={'token': token})
            self.assertFalse(r.json['is_validated'])

    def tearDown(self):
        pass
