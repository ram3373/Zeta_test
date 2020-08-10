package org.zomato.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.zomato.api.helper.APIHelper;
import org.zomato.base.BaseHelper;

public class CitiesAPICases extends BaseHelper {
    APIHelper apiHelper;
    private BaseHelper baseHelper;
    private String service_url;
    private String url;
    private String URI;
    public String authentication_token;

    @BeforeClass()
    public void set_Up() {
        authentication_token = prop.getProperty("Token");
        baseHelper = new BaseHelper();
        url = prop.getProperty("Url");
        service_url = prop.getProperty("CitiesService_URL");
        URI = url + service_url;
        apiHelper = new APIHelper();
    }

    @Test
    public void cities_Case_One() {
        apiHelper.GET(URI, new String[]{"user-key", "Accept"}, new String[]{authentication_token, "application/json"});
        int actualStatusCode = apiHelper.responseStatusCode();
        Assert.assertEquals(actualStatusCode, RESPONSE_STATUS_CODE_200);
        String responseString = apiHelper.jsonResponse("location_suggestions");
        Assert.assertEquals(responseString.contains("[]"), true);
        System.out.println("Success");
    }


    @Test(dataProvider = "citiesparam", dataProviderClass = TestFactory.class)
    public void cities_Case_Two(String city_Name) {
        apiHelper.GET(URI + "?" + "q=" + city_Name, new String[]{"user-key", "Accept"}, new String[]{authentication_token, "application/json"});
        int actualStatusCode = apiHelper.responseStatusCode();
        Assert.assertEquals(actualStatusCode, RESPONSE_STATUS_CODE_200);
        String responseString = apiHelper.jsonResponse("location_suggestions[0]/name");
        Assert.assertEquals(responseString, city_Name);
    }

    @Test(dataProvider = "citiesname_citiesID_and_CountryName", dataProviderClass = TestFactory.class)
    public void cities_Case_Three(String city_Name, String city_ID, String country_Name, int number_Of_Results, int arrayId) {
        apiHelper.GET(URI + "?" + "q=" + city_Name + "&city_ids=" + city_ID +"&count=" + number_Of_Results, new String[]{"user-key", "Accept"}, new String[]{authentication_token, "application/json"});
        int actualStatusCode = apiHelper.responseStatusCode();
        Assert.assertEquals(actualStatusCode, RESPONSE_STATUS_CODE_200);
        String one = apiHelper.jsonResponse("location_suggestions["+arrayId+"]/country_name");
        System.out.println(one);
        Assert.assertEquals(one, country_Name);
    }

}
