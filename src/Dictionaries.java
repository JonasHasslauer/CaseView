import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class Dictionaries {

    public static HashMap<String, Integer> districtKeyDictionary = new HashMap<String, Integer>();

    public static void setDistrictKeyDictionary(JSONObject data){
        try {
            Iterator<String> key = data.keys();
            while(key.hasNext()){
                String singleKey = key.next();
                districtKeyDictionary.put(data.getJSONObject(singleKey).getString("county").trim(),
                        Integer.valueOf(singleKey));
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

}
