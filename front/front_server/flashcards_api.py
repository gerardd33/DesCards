from flask import Blueprint, jsonify, request
import requests
from random import randint, random
from . import utils

bp = Blueprint('flashcards_api', __name__, url_prefix='/api')

FLASHCARDS_HOST = 'http://users_server_1:5000'  # TODO read from .env
# TODO make https

flashcards = [
    {'id': i, 'back': randint(1, 9999), 'front': random(), 'deckId': 1,
     'created': '1:2:3', 'interval': randint(1, 8400000)}
    for i in range(100)
]


@bp.route('/user_decks', methods=['GET'])
def decks():
    # weryfikacja poprawności requesta
    # schema = {'userid': {'type': 'integer'}}
    # data = validate(request.json, schema)

    # print(request.json, flush=True)

    # if data is None:
    #     return 'Incorrect data', 400

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

    first = int(data['page']) * int(data['perPage'])
    after_last = first + int(data['perPage'])

    return jsonify(flashcards[first:after_last]), 200


@bp.route('/categories', methods=['GET'])
def categories():
    categories = [
        {'id': 1, 'name': 'history', 'specialFields': ['date', 'who', 'wher']},
        {'id': 2, 'name': 'biology', 'specialFields': ['definition']},
        {'id': 3, 'name': 'xd', 'specialFields': ['dae', 'o', 'h']},
        {'id': 4, 'name': 'math', 'specialFields': ['definition', 'result']},
    ]

    return jsonify(categories)
