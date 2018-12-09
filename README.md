# Lab 08 - Object Oriented Design: Java Quotes
This lab creates an app that prints out a random quote generated from the ,/assets/recentquotes.json file.

# Lab 09 - Web requests / Potent Quotables
Builds on lab 08 by using an API to retrieve random quotes, plush caching.

## App Description
See Javadoc for application detailed specs:
/java-quotes/javadoc/Quote.html
/java-quotes/javadoc/App.html

This application represents quotes using a Quote class. The App class has a main method which is
intended for users to run and get a random quote. The way the app works, is to attempt to retrieve
a quote from the API below, and if it fails, to fallback to printing a cached quote from the json
file.
http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en

## Instructions
It can be run with Java App, or from within IntelliJ, or in Windows command line using ./gradlew.bat.
No arguments required or supported.

This application also grows the quote cache (recentquotes.json) every time the app is run and
a quote is retrieved from the API. There is a copy of the json cache file that can be used to
restore the cache to it's original state, before insertions based on quotes from the API.
./assets/recentquotes - original.json
       
## Testing
We utilized JUnit testing to test every method in the Quote class, and make sure we were generating
an approximately normal distribution of indexes with our getRandomQuote() method.


## Dependencies
* [Gson](https://github.com/google/gson)    

To install Gson, copy the following code into your dependencies:   
```
dependencies {
       implementation 'com.google.code.gson:gson:2.8.5'
}
```
