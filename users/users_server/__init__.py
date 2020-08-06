from flask import Flask, jsonify
import jwt


def create_app():
    app = Flask(__name__)

    from . import auth
    app.register_blueprint(auth.bp)

    return app

# Authentciate user. returns access token
# @app.route('/auth')
# def hello_world():
#     return "{'access':'ok'}"  # TODO Export algorithm to config


if __name__ == '__main__':
    create_app().run(host='0.0.0.0', port=5000)
