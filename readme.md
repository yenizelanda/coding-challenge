Coding Challenge Readme
=======================

For this coding challenge, I chose the SF Movies problem. I created a backend solution; a java library that 
gathers the necessary information from the internet using API calls and provides functions for easier searching.

I created a library that essentially provides three functions; getting a movie by name, getting a list of movies
that have the given prefix in their title, and getting a list of movies within a rectangular location.

The controller class first starts by making an API call to get the raw data of all movies in JSON format.
Then creates Location and Movie Java objects from these raw data. To get the latitude and longitude, I had
to make 2 google maps API calls for each location. I stored the locations and movies in maps so that duplicates
could be eliminated.

Finally, I also stored the Movies in an array as ordered by title, and Locations in 2 arrays, 1 for latitude
and 1 for longitude ordering. Thus, finding a given movie by title takes O(log(N)) time, and finding the list
of movies with a given string as prefix in their title takes O(log(N) + K) time where K is the number of movies
returned. Other than that, using the latitude and longitude ordered arrays for Locations allows me to eliminate
most of the locations when doing a search by lat/lon.

Also, the library includes unit tests to check the three functions, and movie and location creation.

cv: https://www.dropbox.com/s/laggzyt5jih4554/cv.pdf?dl=0

Since this challenge was implemented as a library, I could not host it as an application.