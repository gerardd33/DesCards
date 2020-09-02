from flask import Blueprint, jsonify

bp = Blueprint('api', __name__, url_prefix='/api')


@bp.route('/test')
def index():
    test = {'test': 123123}
    return jsonify(test), 200
