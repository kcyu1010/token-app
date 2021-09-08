package com.kcyu.springtoken.controller;


import com.kcyu.springtoken.entity.HistoryTable;
import com.kcyu.springtoken.entity.Token;
import com.kcyu.springtoken.service.HistoryTableService;
import com.kcyu.springtoken.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.lang.model.element.VariableElement;
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
            HashMap<String, Object> map = new HashMap<>();
            map.put("code",200);

            map.put("message",tokenByName);
            return map;
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
    public List<Token> getAllUserInfo(){
        return tokenService.findAllToken();
    }

    @RequestMapping(value = "/getAllUsable",method = {RequestMethod.GET})
    public List<Token> getAllUseableUserInfo(){
        return tokenService.findAllUsableToken();
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

}

