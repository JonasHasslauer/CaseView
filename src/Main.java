import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.Scanner;

public class Main {

    Date date;

    public static void main(String[] args) {

        District district = new District("https://api.corona-zahlen.org/districts");
        State state = new State("https://api.corona-zahlen.org/states");
        Scanner sc = new Scanner(System.in);

        district.createDistrictKeyDictionary();

        // Hier geht es um das Bundesland

        System.out.println("Für welches Bundesland hättest du gerne die Zahlen?");

        askForState(state, sc);

        JSONObject stateJSONObj = new JSONObject(state.getTextFromApiEndpoint(state.getURL())).getJSONObject("data");

        System.out.println(String.format("Die Inzidenz für das Bundesland '%s' liegt bei %,.0f",
               state.getWantedState(),
               stateJSONObj.getJSONObject(
                       state.getWantedState()).getDouble("weekIncidence")));


       // Ab hier geht es um den Landkreis

       System.out.println("Für welchen Landkreis benötigst du eine Übersicht?");

        askForDistrict(district, sc);

        JSONObject districtJSONObj = new JSONObject(district.getTextFromApiEndpoint(district.getURL())).getJSONObject("data");

        System.out.println(String.format("Die Inzidenz für deinen gewählten Landkreis '%s' liegt bei %,.2f",
                district.getWantedDistrict(),
                districtJSONObj.getJSONObject(
                        district.getcommonDistrictKey()).getDouble("weekIncidence")));

        District historyincidence = new District("https://api.corona-zahlen.org/districts/history/incidence");

        System.out.println("Die Inzidenz der letzten Tage liegt bei: ");

        String histIncidence = historyincidence.getTextFromApiEndpoint(historyincidence.getURL());

        JSONArray histIncidenceArray = new JSONObject(histIncidence).getJSONObject("data").
                getJSONObject(district.getcommonDistrictKey()).getJSONArray("history");

        //TODO Tabelle kreieren

        for(int i = histIncidenceArray.length() - 1; i >= 0; i--){
            System.out.println(String.format("Die Inzidenz für den %s liegt bei %,.2f",
                   histIncidenceArray.getJSONObject(i).get("date").toString().substring(0 ,10),
                    histIncidenceArray.getJSONObject(i).getDouble("weekIncidence")));
        }

        sc.close();



    }

    private static void askForDistrict(District district, Scanner sc) {
        do{
            district.setWantedDistrict(sc.nextLine());
            if(!district.isValidDistrict(district.getWantedDistrict())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Landkreis ein");
            }else {
                district.setcommonDistrictKey(district.getWantedDistrict());
                System.out.println("Der Ags ist " + district.getcommonDistrictKey());
            }
        }while(!district.isValidDistrict(district.getWantedDistrict()));
    }

    private static void askForState(State state, Scanner sc) {
        do{
            state.setWantedState(sc.nextLine());
            if(!state.isValidState(state.getWantedState())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Bundesland ein");
            }
        }while(!state.isValidState(state.getWantedState()));
    }


}

