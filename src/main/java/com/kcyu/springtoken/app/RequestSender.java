package com.kcyu.springtoken.app;

import okhttp3.*;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RequestSender {


    /*
    * 模拟打卡请求
    * */
    public Integer simulateRequest(String token) throws IOException {
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String seq = "";
        if(hour < 9){
            seq = "1";
        } else if(hour >=11 && hour <15) {
            seq = "2";
        } else if(hour >= 17 && hour < 23){
            seq = "3";
        } else {
            seq = "0";
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "answers=[\"0\"]&seq="+seq+"&temperature=36.0&userId=&latitude=23.088090896606445&longitude=113.35653686523438&country=中国&city=广州市&district=海珠区&province=广东省&township=官洲街道&street=赤沙西约大街&myArea=&areacode=440105");
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
