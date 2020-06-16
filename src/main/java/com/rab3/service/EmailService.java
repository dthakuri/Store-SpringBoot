package com.rab3.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.rab3.controller.dto.ProfileDTO;

@Service
public class EmailService {

	@Autowired
	JavaMailSender emailSender;

	@Autowired
	SpringTemplateEngine templateEngine;

	public void emailSender(String text) {
		 MimeMessage mimeMessage= emailSender.createMimeMessage();
		
		  try { 
			MimeMessageHelper helper = new  MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			helper.setTo("javahunk001@gmail.com");
			helper.setText(text,true);
			helper.setSubject("test");
			helper.setFrom("rab3java2020@gmail.com");//you can write any email
			emailSender.send(mimeMessage);
  
		  } catch (MessagingException e) {
		  e.printStackTrace(); }

		
	}

	public void emailSender(List<ProfileDTO> profileDTOs) {
		 MimeMessage mimeMessage= emailSender.createMimeMessage();
			
		  try { 
			MimeMessageHelper helper = new  MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			helper.setTo("javahunk001@gmail.com");
			helper.setText(profileDTOs.toString(),true);
			helper.setSubject("test");
			helper.setFrom("rab3java2020@gmail.com");//you can write any email
			emailSender.send(mimeMessage);
 
		  } catch (MessagingException e) {
		  e.printStackTrace(); }
		 
		
	}
	
	//image
	public void emailSender() {
		 MimeMessage mimeMessage= emailSender.createMimeMessage();
			
		  try { 
			MimeMessageHelper helper = new  MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		    Context context=new Context();
			String html=templateEngine.process("temp1",context);
			helper.addAttachment("edit.png", new ClassPathResource("resources/static/images/edit.png"));
			helper.setTo("javahunk001@gmail.com");
			helper.setText("image test");
			helper.setSubject("test");
			helper.setFrom("rab3java2020@gmail.com");//you can write any email
			emailSender.send(mimeMessage);

		  } catch (MessagingException e) {
		  e.printStackTrace(); }
		 
		
	}

}
