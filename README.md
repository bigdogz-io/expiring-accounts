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
