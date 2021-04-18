import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class District extends ConnHandler {

    private String wantedDistrict;
    private String commonDistrictKey;
    private String[] allDistrictsToState;
    private Dictionaries dict = new Dictionaries();

    public District(String endpointUrl) {
        super(endpointUrl);
        if(Dictionaries.districtKeyDictionary.size() == 0){
            Dictionaries.setDistrictKeyDictionary(new JSONObject(this.getTextFromApiEndpoint()).getJSONObject("data"));
        }
    }

    public void setWantedDistrict(String wantedDistrict) {
        this.wantedDistrict = (!wantedDistrict.substring(0,1).equals("LK")) ? "LK " + wantedDistrict : wantedDistrict;
    }

    public String getWantedDistrict() {
        return this.wantedDistrict;
    }

    public boolean isValidDistrict(String wantedDistrict){
        try{
            if(Dictionaries.districtKeyDictionary.get(wantedDistrict) instanceof Integer){
                return true;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return false;
    }

    public void setcommonDistrictKey(String wantedDistrict) {
        String var = String.valueOf(Dictionaries.districtKeyDictionary.get(wantedDistrict));
        if(var.length() <= 4){
            this.commonDistrictKey = String.format("%05d", Integer.valueOf(var));
        }else {
            this.commonDistrictKey = var;
        }
    }

    public String getcommonDistrictKey(){
        return this.commonDistrictKey;
    }

    public String[] getAllDistrictsToState(){
        return this.allDistrictsToState;
    }







}
