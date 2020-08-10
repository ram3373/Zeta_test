package org.zomato.api.helper;

import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zomato.base.BaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APIHelper extends BaseHelper {

    private CloseableHttpClient httpClient;
    private CloseableHttpResponse closeableHttpResponse;
    private HttpGet httpGet;
    private String response_string;
    private JSONObject response_json;
    private Header[] allHeadersarray;
    private String valuebyJpath = null;


    //GET Method
    public void GET(String URI, String[] HeaderKey, String[] HeaderValue) {
        try {
            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(URI);
            for (int i = 0; i <= HeaderKey.length-1; i++)
                httpGet.setHeader(HeaderKey[i], HeaderValue[i]);

            closeableHttpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Getting Response status code
    public int responseStatusCode() {
        //Getting status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code : " + statusCode);
        return statusCode;
    }

    //JSON String
    public String jsonResponse(String jsonArrayname) {
        try {
            response_string = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            response_json = new JSONObject(response_string);
            valuebyJpath = getValuebyJpath(response_json, jsonArrayname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valuebyJpath;
    }

    public static String getValuebyJpath(JSONObject response_json, String jsonArrayname) {
        Object obj = response_json;
        for (String s : jsonArrayname.split("/"))
            if (!s.isEmpty()) {
                if (!(s.contains("[") || s.contains("]")))
                    obj = ((JSONObject) obj).get(s);
                else if (s.contains("[") || s.contains("]"))
                    obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replaceAll("]", "")));
            }
        return obj.toString();
    }

    //All headers
    public HashMap responseHeaders() {
        allHeadersarray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();

        for (Header header : allHeadersarray) {
            allHeaders.put(header.getName(), header.getValue());
        }
        return allHeaders;
    }
}
