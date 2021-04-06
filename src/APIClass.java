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

   public void setURL(String url){
       this.url = url;
   }

   public String getURL(){
       return this.url;
   }

    /****
     *
     *
     *
     * @param url
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String getDataFromAPIEndpoint(String url) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }

}
