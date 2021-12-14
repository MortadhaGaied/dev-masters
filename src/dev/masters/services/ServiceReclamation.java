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

import java.util.logging.Level;
import java.util.logging.Logger;
import dev.masters.utils.JavaMail;


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
        String sql = "insert into reclamation(category_id , username , object , description)"+"values(?,?,?,?)";
            
        ste=cnx.prepareStatement(sql);
        ste.setInt(1, r.getCategory());
        ste.setString(2, r.getUsername_reclamation());
        ste.setString(3, r.getObject_reclamation());
        ste.setString(4, r.getDescription_reclamation());
        System.out.println(sql);
        ste.executeUpdate();
            
        mail.send("benromdhane.ahmed@esprit.tn", "sheldoncooper", "hypnose207@gmail.com", "", "vous avez reçu une reclamation ");


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
            r.setId_reclamation(rs.getLong("id"));
            r.setCategory(rs.getInt("category_id"));
            r.setUsername_reclamation(rs.getString("username"));
            r.setObject_reclamation(rs.getString("object"));
            r.setDescription_reclamation(rs.getString("description"));
            reclamation.add(r);
        } 
       return reclamation;
    }
    public void supprimer (long id ) throws SQLException{
        
            String sql = "DELETE FROM reclamation WHERE id=? ;";
            ste=cnx.prepareStatement(sql);
            ste.setLong(1, id);
            ste.executeUpdate();
            System.out.println("reclamation deleted");
       
        
    }
@Override
    public void modifier(long id_reclamation,Reclamation r) throws SQLException{
     
            String sql = "UPDATE reclamation SET category_id=?, object_reclamation=?, description_reclamation=? WHERE id=?";

            ste=cnx.prepareStatement(sql);
            ste.setInt(1, r.getCategory());
            ste.setLong(4, id_reclamation);
            ste.setString(2, r.getObject_reclamation());
            ste.setString(3, r.getDescription_reclamation());

            ste.executeUpdate();
            System.out.println("reclamation updeted");
        }

    public int nbReclamation(){
        int nbreclamation = 0;
        try {
            ResultSet set = Myconnexion.getInstance().
                    getCnx().prepareStatement("SELECT COUNT(id) FROM reclamation")
                    .executeQuery();
            if (set.next()) {
                nbreclamation = set.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbreclamation;
    }
    

}
