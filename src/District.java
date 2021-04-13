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
        setDict();
    }

    public String[] getAllDistricts(String state){

        State stateObj = new State("https://api.corona-zahlen.org/states");

        String data = getDataFromAPIEndpoint(stateObj.getURL());


        return null;
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

    public String getAgsToDistrict(String wantedDistrict){

        try{
            return "0" + this.dict.get(wantedDistrict);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }

    /***
     * This method fills a dictionary, which contains the 'ags' to each Landkreis.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    private void setDict(){

        //TODO werden die integer werte ohne führende 0 in das Dictionary übernommen?

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
