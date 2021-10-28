/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import dev.masters.entites.Reclamation;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mortadha
 */
public class ServiceReclamation implements IService<Reclamation> {
JavaMail mail = new JavaMail();    
public Connection cnx;
public PreparedStatement ste;
    public ServiceReclamation() {  
        cnx = Myconnexion.getInstance().getCnx();
        
    }
    
    public void ajouter(Reclamation r){
    try {
        String sql = "insert into reclamation(username_reclamation , object_reclamation , description_reclamation)"+"values(?,?,?)";
        ste=cnx.prepareStatement(sql);
        ste.setString(1, r.getUsername_reclamation());
        ste.setString(2, r.getObject_reclamation());
        ste.setString(3, r.getDescription_reclamation());
        ste.executeUpdate();
        mail.send("benromdhane.ahmed@esprit.tn", "sheldoncooper", "hypnose207@gmail.com", "", "vous avez reçu une reclamation");

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }
    public List<Reclamation> afficher() throws SQLException{
        List<Reclamation>reclamation=new ArrayList<>();
 
        
        String sql ="Select * from reclamation";
        ste = cnx.prepareCall(sql);
        ResultSet rs;
        rs = ste.executeQuery();
        
        while(rs.next()){
            Reclamation r = new Reclamation();
            r.setId_reclamation(rs.getLong("id_reclamation"));
            r.setUsername_reclamation(rs.getString("username_reclamation"));
            r.setObject_reclamation(rs.getString("object_reclamation"));
            r.setDescription_reclamation(rs.getString("description_reclamation"));
            reclamation.add(r);
        } 
       return reclamation;
    }
    public void supprimer (long id ) throws SQLException{
        
            String sql = "DELETE FROM reclamation WHERE id_reclamation=? ;";
            ste=cnx.prepareStatement(sql);
            ste.setLong(1, id);
            ste.executeUpdate();
            System.out.println("reclamation deleted");
       
        
    }
    public void modifier(long id_reclamation,Reclamation r) throws SQLException{
     
            String sql = "UPDATE reclamation SET  object_reclamation=?, description_reclamation=? WHERE id_reclamation=?";

            ste=cnx.prepareStatement(sql);
            ste.setLong(3, id_reclamation);
            ste.setString(1, r.getObject_reclamation());
            ste.setString(2, r.getDescription_reclamation());

            ste.executeUpdate();
            System.out.println("reclamation updeted");
        }
}
