from flask import Flask


def create_app():
    app = Flask(__name__)

    from . import index
    app.register_blueprint(index.bp)

    from . import users_api
    app.register_blueprint(users_api.bp)

    from . import flashcards_api
    app.register_blueprint(flashcards_api.bp)

    return app


if __name__ == '__main__':
    create_app().run(host='0.0.0.0', port=5000)
