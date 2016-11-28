# Amazon Books

Simple code challenge. The main feature is to get a list of Book objects from an Amazon endpoint. Those books are displayed in a list.

##Architecture design
The architecture is designed as an MVP using RxJava+Retrofit+Dagger. Is inspirated in [Fernando Ceja's clean architecture](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/).

###Layered architecture
We have several layers, first at the bottom of all the layers we have the network layer that is basically the Retrofit layer where we have our server-side endpoints declarated in the Retrofit interface ([RetrofitAmazonService](https://github.com/4gus71n/AmazonBooks/blob/bce90d30537985b1891a9f9fb3d281cedcc01140/app/src/main/java/com/si/amazonbooks/net/services/RetrofitAmazonService.java)). Another layer above we have the [BookRepository](https://github.com/4gus71n/AmazonBooks/blob/bce90d30537985b1891a9f9fb3d281cedcc01140/app/src/main/java/com/si/amazonbooks/repository/BookRepository.java) that is an implementation of the [Repository Pattern](http://martinfowler.com/eaaCatalog/repository.html) (for this case we only have the network implementation, the [NetworkBookRepository](https://github.com/4gus71n/AmazonBooks/blob/bce90d30537985b1891a9f9fb3d281cedcc01140/app/src/main/java/com/si/amazonbooks/datasource/NetworkBookRepository.java) but we can have several repositories implementations and grab the data from different sources, database, memory cache, etc.). Above the Repository layer we have the UseCases or Interactor, in our case we only have the [GetBooksUseCases](https://github.com/4gus71n/AmazonBooks/blob/bce90d30537985b1891a9f9fb3d281cedcc01140/app/src/main/java/com/si/amazonbooks/net/usecases/GetBooksUseCase.java). And above that we have the MVP architecture. Only the Presenters can use the different UseCases or Interactor.

###MVP architecture
The MVP is implemented using LightCycle to bind the views (Fragments, Activities or Custom Views) to the Presenters.

##Libraries and tech
- Retroyfit: To perform the http requests.
- RxJava: To perform the asynchronous tasks.
- Butterknife: To void all the findViewById boilerplate code.
- Dagger: To glue all the components toghether.
- LightCycle: To avoid hooking Presenters into the Fragments/Activities lifecycle methods. 
- Glide: To load the images from the web.
- Mockito/Robolectric: I made a very basic Unit Test for the view. Used Robolectric to build the Unit Test. Used Mockito to mock the Book objects.

##Notes
- I also added a small snippet to cancel the image loading if we scroll too fast. This way we can avoid stacking up several request from images that will not be displayed.
- This is a simplified version of the Clean Architecture, usually I'd have more modules depending in the complexity/size of the project. I also skipped some layers for example, if the project is big enought I'd separate the model layer in the server-side model objects (the ones with the Gson annotations) and in the app model classes (without any annotations and the Parcelable implementation).

##APK
You can download the debug apk from [here](https://github.com/4gus71n/AmazonBooks/blob/master/app-debug.apk)

##Screenshot
![alt tag](https://github.com/4gus71n/AmazonBooks/blob/master/Screenshot_2016-11-28-19-12-35.png?raw=true)
