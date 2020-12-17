# DesCards – flashcard generator

Microservice application for fast and easy automatic generation of flashcards to optimise learning of important facts. Supports creating flashcards automatically and manually, maintaining own flashcard libraries, SRS reviewing and exporting to third-party applications like Anki. Deployed in a Kubernetes cluster on Google Cloud.

More information about the functionality, architecture and flow of the application is available in DesCards.pdf.

## Technologies

- Java, Spring Boot
- Python, Flask
- Vue.js, Vuetify, JavaScript
- RabbitMQ, Docker, Kubernetes, Google Cloud Platform (GKE)
- PostgreSQL, Selenium
- Hibernate, Maven, Lombok, JUnit, Mockito


## Running locally

*Keep in mind that the application is still an early version and work in progress. The basic functionality is available, but there are still major bugs and UX issues to be fixed, improvements to be made as well as new features to be added in the near future.*

To run it locally, first make sure you have Docker installed. Once you do this, clone this repository and enter the *DesCards* folder:

```
git clone https://github.com/gerardd33/DesCards
cd DesCards
```

Then run the following command:

```
docker-compose up
```

If you run it for the first time, the application will take several minutes to build. After that, starting it using the same command will be considerably faster. Once it is up and running, it can be accessed by typing ``localhost:5000`` in your browser.
