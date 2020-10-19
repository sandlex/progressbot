# Development

## How to create app from scratch
* create account on heroku.com
* `heroku login`
* `heroku create`
* `heroku apps:rename prgrssbot`
* `heroku addons:create heroku-postgresql`
* run `heroku config` and make sure `DATABASE_URL` is present
* go to [app page](https://dashboard.heroku.com/apps/prgrssbot/settings) and create variables `BOT_NAME` and `BOT_TOKEN`
* deploy app `git push heroku master` (or `git push heroku HEAD:master`)

## How to develop locally with one bot
* shut down prod instance `heroku ps:scale web=0`
* do your work on local environment
* shut down local app
* scale up prod instance `heroku ps:scale web=1`

## How to check billing
* `heroku ps -a prgrssbot`

## Working with prod database
* `heroku pg:info --app prgrssbot`
* `heroku pg:psql --app prgrssbot`
* `select * from person/project/submission;`

# Links
* [GitHub](https://github.com/sandlex/progressbot)
* [Deploying Spring Boot Applications to Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)
* [Free Dyno Hours](https://devcenter.heroku.com/articles/free-dyno-hours)
* [Creating a Telegram Bot in Java: from conception to deployment](https://medium.com/codegym/creating-a-telegram-bot-in-java-from-conception-to-deployment-8f8230b81b97)
* [Heroku Postgres](https://devcenter.heroku.com/articles/heroku-postgresql#using-the-cli)