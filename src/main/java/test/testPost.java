package test;

import client.RestfulClient;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testPost {
    RestfulClient client;
    JSONObject responseBody;
    JSONParser jParser;
    int responseCode;
    String code;
    String url = "http://uat-api.rr.tv/v3plus/season/detail";
    String postBody;

    @Test
    public void testPostRequest() {
        Assert.assertEquals(code, "0000");
        Assert.assertEquals(responseCode,200);
    }

    @BeforeClass
    public void beforeClass() throws ClientProtocolException, IOException {
        client = new RestfulClient();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("seasonId", "12517"));
        params.add(new BasicNameValuePair("token", "rrtv-9eb0d4ea4ec5fb24609533522a05d46f86460c71"));

        HashMap<String, String> hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");
        hashHead.put("token","rrtv-9eb0d4ea4ec5fb24609533522a05d46f86460c71");
        hashHead.put("clientType","android_RRMJ_REPLACE");
        hashHead.put("clientVersion","4.1.6");

        client.sendPost(url,params,hashHead);

        responseBody = client.getBodyInJSON();
        responseCode = client.getCodeInNumber();

        System.out.println(responseBody);
        jParser = new JSONParser();
        code = jParser.getCode(responseBody);
    }

}
