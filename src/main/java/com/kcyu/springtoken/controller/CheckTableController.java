package com.kcyu.springtoken.controller;


import com.kcyu.springtoken.entity.CheckTable;
import com.kcyu.springtoken.service.CheckTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.lang.model.element.VariableElement;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kcyu
 * @since 2021-09-09
 */
@RestController
@RequestMapping("/checkTable")
public class CheckTableController {

    @Autowired
    private CheckTableService checkTableService;

    @PostMapping("/addRecord")
    public Map<String,Object> addCheckRecord(@RequestBody Map info){
        String who = (String) info.get("who");
        int status = (int) info.get("status");
        try{
            int i = checkTableService.addCheckRecord(who, status);
            HashMap<String, Object> map = new HashMap<>();
            map.put("code",200);
            map.put("message","插入成功");
            return map;
        } catch (Exception e){
            HashMap<String, Object> map = new HashMap<>();
            map.put("code",400);
            map.put("message","插入失败");
            return map;
        }
    }

    @GetMapping("/getRecordsByName/{who}")
    public Map<String, Object> getRecordsByName(@PathVariable String who){
        try{
            List<CheckTable> checkRecordsByName = checkTableService.getCheckRecordsByName(who);
            HashMap<String, Object> map = new HashMap<>();
            map.put("code",200);
            map.put("message",checkRecordsByName);
            return map;
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("code",400);
            map.put("message",e);
            return map;
        }


    }

    @GetMapping("/getRecordsRow")
    public Map<String,Object> getRecordsRow(){
        List<CheckTable> enumRecordsName = checkTableService.enumRecordsName();
        ArrayList<String> arrayList = new ArrayList<>();
        for(CheckTable x: enumRecordsName){
            arrayList.add(x.getWho());
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",arrayList);
        return map;
    }
}

