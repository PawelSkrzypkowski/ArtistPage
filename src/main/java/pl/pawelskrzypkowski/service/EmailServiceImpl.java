package pl.pawelskrzypkowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl{

    @Autowired
    public JavaMailSender emailSender;

    public String sendMessageWithAttachment(
            String to, String subject, String text, Map<String, File> attachments) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        Boolean fail = false;
        for(Map.Entry<String, File> entry : attachments.entrySet()){
            try {
                helper.addAttachment(entry.getKey(), new FileSystemResource(entry.getValue()));
            } catch (MessagingException e) {
                Logger.getAnonymousLogger().info("Can't add attachment.");
                fail = true;
            }
        }
        if(fail)
            return "fail";
        emailSender.send(message);
        return "success";
    }
}