public class State extends APIClass{

    public State(String url) {
        super(url);
    }

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

    private String wantedState;

    public String getWantedState() {
        return this.wantedState;
    }

    public void setWantedState(String wantedState) {
        this.wantedState = wantedState;
    }

    public boolean isValidState(String state){

        if(state.length() == 2){
            for(String x : this.statesAbb){
                if(state.equalsIgnoreCase(x)){
                    return true;
                }
            }
        }
        return false;
    }


}
