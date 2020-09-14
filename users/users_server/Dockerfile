FROM python:3.7-slim as base

RUN pip install flask
RUN pip install pyjwt
RUN pip install psycopg2-binary
RUN pip install cerberus
RUN pip install bcrypt

WORKDIR /usr/src/app

FROM base AS dev
CMD ["flask",  "run", "--host=0.0.0.0"]

FROM base AS test
# CMD ["python3", "./users_server/run_tests.py"]
CMD ["python3", "-m", "unittest"]
# CMD ["python3", "-m", "unittest", "discover","users_server/tests"]

FROM bsae AS prod
# TODO copy code instead of using volume
