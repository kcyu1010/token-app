package com.kcyu.springtoken.controller;


import com.kcyu.springtoken.entity.HistoryTable;
import com.kcyu.springtoken.service.HistoryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kcyu
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/historyTable")
public class HistoryTableController {

    @Autowired
    private HistoryTableService historyTableService;

    @GetMapping("/getAll")
    public Map<String,Object> getAllHisoryList(){
        List<HistoryTable> allHistory = historyTableService.findAllHistory();
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message",allHistory);
        return map;
    }

    @GetMapping("/getHistoryByname/{name}")
    public Map<String,Object> getHistoryByname(@PathVariable String name){
        List<HistoryTable> historyByName = historyTableService.findHistoryByName(name);
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message",historyByName);
        return map;
    }
}

