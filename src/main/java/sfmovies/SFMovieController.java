package sfmovies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SFMovieController {
	
	public static OkHttpClient client = new OkHttpClient();
	
	private ArrayList<Movie> moviesByTitle;
	private ArrayList<Location> locationsByLat;
	private ArrayList<Location> locationsByLng;
	
	
	public SFMovieController() throws IOException{
		this(10);
	}
	
	public SFMovieController(int limit) throws IOException {

		Request datebaseRequest = new Request.Builder().url("https://data.sfgov.org/resource/wwmu-gmzc.json?$limit=" + limit).build();

		Response databaseResponse = client.newCall(datebaseRequest).execute();
		JSONArray rawMoviesArray = new JSONArray(databaseResponse.body().string());
		
		for (int i = 0; i < rawMoviesArray.length(); i++)
			Movie.createMovie(rawMoviesArray.getJSONObject(i));
		
		moviesByTitle = new ArrayList<Movie>(Movie.movieMap.values());
		locationsByLng = new ArrayList<Location>(Location.locationMap.values());
		locationsByLat = new ArrayList<Location>(Location.locationMap.values());
		
		Collections.sort(moviesByTitle);
		Collections.sort(locationsByLat, new LatComparator());
		Collections.sort(locationsByLng, new LngComparator());
	}
	
	public Movie getMovieByTitle(String title) {
		return Movie.movieMap.get(title);
	}
	
	public ArrayList<Movie> getMoviesByAutoCompleteTitle(String input) {
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		Movie title = new Movie(input);
		
		int index = Collections.binarySearch(moviesByTitle, title);
		
		if (index < 0)
			index = -(index+1);
		
		for (;index < moviesByTitle.size() && moviesByTitle.get(index).getTitle().startsWith(input); index++)
			movies.add(moviesByTitle.get(index));
		
		return movies;
	}
	
	public ArrayList<Location> getLocationsByLatLng(double minLat, double maxLat, double minLng, double maxLng) {
		Location minLocation = new Location(minLat, minLng);
		Location maxLocation = new Location(maxLat, maxLng);

		int minLatIndex = Collections.binarySearch(locationsByLat, minLocation, new LatComparator());
		if (minLatIndex < 0)
			minLatIndex = -(minLatIndex+1);
		int minLngIndex = Collections.binarySearch(locationsByLng, minLocation, new LngComparator());
		if (minLngIndex < 0)
			minLngIndex = -(minLngIndex+1);
		int maxLatIndex = Collections.binarySearch(locationsByLat, maxLocation, new LatComparator());
		if (maxLatIndex < 0)
			maxLatIndex = -(maxLatIndex+1);
		int maxLngIndex = Collections.binarySearch(locationsByLng, maxLocation, new LngComparator());
		if (maxLngIndex < 0)
			maxLngIndex = -(maxLngIndex+1);
		
		Set<Location> latLocations = new HashSet<Location>(locationsByLat.subList(minLatIndex, maxLatIndex));
		Set<Location> lngLocations = new HashSet<Location>(locationsByLng.subList(minLngIndex, maxLngIndex));
		
		System.out.println("in get loc:  ");
		
		System.out.println(latLocations + "\n" + lngLocations);
		
		latLocations.retainAll(lngLocations);
		
		return new ArrayList<Location>(latLocations);
	}

	protected ArrayList<Movie> getMoviesByTitle() {
		return moviesByTitle;
	}
	
	protected ArrayList<Location> getLocationList() {
		return locationsByLat;
	}

}
