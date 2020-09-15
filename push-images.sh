sudo docker-compose build

docker tag descards_front-server gcr.io/descards/front-server:$1
docker push gcr.io/descards/front-server:$1

docker tag descards_users-server gcr.io/descards/users-server:$1
docker push gcr.io/descards/users-server:$1

docker tag descards_users-database gcr.io/descards/users-database:$1
docker push gcr.io/descards/users-database:$1

docker tag descards_flashcards-server gcr.io/descards/flashcards-server:$1
docker push gcr.io/descards/flashcards-server:$1

docker tag descards_generator gcr.io/descards/generator:$1
docker push gcr.io/descards/generator:$1
