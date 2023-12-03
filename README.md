# MyDogsBreed

List of random dog's images

-------

* PROJECT EXPLANATION:

  The main project test a list of random images of dogs.
  The first one is fetching data from the Dogs API to get those images.
  That screen represent a list of dog's images.

* DESIGN PATTERS:

1. Builder - Retrofit API as an object creation pattern for Retrofit.
2. Dependency Injection - Dagger Hilt for simplicity as it works well with the VM pero screen.
3. Singleton pattern - as dependency injection modules.
4. Factory - Gson converter factory in Retrofit for parsing HTTP responses into an object.
5. Facade - Imports and dependencies for APIS needed like hilt navigation for example.
6. States - Added along VM to propagate a wrap the flow results.

* APP ARCHITECTURE:

1. Model View ViewModel - Stores data and updates the views and models accordingly.
2. Clean architecture - Data, Business logic, Presentation - VM and UI layers.

* TECHNOLOGIES USED AND DEPENDENCIES:

1. Kotlin - Main language of programming.
2. Jetpack Compose - Jetpack Compose is Android’s modern toolkit for building native UI and make it
   easy to test.
3. Coroutines - Fetching async data along flow.
4. Flow - A cold asynchronous data stream that sequentially emits values and completes normally or
   with an exception.
5. Android Architecture Components - Collection of libraries that help you design robust, testable,
   and maintainable apps.
6. ViewModel - Stores UI-related data that isn't destroyed on UI changes.
7. Dependency Injection - Hilt - Easier way to incorporate Dagger DI into Android apps.
8. Retrofit - A type-safe HTTP client for Android and Java.
9. Material Components - Theming, layouts and animations.
10. Mockito - Main testing tool for mocking object injections and unit testing the presentation and
    repositories of the project.

* What would I improve if I had more time?

  Probably I'd implement a swipe to refresh to call the list of images and get continuously random
  ones.
  also I'd add a caching mechanism like Room database.