import org.json.JSONObject;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

public class District extends APIClass{

    Dictionary<String, Integer> districtKeyDictionary = new Hashtable<String, Integer>();
    private String wantedDistrict;
    private String commonDistrictKey;
    private String[] allDistrictsToState;

    public String getWantedDistrict() {
        return this.wantedDistrict;
    }

    public void setWantedDistrict(String wantedDistrict) {
        this.wantedDistrict = (!wantedDistrict.substring(0,1).equals("LK")) ? "LK " + wantedDistrict : wantedDistrict;

    }

    public District(String url) {
        super(url);
    }

    public boolean isValidDistrict(String wantedDistrict){

        try{
            if(this.districtKeyDictionary.get(wantedDistrict) instanceof Integer){
                return true;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return false;

    }

    public void setcommonDistrictKey(String wantedDistrict) {

        String var = String.valueOf(this.districtKeyDictionary.get(wantedDistrict));
        if(var.length() == 5){
            this.wantedDistrict = var;
        }else this.commonDistrictKey = "0" + (String.valueOf(this.districtKeyDictionary.get(wantedDistrict)));
    }

    public String getcommonDistrictKey(){
        return this.commonDistrictKey;
    }


    /***
     * Creates the following dictionary: commonDistrictKey -> Name of the district and put it in the class variable
     * Performs only with the an object containing the url: "https://api.corona-zahlen.org/districts";
     * //TODO - nicht so starr machen
     *
     *
     *
     */
    public void createDistrictKeyDictionary(){

        try {
            JSONObject d = new JSONObject(getTextFromApiEndpoint(this.getURL())).getJSONObject("data");
            Iterator<String> key = d.keys();

            while(key.hasNext()){
                String singleKey = key.next();
                this.districtKeyDictionary.put(d.getJSONObject(singleKey).getString("county").trim(),
                        Integer.valueOf(singleKey));
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
    public String[] getAllDistrictsToState(){
        return this.allDistrictsToState;
    }

    public void setAllDistrictsToState(){
        //TODO neue Methode => Ausgabe aller Landkreise für das jeweilige Bundesland
    }


    public Dictionary getDistrictKeyDictionary(){
        return this.districtKeyDictionary;
    }




}
