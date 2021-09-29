package com.kcyu.springtoken.service;

import com.kcyu.springtoken.entity.CheckTable;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kcyu
 * @since 2021-09-09
 */
public interface CheckTableService extends IService<CheckTable> {

    int addCheckRecord(String who,int status);

    List<CheckTable> getCheckRecordsByName(String who);

    List<CheckTable> enumRecordsName();

}
