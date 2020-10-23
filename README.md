# Android Note App
This simple application illustrate Android Development with Android Jectpack.

Android Note App is a simple app that use many of the Jetpack libraries build with `minSdkVersion = 28` due to Google Play policies updates.

To clarify the project structure, we use 3 modules:
* **app:** Presentation module that contains all the views and UI Logic with Navigation library.
* **domain:** Domain module contains all the entities, usecases, repositories interfaces.
* **data:** Data module contains the database(Room) and DAO, DataMappers, Data Entities, Repositories implementation and Datasources.
* **buildSrc:** This is not a module itself, this is just a kotlin-dsl that provides all the dependencies for our gradle files. Of course, we use `kotlin-dsl` to build the project.

<br/>
<p align="center"><img width="578" alt="Poster Android" src="https://user-images.githubusercontent.com/7152507/96949303-353cb200-14ad-11eb-9008-e5637f45faab.png"></p>


## Introduction
As you know Google suggest the usage of Android Jetpack libraries to develop high performance applications with the lastest features that it provides.

We use additional features such as Flow with Coroutines and some customization with Day/Night themes.

## Screenshots

### Day: Modo normal
<p align="center">
 <img height="380" alt="Pantalla Listado de Nota en Modo Day" src="https://user-images.githubusercontent.com/7152507/96952424-74223600-14b4-11eb-8e0b-550aee69d706.jpg">
 <img height="380" alt="Pantalla Crear Nota en Modo Day" src="https://user-images.githubusercontent.com/7152507/96952413-71274580-14b4-11eb-9107-a1da7a45a828.jpg">
 <img height="380" alt="Pantalla Eliminar Nota en Modo Day" src="https://user-images.githubusercontent.com/7152507/96952422-73899f80-14b4-11eb-8709-9cdcdbb4d80d.jpg">
</p>

### Night: Modo oscuro
<p align="center">
 <img height="380" alt="Pantalla Listado de Nota en Modo Night" src="https://user-images.githubusercontent.com/7152507/96952407-6d93be80-14b4-11eb-9846-bb0437a47ffb.jpg">
 <img height="380" alt="Pantalla Crear Nota en Modo Night" src="https://user-images.githubusercontent.com/7152507/96952418-72587280-14b4-11eb-8624-35d34db0aba5.jpg">
 <img height="380" alt="Pantalla Eliminar Nota en Modo Night" src="https://user-images.githubusercontent.com/7152507/96952420-72f10900-14b4-11eb-80e7-2676a1895a1f.jpg">
</p>


## Libraries
* [Architecture](https://developer.android.com/topic/libraries/architecture) - Android architecture components are a collection of libraries that help you design robust, testable, and maintainable apps.
  * [View Binding](https://developer.android.com/topic/libraries/view-binding?hl=fr) - View binding is a feature that allows you to more easily write code that interacts with views.
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - LiveData is an observable data holder class.
  * [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library provides an abstraction layer over SQLite.
  * [Navigation](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.
  
* [Foundation](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers follow best practices
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Android KTX is a set of Kotlin extensions that are included with Android Jetpack and other Android libraries
  
* [UI](https://developer.android.com/guide/topics/ui) - Shows how you can use UI elements to build interesting designs
  * [Fragment](https://developer.android.com/guide/components/fragments) - A Fragment represents a behavior or a portion of user interface in a FragmentActivity
  * [Layout](https://developer.android.com/guide/topics/ui/declaring-layout) - A layout defines the structure for a user interface in your app, such as in an activity
  * [ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout) - A ConstraintLayout is a android.view.ViewGroup which allows you to position and size widgets in a flexible way.

* Other libraries
  * [Material Components](https://material.io/components) - Material Components for Android (MDC-Android) help developers execute Material Design.
  * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
  * [Coroutines](https://developer.android.com/kotlin/coroutines) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.

## Future work
This repository is still in progress, the following functionality are:

* ***Testing:** In this feature we will implement unit testing for all the features and for dependency injection with Hilt Test*
* ***Search:** This feature is still in progress for searching notes in the main screen*
* ***Paging:** For this feature we will implement Paging with the Paging library 2 or maybe 3*

## Additional information
* If you find a bug, report it creating and issue.
* If you want to contribute in this simple project, please create a fork and do a pull request with the new features.

## Contact 📋
✉️ [Gmail](jjorge.rc93@gmail.com)

🐦 [Twitter](https://twitter.com/jjrodcast)

😀 [LinkedIn](https://www.linkedin.com/in/jorge-rodr%C3%ADguez-castillo/)
