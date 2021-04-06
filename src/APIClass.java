import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClass {

    private String url = "";

    public APIClass(String url){
        if(url != ""){
            this.url = url;
        }
    }

   public String getURL(){
       return this.url;
   }

    /****
     *Returns the specific data to an endpoint of the api.
     *
     *
     * @param url
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String getDataFromAPIEndpoint(String url){

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;

        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.body();

    }

}
