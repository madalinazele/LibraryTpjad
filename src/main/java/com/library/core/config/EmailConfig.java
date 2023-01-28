package com.library.core.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfig {

    private final Environment environment;

    public EmailConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(environment.getRequiredProperty("mailSender.host"));
        mailSender.setPort(Integer. parseInt(environment.getRequiredProperty("mailSender.port")));
        mailSender.setUsername(environment.getRequiredProperty("mailSender.mail_username"));
        mailSender.setPassword(environment.getRequiredProperty("mailSender.mail_password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.socketFactory", "465");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        return mailSender;
    }

}