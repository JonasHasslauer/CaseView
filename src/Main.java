import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        District district = new District(Properties.DISTRICT.url());
        State state = new State(Properties.STATES.url());
        Scanner sc = new Scanner(System.in);
        Dictionaries dict = new Dictionaries();

        // Hier geht es um das Bundesland

        System.out.println("Für welches Bundesland hättest du gerne die Zahlen?");

        askForState(state, sc);

        JSONObject state_json_obj = new JSONObject(state.getTextFromApiEndpoint()).getJSONObject("data");

        /*
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

        District historyincidence = new District(Properties.INCIDENCE.url());

        System.out.println(String.format("Hier die Inzidenz für %d Tage", 3));

        String incidence = historyincidence.getTextFromApiEndpoint();

        JSONArray historyIncidenceForSpecificDistrict = new JSONObject(incidence).getJSONObject("data").
                getJSONObject(district.getcommonDistrictKey()).getJSONArray("history");

        getIncidenceHistoryForNthDays(historyIncidenceForSpecificDistrict, 3);

        sc.close();
    }

    private static void getIncidenceHistoryForNthDays(JSONArray histIncidenceArray, int N) {
        for(int i = histIncidenceArray.length() - 1; i >= histIncidenceArray.length() - N; i--){
            System.out.println(String.format("%s -> Inzidenz von %,.2f",
                    histIncidenceArray.getJSONObject(i).get("date").toString().substring(0,10), //gives the date
                    histIncidenceArray.getJSONObject(i).getDouble("weekIncidence")));
        }
    }

    private static void askForDistrict(District district, Scanner sc) {
        do{
            district.setWantedDistrict(sc.nextLine());
            if(!district.isValidDistrict(district.getWantedDistrict())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Landkreis ein");
            }
        }while(!district.isValidDistrict(district.getWantedDistrict()));
        district.setcommonDistrictKey(district.getWantedDistrict());
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

