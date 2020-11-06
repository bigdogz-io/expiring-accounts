# The Shopping Spree Account

TODO:
* Build status and code coverage badges
* API docs link on service

Sample Axon based service which manages the funds available and transactions posted against
a simple account. The type of account (ie. credit card, gift card, bank account) is agnostic 
in this example. The goal is to provide actual functionality against a generic account.

## Features

* REST API: Show CQRS with web-based calls
* Commands: Simple commands to manage a simple agnostic account context
* Framework of the application is functional

## Usage

The service manages an Account and tracks its balance and list of transactions charged to the account. It
allows for the creation of a new account, adding funds to an account, using funds in the account (charge transactions),
and viewing all debit transactions charged to the account.

### REST

TODO: Link to API docs

The simple way to control the service is to use the Postman collection in this repo to send commands 
and run queries.

## Running

The application can be run as a standard Spring Boot application. However it is configured to be built and run with Helm 
onto a Kubernetes cluster using skaffold.

`skaffold dev`

Assuming you have minikube or an external cluster configured this will build and deploy the application.

**NOTE** 
If you are using the `--port-forward` option to skaffold you may see a bunch of warnings about being unable to map
pods to a service. Set an environment variable of `SKAFFOLD_DISABLE_SERVICE_FORWARDING=1` to make this go away. The 
value of this variable doesn't matter, it just needs to be set.

### Prerequisites

You must run both MongoDB and Axon Server on your local system for the application to function properly.
While there are a few ways to install Mongo and Axon server for local system use, this example will use
docker images running on a local docker runtime.

#### Run Axon Server

```shell script
docker run -d --name axon-server -p 8024:8024 -p 8124:8124 axoniq/axonserver
```

#### Run MongoDB

```shell script
docker run --name mongo -p 27017:27017 -d mongo:4.2
```

### Running the Service

Run within your IDE or with `java -jar` for initial command processing.
