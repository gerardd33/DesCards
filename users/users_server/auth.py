from flask import Blueprint, request, jsonify, current_app
from . import db
# from cerberus import Validator
import jwt
from . import verify

bp = Blueprint('auth', __name__, url_prefix='/auth')


@bp.route('/login', methods=['POST'])
def login():
    schema = {'username': {'type': 'string'},
              'password': {'type': 'string'}}
    data = verify.validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    database = db.get_db()

    user_id = database.validate_user(data['username'],
                                     data['password'])

    if user_id is None:
        return 'Incorrect login or password', 403

    session_id = database.create_session(user_id)
    token = jwt.encode({'username': data['username'],
                        'userId': user_id,
                        'sessionId': session_id},
                       current_app.config['JWT_SECRET'],
                       algorithm=current_app.config['JWT_ALGORITHM'])
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

    user_id = json['userId']

    database = db.get_db()
    database.delete_session(user_id)
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

    user_id = json['userId']

    database = db.get_db()
    is_valid = database.validate_session(user_id)

    return jsonify({'is_valid': is_valid}), 200
