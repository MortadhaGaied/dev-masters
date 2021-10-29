/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author LENOVO
 */
/**
 *
 * @author user
 */
public class Mailer_Abonnement {

    public Mailer_Abonnement() {
    }

    public void SendMail(String mail, String msg) {

        final String username = "tunisport32@gmail.com";
        final String password = "14356216";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tunisport32@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(mail)
            );
            message.setSubject("Confirmation de votre abonnement");
            message.setText("Votre abonnement est enregistrée avec succées, " + msg);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("ereur mailer");
        }
    }

}
