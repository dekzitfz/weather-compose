# Weather App
A simple weather application that allow users to search for a city, display its weather on the home screen, and persist the selected city across launches.

## Tech stack & Open-source libraries
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
- Jetpack Libraries:
    - Jetpack Compose: Android’s modern toolkit for declarative UI development.
    - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
    - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
    - DataStore: Store key-value pairs to persist data.
    - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
    - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
    - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Constructs REST APIs for network data retrieval.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API for code generation and analysis.
- [Coil](https://github.com/coil-kt/coil): Library for load image from network.
- [Secrets Gradle Plugin](https://github.com/google/secrets-gradle-plugin): Gradle plugin for providing your secrets to your Android project.

## Setup instructions
- Clone this repository
- Get your API Key from [WeatherAPI](https://www.weatherapi.com/docs/)
- Create file `secrets.properties` on the root project and place your API Key: `apiKey=YOUR_API_KEY`
- Build & run the project
