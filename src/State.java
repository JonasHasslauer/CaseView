public class State extends ConnHandler {

    public State(String url) {
        super(url);
    }

    final private static String[] statesAbbreviation = {
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

    private String wantedState;

    public String getWantedState() {
        return this.wantedState;
    }

    public void setWantedState(String wantedState) {
        this.wantedState = wantedState;
    }

    public boolean isValidState(String state){

        if(state.length() == 2){
            for(String x : statesAbbreviation){
                if(state.equalsIgnoreCase(x)){
                    return true;
                }
            }
        }
        return false;
    }


}
