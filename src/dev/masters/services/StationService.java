package dev.masters.services;


import dev.masters.entites.MoyenDeTransport;
import dev.masters.entites.Position;
import dev.masters.entites.Station;
import dev.masters.entites.Voyage;
import dev.masters.services.IService;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mouha
 */
public class StationService implements IService{

     Connection cnx;
     Statement st;
    public StationService(){
         cnx = Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List afficher() throws SQLException {
        List<Station> stations = new ArrayList<Station>();
        
        st = cnx.createStatement();
        
        String query = "SELECT * FROM station";
         ResultSet rs= st.executeQuery(query);
        while (rs.next()){
            Station station = new Station();
            station.setId(rs.getInt("id"));
            station.setRef_station(rs.getString("ref_station"));
            station.setPosition_station(new Position(rs.getString("position_station").split(",")[0],rs.getString("position_station").split(",")[1]));
            station.setNom_station(rs.getString("nom_station"));
            
            
            stations.add(station);
        }
        return stations;
    }

    @Override
    public void supprimer(long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(long id, Object t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
