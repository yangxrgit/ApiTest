package testcase;

import com.httpclient.HttpTool;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class TestCase {

    @Test
    public static void testUserProfile() throws IOException {
        String url = "http://api.rr.tv/user/profile";
        HashMap<String,String> paramsMap = new HashMap<String, String>();
        paramsMap.put("token","rrtv-930bd846ecc25c9c3cf716d7e9637f70c4fd94be");
        paramsMap.put("otherUser","4444767");
        String result = HttpTool.post(url,paramsMap);
        System.out.println(result);
    }
}
