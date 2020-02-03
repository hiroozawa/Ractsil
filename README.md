# Ractsil
[![CircleCI](https://circleci.com/gh/hiroozawa/Ractsil.svg?style=svg&circle-token=0056cfbc318289166b1432c78a96345406381dd7)](https://circleci.com/gh/hiroozawa/Ractsil)
[![codebeat badge](https://codebeat.co/badges/1eae3c89-f0bb-41b3-b723-94a60b463a16)](https://codebeat.co/projects/github-com-hiroozawa-ractsil-master)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.50-blue.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Repo Size](https://img.shields.io/github/repo-size/nuhkoca/market_tech_challenge)](https://github.com/hiroozawa/ractsil)


Native android application that shows a list of cars and also shows them in a map.

### apk
A apk can be downloaded [here](https://drive.google.com/open?id=1U2mRZsVCsjSiNDGXC4RpknmqtScHarmB)

### Libraries

- Kotlin Coroutines - Used for writing asynchronous code and easily keeping the hard work off the main thread.
- Retrofit2, OkHttp3 and Gson - For the api network calls and json serialization. Also retrofit integrates nicely with coroutines.
- Dagger Android - Used for constructing the dependency injection graph of our project.
- Coil - An image loading library for Android backed by Kotlin Coroutines.
- Architecture Components: Navigation with safeargs, ViewModel, LiveData are used in this project.
- Espresso - For UI and navigation tests.
- MockWebserver - As its name suggests, it is used for mocking our api calls

### Architecture and Tests

The project Ractsil uses a rather simple architecture based on architecture blueprints. The repository, viewmodels and data mappers are all unit tested. 
Using Dagger, Espresso and MockWebServer we are able to make “end to end tests”, these tests reach from the API calls to the UI  and navigation.
