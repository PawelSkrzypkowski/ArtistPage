package pl.pawelskrzypkowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl{

    @Autowired
    public JavaMailSender emailSender;

    public Map<String, ByteArrayResource> convertMultipartFiles(MultipartFile[] files) throws IOException {
        Map<String, ByteArrayResource> map = new LinkedHashMap<>();
        for(MultipartFile mf : files){
            map.put(mf.getOriginalFilename(), new ByteArrayResource(mf.getBytes()));
        }
        return map;
    }

    public String sendMessageWithAttachment(
            String to, String subject, String text, Map<String, ByteArrayResource> attachments) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        boolean fail = false;
        for(Map.Entry<String, ByteArrayResource> entry : attachments.entrySet()){
            try {
                helper.addAttachment(entry.getKey(), entry.getValue());
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