package dataManagment;

public class TestData {

    private final String TEST_CASE_NAME = "TEST_CASE_NAME";
    private String testCaseName;

    private final String TEST_DESCRIPTION = "TEST_DESCRIPTION";
    private String testDescription;

    private final String BROWSER = "BROWSER";
    private String browser;

    private final String SEARCH_TEXT = "SEARCH_TEXT";
    private String searchText;

    private final String SEARCH_RESULT = "SEARCH_RESULT";
    private String searchResult;

    public String getTestCaseName() {
        return testCaseName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public String getBrowser() {
        return browser;
    }

    public String getSearchText() {
        return searchText;
    }

    public String getSearchResult() {
        return searchResult;
    }

    public void setTestDataByHeaders(String header, String value) {
        try {
            switch (header) {
                case TEST_CASE_NAME:
                    testCaseName = value;
                    break;
                case TEST_DESCRIPTION:
                    testDescription = value;
                    break;
                case BROWSER:
                    browser = value;
                    break;
                case SEARCH_TEXT:
                    searchText = value;
                    break;
                case SEARCH_RESULT:
                    searchResult = value;
                    break;
                default:
                    throw new Exception("Illegal header name: " + header);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
