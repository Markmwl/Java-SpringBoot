package com.mark1.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class JavaMailProperty {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.default-encoding}")
    private String encoding;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean smtp_auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean smtp_enable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private boolean smtp_required;

    @Value("${spring.mail.properties.mail.smtp.socketFactory.class}")
    private String smtp_socketFactory_class;
}
