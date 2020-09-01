from flask import Flask, render_template
import requests


def create_app():
    app = Flask(__name__)

    @app.route('/')
    def hello_world():
        token = requests.get('http://users_server_1:5000/auth').text
        return render_template('index.html', token=token)

    return app


if __name__ == '__main__':
    create_app().run(host='0.0.0.0', port=5000)
