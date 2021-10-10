
package dev.masters.services;

import dev.masters.entites.MoyenDeTransport;
import dev.masters.utils.Myconnexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ServiceMoyenDeTransport implements MTService <MoyenDeTransport> {

        Connection cnx;
        public ServiceMoyenDeTransport (){
        cnx = Myconnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(MoyenDeTransport moyenTransport) {
               Statement st;
        try {
            st = cnx.createStatement();
            String query ="INSERT INTO `moyentransport`(`Nom_Station`, `Num_ligne`, `Nombre_station`, `Accessible_au_handicapes`) VALUES ('"+moyenTransport.getNom_Station()+"','"+moyenTransport.getNum_ligne()+"','"+moyenTransport.getNombre_Station()+"','"+moyenTransport.getAccessible_au_handicapes();
      
        st.executeUpdate(query);
        
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public List<MoyenDeTransport> afficher() throws SQLException {
                Statement stm = cnx.createStatement();
        List<MoyenDeTransport> MT = new ArrayList<>();
    
    String query = "SELECT * FROM moyentransport";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
          MoyenDeTransport moyenTransport = new MoyenDeTransport();
            moyenTransport.setId_MoyenTransport(rs.getInt("Id_MoyenTransport"));
            moyenTransport.setNom_Station(rs.getString("Nom_Station"));
            moyenTransport.setNum_ligne(rs.getInt("Num_ligne"));
            moyenTransport.setNombre_Station(rs.getInt("Nombre_Station"));
            moyenTransport.setAccessible_au_handicapes(rs.getString("Accessible_au_handicapes"));
            MT.add(moyenTransport);
        }
        return MT;
    }

    @Override
    public void supprimer(Long id_MoyenTransport) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from user where Id_MoyenTransport="+id_MoyenTransport;
        stm.executeUpdate(query);
    }

        public MoyenDeTransport SearchById(Long id_MoyenTransport) throws SQLException{
        Statement stm = cnx.createStatement();
        MoyenDeTransport moyenTransport =new MoyenDeTransport();
        String query = "select * from user where id_user="+id_MoyenTransport;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
            moyenTransport.setId_MoyenTransport(rs.getInt("id_user"));
            moyenTransport.setNom_Station(rs.getString("Nom_Station"));
            moyenTransport.setNum_ligne(rs.getInt("Num_ligne"));
            moyenTransport.setNombre_Station(rs.getInt("Nombre_Station"));
            moyenTransport.setNom_Station(rs.getString("Accessible_au_handicapes"));
        }
        return moyenTransport;
    }
            
    @Override
    public void modifier(Long id_MoyenTransport, MoyenDeTransport MoyenTransport_modifier) throws SQLException {
        Statement stm = cnx.createStatement();
        MoyenDeTransport moyenTransport = SearchById(id_MoyenTransport);
        String query = "UPDATE `user` SET `Nom_Station`='"+MoyenTransport_modifier.getNom_Station()+"',`Num_ligne`='"+MoyenTransport_modifier.getNum_ligne()+"',`Nombre_Station`='"+MoyenTransport_modifier.getNombre_Station()+"',`Accessible_au_handicapes`='"+MoyenTransport_modifier.getAccessible_au_handicapes();
        stm.executeUpdate(query);
    }

}
