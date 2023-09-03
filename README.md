# MyDogsBreed
List of dog's breeds and images

-------
- PROJECT EXPLANATION:
The project is divided in two main screens. The first one is fetching data from the Dogs API provided. That screen represent a list of dog's breeds.
The second screen fetches data of a specific breed queried and add that data into a room database for faster loading and not constant random representation
of images for the same breed.
- DESIGN PATTERS:
 -- Builder - Retrofit API as a creational pattern.
 -- Dependency Injection - Dagger Hilt for simplicity as it works well with the VM.
 -- Singleton pattern as dependency injection modules.
 -- Factory - Gson converter factory in Retrofit for parsing HTTP responses into an object.
 -- Facade - GsonConverterFactory, working behind the scenes as a JSON deserializer.
 -- States - Added along VM to propagate a wrap the flow results.
- APP ARCHITECTURE:
 -- Model View ViewModel - Stores data and updates the views and models accordingly.
 -- Clean architecture - Data, Business logic, Presentation - VM and UI layers.
- TECHNOLOGIES USED AND DEPENDENCIES:
 -- Kotlin - Main language of programming.
 -- Jetpack Compose - Jetpack Compose is Android’s modern toolkit for building native UI.
 -- Coroutines - Fetching async data along flow.
 -- Flow - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
 -- Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.
 -- ViewModel - Stores UI-related data that isn't destroyed on UI changes.
 -- Dependency Injection - Hilt - Easier way to incorporate Dagger DI into Android apps.
 -- Retrofit - A type-safe HTTP client for Android and Java.
 -- Room database for caching data within the app and representing some data faster.
 -- Material Components - Theming, layouts and animations.
 -- Mockito - Main testing tool for mocking object injections and unit testing the presentation and repositories of the project.
