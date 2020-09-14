sudo dokcer-compose build

docker tag descards_front-server gcr.io/descards/front-server:0.1
docker push gcr.io/descards/front-server:0.1

docker tag descards_users-server gcr.io/descards/users-server:0.1
docker push gcr.io/descards/users-server:0.1

docker tag descards_users-database gcr.io/descards/users-database:0.1
docker push gcr.io/descards/users-database:0.1

docker tag descards_flascards-server gcr.io/descards/flascards-server:0.1
docker push gcr.io/descards/flascards-server:0.1

docker tag descards_generator gcr.io/descards/generator:0.1
docker push gcr.io/descards/generator:0.1
