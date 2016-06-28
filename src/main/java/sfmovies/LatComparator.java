package sfmovies;

import java.util.Comparator;

public class LatComparator implements Comparator<Location>{

	public int compare(Location arg0, Location arg1) {
		return new Double(arg0.getLat()).compareTo(new Double(arg1.getLat()));
	}

}
