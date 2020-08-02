from flask import Flask, jsonify
import jwt

app = Flask(__name__)


# Authentciate user. returns access token
@app.route('/auth')
def hello_world():
    token = jwt.encode({'access': 'true'}, 'secret TODO better secret', algorithm='HS256')
    print(token)
    return "{'access':'ok'}"  # TODO Export algorithm to config


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
