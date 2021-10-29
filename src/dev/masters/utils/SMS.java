/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author LENOVO
 */
public class SMS {

    public SMS() {
    }
    public static final String ACCOUNT_SID = "AC3178714705f233f4d9661d3531c30863";
    public static final String AUTH_TOKEN = "3e6cbfffed03e07e8b65efc37548b4f2";

    public void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21621062777"),new PhoneNumber("+13186109336"),"Votre abonnement est enregistrer avec succés," + msg).create();

        System.out.println(message.getSid());

    }
}
