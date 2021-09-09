package com.kcyu.springtoken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kcyu.springtoken.entity.HistoryTable;
import com.kcyu.springtoken.mapper.HistoryTableMapper;
import com.kcyu.springtoken.service.HistoryTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Wrapper;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.TimeZone;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kcyu
 * @since 2021-09-08
 */
@Service
public class HistoryTableServiceImpl extends ServiceImpl<HistoryTableMapper, HistoryTable> implements HistoryTableService {

    @Autowired
    private HistoryTableMapper historyTableMapper;

    @Override
    public int addHistoryRecord(String token, String who) {
        HistoryTable historyTable = new HistoryTable();
        historyTable.setToken(token);
        historyTable.setChangedate(new Timestamp(new Date().getTime()));
        historyTable.setWho(who);
        try {
            int insert = historyTableMapper.insert(historyTable);
        } catch (HttpServerErrorException.InternalServerError error) {
            System.out.println(error);
        }

        return 1;
    }

    @Override
    public boolean findOneRecordByToken(String token) {
        QueryWrapper<HistoryTable> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        List<HistoryTable> historyTables = historyTableMapper.selectList(wrapper);
        return historyTables.size() > 0;
    }

    @Override
    public List<HistoryTable> findAllHistory() {
        HistoryTable historyTable = new HistoryTable();
        return historyTable.selectAll();
    }

    @Override
    public List<HistoryTable> findHistoryByName(String who) {
        QueryWrapper<HistoryTable> wrapper = new QueryWrapper<>();
        wrapper.eq("who",who);
        wrapper.orderByDesc("changedate");
        return historyTableMapper.selectList(wrapper);
    }
}
