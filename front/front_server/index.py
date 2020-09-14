from flask import Blueprint, render_template

bp = Blueprint('index', __name__, url_prefix='/')


@bp.route('/', defaults={'path': ''})
@bp.route('/<path:path>')
def index(path):
    return render_template('index.html')
