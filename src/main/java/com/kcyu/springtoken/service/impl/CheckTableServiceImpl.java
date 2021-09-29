package com.kcyu.springtoken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kcyu.springtoken.entity.CheckTable;
import com.kcyu.springtoken.mapper.CheckTableMapper;
import com.kcyu.springtoken.service.CheckTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kcyu
 * @since 2021-09-09
 */
@Service
public class CheckTableServiceImpl extends ServiceImpl<CheckTableMapper, CheckTable> implements CheckTableService {

    @Autowired
    private CheckTableMapper checkTableMapper;

    @Override
    public int addCheckRecord(String who, int status) {
        CheckTable checkTable = new CheckTable();
        checkTable.setWho(who);
        checkTable.setStatus(status);
        checkTable.setCheckTime(new Timestamp(new Date().getTime()));
        int insert = checkTableMapper.insert(checkTable);
        return insert;
    }

    @Override
    public List<CheckTable> getCheckRecordsByName(String who) {

        QueryWrapper<CheckTable> wrapper = new QueryWrapper<>();
        wrapper.eq("who",who);
        wrapper.orderByDesc("check_time");
        List<CheckTable> checkTables = checkTableMapper.selectList(wrapper);
        return checkTables;
    }

    @Override
    public List<CheckTable> enumRecordsName() {
        QueryWrapper<CheckTable> wrapper = new QueryWrapper<>();
        wrapper.select("distinct who");
        List<CheckTable> row = checkTableMapper.selectList(wrapper);
        return row;
    }
}
