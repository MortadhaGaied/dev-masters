/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import dev.masters.entites.MoyenDeTransport;
import dev.masters.entites.Position;
import dev.masters.entites.Station;
import dev.masters.entites.Voyage;
import dev.masters.utils.Myconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mouha
 */
public class VoyageService implements IService<Voyage> {

    Connection cnx;
    Statement st;

    public VoyageService() {

        cnx = Myconnexion.getInstance().getCnx();

    }

    /**
     * ****************************** Add Voyage ********************************
     */
    @Override
    public void ajouter(Voyage voyage) {

        //check for existance of the station and the MDP first 
        try {

            String query = "INSERT INTO `voyage`(`station_depart_id`, `station_arrive_id`, `moyen_de_transport_id`,`ref_voyage`, `date_depart`,`date_arrive`) VALUES ('" + voyage.getStation_depart().getId() + "','" + voyage.getStation_arrive().getId()+ "','" + voyage.getMoyen_de_transport().getId_MoyenTransport()+ "','" + voyage.getRef_voyage() + "','" + voyage.getDate_depart() +"','"+voyage.getDate_arrive()+ "')";

            st = cnx.createStatement();

            st.executeUpdate(query);
            System.out.println("voyage ajouté avec succes");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * ***************************getAll Voyages**************************
     */
    @Override
    public List<Voyage> afficher() throws SQLException {
        List<Voyage> voyages = new ArrayList<Voyage>();

        st = cnx.createStatement();

        String query = "SELECT * FROM voyage";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Voyage voyage = new Voyage();
            voyage.setId(rs.getLong("id"));
            voyage.setStation_depart(new Station(rs.getInt("station_depart_id")));
            voyage.setStation_arrive(new Station(rs.getInt("station_arrive_id")));
            voyage.setMoyen_de_transport(new MoyenDeTransport(rs.getInt("moyen_de_transport_id")));
            voyage.setRef_voyage(rs.getString("ref_voyage"));
            voyage.setDate_depart(rs.getTimestamp("date_depart").toLocalDateTime());
            voyage.setDate_arrive(rs.getTimestamp("date_arrive").toLocalDateTime());
            
            voyages.add(voyage);
        }
        return voyages;
    }

    public List<Voyage> afficherArchive() throws SQLException {
        List<Voyage> voyages = new ArrayList<Voyage>();

        st = cnx.createStatement();

        String query = "SELECT * FROM voyage_archive";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Voyage voyage = new Voyage();
            voyage.setId(rs.getLong("id"));
            voyage.setStation_depart(new Station(rs.getInt("station_depart_id")));
            voyage.setStation_arrive(new Station(rs.getInt("station_arrive_id")));
            voyage.setMoyen_de_transport(new MoyenDeTransport(rs.getInt("moyen_de_transport_id")));
            voyage.setRef_voyage(rs.getString("ref_voyage"));
            voyage.setDate_depart(rs.getTimestamp("date_depart").toLocalDateTime());
            voyage.setDate_arrive(rs.getTimestamp("date_arrive").toLocalDateTime());
            
            voyages.add(voyage);
        }
        return voyages;
    }

    @Override
    public void supprimer(long id) throws SQLException {

        String query = "delete from voyage where id=" + id;
        st.executeUpdate(query);
    }

    public void archiver(Voyage voyage) throws SQLException {

        String query = "INSERT INTO `voyage_archive`(`id`,`ref_voyage`, `station_depart`, `moyen_de_transport`,`date_depart`, `date_arrive`, `station_arrive`) VALUES ('" + voyage.getId() + "','" + voyage.getRef_voyage() + "','" + voyage.getStation_depart().getId() + "','" + voyage.getMoyen_de_transport().getId_MoyenTransport()+ "','" + voyage.getDate_depart() + "','" + voyage.getDate_arrive() +"','"+voyage.getStation_arrive().getId()+ "')";
        System.out.println(query);
        st.executeUpdate(query);

        String query2 = "delete from voyage where id=" + voyage.getId();
        st.executeUpdate(query2);

    }

    public void desarchiver(Voyage voyage) throws SQLException {

        String query = "INSERT INTO `voyage`(`station_depart_id`, `station_arrive_id`, `moyen_de_transport_id`,`ref_voyage`, `date_depart`,`date_arrive`) VALUES ('" + voyage.getStation_depart().getId() + "','" + voyage.getStation_arrive().getId()+ "','" + voyage.getMoyen_de_transport().getId_MoyenTransport()+ "','" + voyage.getRef_voyage() + "','" + voyage.getDate_depart() +"','"+voyage.getDate_arrive()+ "')";
        st.executeUpdate(query);

        String query2 = "delete from voyage_archive where id_voyage=" + voyage.getId();
        st.executeUpdate(query2);

    }

