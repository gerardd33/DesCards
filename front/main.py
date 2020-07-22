from flask import Flask, render_template
import requests

app = Flask(__name__)

@app.route('/')
def hello_world():
    token = requests.get('http://users_server_1:5000/auth').text
    return render_template('index.html', token=token)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
