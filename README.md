Nasa Rover photos - Android App
===============================

**Learn how this app was designed and built in the [coding challenge](https://gist.github.com/1fd9f5e6447d00e57d7ef7bf0fe39d25.git), [architecture learning journey](docs/ArchitectureLearningJourney.md) and [modularization learning journey](docs/ModularizationLearningJourney.md).**

This is currently in a heave **work in progress**  🚧.

**Nasa Android app** is a partially functional Android app that is being build entirely with Kotlin and Jetpack Compose and XML (legacy imperative layouts).

# Features

**Nasa Android app** displays content of photos taken by any rovers and show the details of specific photo.

# Architecture

**Nasa Android app** follows the
[official architecture guidance](https://developer.android.com/topic/architecture)
and is described in detail in the
[architecture learning journey](docs/ArchitectureLearningJourney.md).

# Modularization

**Nasa Android app** has been fully modularized and you can find the detailed guidance and
description of the modularization strategy used in
[modularization learning journey](docs/ModularizationLearningJourney.md).

# UI
The app was designed using [Material 3 guidelines](https://m3.material.io/). Learn more about the design process and
obtain the design files in the [Now in Android Material 3 Case Study](https://goo.gle/nia-figma) (design assets [also available as a PDF](docs/Now-In-Android-Design-File.pdf)).

The Screens and UI elements are built entirely using [Jetpack Compose](https://developer.android.com/jetpack/compose).

The app has two themes:

- Dynamic color - uses colors based on the [user's current color theme](https://material.io/blog/announcing-material-you) (if supported)
- Default theme - uses predefined colors when dynamic color is not supported

Each theme also supports dark mode.

The app uses adaptive layouts to
[support different screen sizes](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes).

Find out more about the [UI architecture here](docs/ArchitectureLearningJourney.md#ui-layer).