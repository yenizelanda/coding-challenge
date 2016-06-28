package sfmovies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SFMovieControllerTest {
	
	public static SFMovieController controller;
	
	@BeforeClass
	public static void initializeController() {
		try {
			controller = new SFMovieController(100);
		} catch (IOException e) {
			fail("IOException occured");
		}
	}

	@Test
	public void testMoviesByTitleSize() {
		assertEquals(26, controller.getMoviesByTitle().size()); //26 for 100
	}
	
	@Test
	public void testGetMovieByTitle() {
		assertEquals("180", controller.getMovieByTitle("180").getTitle());
	}
	
	@Test
	public void testGetMoviesByAutoCompleteTitle() {
		ArrayList<Movie> result = new ArrayList<Movie>();
		assertEquals(result,controller.getMoviesByAutoCompleteTitle("NonExistentTitleName"));

		result.add(controller.getMovieByTitle("Another 48 Hours"));
		result.add(controller.getMovieByTitle("Ant-Man"));
		assertEquals(result, controller.getMoviesByAutoCompleteTitle("An"));

	}
	
	@Test
	public void testGetLocationsByLatLon() {
		double minLat = 37.78;
		double maxLat = 37.8;
		double minLng = -122.5;
		double maxLng = -122.4;
		
		ArrayList<Location> expected = new ArrayList<Location>();
		
		for (Location l : controller.getLocationList()) {
			if (minLat < l.getLat() && l.getLat() < maxLat && minLng < l.getLng() && l.getLng() < maxLng)
				expected.add(l);
		}
		ArrayList<Location> result = controller.getLocationsByLatLng(minLat, maxLat, minLng, maxLng);
		
		Collections.sort(expected, new LatComparator());
		Collections.sort(result, new LatComparator());
		
		assertEquals(expected, result);
	}
	

}
