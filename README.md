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


For command line actions, you have the option of using a fastlane script.
Run `bundle exec fastlane` for faster execution.
* Build debug apk
  * `fastlane build`

* Tests with code coverage
  * `fastlane test`

  The instrumented test reports are merged in /build/reports/androidTests.
  The unit test reports are found in /$module/build/reports/tests.
  The code coverage reports are found in /build/reports/javacoco.

* Lint checks
  * `fastlane lint`

  All reports are saved in /build/reports/lint

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