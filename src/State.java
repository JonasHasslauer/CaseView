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

    private String wantedState;


    public String getWantedState() {
        return this.wantedState;
    }

    public void setWantedState(String wantedState) {
        if(wantedState != null){
            this.wantedState = wantedState;
        }
    }


    public State(String url) {
        super(url);
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
