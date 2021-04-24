import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnHandlerClass {

    private final String endpointUrl;

    public ConnHandlerClass(String endpointUrl){
        this.endpointUrl = endpointUrl;
    }

    public String getURL(){
       return this.endpointUrl;
   }

    public String getTextFromApiEndpoint(){

        if(this.endpointUrl == null) return null;
        HttpResponse<String> response_text = null;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(this.endpointUrl))
                .build();

        do {
            try{
                response_text = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (InterruptedException | IOException e ) {
                e.printStackTrace();
            }
        }while(!(response_text != null));

        return response_text.body();
    }
}
