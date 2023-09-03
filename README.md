# MyDogsBreed
List of dog's breeds and images

-------
* PROJECT EXPLANATION:
The project is divided in two main screens. The first one is fetching data from the Dogs API provided. That screen represent a list of dog's breeds.
The second screen fetches data of a specific breed queried and add that data into a room database for faster loading and not constant random representation
of images for the same breed.

* DESIGN PATTERS:
1. Builder - Retrofit API as a creational pattern. 
2. Dependency Injection - Dagger Hilt for simplicity as it works well with the VM. 
3. Singleton pattern as dependency injection modules. 
4. Factory - Gson converter factory in Retrofit for parsing HTTP responses into an object. 
5. Facade - GsonConverterFactory, working behind the scenes as a JSON deserializer. 
6. States - Added along VM to propagate a wrap the flow results.

* APP ARCHITECTURE:
1. Model View ViewModel - Stores data and updates the views and models accordingly. 
2. Clean architecture - Data, Business logic, Presentation - VM and UI layers.

* TECHNOLOGIES USED AND DEPENDENCIES:
1. Kotlin - Main language of programming. 
2. Jetpack Compose - Jetpack Compose is Android’s modern toolkit for building native UI. 
3. Coroutines - Fetching async data along flow. 
4. Flow - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception. 
5. Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps. 
6. ViewModel - Stores UI-related data that isn't destroyed on UI changes. 
7. Dependency Injection - Hilt - Easier way to incorporate Dagger DI into Android apps. 
8. Retrofit - A type-safe HTTP client for Android and Java. 
9. Room database for caching data within the app and representing some data faster. 
10. Material Components - Theming, layouts and animations. 
11. Mockito - Main testing tool for mocking object injections and unit testing the presentation and repositories of the project.
