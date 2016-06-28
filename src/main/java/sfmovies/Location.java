package sfmovies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Location {
	private String name;
	private double lat;
	private double lng;
	private ArrayList<Movie> movieList;
	
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public String getName() {
		return name;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	protected static Map<String,Location> locationMap = new HashMap<String, Location>();
	
	public static Location getLocation(String name) {
		if (!locationMap.containsKey(name))
			locationMap.put(name, new Location(name));

		return locationMap.get(name);
		
	}
	
	public void addMovie(Movie movie) {
		movieList.add(movie);
	}
	
	public Location(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	Location(String name) {

		this.name = name;

		try {
			String placeString = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=" + name + "&location=37.76999,-122.44696&radius=500&key=AIzaSyAkD_QXmA9edU-7dfZl0gyi42zCHwV7Yh4";

			Request placeRequest = new Request.Builder().url(placeString).build();
			Response placeResponse = SFMovieController.client.newCall(placeRequest).execute();
			
			String placeId = new JSONObject(placeResponse.body().string()).getJSONArray("predictions").getJSONObject(0).getString("place_id");

			String coordString = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + placeId + "&key=AIzaSyAkD_QXmA9edU-7dfZl0gyi42zCHwV7Yh4";

			Request coordRequest = new Request.Builder().url(coordString).build();
			Response coordResponse = SFMovieController.client.newCall(coordRequest).execute();
			String coordRawJSON = coordResponse.body().string();
			
			this.lat = new JSONObject(coordRawJSON).getJSONObject("result").getJSONObject("geometry").getJSONObject("location").getDouble("lat");
			this.lng = new JSONObject(coordRawJSON).getJSONObject("result").getJSONObject("geometry").getJSONObject("location").getDouble("lng");
		}
		catch (Exception e) {
			this.lat = 0;
			this.lng = 0;
		}	
	}
	
	public String toString() {
		return name + " (" + lat + ", " + lng + ")";
	}

	public static void clearMap() {
		locationMap.clear();
	}
	
}
