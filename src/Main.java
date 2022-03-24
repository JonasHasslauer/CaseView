import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DistrictClass district = new DistrictClass(EProperties.DISTRICT.url);
        StateClass state = new StateClass(EProperties.STATES.url);
        Scanner sc = new Scanner(System.in);

        // Hier geht es um das Bundesland

        System.out.println("Für welches Bundesland hättest du gerne die Zahlen?");

        askForState(state, sc);

        /*

        JSONObject state_json_obj = new JSONObject(state.getTextFromApiEndpoint()).getJSONObject("data");


        System.out.println(String.format("Die Inzidenz für das Bundesland '%s' liegt bei %,.0f",
               state.getWantedState(),
               state_json_obj.getJSONObject(
                       state.getWantedState()).getDouble("weekIncidence")));



         */

       // Ab hier geht es um den Landkreis

        System.out.println("Für welchen Landkreis benötigst du eine Übersicht?");

        askForDistrict(district, sc);

        JSONObject district_json_obj = new JSONObject(district.getTextFromApiEndpoint()).getJSONObject("data");

        System.out.println(String.format("Die Inzidenz für deinen gewählten Landkreis '%s' liegt bei %,.2f",
                district.getWantedDistrict(),
                district_json_obj.getJSONObject(
                        district.getcommonDistrictKey()).getDouble("weekIncidence")));

        HistoryClass history = new HistoryClass(EProperties.INCIDENCE.url);

        System.out.println(String.format("Hier die Inzidenz für %d Tage", 3));

        String incidence = history.getTextFromApiEndpoint();

        JSONArray historyIncidenceForSpecificDistrict = new JSONObject(incidence).getJSONObject("data")
                .getJSONObject(district.getcommonDistrictKey())
                .getJSONArray("history");

        getIncidenceHistoryForNthDays(historyIncidenceForSpecificDistrict, 3);

        sc.close();
    }

    private static void getIncidenceHistoryForNthDays(JSONArray jsonArray, int days) {
        for(int i = jsonArray.length() - 1; i >= jsonArray.length() - days; i--){
            System.out.println(String.format("%s -> Inzidenz von %,.2f",
                    jsonArray.getJSONObject(i).get("date").toString().substring(0,10), //gives the date
                    jsonArray.getJSONObject(i).getDouble("weekIncidence")));
        }
    }

    private static void askForDistrict(DistrictClass district, Scanner sc) {
        do{
            district.setWantedDistrict(sc.nextLine());
            if(!district.isValidDistrict(district.getWantedDistrict())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Landkreis ein");
            }
        }while(!district.isValidDistrict(district.getWantedDistrict()));
        district.setcommonDistrictKey(district.getWantedDistrict());
    }

    private static void askForState(StateClass state, Scanner sc) {
        do{
            state.setWantedState(sc.nextLine());
            if(!state.isValidState(state.getWantedState())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Bundesland ein");
            }
        }while(!state.isValidState(state.getWantedState()));
    }


}

