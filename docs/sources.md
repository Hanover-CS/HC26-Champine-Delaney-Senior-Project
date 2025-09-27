---
---

# Reme&#347;

An Android app that logs insect/other discoveries with photos, location, and date

My app will allow you to save discoveries in a database with description, time, date, and personal notes. It will implement a trained AI model that is trained to identify at least the family that the insect is in, Beetle/Coleoptera for example. Keeps track of discoveries in an organized way and allows users to search by time, location, or group.
<br>
<br>
<br>
<br>
# Existing Solutions: 
***

## First Solution: Picture Insect

A similar existing app is Picture Insect which lets users identify insects using image recognition software. The app does keep track of discoveries but does not organize it well and does not let the user add personal notes to the data entry or keep track of date and time.

![Alt text](/docs/images/flamingo.png)

[Reference page](#picture-insect)

## Second Solution: Seek by Inaturalist

This is an app that does everything I want my app to be able to do and much more. It has an AI model that can identify any kind of animal, plant, or fungus. it organizes everything well. Most of the images captured by its thousands of users are uploaded into their API, which makes their AI model more accurate. The app is also able to use the information from its users to track the locations of certain animals and gather data on them, making their app more useful. the app only organizes observations by broad animal groups such as plants, fungus, or mammal, I hope to offer more options hopefully in a way that does not overcomplicate the search function. 

[Reference Page](#seek)
<br>
<br>
<br>
<br>
<br>
# Android Studio Resources
*** 

## First Android Resource: Android Studio

Android Studio is  the IDE that I will be using to program and test the Android app that I am making. It often supports Android Emulators: virtual machines that allow you to test apps and app functionality. However, I have an Android phone set up to test my apps on, so I will not be using this. Android Studio is designed specifically for Android apps and is therefore optimized to build them.

[Reference Page](#android-studio)

## Second Android Resource: Room DB

This is a tool for creating databases with Android Studio in Android apps. It works well with Android Studio, Jetpack Compose, and the other resources I am using.

This is a resource I will be able to use to keep track of data in my app and remember the user's information even after they leave the app.

[Reference Page](#room-db)
<br>
<br>
<br>
<br>
# AI Training Resources
***

## First AI Resource: Google Colaboratory

Google Colaboratory or Google Colab is a service that allows you to write and execute python code as well as write text in the same document. It makes it easy to share code with others and provides access to GPUs for free. Google Colab uses Jupyter Notebook Environment to achieve this. 

This is where I will gather images from Inaturalist API and train and test the PyTorch AI model. The GPU that the service provides is very helpful in training an AI model in image recognition. Google Colabs direct connection to Google Drive also makes storing and editing my dataset easier.

[Reference Page](#google-colab)

## Second AI Resource: Inaturalist API

If i want to train an AI model to recognize insect images, I will need a lot of images, hundreds or thousands for each group. Inaturalist API is a community database of natural history discovery images. You can query the API for insects, plants, mushrooms and other things of that type, and get back thousands of images. I will be using this API to get images to train my AI model.

[Reference Page](#inaturalist)

## Third AI Resource: PyTorch

PyTorch is an open source machine learning library developed by Meta AI. It is a popular choice for beginners as it is easy to use and very flexible. It is also chosen for more advanced projects for its efficient AI learning tools. 

I will be using a PyTorch AI model within Google Colab. I will be training it to recognize different groups of insects and maybe even specific species depending on how well the initial training goes. 

[Reference Page](#pytorch)
<br>
<br>
<br>
<br>
## languages:
***

- Kotlin: for Android app functionality
    - I chose Kotlin specifically because of its easy to read and understand code and because it has some security measures in place that other languages such as c++ and Java do not have. This could mean less time trying to fix problems in the code and more time improving my project.
    - Java is another language that would work well towards Android app functionality. However, Java is incompatible with Jetpack Compose, which is the UI and design software I am using.
- Jetpack Compose: for UI in app
    - I chose Kotlin and Jetpack Compose because I have experience making Android apps with them and setting up the environment. It is very easy to understand and to visualize the changes I am making to the UI while writing the code.
    - Another method of creating UI in Android apps is XML based UI development. This method is widely used and the more traditional way to create a UI. One disadvantage that XML development has that Jetpack Compose does not have is that the code for layout and logic are seperate which added a layer of complexity for me that was not in Jetpack compose.
- Python
    - Python is used in Google colab to train and export AI. I chose Google Colab because it is probably the most efficient way to train image recognition software for free.
    - Tensorflow is another similar AI learning model. It is also open source and free to use. Ultimately, i decided to use PyTorch instead because of its more beginner friendly nature.
- SQL
    - I will need to use SQL to make queries from inaturalist API and manage my own database in Android Studio. As far as I know, SQL is the only language used for interacting with relational databases. However, I do know that it is by far the most widely used for its versitility and ease of use.



***
# <h2 align="center">References</h2>
***
<br>

## Picture Insect

...
[Picture Insect App](https://pictureinsect.com/)

## Seek

[Seek](https://www.inaturalist.org/)

## Android Studio

[Android Studio](https://developer.android.com/studio)

## Room DB

[Room DB](https://developer.android.com/training/data-storage/room)

## Google Colab

[Google Colab](https://colab.research.google.com/)

## Inaturalist

[Inaturalist](https://www.inaturalist.org/)

## PyTorch

[PyTorch](https://pytorch.org/)
    
