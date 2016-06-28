package sfmovies;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LocationTest {
	
	public String locationName = "522 Main St.";
	public double lat = 37.7869436;
	public double lng = -122.3888088;

	@AfterClass
	@BeforeClass
	public static void cleanLocationMap() {
		Location.clearMap();
	}
	
	@Test
	public void testConstructor() {
		Location location = new Location("522 Main St.");
		assertEquals(locationName, location.getName());
		assertEquals(lat, location.getLat(),1e-6);
		assertEquals(lng, location.getLng(),1e-6);
	}
	
	@Test
	public void testLocationMap() {
		assertEquals(0, Location.locationMap.size());
		Location.getLocation("522 Main St.");
		assertEquals(1, Location.locationMap.size());
		Location.getLocation("City Hall");
		assertEquals(2, Location.locationMap.size());
		Location.getLocation("522 Main St.");
		assertEquals(2, Location.locationMap.size());
		
	}
}
