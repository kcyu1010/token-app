package com.kcyu.springtoken.service;

import com.kcyu.springtoken.entity.HistoryTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kcyu
 * @since 2021-09-08
 */
public interface HistoryTableService extends IService<HistoryTable> {

    int addHistoryRecord(String token,String who);

    boolean findOneRecordByToken(String token);

    List<HistoryTable> findAllHistory();

    List<HistoryTable> findHistoryByName(String who);
}
