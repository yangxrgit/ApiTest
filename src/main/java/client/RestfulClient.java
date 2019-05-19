package client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RestfulClient {

    CloseableHttpClient httpclient;
    HttpGet httpGet;
    HttpPost httpPost;
    CloseableHttpResponse httpResponse;
    int responseCode;
    JSONObject responseBody;
    HashMap<String, String> responseHeads;

    //通过httpclient获取请求的反馈
    public void getResponse(String url) throws ClientProtocolException, IOException {
        httpclient = HttpClients.createDefault();
        httpGet = new HttpGet(url);
        //httpGet.setHeader("Content-Type","application/json;charset=utf8");
        httpGet.setHeader("token","rrtv-9eb0d4ea4ec5fb24609533522a05d46f86460c71");
        httpGet.setHeader("clientType","android_RRMJ_REPLACE");
        httpGet.setHeader("clientVersion","4.1.6");
        httpResponse = httpclient.execute(httpGet);

    }

    //以JSON格式获取到反馈的主体
    public JSONObject getBodyInJSON() throws ParseException, IOException{
        responseBody = JSON.parseObject(EntityUtils.toString(httpResponse.getEntity()));
        System.out.println("This is your response body" + responseBody);
        return responseBody;
    }

    //以哈希图的方式获取到反馈头部
    public HashMap<String, String> getHeaderInHash(){
        Header[] headers = httpResponse.getAllHeaders();
        responseHeads = new HashMap<String, String>();
        for(Header header:headers){
            responseHeads.put(header.getName(), header.getValue());
        }
        System.out.println("This is your response header" + responseHeads);
        return responseHeads;
    }

    //获取反馈状态码
    public int getCodeInNumber(){
        responseCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("This is your response code" + responseCode);
        return responseCode;
    }


    //通过httpclient获取post请求的反馈
    public void sendPost(String url, List<NameValuePair> params,HashMap<String,String> headers) throws ClientProtocolException, IOException{
        httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        //设置头部信息
        Set<String> set = headers.keySet();
        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
            String key = iterator.next();
            String value = headers.get(key);
            httpPost.addHeader(key, value);
        }
        httpResponse = httpclient.execute(httpPost);
    }

}

