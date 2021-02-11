# Introduction

TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project.

# Getting Started

1- Install GIT

	sudo apt install git

2- Install Maven

	sudo apt install maven

3- Clone project to your local machine

	git clone git@github.com:Fel3180/scrapOfProducts.git

4- To run the project locally, we have a docker-compose file committed to the project's root path that downloads and runs an instance of
MongoDB, which is needed for execution. Run the following command to start it.

    docker-compose up

5- Run application:

    mvn spring-boot:run

6- Access Swagger:

    http://localhost:8082/swagger-ui.html#/