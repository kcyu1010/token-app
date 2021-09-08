package com.kcyu.springtoken.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kcyu.springtoken.entity.HistoryTable;
import com.kcyu.springtoken.mapper.HistoryTableMapper;
import com.kcyu.springtoken.service.HistoryTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

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

    public static long getTimeZoneTimeStr(String dateStr,String timeZone) {
        long result = 0L;
        int year;
        int month;
        int day;
        int hour;
        int minute;
        int second;
        Calendar calendarTime = Calendar.getInstance();

        if(timeZone != null){
            TimeZone tz = TimeZone.getTimeZone(timeZone);
            calendarTime.setTimeZone(tz);
        }

        if (null != dateStr && 14 == dateStr.length()) {
            year = Integer.parseInt(dateStr.substring(0, 4));
            month = Integer.parseInt(dateStr.substring(4, 6));
            day = Integer.parseInt(dateStr.substring(6, 8));
            hour = Integer.parseInt(dateStr.substring(8, 10));
            minute = Integer.parseInt(dateStr.substring(10, 12));
            second = Integer.parseInt(dateStr.substring(12, 14));
            calendarTime.set(1, year);
            calendarTime.set(2, month - 1);
            calendarTime.set(5, day);
            calendarTime.set(11, hour);
            calendarTime.set(12, minute);
            calendarTime.set(13, second);
            result = calendarTime.getTime().getTime();
        }else if (null != dateStr && 19 == dateStr.length()) {
            year = Integer.parseInt(dateStr.substring(0, 4));
            month = Integer.parseInt(dateStr.substring(5, 7));
            day = Integer.parseInt(dateStr.substring(8, 10));
            hour = Integer.parseInt(dateStr.substring(11, 13));
            minute = Integer.parseInt(dateStr.substring(14, 16));
            second = Integer.parseInt(dateStr.substring(17, 19));
            calendarTime.set(1, year);
            calendarTime.set(2, month - 1);
            calendarTime.set(5, day);
            calendarTime.set(11, hour);
            calendarTime.set(12, minute);
            calendarTime.set(13, second);
            result = calendarTime.getTime().getTime();
        }

        return result;
    }

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
}
