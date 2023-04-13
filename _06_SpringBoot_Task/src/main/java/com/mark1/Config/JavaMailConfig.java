package com.mark1.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfig {

    @Autowired
    public JavaMailProperty javaMailProperty;

    @Bean("javaMailSender")
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(javaMailProperty.getHost());
        javaMailSender.setPort(javaMailProperty.getPort());
        javaMailSender.setUsername(javaMailProperty.getUsername());
        javaMailSender.setPassword(javaMailProperty.getPassword());
        javaMailSender.setDefaultEncoding(javaMailProperty.getEncoding());
        Properties properties = new Properties();
        properties.setProperty("spring.mail.properties.mail.smtp.auth", javaMailProperty.isSmtp_auth()+"");
        properties.setProperty("spring.mail.properties.mail.smtp.starttls.enable", javaMailProperty.isSmtp_enable()+"");
        properties.setProperty("spring.mail.properties.mail.smtp.starttls.required", javaMailProperty.isSmtp_required()+"");
        properties.setProperty("spring.mail.properties.mail.smtp.socketFactory.class", javaMailProperty.getSmtp_socketFactory_class());
        javaMailSender.setJavaMailProperties(properties);
        return  javaMailSender;
    }
}
