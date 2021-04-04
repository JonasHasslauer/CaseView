import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataHandling {

    private String url = "https://api.corona-zahlen.org/";

    public void setURL(String url){
      this.url += url;
    }

    public String getURL(){
        return this.url;
    }

    public String getDataFromEndpoint(String url) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

    public boolean checkForValidState(String input){

        boolean state = false;

        if(input.length() == 2){
            for(Enum x : States.values()){
                if(input.equalsIgnoreCase(String.valueOf(x))){
                    return true;
                }
            }
        }

        return false;


    }

}
