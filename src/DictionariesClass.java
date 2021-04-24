import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class DictionariesClass {

    static final HashMap<String, Integer> districtKeyDictionary = new HashMap<String, Integer>();

    public static void setDistrictKeyDictionary(JSONObject data){

        try {
            Iterator<String> key = data.keys();
            while(key.hasNext()){
                String singleKey = key.next();
                districtKeyDictionary.put(data.getJSONObject(singleKey).getString("county").trim().substring(3),
                        Integer.valueOf(singleKey));
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

}
