# DesCards – flashcard generator

Microservice application for fast and easy automatic generation of flashcards to optimise learning of important facts. Supports creating flashcards automatically and manually, maintaining own flashcard libraries, SRS reviewing and exporting to third-party applications like Anki. Deployed in a Kubernetes cluster on Google Cloud.

More information about the functionality, architecture and flow of the application is available in DesCards.pdf.


## Technologies

- Java, Spring Boot
- Python, Flask
- Vue.js, JavaScript
- RabbitMQ, Docker, Kubernetes, Google Cloud Platform (GKE)
- PostgreSQL, Selenium
- Hibernate, Maven, Lombok, JWT


## Contributors

- [Gerard Dróżdż](https://github.com/gerardd33): Flashcards, Generator, improvements to Front
- [Artur Matyjasek](https://github.com/Jaskier234): Front, Users, deployment configuration


## Running locally

### Setup

*Please keep in mind that the application is still an early version and work in progress. The basic functionality is available, but there are still major bugs and issues to be fixed, improvements to be made as well as new features to be added in the near future.*

To run it locally, first make sure you have ``docker`` and ``docker-compose`` installed and the [Docker daemon running](https://docs.docker.com/config/daemon/systemd/#start-manually). Once you do this, clone this repository and enter the *DesCards* folder:

```
git clone https://github.com/gerardd33/DesCards
cd DesCards
```

Then run the following command:

```
sudo docker-compose up
```

If you run it for the first time, the application will take several minutes to build and run. Running it the next time using the same command will not require building from scratch so it will be considerably faster. Once it is up and running, it can be accessed by typing ``localhost:5000`` in your browser.

### Cleanup

If you have run DesCards locally and don't need it anymore, remember to clean up your machine by removing the created Docker containers and images, for example using the following commands:

```
sudo docker rm $(docker ps -aq --filter "name=descards*")
sudo docker rmi $(docker images -q "descards*")
sudo docker image prune
```
