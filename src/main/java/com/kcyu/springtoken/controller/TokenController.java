package com.kcyu.springtoken.controller;


import com.kcyu.springtoken.app.RequestSender;
import com.kcyu.springtoken.entity.HistoryTable;
import com.kcyu.springtoken.entity.Token;
import com.kcyu.springtoken.service.HistoryTableService;
import com.kcyu.springtoken.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kcyu
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/token")
@ResponseBody()
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HistoryTableService historyTableService;

    /*
     * @Author KC-Yu
     * @Description //TODO 通过名字获取Token
     * @Date 21:58 2021-09-08
     * @Param [name]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @RequestMapping("/{name}")
    public Map<String, Object> getTokenByName(@PathVariable String name){
        try {
            Token tokenByName = tokenService.findTokenByName(name);
            if(tokenByName != null){
                HashMap<String, Object> map = new HashMap<>();
                map.put("code",200);
                map.put("message",tokenByName);
                return map;
            } else {
                HashMap<String, Object> map = new HashMap<>();
                map.put("code",300);
                map.put("message","无此信息");
                return map;
            }

        } catch (Exception e){
            System.out.println(e);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",400);
        map.put("message","无此信息");
        return map;

    }

    /*
     * @Author KC-Yu
     * @Description //TODO 获取所有的信息
     * @Date 21:58 2021-09-08
     * @Param []
     * @return java.util.List<com.kcyu.springtoken.entity.Token>
     **/
    @RequestMapping(value = "/getAll",method = {RequestMethod.GET})
    public Map<String, Object> getAllUserInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message",tokenService.findAllToken());
        return map;
    }

    @RequestMapping(value = "/getAllUsable",method = {RequestMethod.GET})
    public Map<String, Object> getAllUseableUserInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message",tokenService.findAllUsableToken());
        return map;
    }


    @RequestMapping(value = "/updateToken",method = {RequestMethod.POST})
    public Map<String, Object> updateToken(@RequestBody Map info){
        String who =(String) info.get("who");
        String token =(String) info.get("Token");
        // 先检查是否有重复的token
        boolean oneRecordByToken = historyTableService.findOneRecordByToken(token);
        // 执行判断
        Map<String, Object> map = new HashMap<>();
        if(!oneRecordByToken){
            int i = tokenService.updateToken(token, who);
            int i1 = historyTableService.addHistoryRecord(token, who);
            if(i+i1 == 2){
               map.put("code",200);
               map.put("message","更新成功");
               return map;
            } else {
                map.put("code",300);
                map.put("message","更新成功,但出现异常");
                return map;
            }
        } else {
            map.put("code",400);
            map.put("message","更新失败，有可能与已存在Token重复，请检查");
            return map;
        }
    }

    @PostMapping("/updateIsCheck")
    public Map<String, Object> updateIsCheck(@RequestBody Map info){
        String who = (String) info.get("who");
        int status = (int) info.get("status");
        int i = tokenService.updateIsCheck(who, status);
        Map<String, Object> map = new HashMap<>();
        if(i > 0){
            map.put("code",200);
            map.put("message","更新成功");
            return map;
        } else {
            map.put("code",400);
            map.put("message","更新失败");
            return map;
        }
    }

    @PostMapping("/updateStatus")
    public Map<String, Object> updateStatus(@RequestBody Map info){
        String who = (String) info.get("who");
        int status = (int) info.get("status");
        int i = tokenService.updateStatus(who, status);
        Map<String, Object> map = new HashMap<>();
        if(i > 0){
            map.put("code",200);
            map.put("message","更新成功");
            return map;
        } else {
            map.put("code",400);
            map.put("message","更新失败");
            return map;
        }
    }

    @GetMapping("/verifyToken/{token}/{who}")
    public Map<String, Object> updateString(@PathVariable String token,@PathVariable String who){
        RequestSender requestSender = new RequestSender();
        try {
            Integer integer = requestSender.simulateRequest(token);
            if(integer == 0){
                tokenService.updateStatus(who,1);
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", 200);
                map.put("message", "验证成功");
                return map;
            } else {
                HashMap<String, Object> map = new HashMap<>();
                map.put("code", 400);
                map.put("message", "验证失败");
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
            HashMap<String, Object> map = new HashMap<>();
            map.put("code", 400);
            map.put("message", "验证失败");
            return map;
        }
    }

}

