package utilities.driver;

public enum Browser {

    IE ("IE"),
    CHROME("Chrome"),
    EDGE("Edge");

    private String browserName;

    Browser(String browserName){
        this.browserName = browserName;
    }

    public String getBrowserName(){
        return browserName;
    }

}
