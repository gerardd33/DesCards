from flask import Blueprint, request, g
import requests
import os
from random import randint, random
from . import utils
import pika
import json

bp = Blueprint('flashcards_api', __name__, url_prefix='/api')

USERS_HOST = 'http://' + os.environ['USERS']
FLASHCARDS_HOST = 'http://' + os.environ['FLASHCARDS']
RABBIT_HOST = 'generator-queue'
GENERATOR_EXCHANGE = 'generator-request-exchange'
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
    res = requests.get(FLASHCARDS_HOST + '/categories')
    return res.content, res.status_code


@bp.route('/update_cards', methods=['POST'])
def update_cards():
    schema = {'deckId': {'type': 'string'},
              'flashcards': {'type': 'list'}}
    print(request.json, flush=True)
    data = utils.validate(request.json, schema)

    if data is None:
        return "Invalid data", 400

    deckId = data['deckId']
    res = requests.post(FLASHCARDS_HOST + f'/deck/{deckId}/update-cards/',
                        json=data['flashcards'])
    return res.content, res.status_code


@bp.route('/remove_cards', methods=['POST'])
def remove_cards():
    schema = {'deckId': {'type': 'string'},
              'removedIds': {'type': 'list'}}
    data = utils.validate(request.json, schema)

    if data is None:
        return "Invalid data", 400

    deckId = data['deckId']
    res = requests.post(FLASHCARDS_HOST + f'/deck/{deckId}/remove-cards/',
                        json=data['removedIds'])
    return res.content, res.status_code


@bp.route('/update_intervals', methods=['POST'])
def update_intervals():
    schema = {'deckId': {'type': 'string'},
              'intervals': {'type': 'list'}}
    data = utils.validate(request.json, schema)

    if data is None:
        return "Invalid data", 400

    deckId = data['deckId']
    res = requests.post(FLASHCARDS_HOST + f'/deck/{deckId}/update-intervals/',
                        json=data['intervals'])
    return res.content, res.status_code


@bp.route('/deck_info', methods=['GET'])
def deck_info():
    schema = {'deckId': {'type': 'string'}}
    json = request.args.to_dict()
    data = utils.validate(json, schema)

    if data is None:
        return "Invalid data", 400

    deckId = data['deckId']
    res = requests.get(FLASHCARDS_HOST + f'/deck/{deckId}/info/')
    return res.content, res.status_code


@bp.route('/add_manual', methods=['POST'])
def add_manual():
    schema = {'deckId': {'type': 'string'},
              'front': {'type': 'string'},
              'back': {'type': 'string'}}
    data = utils.validate(request.json, schema)

    if data is None:
        return "Invalid data", 400

    deckId = data['deckId']
    res = requests.post(FLASHCARDS_HOST + f'/deck/{deckId}/add-card/',
                        json=data)
    return res.content, res.status_code


@bp.route('/add_auto', methods=['POST'])
def add_auto():
    schema = {'deckId': {'type': 'string'},
              'query': {'type': 'string'},
              'fields': {'type': 'list'},
              'verbosity': {'type': 'string'}}
    print(request.json, flush=True)
    data = utils.validate(request.json, schema)

    if data is None:
        return "Invalid data", 400

    # TODO make connection once and store it
    connection = pika.BlockingConnection(
        pika.ConnectionParameters(host=RABBIT_HOST))
    channel = connection.channel()

    channel.exchange_declare(exchange=GENERATOR_EXCHANGE)

    channel.basic_publish(exchange=GENERATOR_EXCHANGE, routing_key='',
                          body=json.dumps(data))

    print('added to queue', data, flush=True)

    return "Request added", 200
