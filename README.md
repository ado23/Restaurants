# Restaurants Ember.JS & Play Framework app

The purpose of this repository is to allow people to practice their Java and JavaScript skills by adding Features to an existing and working Play Fraemwork and Ember.JS application.

## Setup

You need to have a running PostgreSQL server, with an empty database.
On it, you will need to run the following two commands before starting the application for the first time:

`CREATE EXTENSION IF NOT EXISTS pgcrypto;`

`CREATE EXTENSION IF NOT EXISTS postgis;`

After that, you should configure yout database settings in the `conf/application.conf` file.
