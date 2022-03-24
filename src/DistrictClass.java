import org.json.JSONObject;

public class DistrictClass extends ConnHandlerClass {

    private String wantedDistrict;
    private String commonDistrictKey;

    public DistrictClass(String endpointUrl) {
        super(endpointUrl);
        if(DictionariesClass.districtKeyDictionary.size() == 0){
            DictionariesClass.setDistrictKeyDictionary(new JSONObject(this.getTextFromApiEndpoint()).getJSONObject("data"));
        }
    }

    public void setWantedDistrict(String wantedDistrict) {
        this.wantedDistrict = CommonUtilitiesClass.setRightSpelling(wantedDistrict);
    }

    public String getWantedDistrict() {
        return this.wantedDistrict;
    }

    public boolean isValidDistrict(String wantedDistrict){
        try{
            if(DictionariesClass.districtKeyDictionary.get(wantedDistrict) instanceof Integer){
                return true;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return false;
    }

    public void setcommonDistrictKey(String wantedDistrict) {
        String var = String.valueOf(DictionariesClass.districtKeyDictionary.get(wantedDistrict));
        if(var.length() <= 4){
            this.commonDistrictKey = String.format("%05d", Integer.valueOf(var));
        }else {
            this.commonDistrictKey = var;
        }
    }

    public String getcommonDistrictKey(){
        return this.commonDistrictKey;
    }







}
