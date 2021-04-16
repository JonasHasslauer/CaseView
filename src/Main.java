import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    Date date;

    public static void main(String[] args) {

        District district = new District("https://api.corona-zahlen.org/districts");
        State state = new State("https://api.corona-zahlen.org/states");
        Scanner sc = new Scanner(System.in);
        district.createDict();

        // Hier geht es um das Bundesland

        System.out.println("Für welches Bundesland hättest du gerne die Zahlen?");

        do{
            state.setWantedState(sc.nextLine());
            if(!state.isValidState(state.getWantedState())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Bundesland ein");
            }
        }while(!state.isValidState(state.getWantedState()));

       JSONObject stateJSONObj = new JSONObject(state.getDataFromAPIEndpoint(state.getURL())).getJSONObject("data");

       System.out.println(String.format("Die Inzidenz für das Bundesland '%s' liegt bei %,.0f",
               state.getWantedState(),
               stateJSONObj.getJSONObject(
                       state.getWantedState()).getDouble("weekIncidence")));


       // Ab hier geht es um den Landkreis

       System.out.println("Für welchen Landkreis benötigst du eine Übersicht?");

        do{
            district.setWantedDistrict(sc.nextLine());
            if(!district.isValidDistrict(district.getWantedDistrict())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Landkreis ein");
            }else {
                district.setAgsToDistrict(district.getWantedDistrict());
                System.out.println("Der Ags ist " + district.getAgs());
            }
        }while(!district.isValidDistrict(district.getWantedDistrict()));

        JSONObject districtJSONObj = new JSONObject(district.getDataFromAPIEndpoint(district.getURL())).getJSONObject("data");

        System.out.println(String.format("Die Inzidenz für deinen gewählten Landkreis '%s' liegt bei %,.2f",
                district.getWantedDistrict(),
                districtJSONObj.getJSONObject(
                        district.getAgs()).getDouble("weekIncidence")));

        District historyincidence = new District("https://api.corona-zahlen.org/districts/history/incidence");

        System.out.println("Die Inzidenz der letzten Tage liegt bei: ");

        String histIncidence = historyincidence.getDataFromAPIEndpoint(historyincidence.getURL());

        JSONArray histIncidenceArray = new JSONObject(histIncidence).getJSONObject("data").
                getJSONObject(district.getAgs()).getJSONArray("history");

        for(int i = histIncidenceArray.length() - 1; i >= 0; i--){
            System.out.println(String.format("Die Inzidenz für den %s liegt bei %,.2f => %s",
                   histIncidenceArray.getJSONObject(i).get("date").toString().substring(0 ,10),
                    histIncidenceArray.getJSONObject(i).getDouble("weekIncidence")));
        }

        sc.close();



    }


}

