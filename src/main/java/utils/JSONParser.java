package utils;

import com.alibaba.fastjson.JSONObject;

public class JSONParser {

    public String getCode(JSONObject jo){

        String code = jo.getString("code");
        return code;

    }



     //写个解析json 一层、2层、3层的代码？
}