    /**
     * ******************** Voyage Search By ID*******************
     */
    public Voyage GetVoyageById(long id_voyage) {

        Voyage voyage = new Voyage();
        String query = "select * from voyage where id= ?";

        try {

            PreparedStatement ps
                    = cnx.prepareStatement(query);

            ps.setLong(1, id_voyage);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                 
            voyage.setId(rs.getLong("id"));
            voyage.setStation_depart(new Station(rs.getInt("station_depart_id")));
            voyage.setStation_arrive(new Station(rs.getInt("station_arrive_id")));
            voyage.setMoyen_de_transport(new MoyenDeTransport(rs.getInt("moyen_de_transport_id")));
            voyage.setRef_voyage(rs.getString("ref_voyage"));
            voyage.setDate_depart(rs.getTimestamp("date_depart").toLocalDateTime());
            voyage.setDate_arrive(rs.getTimestamp("date_arrive").toLocalDateTime());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return voyage;

    }

    /**
     * ***************************update Voyage***************************
     */
    @Override
    public void modifier(long id, Voyage updatedVoyage) throws SQLException {

        //check the avalability of the stations and the MDT(moyen de transport) if they exist 
        String query = "update voyage set `station_depart_id`= '" + updatedVoyage.getStation_depart().getId()+ "', `station_arrive_id`= '" + updatedVoyage.getStation_arrive().getId()+ "', `moyen_de_transport_id`= '" + updatedVoyage.getMoyen_de_transport().getId_MoyenTransport() + "', `ref_voyage`= '" + updatedVoyage.getRef_voyage() + "', `date_depart`= '" + updatedVoyage.getDate_depart() +"', `date_arrive`= '"+updatedVoyage.getDate_arrive()+ "'where `id` = '" + id + "'";

        st.executeUpdate(query);

    }

    public List<Voyage> getVoyagesByRefStation(int idStation) {

        List<Voyage> voyages = new ArrayList<Voyage>();
        String query = "select * from voyage where station_depart_id= ?";

        try {

            PreparedStatement ps
                    = cnx.prepareStatement(query);

            ps.setInt(1, idStation);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Voyage voyage = new Voyage();
            voyage.setId(rs.getLong("id"));
            voyage.setStation_depart(new Station(rs.getInt("station_depart_id")));
            voyage.setStation_arrive(new Station(rs.getInt("station_arrive_id")));
            voyage.setMoyen_de_transport(new MoyenDeTransport(rs.getInt("moyen_de_transport_id")));
            voyage.setRef_voyage(rs.getString("ref_voyage"));
            voyage.setDate_depart(rs.getTimestamp("date_depart").toLocalDateTime());
            voyage.setDate_arrive(rs.getTimestamp("date_arrive").toLocalDateTime());
            
            voyages.add(voyage);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return voyages;

    }

    public List<Voyage> getVoyagesByDate(LocalDateTime date) {

        List<Voyage> voyages = new ArrayList<Voyage>();
        String query = "select * from voyage where date_depart= ?";

        try {

            PreparedStatement ps
                    = cnx.prepareStatement(query);

            ps.setDate(1, java.sql.Date.valueOf(date.toLocalDate()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               Voyage voyage = new Voyage();
            voyage.setId(rs.getLong("id"));
            voyage.setStation_depart(new Station(rs.getInt("station_depart_id")));
            voyage.setStation_arrive(new Station(rs.getInt("station_arrive_id")));
            voyage.setMoyen_de_transport(new MoyenDeTransport(rs.getInt("moyen_de_transport_id")));
            voyage.setRef_voyage(rs.getString("ref_voyage"));
            voyage.setDate_depart(rs.getTimestamp("date_depart").toLocalDateTime());
            voyage.setDate_arrive(rs.getTimestamp("date_arrive").toLocalDateTime());
            
            voyages.add(voyage);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return voyages;

    }

    public List<Voyage> getVoyagesByMt(long refMt) {

        List<Voyage> voyages = new ArrayList<Voyage>();
        String query = "select * from voyage where moyen_transport_id= ?";

        try {

            PreparedStatement ps
                    = cnx.prepareStatement(query);

            ps.setLong(1, refMt);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Voyage voyage = new Voyage();
            voyage.setId(rs.getLong("id"));
            voyage.setStation_depart(new Station(rs.getInt("station_depart_id")));
            voyage.setStation_arrive(new Station(rs.getInt("station_arrive_id")));
            voyage.setMoyen_de_transport(new MoyenDeTransport(rs.getInt("moyen_de_transport_id")));
            voyage.setRef_voyage(rs.getString("ref_voyage"));
            voyage.setDate_depart(rs.getTimestamp("date_depart").toLocalDateTime());
            voyage.setDate_arrive(rs.getTimestamp("date_arrive").toLocalDateTime());
            
            voyages.add(voyage);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return voyages;

    }

    public HashMap<String, Double> StatistiquesParStation() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT station_depart_id, COUNT(*) as nb FROM voyage GROUP BY station_depart_id;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("station_depart_id");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
    }

}
