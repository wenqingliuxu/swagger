package com.demo.springboot.vue.base.servcie.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 基础实体类，业务实体继承
 */
abstract public class BaseEntity {
    @ApiModelProperty(hidden = true)
    private Date createTime = null;

    @ApiModelProperty(hidden = true)
    private Date modifyTime = null;

    @ApiModelProperty(hidden = true)
    private String createUser = null;

    @ApiModelProperty(hidden = true)
    private String modifyUser = null;

    public  abstract long getKey();

    //datetime值,通过@DateTimeFormat指定日期转换器格式
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //指定json序列化时的日期格式，配置文件中的p:simpleDateFormat="yyyy-MM-dd"指定通用格式，特殊的格式需要@JsonFormat配置
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
}
