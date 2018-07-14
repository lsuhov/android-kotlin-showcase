Android Kotlin Showcase

A MVVM Android project that tries to use the most popular libraries from
2018 in combination with Kotlin.

This project is hitting the New York Times API in order to get a list of
most viewed articles. The user can select one of the articles and will be
redirected to a web browser.

Getting Started
---------------
Before trying to build the project you have to go to https://developer.nytimes.com/signup
and generate an API Key. Besides the Name and Email address, in the Website
field I've put the package name of the app which is com.test.showcase.masterdetail_kotlintechnology.
Also in the API section please select 'Most Popular API'.

After you receive the apiKey, you'll have to add it to the build.gradle
from your home directory, in the following format:
MASTER_DETAIL_APP_API_KEY_NYT_MOST_VIEWED="YOUR API KEY"
More info in this link: https://medium.com/code-better/hiding-api-keys-from-your-android-repository-b23f5598b906


This project uses the Gradle build system. To build this project, use the
`gradlew build` command or use "Import Project" in Android Studio.

There are two Gradle tasks for testing the project:
* `connectedAndroidTest` - for running Espresso on a connected device
* `test` - for running unit tests


Relevant libraries used
-----------------------

* Dagger

* Model layer
  * Retrofit
  * RxJava

* View model layer
  * ViewModel from Android Architecture Components
  * LiveData

* View layer
  * ConstraintLayout
  * RecyclerView