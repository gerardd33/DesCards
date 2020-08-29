# import functools
from flask import Blueprint, request, jsonify
from . import db
from cerberus import Validator
import jwt

bp = Blueprint('auth', __name__, url_prefix='/auth')

JWT_SECRET = 'secret'  # TODO make it better
JWT_ALGORITHM = 'HS256'


@bp.route('/login', methods=['POST'])
def login():
    schema = {'username': {'type': 'string'},
              'password': {'type': 'string'}}
    val = Validator(schema)

    if request.json is None or not val.validate(request.json):
        return 'Incorrect data', 400

    database = db.get_db()

    is_validated = database.validate_user(request.json['username'],
                                          request.json['password'])

    if is_validated is None:
        return 'Incorrect login or password', 403

    database.create_session(request.json['username'])
    token = jwt.encode({'username': request.json['username']},
                       JWT_SECRET,
                       algorithm=JWT_ALGORITHM)
    # Add some noise, so that token is different every time
    token = token.decode()
    return jsonify({'token': token}), 200


@bp.route('/logout', methods=['POST'])
def logout():
    schema = {'username': {'type': 'string'}}
    val = Validator(schema)
    if request.json is None or not val.validate(request.json):
        return 'Incorrect data', 400

    database = db.get_db()
    database.delete_session(request.json['username'])
    return 'Logged out', 200


@bp.route('/validate', methods=['GET'])
def validate():
    schema = {'token': {'type': 'string'}}
    val = Validator(schema)
    if request.json is None or not val.validate(request.json):
        return 'Incorrect data', 400

    # validate token
    token = request.json['token']
    try:
        json = jwt.decode(token, JWT_SECRET, algorithms=[JWT_ALGORITHM])
    except jwt.exceptions.InvalidSignatureError:
        return jsonify({'is_validated': False}), 200

    username = json['username']

    database = db.get_db()
    is_validated = database.validate_session(username)

    return jsonify({'is_validated': is_validated}), 200
