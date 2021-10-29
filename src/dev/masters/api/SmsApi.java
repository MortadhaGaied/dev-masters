/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.api;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author mouha
 */
public class SmsApi {
    
    
    
    
    public static final String ACCOUNT_SID = "AC827499c505a0825c13b9c15a5e57dcde";
    public static final String AUTH_TOKEN = "59edc298dec6da7ab85b8c69fc8db4f5";

    public void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21625892319"),new PhoneNumber("+14704444081"), msg).create();

        System.out.println(message.getSid());

    }
    
}
