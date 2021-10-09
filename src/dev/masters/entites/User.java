/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;

import java.time.LocalDateTime;


public class User {
    
    private long id_user ;
    
    private String first_name;
    
    private String last_name;
    
    private String email;
    
    private String password;
    
    private int number;
    
    private LocalDateTime birthday;
    
    private LocalDateTime date_created_user;
    
    private LocalDateTime last_updated_user;

    public User() {
    }
    

    public User(String first_name, String last_name, String email, String password, int number, LocalDateTime birthday) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.number = number;
        this.birthday = birthday;
    }

    public User(long id_user, String first_name, String last_name, String email, String password, int number, LocalDateTime birthday, LocalDateTime date_created_user, LocalDateTime last_updated_user) {
        this.id_user = id_user;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.number = number;
        this.birthday = birthday;
        this.date_created_user = date_created_user;
        this.last_updated_user = last_updated_user;
    }
    

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getDate_created_user() {
        return date_created_user;
    }

    public void setDate_created_user(LocalDateTime date_created_user) {
        this.date_created_user = date_created_user;
    }

    public LocalDateTime getLast_updated_user() {
        return last_updated_user;
    }

    public void setLast_updated_user(LocalDateTime last_updated_user) {
        this.last_updated_user = last_updated_user;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", password=" + password + ", number=" + number + ", birthday=" + birthday + ", date_created_user=" + date_created_user + ", last_updated_user=" + last_updated_user + '}'+"\n";
    }
    
    

    
    
    
}

