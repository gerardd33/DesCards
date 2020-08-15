# import functools
from flask import Blueprint
from . import db

bp = Blueprint('auth', __name__, url_prefix='/auth')


@bp.route('/login', methods=['POST', 'GET'])
def login():
    database = db.get_db()
    users = database.create_user('test', 'test')
    print(users, flush=True)
    return 'login'
