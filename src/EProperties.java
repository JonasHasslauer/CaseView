public enum EProperties {

    INCIDENCE("https://api.corona-zahlen.org/districts/history/incidence"),
    DISTRICT("https://api.corona-zahlen.org/districts"),
    STATES("https://api.corona-zahlen.org/states");

    final String url;

    EProperties(String url) {
        this.url = url;
    }

}
