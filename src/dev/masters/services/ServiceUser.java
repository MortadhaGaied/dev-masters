/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import dev.masters.entites.User;
import dev.masters.utils.Myconnexion;

/**
 *
 * @author Mortadha
 */
public class ServiceUser implements IService<User>{
    Connection cnx;
    
    public ServiceUser (){
        cnx = Myconnexion.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(User user) {
      
        Statement st;
        try {
            st = cnx.createStatement();
            String query ="INSERT INTO `user`(`first_name`, `last_name`, `email`, `number`, `password`, `birthday`, `date_created_user`, `last_updated_user`) VALUES ('"+user.getFirst_name()+"','"+user.getLast_name()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getNumber()+"','"+user.getBirthday()+"','"+LocalDateTime.now()+"','"+LocalDateTime.now()+"')";
      
        st.executeUpdate(query);
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
    }

    @Override
    public List<User> afficher() throws SQLException {
    Statement stm = cnx.createStatement();
    List<User> lp = new ArrayList<>();
    
    String query = "SELECT * FROM user";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            User user = new User();
            user.setId_user(rs.getLong("id_user"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setNumber(rs.getInt("number"));
            user.setBirthday(rs.getTimestamp(7).toLocalDateTime());
            user.setDate_created_user(rs.getTimestamp(8).toLocalDateTime());
            user.setLast_updated_user(rs.getTimestamp(9).toLocalDateTime());
            lp.add(user);
        }
        return lp;
    }

    @Override
    public void supprimer(Long id_user) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from user where id_user="+id_user;
        stm.executeUpdate(query);
        
    } 
    public User SearchById(Long id_user) throws SQLException{
        Statement stm = cnx.createStatement();
        User user =new User();
        String query = "select * from user where id_user="+id_user;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            user.setId_user(rs.getLong("id_user"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setNumber(rs.getInt("number"));
            user.setBirthday(rs.getTimestamp(7).toLocalDateTime());
            user.setDate_created_user(rs.getTimestamp(8).toLocalDateTime());
            user.setLast_updated_user(rs.getTimestamp(9).toLocalDateTime());
        }
        return user;
    }
    public void modifier(Long id_user_a_modifier,User user_modifier) throws SQLException {
        Statement stm = cnx.createStatement();
        User user=SearchById(id_user_a_modifier);
        String query = "UPDATE `user` SET `first_name`='"+user_modifier.getFirst_name()+"',`last_name`='"+user_modifier.getLast_name()+"',`email`='"+user_modifier.getEmail()+"',`number`='"+user_modifier.getNumber()+"',`password`='"+user_modifier.getPassword()+"',`birthday`='"+user_modifier.getBirthday()+"',`date_created_user`='"+user.getDate_created_user()+"',`last_updated_user`='"+LocalDateTime.now()+"' where id_user="+user.getId_user();
        stm.executeUpdate(query);
    }
    
}

