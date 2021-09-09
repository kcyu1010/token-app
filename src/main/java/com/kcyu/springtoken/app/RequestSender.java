package com.kcyu.springtoken.app;

import okhttp3.*;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;

public class RequestSender {

    public Integer simulateRequest(String token) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "answers=[\"0\"]&seq=1&temperature=36.0&userId=&latitude=23.088090896606445&longitude=113.35653686523438&country=中国&city=广州市&district=海珠区&province=广东省&township=官洲街道&street=赤沙西约大街&myArea=&areacode=440105");
        Request request = new Request.Builder()
                .url("https://student.wozaixiaoyuan.com/heat/save.json")
                .method("POST", body)
                .addHeader("JWSESSION", token)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        String s = response.body().string();
        JSONObject jsonObject = JSONObject.parseObject(s);
//        System.out.println(parse);
        return jsonObject.getInteger("code");
//        System.out.println(response);
    }
}
