import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        DataHandling dataHandling = new DataHandling();
        Scanner sc = new Scanner(System.in);

        System.out.println("Für welches Bundesland hättest du gerne die Zahlen?");

        boolean validState = false;
        String wantedState = "";

        do{
            wantedState = sc.nextLine();
            if(dataHandling.checkForValidState(wantedState)){
                validState = true;
                System.out.println("Deine Eingabe wurde gewählt");
                dataHandling.setURL("states/" + wantedState);
            }else {
                System.out.println("Bitte geb nocheinmal dein Bundesland ein");
            }
        }while(!validState);

        sc.close();

        JSONObject state = null;

       try{

           String api_response = dataHandling.getDataFromEndpoint(dataHandling.getURL());
           JSONObject endpoint_object = new JSONObject(api_response);
           state = endpoint_object.getJSONObject("data");

       }catch (IOException e){e.printStackTrace();}
        catch (NumberFormatException e){e.printStackTrace();}


        if(state != null){
            System.out.println(state.toString());
        }

        //Ausgabe




    }


}

