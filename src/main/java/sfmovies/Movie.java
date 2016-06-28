package sfmovies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Movie implements Comparable<Movie>{
	
	public static Map<String, Movie> movieMap = new HashMap<String,Movie>();
	
	private ArrayList<String> actors;
	private ArrayList<Location> locations;
	private ArrayList<String> funFacts;
	private String title;
	private String productionCompany;
	private String distributor;
	private String director;
	private String writer;
	private int releaseYear;
	
	public static Movie createMovie(JSONObject rawJSON) {
		if (movieMap.containsKey(rawJSON.optString("title")))
			return movieMap.get(rawJSON.optString("title")).addLocationAndFunFact(rawJSON);
		else
			return movieMap.put(rawJSON.optString("title"), new Movie(rawJSON));
	}

	public Movie(String title) {
		this.title = title;
	}
	
	Movie(JSONObject rawJSON) {

		actors = new ArrayList<String>();
		locations = new ArrayList<Location>();
		funFacts = new ArrayList<String>();
		
		if (!rawJSON.optString("actor_1").equals(""))
			actors.add(rawJSON.optString("actor_1"));
		if (!rawJSON.optString("actor_2").equals(""))
			actors.add(rawJSON.optString("actor_2"));
		if (!rawJSON.optString("actor_3").equals(""))
			actors.add(rawJSON.optString("actor_3"));
		
		addLocationAndFunFact(rawJSON);
		
		title = rawJSON.optString("title");
		productionCompany = rawJSON.optString("production_company");
		distributor = rawJSON.optString("distributor");
		director = rawJSON.optString("director");
		writer = rawJSON.optString("writer");
		releaseYear = rawJSON.optInt("release_year");
	}
	
	public Movie addLocationAndFunFact(JSONObject rawJSON) {

		if (!rawJSON.optString("locations").equals(""))
			locations.add(Location.getLocation(rawJSON.optString("locations")));
		
		if (!rawJSON.optString("fun_facts").equals(""))
			funFacts.add(rawJSON.optString("fun_facts"));
		
		Location.getLocation(rawJSON.optString("locations"));
		
		return this;
	}

	public int compareTo(Movie o) {
		return this.title.compareTo(o.getTitle());
	}

	public ArrayList<String> getActors() {
		return actors;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public ArrayList<String> getFunFacts() {
		return funFacts;
	}

	public String getTitle() {
		return title;
	}

	public String getProductionCompany() {
		return productionCompany;
	}

	public String getDistributor() {
		return distributor;
	}

	public String getDirector() {
		return director;
	}

	public String getWriter() {
		return writer;
	}

	public int getReleaseYear() {
		return releaseYear;
	}
	
	public String toString() {
		return title;
	}

	public static void clearMap() {
		movieMap.clear();
	}
}
