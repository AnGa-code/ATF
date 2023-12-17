package properties;

public enum PropertyKey {
    BROWSER_TYPE ("browserType"),
    APP_URL ("app_url"),
    APP_URL_MYPROFILE("app_url_myprofile"),
    API_URL("api_url"),
    CHROMEDRIVER("chromeDriver"),
    EDGEDRIVER("edgeDriver"),
    DURATION_TIMEOUT("durationTimeout"),
    DURATION_POLLING("durationPoling"),
    SAVE_CHANGES_BUTTON("//*[@id='root']/div[2]/div[2]/div/form/div/div[2]/button");


    private final String key;

    PropertyKey(String key) {
        this.key = key;
    }

    public String getPropertyKey() {
        return key;
    }
}