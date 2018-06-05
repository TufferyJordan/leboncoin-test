# Technical test for Le Bon Coin
#### CI status for master : ![Image du status de master sur circle ci](https://circleci.com/gh/TufferyJordan/leboncoin-test/tree/master.png?style=shield&circle-token=0263721f1457026f4cefaec0d039fa71c78525b3)
A small application developed for LeBonCoin as a technical test.
The API REST is available [here](http://jsonplaceholder.typicode.com/photos)

## Technical choices
* MVP architecture
* Retrofit for REST request
* Android SDK for caching the data
* Dagger 2 for the dependency injection

## Technical constraints
* Min API : 14
* GIT versionning
* Data still available when offline after restarting the application
* Code should be docummented 