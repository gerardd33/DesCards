FROM python:3.7-slim

RUN pip install flask
RUN pip install pyjwt
RUN pip install psycopg2-binary

WORKDIR /usr/src/app

CMD ["flask",  "run", "--host=0.0.0.0"]
# TODO copy code instead of using volume
