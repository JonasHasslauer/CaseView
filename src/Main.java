import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        District district = new District("https://api.corona-zahlen.org/districts");
        State state = new State("https://api.corona-zahlen.org/states");
        Scanner sc = new Scanner(System.in);

        System.out.println("Für welches Bundesland hättest du gerne die Zahlen?");

        String wantedState = "";

        do{
            wantedState = sc.nextLine();
            if(!state.isValidState(wantedState)){
                System.out.println("Ungültig - Bitte geb noch einmal dein Bundesland ein");
            }
        }while(!state.isValidState(wantedState));

       JSONObject stateObj = null;


       stateObj = new JSONObject(state.getDataFromAPIEndpoint(state.getURL())).getJSONObject("data");


       System.out.println(String.format("Die Inzidenz für das Bundesland '%s' liegt bei %,.0f",
               wantedState, stateObj.getJSONObject(wantedState).getDouble("weekIncidence")));


       System.out.println("Für welchen Landkreis benötigst du eine Übersicht?");

       String wantedDistrict = "";

        do{
            wantedDistrict = sc.nextLine();
            if(!district.isValidDistrict(wantedDistrict)){
                System.out.println("Ungültig - Bitte geb noch einmal dein Landkreis ein");
            }
        }while(!district.isValidDistrict(wantedDistrict));

        sc.close();

        System.out.println(String.format("Die Inzidenz für deinen gewählten Landkreis '%s' liegt bei %,.0f",
                wantedState, stateObj.getJSONObject(wantedDistrict).getDouble("weekIncidence")));


    }


}

