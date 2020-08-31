from flask import Blueprint, request, jsonify, current_app
from . import db
# from cerberus import Validator
import jwt
from . import verify

bp = Blueprint('auth', __name__, url_prefix='/auth')

# TODO test falied database queries


@bp.route('/login', methods=['POST'])
def login():
    schema = {'username': {'type': 'string'},
              'password': {'type': 'string'}}
    data = verify.validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    database = db.get_db()

    is_validated = database.validate_user(data['username'],
                                          data['password'])

    if is_validated is False:
        return 'Incorrect login or password', 403

    database.create_session(data['username'])
    token = jwt.encode({'username': data['username']},
                       current_app.config['JWT_SECRET'],
                       algorithm=current_app.config['JWT_ALGORITHM'])
    # TODO Add some noise, so that token is different every time
    token = token.decode()
    return jsonify({'token': token}), 200


@bp.route('/logout', methods=['POST'])
def logout():
    schema = {'token': {'type': 'string'}}
    data = verify.validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    # validate token
    token = request.json['token']
    json = verify.jwt_decode(token,
                             current_app.config['JWT_SECRET'],
                             current_app.config['JWT_ALGORITHM'])

    if json is None:
        return 'Incorrect token', 403

    username = json['username']

    database = db.get_db()
    database.delete_session(username)
    return 'Logged out', 200


@bp.route('/validate', methods=['GET'])
def validate():
    schema = {'token': {'type': 'string'}}
    data = verify.validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    # validate token
    token = request.json['token']
    json = verify.jwt_decode(token,
                             current_app.config['JWT_SECRET'],
                             current_app.config['JWT_ALGORITHM'])

    if json is None:
        return jsonify({'is_valid': False}), 200

    username = json['username']

    database = db.get_db()
    is_valid = database.validate_session(username)

    return jsonify({'is_valid': is_valid}), 200
