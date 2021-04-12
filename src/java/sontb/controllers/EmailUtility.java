package sontb.controllers;



import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PC
 */
public class EmailUtility {

    private static final String fromEmail = "tranbason241@gmail.com";//user.getFromEmail(); //requires valid gmail id
    private static final String password = "0935287090";//user.getPassword(); // correct password for gmail id

    public static boolean sendMail(String toEmail, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        try {
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "DreamCar!!!"));

            msg.setReplyTo(InternetAddress.parse(fromEmail, false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(text, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport trans = session.getTransport("smtp");
            trans.connect("smtp.gmail.com", fromEmail, password);
            trans.sendMessage(msg, msg.getAllRecipients());

        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
