package sfmovies;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.*;

public class Test {

	public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://data.sfgov.org/resource/wwmu-gmzc.json?title=Ant-Man").build();
		try {
			Response response = client.newCall(request).execute();
			
			
			JSONArray arr = new JSONArray(response.body().string());
			System.out.println(arr.get(0));
			
			SFMovieController sf = new SFMovieController();
			
			System.out.println();

			System.out.println(sf.getMoviesByAutoCompleteTitle("An").toString());
			System.out.println(sf.getMoviesByAutoCompleteTitle("1").toString());
			System.out.println(sf.getMoviesByAutoCompleteTitle("0").toString());
			System.out.println(sf.getLocationsByLatLng(37.78, 37.8, -122.5, -122.4));
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
