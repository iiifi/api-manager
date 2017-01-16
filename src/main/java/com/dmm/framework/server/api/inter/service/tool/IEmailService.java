package com.dmm.framework.server.api.inter.service.tool;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.dmm.framework.server.api.dto.MailBean;

public interface IEmailService {

	void sendMail(MailBean mailBean) throws UnsupportedEncodingException, MessagingException;

	void sendRegisterEmail(String eamil, String id) throws UnsupportedEncodingException, MessagingException;

	void sendFindPwdEmail(String eamil) throws UnsupportedEncodingException, MessagingException;

	boolean sendMail(String subject, String toEmail, String context);

}