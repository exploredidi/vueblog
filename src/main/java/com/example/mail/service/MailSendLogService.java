package com.example.mail.service;

import com.example.mail.entity.MailSendLog;
import com.example.mapper.MailSendLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailSendLogService {

    @Autowired
    MailSendLogMapper mailSendLogMapper;

    public Integer insert(MailSendLog mailSendLog) {
        return mailSendLogMapper.insert(mailSendLog);
    }
}
