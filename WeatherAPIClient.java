import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherAPIClient {

    public static void main(String[] args) {

        try {
            String apiURL = "https://api.open-meteo.com/v1/forecast?latitude=19.07&longitude=72.87&current_weather=true";

            URL url = new URL(apiURL);
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String line;
            String response = "";

            // Read full JSON response
            while ((line = reader.readLine()) != null) {
                response += line;
            }

            reader.close();

            // Extract values manually
            String temperature = response.split("\"temperature\":")[1].split(",")[0];
            String windspeed = response.split("\"windspeed\":")[1].split(",")[0];
            String winddirection = response.split("\"winddirection\":")[1].split(",")[0];
            String time = response.split("\"time\":\"")[1].split("\"")[0];

            // Display clean output
            System.out.println("\n==============================");
            System.out.println("       WEATHER REPORT");
            System.out.println("==============================");
            System.out.println("Location       : Mumbai");
            System.out.println("Time           : " + time);
            System.out.println("Temperature    : " + temperature + " °C");
            System.out.println("Wind Speed     : " + windspeed + " km/h");
            System.out.println("Wind Direction : " + winddirection + "°");
            System.out.println("==============================");

        } catch (Exception e) {
            System.out.println("Error fetching weather data");
            e.printStackTrace();
        }
    }
}