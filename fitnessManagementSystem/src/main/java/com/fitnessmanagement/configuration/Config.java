package com.fitnessmanagement.configuration;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.fitnessmanagement.mapper.ManagerMapper;

@Configuration
@PropertySource("classpath:mail.properties")
//메일전송 Config
public class Config {
	
	Properties pt=new Properties();
	
    @Value("${mail.smtp.port}")
    private int port;
    @Value("${mail.smtp.socketFactory.port}")
    private int socketPort;
    @Value("${mail.smtp.auth}")
    private boolean auth;
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${mail.smtp.starttls.required}")
    private boolean startlls_required;
    @Value("${mail.smtp.socketFactory.fallback}")
    private boolean fallback;



	 @Bean
	 public JavaMailSender javaMailService() {
	       JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	       javaMailSender.setHost("smtp.gmail.com");
	       javaMailSender.setUsername("aa1010100zz@gmail.com");
	       javaMailSender.setPassword("seo2518jy!");
	       javaMailSender.setPort(port);
	        
	       pt.put("mail.smtp.socketFactory.port", socketPort); pt.put("mail.smtp.auth", auth);
	       pt.put("mail.smtp.starttls.enable", starttls); pt.put("mail.smtp.starttls.required", startlls_required);
	       pt.put("mail.smtp.socketFactory.fallback",fallback);
	       pt.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	       javaMailSender.setJavaMailProperties(pt);      
	       javaMailSender.setDefaultEncoding("UTF-8");
	       return javaMailSender;
	 }
	  





}
