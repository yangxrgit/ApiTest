package api;

import api.TestAPI;
import client.RestfulClient;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ExcelProcess;
import utils.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestPost extends TestAPI {
    RestfulClient client;
    JSONObject responseBody;
    JSONParser jParser;
    int responseCode;
    String code;
    String url;//= "http://uat-api.rr.tv/v3plus/season/detail";
    String postBody;
    Object[][] excelData;
    HashMap<String, String> hashHead;

    //测试类继承TestAPI类，setup方法读取excel表单，设置好请求头部，testPostRequest发送请求并分析结果。
    @BeforeClass
    public void setup() throws ClientProtocolException, IOException {
        //读取用例excel
        excelData = ExcelProcess.proessExcel(excelPath, 1);

        //实例化client
        client = new RestfulClient();

        //设置好请求头部
        hashHead = new HashMap<String, String>();
        hashHead.put("Content-Type", "application/x-www-form-urlencoded");
        hashHead.put("token","rrtv-f021ec952edaf4c615570a6957fa788105360aed");
        hashHead.put("clientType","ios_rrsp_jzsp");
        hashHead.put("clientVersion","4.1.5");
    }

    @Test
    public void testPostRequest() throws ClientProtocolException, IOException {
        //从第二行开始遍历表单，跳过表头
        for(int i=1;i<excelData.length;i++){
            //从特定位置读取测试数据
            String address = excelData[i][3].toString();
            url = host+address;
            String checkPoint = excelData[i][4].toString();
            String checkValue = excelData[i][5].toString();
            //用NameValuePair存储所有请求参数
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for(int j=7;j<excelData[i].length-2;j=j+2){
                //因为每种请求的参数个数不确定，在这里进行非空判断
                if(excelData[i][j]==null){
                    break;
                }
                NameValuePair pair = new BasicNameValuePair(excelData[i][j].toString(),excelData[i][j+1].toString());
                params.add(pair);
            }

//            params.add(new BasicNameValuePair("token", "rrtv-9eb0d4ea4ec5fb24609533522a05d46f86460c71"));
//            params.add(new BasicNameValuePair("seasonId","12517"));


            //发出请求
            client.sendPost(url,params, hashHead);

            responseBody = client.getBodyInJSON();
            responseCode = client.getCodeInNumber();


            JSONParser jParser = new JSONParser();
            boolean result = jParser.isResponseCorrect(responseBody, checkPoint, checkValue);


            //断言判断结果
            Assert.assertTrue(result);
            Assert.assertEquals(responseCode, 200);

        }
    }


//    @Test
//    public void testPostRequest() {
//        Assert.assertEquals(code, "0000");
//        Assert.assertEquals(responseCode,200);
//    }
//
//    @BeforeClass
//    public void beforeClass() throws ClientProtocolException, IOException {
//        client = new RestfulClient();
//
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("seasonId", "12517"));
//        params.add(new BasicNameValuePair("token", "rrtv-9eb0d4ea4ec5fb24609533522a05d46f86460c71"));
//
//        HashMap<String, String> hashHead = new HashMap<String, String>();
//        hashHead.put("Content-Type", "application/x-www-form-urlencoded");
//        hashHead.put("token","rrtv-9eb0d4ea4ec5fb24609533522a05d46f86460c71");
//        hashHead.put("clientType","android_RRMJ_REPLACE");
//        hashHead.put("clientVersion","4.1.6");
//
//        client.sendPost(url,params,hashHead);
//
//        responseBody = client.getBodyInJSON();
//        responseCode = client.getCodeInNumber();
//
//        System.out.println(responseBody);
//        jParser = new JSONParser();
//        code = jParser.getCode(responseBody);
//    }

}
