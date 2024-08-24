package samples;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;



public class JsonSample {
	 public static void main(String[] args) {
	        try {
	            // URL to fetch data from
	            URL url = new URL("https://jsonplaceholder.typicode.com/todos");

	            // Open a connection to the URL
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            // Set the request method to GET
	            connection.setRequestMethod("GET");

	            // Get the response code
	            int responseCode = connection.getResponseCode();

	            if (responseCode == HttpURLConnection.HTTP_OK) { // success
	                // Create an InputStreamReader to read the response
	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String inputLine;
	                StringBuilder response = new StringBuilder();

	                // Read the response line by line
	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                // Close the BufferedReader
	                in.close();

	                // Parse the JSON response
	                JSONArray jsonArray = new JSONArray(response.toString());

	                // Print each todo item
	                for (int i = 0; i < jsonArray.length(); i++) {
	                    JSONObject todo = jsonArray.getJSONObject(i);
	                    System.out.println("ID: " + todo.getInt("id"));
	                    System.out.println("User ID: " + todo.getInt("userId"));
	                    System.out.println("Title: " + todo.getString("title"));
	                    System.out.println("Completed: " + todo.getBoolean("completed"));
	                    System.out.println("-------------");
	                }

	            } else {
	                System.out.println("GET request failed. Response Code: " + responseCode);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
