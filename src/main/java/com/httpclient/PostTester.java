package com.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostTester {
    //接口POST:http://api.rr.tv/user/profile
    //参数：
    //token=rrtv-930bd846ecc25c9c3cf716d7e9637f70c4fd94be
    //otherUser=4444767

    public static void main(String[] args) throws IOException {
        //1.准备请求url
        String url = "http://api.rr.tv/user/profile";

        //2.准备post请求
        HttpPost post = new HttpPost(url);

        //3-0:准备请求头
        post.setHeader("Content-Type","application/x-www-form-urlencoded");
        post.setHeader("token","rrtv-930bd846ecc25c9c3cf716d7e9637f70c4fd94be");
        post.setHeader("clientType","ios_rrsp_jzsp");
        post.setHeader("clientVersion","4.1.5");

        //3.准备参数：post请求的参数，大部分会放到请求体中间
        List<NameValuePair> parameters  = new ArrayList();
        parameters.add(new BasicNameValuePair("token","rrtv-930bd846ecc25c9c3cf716d7e9637f70c4fd94be"));
        parameters.add(new BasicNameValuePair("otherUser","4444767"));
        HttpEntity requestEntity = new UrlEncodedFormEntity(parameters);
        post.setEntity(requestEntity);

        //4.创建一个客户端用来发包
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //5.通过客户端进行发包，得到响应
        CloseableHttpResponse response = httpClient.execute(post);

        //6.获得响应中的信息
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity);
        System.out.println(entityString);
    }
}
