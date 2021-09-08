package com.kcyu.springtoken.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author kcyu
 * @since 2021-09-07
 */
public class Token extends Model<Token> {

    private static final long serialVersionUID=1L;

    private String token;

    @TableId(value = "who", type = IdType.AUTO)
    private String who;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp lastupdatetime;

    private String chnName;

    private String updateTime;

    private Integer status;

    @TableField("isCheck")
    private Integer isCheck;

    @TableField("miaoCode")
    private String miaoCode;

    private String location;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public Timestamp getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Timestamp lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public String getMiaoCode() {
        return miaoCode;
    }

    public void setMiaoCode(String miaoCode) {
        this.miaoCode = miaoCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    protected Serializable pkVal() {
        return this.who;
    }

    @Override
    public String toString() {
        return "Token{" +
        "token=" + token +
        ", who=" + who +
        ", lastupdatetime=" + lastupdatetime +
        ", chnName=" + chnName +
        ", updateTime=" + updateTime +
        ", status=" + status +
        ", isCheck=" + isCheck +
        ", miaoCode=" + miaoCode +
        ", location=" + location +
        "}";
    }
}
