package com.kcyu.springtoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author kcyu
 * @since 2021-09-08
 */
public class HistoryTable extends Model<HistoryTable> {

    private static final long serialVersionUID=1L;

    @TableId(value = "token")
    private String token;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp changedate;

    private String who;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getChangedate() {
        return changedate;
    }

    public void setChangedate(Timestamp changedate) {
        this.changedate = changedate;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    protected Serializable pkVal() {
        return this.token;
    }

    @Override
    public String toString() {
        return "HistoryTable{" +
        "token=" + token +
        ", changedate=" + changedate +
        ", who=" + who +
        "}";
    }
}
