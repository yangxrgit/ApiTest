package com.httpclient;

import com.sun.net.httpserver.Headers;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetTester {
    public static void main(String[] args) throws IOException {
        //get请求 http://api.rr.tv/v3plus/mission/getMaxMissionId

        //1.请求的地址
        String url ="http://api.rr.tv/v3plus/mission/getMaxMissionId";
        
        //2.请求方法-->Get请求-->创建一个get请求(HttpClient库提供的工具类)
        HttpGet get = new HttpGet(url);
        
        //3.创建一个发包的客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        //4.通过客户端进行发包——>响应
        CloseableHttpResponse response = httpClient.execute(get);//响应报文-->响应对象
        
        //5.关注响应中的什么内容？
        //1)状态行
        StatusLine statusLine = response.getStatusLine();
        System.out.println(statusLine.getProtocolVersion());
        System.out.println(statusLine.getReasonPhrase());
        System.out.println(statusLine.getStatusCode());
        //加断言——>
        //2)响应头
        Header[] Headers = response.getAllHeaders();
        for (Header header: Headers){
            System.out.println(header.getName()+":"+header.getValue());
        }
        //3)响应体
        HttpEntity entity = response.getEntity();
        //把响应体转换成字符串去
        String entityString = EntityUtils.toString(entity);
        System.out.println(entityString);

        //拿到信息去和测试用例期望结果去做对比-->判断测试用例是否执行通过：断言

    }
}
