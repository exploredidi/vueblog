package com.example.mail.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class MailSendLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msgid;
    private Integer empid;
    //0 消息投递中   1 投递成功   2投递失败
    private Integer status;
    private String routekey;
    private String exchange;
    private Integer count;
    private Date trytime;
    private Date createtime;
    private Date updatetime;

}
