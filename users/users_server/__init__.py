from flask import Flask
import secrets


def create_app():
    app = Flask(__name__)

    app.config.from_mapping(
        JWT_SECRET=secrets.token_hex(32),
        JWT_ALGORITHM='HS256',
    )

    from . import auth
    app.register_blueprint(auth.bp)

    from . import account
    app.register_blueprint(account.bp)

    # register close db
    from . import db
    app.teardown_appcontext(db.close_db)

    return app


if __name__ == '__main__':
    create_app().run(host='0.0.0.0', port=5000)
