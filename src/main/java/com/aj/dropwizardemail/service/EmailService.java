package com.aj.dropwizardemail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    // Recipient's Email ID
    public static final String to = "techdeveloper.aj@gmail.com";
    // Sender's Email ID
    public static final String from = "techdeveloper.aj@gmail.com";
    public static final String emailSubject = "Mail from Dropwizard Email Application";
    public static final String msg = "<p>Hi</p>\n" +
            "<p>This is a mail from Dropwizard Email Application!!!</p>\n" +
            "Regards<br>\n" +
            "Aj Tech Developer";
    public static final String successResult = "Mail sent successfully.";
    public static final String failureResult = "Failed to send mail.";

    //Send email with body only without any attachment.
    public String sendMail() {
        String result;
        try {
            logger.info("Sending Email to {}", to);
            Session session = getSession();
            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);
                // Set From
                message.setFrom(new InternetAddress(from));
                // Set To
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // Set Subject
                message.setSubject(emailSubject);
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                message.setContent(multipart);
                // Send message
                Transport.send(message);
                logger.info(successResult);
                result = successResult;
            } catch (MessagingException ex) {
                logger.error("Failed to send email to: {}", to);
                logger.error("Exception is: {}", ex.getMessage());
                result = failureResult;
            }
        }
        catch (Exception e){
            logger.error("Exception in EmailService.sendMail(): ",e.getMessage());
            result = failureResult;
        }
        return result;
    }

    //Fetch the file from the specified file path and send email by attaching the file fetched.
    public String sendMailAttachment() {
        String result;
        try {
            logger.info("Sending Email to {}", to);
            Session session = getSession();
            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);
                // Set From
                message.setFrom(new InternetAddress(from));
                // Set To
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // Set Subject
                message.setSubject(emailSubject);
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html");
                MimeBodyPart mimeBodyPartAttachment = new MimeBodyPart();
                mimeBodyPartAttachment.attachFile(new File("src/main/resources/attachment/Hello.txt"));

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                multipart.addBodyPart(mimeBodyPartAttachment);
                message.setContent(multipart);
                // Send message
                Transport.send(message);
                logger.info(successResult);
                result = successResult;
            } catch (MessagingException ex) {
                logger.error("Failed to send email to: {}", to);
                logger.error("Exception is: {}", ex.getMessage());
                ex.printStackTrace();
                result = failureResult;
            }
        }
        catch (Exception e){
            logger.error("Exception in EmailService.sendMailAttachment(): ",e.getMessage());
            result = failureResult;
        }
        return result;
    }

    //Create an attachment and send email.
    public String sendMailCreateAttachment() {
        String result;
        try {
            logger.info("Sending Email to {}", to);
            Session session = getSession();
            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);
                // Set From
                message.setFrom(new InternetAddress(from));
                // Set To
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // Set Subject
                message.setSubject(emailSubject);
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(msg, "text/html");
                File file = getTextFile();
                MimeBodyPart mimeBodyPartAttachment = new MimeBodyPart();
                mimeBodyPartAttachment.attachFile(file);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                multipart.addBodyPart(mimeBodyPartAttachment);
                message.setContent(multipart);
                // Send message
                Transport.send(message);
                logger.info(successResult);
                result = successResult;
            } catch (MessagingException ex) {
                logger.error("Failed to send email to: {}", to);
                logger.error("Exception is: {}", ex.getMessage());
                result = failureResult;
            }
        }
        catch (Exception e){
            logger.error("Exception in EmailService.sendMailCreateAttachment(): ",e.getMessage());
            result = failureResult;
        }
        return result;
    }

    private Session getSession() {
        //Gmail Host
        String host = "smtp.gmail.com";
        String username = "techdeveloper.aj@gmail.com";
        //Enter your Gmail password
        String password = "";

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", host);

        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private File getTextFile() throws IOException {
        String text = "Hello World!!!";
        BufferedWriter bufferedWriter = null;
        File file = null;
        try {
            file = new File("Hello.txt");
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(text);
        } catch ( IOException e ) {
            logger.error("Error in creating text file: {}", e.getMessage());
        } finally {
            if ( bufferedWriter != null ) {
                bufferedWriter.close();
            }
        }
        return file;
    }
}
