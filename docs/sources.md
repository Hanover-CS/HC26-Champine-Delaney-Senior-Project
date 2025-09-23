---
---

An android app that logs insect/other discoveries with photos, location, and date

My app will allow you to save discoveries in a database with description, time, date, and personal notes. It will implement a trained AI model that is trained to identify at least the family that the insect is in, Beetle/Coleoptera for example. Keeps track of discoveries in an organized way and allows users to search by time, location, or group.

## First Resource: Picture Insect

A similar existing app is Picture Insect which lets users identify insects using image recognition software. The app does keep track of discoveries but does not organize it well and does not let the user add personal notes to the data entry or keep track of date and time.

[Picture Insect App](https://pictureinsect.com/)

## Second Resource: Inaturalist API

If i want to train an AI model to recognize insect images, I will need a lot of images, hundreds or thousands for each group. Inaturalist API is a community database of natural history discovery images. You can query the API for insects, plants, mushrooms and other things of that type, and get back thousands of images. I will be using this API to get images to train my AI model.

## Third Resource: Room DB

[Room DB](https://developer.android.com/training/data-storage/room)

This is a tool for creating databases with Android Studio in Android apps.

This is a resource I think I will be able to use to keep track of data in my app and remember the user's information even after they leave the app.

## Fourth Resource: Seek by Inaturalist

[Seek]()

This is an app that does everything I want my app to be able to do and much more. It has an AI model that can identify any kind of animal, plant, or fungus. it organizes everything well. Most of the images captured by its thousands of users are uploaded into their API, which makes their AI model more accurate. The app is also able to use the information from its users to track the locations of certain animals and gather data on them, making their app more useful. the app only organizes observations by broad animal groups such as plants, fungus, or mammal, I hope to offer more options hopefully in a way that does not overcomplicate the search function. 

## Fifth Resource: Google Colaboratory

## Sixth Resource: Android Studio

## Seventh  Resource: PyTorch

## languages:

- Kotlin: for Android app functionality
    - I chose Kotlin specifically because of its easy to read and understand code and because it has some security measures in place that other languages such as c++ and Java do not have. This could mean less time trying to fix problems in the code and more time improving my project.
    - alternative
- Jetpack Compose: for UI in app
    - I chose Kotlin and Jetpack Compose because I have experience making Android apps with them and setting up the environment.
    - alternative
- Python
    - Python is used in Google colab to train and export AI. I chose Google Colab because it is probably the most efficient way to train image recognition software for free.
    - alternative
- SQL
    - I will need to use SQL to make queries from inaturalist API and manage my own database in Android Studio. As far as I know, SQL is the only language used for interacting with relational databases. However, I do know that it is by far the most widely used for its versitility and ease of use.




