from flask import Blueprint, jsonify, request
from cerberus import Validator
import requests
from random import randint, random

bp = Blueprint('api', __name__, url_prefix='/api')

USERS_HOST = 'http://users_server_1:5000'  # TODO read from .env
FLASHCARDS_HOST = 'http://users_server_1:5000'  # TODO read from .env
# TODO make https

flashcards = [
    {'id': i, 'back': randint(1, 9999), 'front': random(), 'deckId': 1,
     'created': '1:2:3', 'interval': randint(1, 8400000)}
    for i in range(100)
]


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
    data = validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    # wysłanie zapytania do users
    try:
        res = requests.post(USERS_HOST + '/account/register', json=data)
        return res.content, res.status_code

    except requests.exceptions.ConnectionError:
        return 'Users service not available', 500


@bp.route('/user_decks', methods=['GET'])
def decks():
    # weryfikacja poprawności requesta
    schema = {'userid': {'type': 'integer'}}
    data = validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    decks = [
        {'id': 1, 'name': 'Talia 1', 'user': 'admin'},
        {'id': 2, 'name': 'Talia 2', 'user': 'admin'},
        {'id': 3, 'name': 'Talin 3', 'user': 'admin'},
        {'id': 4, 'name': 'Historia', 'user': 'admin'},
        {'id': 5, 'name': 'Angielski', 'user': 'admin'},
    ]

    return jsonify(decks), 200


@bp.route('/deck', methods=['GET'])
def deck():
    schema = {'deckid': {'type': 'integer'},
              'offset': {'type': 'integer'},
              'limit': {'type': 'integer'},
              'sortBy': {'type': 'string',
                         'allowed': ['interval', 'created', 'front', 'back']},
              'direction': {'type': 'string',
                            'allowed': ['asc', 'desc']},
              }

    data = validate(request.json, schema)

    if data is None:
        return 'Incorrect data', 400

    first = data['offset']
    after_last = first + data['limit']

    return jsonify(flashcards[first:after_last]), 200
