import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        District district = new District("https://api.corona-zahlen.org/districts");
        State state = new State("https://api.corona-zahlen.org/states");
        Scanner sc = new Scanner(System.in);

        // Hier geht es um das Bundesland

        System.out.println("Für welches Bundesland hättest du gerne die Zahlen?");

        do{
            state.setWantedState(sc.nextLine());
            if(!state.isValidState(state.getWantedState())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Bundesland ein");
            }
        }while(!state.isValidState(state.getWantedState()));

       JSONObject stateObj = new JSONObject(state.getDataFromAPIEndpoint(state.getURL())).getJSONObject("data");

       System.out.println(String.format("Die Inzidenz für das Bundesland '%s' liegt bei %,.0f",
               state.getWantedState(), stateObj.getJSONObject(state.getWantedState()).getDouble("weekIncidence")));


       // Ab hier geht es um den Landkreis


       System.out.println("Für welchen Landkreis benötigst du eine Übersicht?");

        do{
            district.setWantedDistrict(sc.nextLine());
            if(!district.isValidDistrict(district.getWantedDistrict())){
                System.out.println("Ungültig - Bitte geb noch einmal dein Landkreis ein");
            }
        }while(!district.isValidDistrict(district.getWantedDistrict()));

        sc.close();

        System.out.println(String.format("Die Inzidenz für deinen gewählten Landkreis '%s' liegt bei %,.0f",
                district.getWantedDistrict(), stateObj.getJSONObject(district.getWantedDistrict()).getDouble("weekIncidence")));


    }


}

