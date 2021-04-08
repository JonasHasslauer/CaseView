import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

public class District extends APIClass{

    Dictionary<String, Integer> dict = new Hashtable<String, Integer>();
    private String wantedDistrict;

    public String getWantedDistrict() {
        return wantedDistrict;
    }

    public void setWantedDistrict(String wantedDistrict) {
        this.wantedDistrict = wantedDistrict;
    }

    public District(String url) {
        super(url);
        try{
            setDict();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidDistrict(String wantedCounty){

        //TODO



        return false;
    }

    /***
     * This method fills a dictionary, which contains the 'ags' to each Landkreis.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    private void setDict() throws IOException, InterruptedException {

        JSONObject districts = new JSONObject(getDataFromAPIEndpoint(this.getURL())).getJSONObject("data");
        Iterator<String> key = districts.keys();

        while(key.hasNext()){
            String singleKey = key.next();
            this.dict.put(districts.getJSONObject(singleKey).getString("county").trim(), Integer.valueOf(singleKey));
        }

    }

    public Dictionary getDict(){
        return this.dict;
    }




}
