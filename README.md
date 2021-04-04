## About
Weather finder is an application to search weather information in Indonesia.
You can put the favourite location also to make you easier find it next time.

## Project Configuration WeatherFinder (v1.0.0):
- Android Studio: **4.1.2**
- Gradle Version: **5.1.1**
- Kotlin Version: **1.3.50**
- Minimum SDK Support: **API 16 (Jelly Bean)**
- Compile SDK Version: **API 28**
- Source Compatibility: **Java version 1.8 (Java 8)**
- Target Compatibility: **Java version 1.8 (Java 8)**
- Android Support Library: **AndroidX**
- Coroutine : **1.3.9** (Threading)
- Retrofit : **2.6.1** (Web service)
- Dagger : **2.8** (Inject)
- Picasso : **2.5.2** (Load and caching image)


## Project Architecture:
- Using MVP architecture to connect between view and presenter, presenter hold all the business logic including request.
- Using room dao to store the favourite location.
- Using retrofit to request webservice.
- All the process in business logic runs on background thread while UI run on UIthread.
- Using coroutine to thread the webservice request.
- Controller for request is at ControllerEndPoint.
- Repo for database is at RepositoryContent.
- Class for loading image is at UtilImage.
- All the class the we create include fragment, inject to ComponentActivity


## Permissions
On Android versions prior to Android 6.0, WeatherFinder requires the following permissions:
- Full Network Access.
- View Network Connections.

## Code Workflow:
- SplashActivity : Use handler to show logo animation popping up in 0.5 seconds, then after 2 seconds intent to HomeActivity.
- HomeActivity : Contain navigation between fragment and bottom navigation view. For navigation we use navigation android to open between HomeFragment, SearchFragment, and FavouriteFragment.
- HomeFragment : Contain weather UI for jakarta, first we call presenterHome to request Jakarta weather detail using couroutine. if response is either successfull or failed, call viewlayer to send to view. View will update the UI by receive the data and run UIThread
- SearchFragment : Contain list of location with ID, for this one I download id and location for Indonesia only and put to assets called city_list_indo.json. <br>
First step we call presenter view created to load all the city and put to arraylist. Then show to view all the data in recyclerview. <br>
At the edit text, I put ontextchange listener, so when user type something, it will immediately call the presenter, to filter the array by the words that user type just now. Then send the array location to view.<br>
Before show the array of location to view, call the location database, and get all the id. it will return the id that user already put to favourite. And if locationn contain the id, put hasFavourite to true so holder will update the heart to red.<br>
Everytime user press the heart, check the status of hasFavouite, either we put to database, or delete from database.<br>
Last one everytime user click the location, call the intent to weather detail to load 3 days forecase by sending name and ID.
- FavouriteFragment : Contain list of location in user database. Just all the location inside database.<br>
Everytime user press the heart, check the status of hasFavouite, either we put to database, or delete from database.<br>
Last one everytime user click the location, call the intent to weather detail to load 3 days forecase by sending name and ID.
- WeatherActivity : Contain 3 days forecast detail for the location that user choose already.<br>
First get intent id and name from get intent.<br>
Load data detail at presenter by request the ID. <br>
API only have 5 days forecase no 3 days forecast, in that case i need to filter 3 days first to serve to user. <br>
Get all the data in one array list. <br>
Create 3 array list to hold the data between date, then get date from the first index. <br>
Loop the arraylist if contain date, then put to arraylist while remove data.<br>
If the next item have diff date, break. get the date frorm the first index and repeat until 3 times.<br>
Can get 3 arraylist with differrent date and then put adapter so the adapter will show the data by date.




## Screenshots

[<img src="/readme/ss1.png" align="left"
width="200"
    hspace="10" vspace="10">](/readme/ss1.png)
[<img src="/readme/ss2.png" align="left"
width="200"
    hspace="10" vspace="10">](/readme/ss2.png)
[<img src="/readme/ss3.png" align="left"
width="200"
    hspace="10" vspace="10">](/readme/ss3.png)
