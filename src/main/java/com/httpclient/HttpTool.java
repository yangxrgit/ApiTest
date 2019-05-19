package com.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HttpTool {
    public static String post(String url, HashMap<String,String> paramsMap) throws IOException {
        HttpPost post = new HttpPost(url);
        //参数
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        Set<String> keySet = paramsMap.keySet();
        for (String key:keySet){
            //每个键值对，包装成一个名值对
            parameters.add(new BasicNameValuePair(key,paramsMap.get(key)));
        }
        //把参数设置到请求体
        post.setEntity(new UrlEncodedFormEntity(parameters));

        //设置请求头
        post.setHeader("Content-Type","application/json;charset=utf8");
        post.setHeader("token","rrtv-930bd846ecc25c9c3cf716d7e9637f70c4fd94be");
        post.setHeader("clientType","ios_rrsp_jzsp");
        post.setHeader("clientVersion","4.1.5");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        return entityString;
    }
}
