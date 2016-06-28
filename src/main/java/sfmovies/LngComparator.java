package sfmovies;

import java.util.Comparator;

public class LngComparator implements Comparator<Location>{

	public int compare(Location arg0, Location arg1) {
		return new Double(arg0.getLng()).compareTo(new Double(arg1.getLng()));
	}

}
