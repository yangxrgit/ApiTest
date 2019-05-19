package com.httpclient;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;

public class userPrivilegeList {

    public static void main(String[] args) throws IOException {

        String url ="http://uat-api.rr.tv/user/privilege/list?userId=4444767";//去进行get请求的地址


        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);//httpResponse用来存储我们接收到的反馈；


        int responseCode = httpResponse.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        Header[] responseHeader = httpResponse.getAllHeaders();
        HashMap<String,String> hashMap = new HashMap<String, String>();
        for (Header header:responseHeader){
            hashMap.put(header.getName(),header.getValue());
        }


        System.out.println(responseCode);
        System.out.println(responseBody);
        System.out.println(hashMap);
    }

}
