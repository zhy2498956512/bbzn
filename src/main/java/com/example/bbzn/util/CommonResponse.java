package com.example.bbzn.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommonResponse {

    public String commonReturn(String json) {
        System.out.println(json);
        CommonReturnJson commonReturnJson = new CommonReturnJson();
        commonReturnJson.setCode(200);
        commonReturnJson.setMessage("请求成功");
        commonReturnJson.setData(json);
        return JSONObject.toJSONString(commonReturnJson);
    }

    public String commonAESReturn(String json) throws Exception {
        //json = Aes.getInstance().encrypt(json);
        System.out.println(json);
        CommonReturnJson commonReturnJson = new CommonReturnJson();
        commonReturnJson.setCode(200);
        commonReturnJson.setMessage("请求成功");
        commonReturnJson.setData(json);
        return JSONObject.toJSONString(commonReturnJson);
    }

    public String commonErrorReturn(int code,String message) {
        CommonReturnJson commonReturnJson = new CommonReturnJson();
        commonReturnJson.setCode(code);
        commonReturnJson.setMessage(message);
        commonReturnJson.setData("");
        System.out.println("errorCode");
        return JSONObject.toJSONString(commonReturnJson);
    }


}
