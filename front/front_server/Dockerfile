FROM python:3.7-slim

RUN pip install flask
RUN pip install requests
RUN pip install cerberus

WORKDIR /usr/src/app
CMD ["flask", "run", "--host=0.0.0.0"]
