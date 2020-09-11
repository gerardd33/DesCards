from flask import Blueprint, jsonify, request
import requests
from . import utils

bp = Blueprint('api', __name__, url_prefix='/api')

USERS_HOST = 'http://users_server_1:5000'  # TODO read from .env
# TODO make https


@bp.route('/test')
def index():
    test = {'test': 123123}
    return jsonify(test), 200


@bp.route('/login', methods=['POST'])
def login():
    # weryfikacja poprawności requesta
    schema = {'username': {'type': 'string'},
              'password': {'type': 'string'}}
    data = utils.validate(request.json, schema)

    print(data, flush=True)

    if data is None:
        return 'Incorrect data', 400

    # wysłanie zapytania do users
    try:
        res = requests.post(USERS_HOST + '/auth/login', json=data)
        if res.status_code != 200:
            return 'Falied to authenticate', res.status_code

        # zwrócenie tokena
        token = res.json()['token']
        response = jsonify({})
        response.set_cookie('token', token, httponly=True)
        return response

    except requests.exceptions.ConnectionError:
        return 'Users service not available', 500


@bp.route('/logout', methods=['POST'])
def logout():
    token = request.cookies.get('token')

    print(token, flush=True)

    if token is None:
        return 'Invalid token', 403

    res = requests.post(USERS_HOST + '/auth/logout',
                        json={'token': token})

    if res.status_code == 200:
        response = jsonify({})
        response.set_cookie('token', expires=0)
        return response
    else:
        return 'Failed to logout', res.status_code


@bp.route('/validate', methods=['GET'])
def verify():
    token = request.cookies.get('token')

    print(token, flush=True)

    if token is None:
        return 'Invalid token', 403

    res = requests.get(USERS_HOST + '/auth/validate',
                       json={'token': token})

    return res.content, res.status_code


@bp.route('/register', methods=['POST'])
def register():
    # weryfikacja poprawności requesta
    schema = {'username': {'type': 'string'},
              'password': {'type': 'string'}}
    data = utils.validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    # wysłanie zapytania do users
    try:
        res = requests.post(USERS_HOST + '/account/register', json=data)
        return res.content, res.status_code

    except requests.exceptions.ConnectionError:
        return 'Users service not available', 500
