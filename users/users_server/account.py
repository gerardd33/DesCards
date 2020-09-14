# import functools
from flask import Blueprint, request, current_app
from . import db
from . import verify


bp = Blueprint('account', __name__, url_prefix='/account')


@bp.route('/register', methods=['POST'])
def register():
    schema = {'username': {'type': 'string'},
              'password': {'type': 'string'}}
    data = verify.validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    username = data['username']
    password = data['password']

    database = db.get_db()

    try:
        database.create_user(username, password)
    except Exception:  # TODO use correct type of exception
        return 'Failed to create an account', 409

    return 'Success', 200


@bp.route('/delete', methods=['POST'])
def delete():
    schema = {'token': {'type': 'string'}}
    data = verify.validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    # validate token
    token = data['token']
    json = verify.jwt_decode(token,
                             current_app.config['JWT_SECRET'],
                             current_app.config['JWT_ALGORITHM'])

    user_id = json['userId']

    database = db.get_db()

    # Do logout here
    try:
        database.delete_session(user_id)
        database.delete_user(user_id)
    except Exception:  # TODO use correct type of exception
        return 'Failed to delete an account', 409

    return 'Success', 200
