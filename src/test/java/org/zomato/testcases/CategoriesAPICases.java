package org.zomato.testcases;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.zomato.api.helper.APIHelper;
import org.zomato.api.helper.TestSetUp;
import org.zomato.base.BaseHelper;

import java.util.List;


public class CategoriesAPICases extends BaseHelper {

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
        service_url = prop.getProperty("CateService_URL");
        URI = url + service_url;
        apiHelper = new APIHelper();
    }

    @Test
    public void categories_Case_One() {
        apiHelper.GET(URI, new String[]{"user-key", "Accept"},new String[]{authentication_token, "application/json" });
        int actualStatusCode = apiHelper.responseStatusCode();
        Assert.assertEquals(actualStatusCode, RESPONSE_STATUS_CODE_200, "Success");
    }

    @Test
    public void categories_Case_Two()
    {
        apiHelper.GET(URI,new String[]{"user-key", "Accept"},new String[]{authentication_token, "application/json" });
        int actualStatusCode = apiHelper.responseStatusCode();
        Assert.assertEquals(actualStatusCode, RESPONSE_STATUS_CODE_200,"Success");
        String responseString = apiHelper.jsonResponse("");
        Assert.assertEquals(responseString.contains("categories"), true);
        System.out.println("Response Success");

    }

}

