package com.dicka.springbootemail.service;

import com.dicka.springbootemail.MailProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EmailSenderService {

    private final MailProperties mailProperties;
    private final Configuration configuration;

    @Autowired
    public EmailSenderService(MailProperties mailProperties, Configuration configuration){
        this.mailProperties = mailProperties;
        this.configuration = configuration;
    }

    /** email sender **/
    public boolean sendVerificationEmail(String toMail, String verifiedToken){
        /** subject email **/
        String subject = "Please verified your email.";
        String body = "";
        try{
            Template template = configuration
                    .getTemplate("email-verification.ftl");
            Map<String, String> map = new HashMap<>();
            map.put("VERIFICATION_URL", mailProperties.getVerificationApi() + verifiedToken);
            body = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        }catch (Exception ex){
            ex.printStackTrace();
            Logger.getLogger(this.getClass().getName())
                    .log(Level.SEVERE, ex.getMessage(), ex);
        }
        return sendMail(toMail, subject, body);
    }

    /** email confirm **/
    private boolean sendMail(String toEmail, String subject, String body){
        try{

            Properties prop = System.getProperties();
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.port", mailProperties.getSmtp().getPort());
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(prop);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailProperties.getFrom(),
                    mailProperties.getFromName()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            Transport transport = session.getTransport();
            transport.connect(
                    mailProperties.getSmtp().getHost(),
                    mailProperties.getSmtp().getUsername(),
                    mailProperties.getSmtp().getPassword()
            );
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;

        }catch (Exception e){
            e.printStackTrace();
            Logger.getLogger(
                    this.getClass()
                    .getName()
            ).log(Level.SEVERE, e.getMessage(), e);
        }
        return false;
    }
}
