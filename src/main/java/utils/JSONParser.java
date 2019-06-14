package utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.List;

public class JSONParser {

    public String getCode(JSONObject jo){

        String code = jo.getString("code");
        return code;

    }

    public boolean isResponseCorrect(JSONObject jo,String checkpoint,String passValue){
        //用jsonpath处理json，获取result中特定键值
        ReadContext context = JsonPath.parse(jo);

//        JSONArray result = context.read("$.data.."+checkpoint);
//        String resultString = result.get(0);
//        if(resultString.equals(passValue)){
//            return true;
//        }else{
//            return false;
//        }

        String result = context.read("$."+checkpoint);
        if(result.equals(passValue)){
            return true;
        }else{
            return false;
        }

    }



}
