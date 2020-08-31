# import functools
from flask import Blueprint, request
from . import db
from cerberus import Validator


bp = Blueprint('account', __name__, url_prefix='/account')


@bp.route('/register', methods=['POST'])
def register():
    schema = {'username': {'type': 'string'},
              'password': {'type': 'string'}}
    val = Validator(schema)

    if request.json is None or not val.validate(request.json):
        return 'Incorrect data', 400

    username = request.json['username']
    password = request.json['password']

    database = db.get_db()

    try:
        database.create_user(username, password)
    except Exception:  # TODO use correct type of exception
        return 'Failed to create an account', 409

    return 'Success', 200


@bp.route('/delete', methods=['POST'])
def delete():
    schema = {'username': {'type': 'string'}}
    val = Validator(schema)

    if request.json is None or not val.validate(request.json):
        return 'Incorrect data', 400

    username = request.json['username']

    database = db.get_db()

    try:
        database.delete_user(username)
    except Exception:  # TODO use correct type of exception
        return 'Failed to delete an account', 409

    return 'Success', 200
