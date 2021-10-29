/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.api;

import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;

/**
 *
 * @author mouha
 */
public class NotificationApi {
    
    
    
        
        

    public NotificationApi() {
       
        
    }
        
        
        
        
        public void showNotif(String title,String message,Notification notification){
           
       
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndWait();
        }
        
        
        
        

    
}
