//
//package com.semicolon.africa.ecommerceversion40.service.impl;
//
//import com.semicolon.africa.ecommerceversion40.service.EmailServices;
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import java.util.Map;
//@Service
//@RequiredArgsConstructor
//public class EmailServicesImpl implements EmailServices {
//    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
//    public static final String UTF_8_ENCODING = "UTF-8";
//    public static final String EMAIL_TEMPLATE = "emailtemplate";
//    public static final String TEXT_HTML_ENCODING = "text/html";
//    private final JavaMailSender javaMailSender;
//    private final TemplateEngine templateEngine;
//    @Value("http://localhost:8080")
//    private String host;
//    @Value("${spring.mail.username}")
//    private String fromEmail;
//    @Async
//    public void sendHtmlEmail(String name, String to, String username) {
//        try {
//             Context context = new Context();
//            context.setVariables(Map.of("name", name, "url", getVerificationUrl(host, username)));
//            String text = templateEngine.process(EMAIL_TEMPLATE, context);
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
//            helper.setPriority(1);
//            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
//            helper.setFrom(fromEmail);
//            helper.setTo(to);
//            helper.setText(text, true);
//            javaMailSender.send(message);
//        } catch (Exception exception) {
//            System.out.println(exception.getMessage());
//            throw new RuntimeException(exception.getMessage());
//        }
//    }
//    public static String getVerificationUrl(String host, String username) {
//        return host+"/public/view/"+username+"/order";
//    }
//}
//
