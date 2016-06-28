package sfmovies;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MovieTest {
	
	
	public JSONObject rawMovieJSON = new JSONObject("{\"actor_1\":\"Michael Douglas\",\"fun_facts\":\"Driving shots\",\"director\":\"Peyton Reed\",\"release_year\":\"2015\",\"locations\":\"California between Kearney and Davis\",\"actor_2\":\"Paul Rudd\",\"writer\":\"Gabriel Ferrari \",\"title\":\"Ant-Man\",\"distributor\":\"Walt Disney Studios Motion Pictures\",\"production_company\":\"PYM Particles Productions, LLC\"}");
	
	public String title = "Ant-Man";
	public List<String> actors = Arrays.asList("Michael Douglas","Paul Rudd");

	private String productionCompany = "PYM Particles Productions, LLC";
	private String distributor = "Walt Disney Studios Motion Pictures";
	private String director = "Peyton Reed";
	private String writer = "Gabriel Ferrari ";
	private int releaseYear = 2015;

	@AfterClass
	@BeforeClass
	public static void cleanLocationMap() {
		Movie.clearMap();
		Location.clearMap();
	}
	
	@Test
	public void testConstructor() {
		Movie antManMovie = new Movie(rawMovieJSON);
		assertEquals(title, antManMovie.getTitle());
		assertEquals(actors, antManMovie.getActors());
		assertEquals(productionCompany, antManMovie.getProductionCompany());
		assertEquals(distributor, antManMovie.getDistributor());
		assertEquals(director, antManMovie.getDirector());
		assertEquals(writer, antManMovie.getWriter());
		assertEquals(releaseYear, antManMovie.getReleaseYear());
	}

	@Test
	public void testMovieMap() {
		JSONObject antManFirstLocation = new JSONObject("{\"locations\":\"California between Kearney and Davis\",\"title\":\"Ant-Man\"}");
		JSONObject antManSecondLocation = new JSONObject("{\"locations\":\"City Hall\",\"title\":\"Ant-Man\"}");
		JSONObject alcatrazFirstLocation = new JSONObject("{\"locations\":\"City Hall\",\"title\":\"Alcatraz\"}");
		assertEquals(0, Movie.movieMap.size());
		Movie.createMovie(antManFirstLocation);
		assertEquals(1, Movie.movieMap.size());
		Movie.createMovie(alcatrazFirstLocation);
		assertEquals(2, Movie.movieMap.size());
		Movie.createMovie(antManSecondLocation);
		assertEquals(2, Movie.movieMap.size());
		
	}
}
