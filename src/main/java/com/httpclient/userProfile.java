package com.httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class userProfile {

    //接口POST:http://api.rr.tv/user/profile
    //参数：
    //token=rrtv-930bd846ecc25c9c3cf716d7e9637f70c4fd94be
    //otherUser=4444767

    public static void main(String[] args) throws IOException {
        //获得Http客户端（可以理解为：你得先有一个浏览器；注意：实际上HttpClient与浏览器是不一样的）
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //参数
        StringBuffer params = new StringBuffer();

        //字符数据最好是encoding一下，这样一来，某些特殊字符才能传过去（如：某人的名字就是"&"，不encoding的话，传不过去）
        //params.append("platform=iphone"+ URLEncoder.encode("&","utf-8"));
        params.append("token=rrtv-930bd846ecc25c9c3cf716d7e9637f70c4fd94be");
        params.append("&");
        params.append("otherUser=4444767");

        //创建HttpPost请求
        HttpPost httpPost = new HttpPost("http://api.rr.tv/user/profile"+"?"+params);

        //设置ContentType(注：如果只是传普通参数的话，ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type","application/json;charset=utf8");
        httpPost.setHeader("token","rrtv-930bd846ecc25c9c3cf716d7e9637f70c4fd94be");
        httpPost.setHeader("clientType","ios_rrsp_jzsp");
        httpPost.setHeader("clientVersion","4.1.5");

        //响应模型
        CloseableHttpResponse response = null;

        //由客户端执行（发送）Post请求
        response = httpClient.execute(httpPost);

        //从响应模型中获取响应实体
        HttpEntity responseEntity = response.getEntity();
        System.out.println("响应状态为："+response.getStatusLine());
        if (responseEntity!=null){
            System.out.println("响应内容长度为："+responseEntity.getContentLength());
            String result = EntityUtils.toString(responseEntity);
            System.out.println("响应内容为："+result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String code =jsonObject.getString("code");
            System.out.println(code);

            //解析json  外层-->data-->user——>id
            String a = jsonObject.getString("data");
            //System.out.println(a);
            JSONObject datajsonObject = JSONObject.parseObject(a);
            String b = datajsonObject.getString("user");
            //System.out.println(b);
            JSONObject userjsonObject = JSONObject.parseObject(b);
            String c = userjsonObject.getString("id");
            System.out.println(c);
        }

        //释放资源
        if (httpClient!=null){
            httpClient.close();
        }
        if (response!=null){
            response.close();
        }
    }
}
