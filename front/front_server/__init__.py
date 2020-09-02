from flask import Flask


def create_app():
    app = Flask(__name__)

    from . import index
    app.register_blueprint(index.bp)

    from . import api
    app.register_blueprint(api.bp)

    return app


if __name__ == '__main__':
    create_app().run(host='0.0.0.0', port=5000)
