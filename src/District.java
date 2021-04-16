import org.json.JSONObject;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class District extends APIClass{

    Dictionary<String, Integer> dict = new Hashtable<String, Integer>();
    private String wantedDistrict;
    private String ags;

    public String getWantedDistrict() {
        return this.wantedDistrict;
    }

    public void setWantedDistrict(String wantedDistrict) {
        if(wantedDistrict != null){
            this.wantedDistrict = wantedDistrict;
        }
    }

    public District(String url) {
        super(url);
    }

    public boolean isValidDistrict(String wantedDistrict){

        try{
            if(this.dict.get(wantedDistrict) != 0){
                return true;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return false;

    }

    public void setAgsToDistrict(String wantedDistrict) {
        this.ags = "0" + (String.valueOf(this.dict.get(wantedDistrict)));
    }

    public String getAgs(){
        return this.ags;
    }

    /***
     * This method fills a dictionary, which contains the 'ags' to each Landkreis.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void createDict(){

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
