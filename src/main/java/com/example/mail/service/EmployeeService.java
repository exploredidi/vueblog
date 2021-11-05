package com.example.mail.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.Utils.JwtUtils;
import com.example.entity.User;
import com.example.mail.entity.MailConstants;
import com.example.mail.entity.MailSendLog;
import com.example.mapper.MailSendLogMapper;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-10-29 7:44
 */
@Service
public class EmployeeService {

    @Autowired
    UserService imUserService;

    @Autowired
    UserMapper mUserMapper;

    @Autowired
            //这里直接是源码rabbitTemplate
    RabbitTemplate rabbitTemplate;

    @Autowired
    MailSendLogService mailSendLogService;

    @Autowired
    MailSendLogMapper mailSendLogMapper;
    @Autowired
    JwtUtils jwtUtils;

    public final static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");


    //添加员工
    public Integer addEmp(User mUser) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("email",mUser.getEmail());

        User user = mUserMapper.selectOne(wrapper);

        if (user != null){
            return 0;
        }
        //往数据库插入数据并且获取返回值
        int result = mUserMapper.insert(mUser);

        if (result == 1) {
            //准备发送邮件
            //生成消息的唯一id

            String msgId = UUID.randomUUID().toString();
            MailSendLog mailSendLog = new MailSendLog();
            mailSendLog.setMsgid(msgId);
            mailSendLog.setCreatetime(new Date());
            mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailSendLog.setRoutekey(MailConstants.MAIL_ROUTING_KEY_NAME);
            mailSendLog.setTrytime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));

            //获取当前最大userId
            QueryWrapper<MailSendLog> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("empid").last("limit 1");
            MailSendLog mailSendLog1 = mailSendLogMapper.selectOne(queryWrapper);

            mailSendLog.setEmpid(mailSendLog1.getEmpid() + 1);

            mailSendLogService.insert(mailSendLog);

            //rabbit队列产生消息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME, mUser, new CorrelationData(msgId));
        }
        return result;
    }

  }
