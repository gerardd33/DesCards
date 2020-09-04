from flask import Blueprint, jsonify, request
from cerberus import Validator
import requests

bp = Blueprint('api', __name__, url_prefix='/api')

USERS_HOST = 'http://users_server_1:5000'  # TODO read from .env
# TODO make https


def validate(dictionary, schema):
    val = Validator(schema)

    if dictionary is None or not val.validate(dictionary):
        return None

    return dictionary


@bp.route('/test')
def index():
    test = {'test': 123123}
    return jsonify(test), 200


@bp.route('/login', methods=['POST'])
def login():
    # weryfikacja poprawności requesta
    schema = {'username': {'type': 'string'},
              'password': {'type': 'string'}}
    data = validate(request.json, schema)

    print(data, flush=True)

    if data is None:
        return 'Incorrect data', 400

    # wysłanie zapytania do users
    try:
        res = requests.post(USERS_HOST + '/auth/login', json=data)
        if res.status_code != 200:
            return 'Falied to authenticate', 403

        # zwrócenie tokena
        token = res.json()['token']
        response = jsonify({})
        response.set_cookie('token', token, httponly=True)
        return response

    except requests.exceptions.ConnectionError:
        return 'Users service not available', 500
