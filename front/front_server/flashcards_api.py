from flask import Blueprint, jsonify, request, g
import requests
import os
from random import randint, random
from . import utils

bp = Blueprint('flashcards_api', __name__, url_prefix='/api')

USERS_HOST = 'http://' + os.environ['USERS']
FLASHCARDS_HOST = 'http://' + os.environ['FLASHCARDS']
# TODO make https

flashcards = [
    {'id': i, 'back': randint(1, 9999), 'front': random(), 'deckId': 1,
     'created': '1:2:3', 'interval': randint(1, 8400000)}
    for i in range(100)
]


def get_session():
    if 'session' not in g:
        g.session = requests.Session()

    return g.session


@bp.before_request
def auth():
    token = request.cookies.get('token')
    if token is None:
        return "Invalid token", 403

    res = get_session().get(USERS_HOST + '/auth/validate',
                            json={'token': token})
    if res.status_code != 200:
        return res.content, res.status_code

    print(res.json(), flush=True)
    if not res.json()['is_valid']:
        return "Invalid token", 403

    return None


@bp.route('/user_decks', methods=['GET'])
def decks():
    token = request.cookies.get('token')
    if token is None:
        return "Invalid token", 403

#   json = utils.jwt_decode(token)

#   res = requests.get(FLASHCARDS_HOST + '/user-decks/' + json['userId'])
    res = requests.get(FLASHCARDS_HOST + '/user-decks/1')

    return res.content, res.status_code


@bp.route('/deck', methods=['GET'])
def deck():
    schema = {'deckId': {'type': 'string'},
              'page': {'type': 'string'},
              'perPage': {'type': 'string'},
              'sortBy': {'type': 'string',
                         'allowed': ['interval', 'created', 'front', 'back']},
              'direction': {'type': 'string',
                            'allowed': ['asc', 'desc']},
              }

    json = request.args.to_dict()
    print(json, flush=True)
    data = utils.validate(json, schema)

    if data is None:
        return 'Incorrect data', 400

    res = requests.get(FLASHCARDS_HOST + '/deck/' + data['deckId'],
                       json=data)

#   print(res.json(), flush=True)
    return res.content, res.status_code

#   first = int(data['page']) * int(data['perPage'])
#   after_last = first + int(data['perPage'])

#   return jsonify(flashcards[first:after_last]), 200


@bp.route('/categories', methods=['GET'])
def categories():
    categories = [
        {'id': 1, 'name': 'history', 'specialFields': ['date', 'who', 'wher']},
        {'id': 2, 'name': 'biology', 'specialFields': ['definition']},
        {'id': 3, 'name': 'xd', 'specialFields': ['dae', 'o', 'h']},
        {'id': 4, 'name': 'math', 'specialFields': ['definition', 'result']},
    ]

    return jsonify(categories)
