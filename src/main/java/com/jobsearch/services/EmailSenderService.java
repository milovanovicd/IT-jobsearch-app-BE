package com.jobsearch.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author dejanmilovanovic
 *
 */
@Service("emailSenderService")
public class EmailSenderService {

	@Autowired
    @Qualifier("gmail")
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(MimeMessage email) {
        javaMailSender.send(email);
    }
    
    @Async
    public void sendMimeEmail(MimeMessage email) {
        javaMailSender.send(email);
    }

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}
    
}
