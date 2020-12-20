FROM python:3.7-slim AS base

RUN pip install flask
RUN pip install requests
RUN pip install cerberus
RUN pip install pyjwt
RUN pip install pika

FROM base AS dev
WORKDIR /usr/src/app
CMD ["flask", "run", "--host=0.0.0.0"]

FROM node AS node-base
WORKDIR /usr/src/app/vue_app
COPY ./front_app/package.json ./
RUN ["npm", "install"]
COPY ./front_app/vue.config.js ./
COPY ./front_app/.eslintrc.js ./
COPY ./front_app/public/ ./public 
COPY ./front_app/src/ ./src 
RUN ["npm", "run", "build"]

FROM base AS prod
WORKDIR /usr/src/app/
COPY ./front_server/*.py ./front_server/
COPY --from=node-base /usr/src/app/front_server/static ./front_server/static
COPY --from=node-base /usr/src/app/front_server/templates ./front_server/templates
CMD ["flask", "run", "--host=0.0.0.0"]
