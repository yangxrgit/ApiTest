package api;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ExcelProcess;
import utils.JSONParser;
import client.RestfulClient;

import java.io.IOException;
import java.util.HashMap;

public class TestGet extends TestAPI{
    RestfulClient client;
    JSONObject responseBody;
    JSONParser jParser;
    int responseCode;
    String code;
    String url;  //"http://uat-api.rr.tv/user/privilege/list?userId=4444767";
    String postBody;
    Object[][] excelData;
    HashMap<String, String> hashHead;








//    @Test
//    public void TestGetRequest() {
//        Assert.assertEquals(code, "0000");
//        Assert.assertEquals(responseCode, 200);
//    }
//
//    @BeforeClass
//    public void beforeClass() throws ParseException, IOException {
//
//        client = new RestfulClient();
//        client.getResponse(url);
//        responseBody = client.getBodyInJSON();
//        responseCode = client.getCodeInNumber();
//
//        jParser = new JSONParser();
//        code = jParser.getCode(responseBody);
//    }


}
