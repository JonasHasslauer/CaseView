public class State extends APIClass{

    String[] statesAbb = {
            "SH",
            "HH",
            "NI",
            "HB",
            "NW",
            "HE",
            "RP",
            "BW",
            "BY",
            "SL",
            "BE",
            "BB",
            "MV",
            "SN",
            "ST",
            "TH"
    };

    public State(String url) {
        super(url);
    }

    public boolean isValidState(String input){

        boolean state = false;

        if(input.length() == 2){
            for(String x : this.statesAbb){
                if(input.equalsIgnoreCase(x)){
                    return true;
                }
            }
        }
        return false;
    }


}
