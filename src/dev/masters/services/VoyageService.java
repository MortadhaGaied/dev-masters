/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import dev.masters.entites.MoyenTransport;
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
     * ****************************** Add Voyage
     * ********************************
     */
    @Override
    public void ajouter(Voyage voyage) {

        //check for existance of the station and the MDP first 
        try {

            String query = "INSERT INTO `voyage`(`position_depart`, `position_arrive`, `date_voyage`,`station`, `moyen_transport`) VALUES ('" + voyage.getPosition_depart().getGoogleMapsPostionFormat() + "','" + voyage.getPosition_arrive().getGoogleMapsPostionFormat() + "','" + voyage.getDate_de_voyage() + "','" + voyage.getStation().getRefStation() + "','" + voyage.getMoyen_transport().getRef_mt() + "')";

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
            voyage.setId(rs.getInt("id_voyage"));
            voyage.setPosition_depart(new Position(rs.getString("position_depart").split(",")[0], rs.getString("position_depart").split(",")[1].trim()));
            voyage.setPosition_arrive(new Position(rs.getString("position_arrive").split(",")[0], rs.getString("position_arrive").split(",")[1].trim()));
            voyage.setDate_de_voyage(rs.getTimestamp(4).toLocalDateTime());

            //get the station that contains the specific id 
            voyage.setStation(new Station());
            voyage.getStation().setRefStation(rs.getString("station"));
            voyage.setMoyen_transport(new MoyenTransport());
            voyage.getMoyen_transport().setRef_mt(rs.getString("moyen_transport"));
            voyages.add(voyage);
        }
        return voyages;
    }

    /**
     * ***************************update Voyage***************************
     */
    @Override
    public void modifier(int id, Voyage updatedVoyage) throws SQLException {
        st = cnx.createStatement();
        //check the avalability of the stations and the MDT(moyen de transport) if they exist 

        String query = "update voyage set `position_depart`= '" + updatedVoyage.getPosition_depart().getGoogleMapsPostionFormat() + "', `position_arrive`= '" + updatedVoyage.getPosition_arrive().getGoogleMapsPostionFormat() + "', `date_voyage`= '" + java.sql.Date.valueOf(updatedVoyage.getDate_de_voyage().toLocalDate()) + "', `station`= '" + updatedVoyage.getStation().getRefStation() + "', `moyen_transport`= '" + updatedVoyage.getMoyen_transport().getRef_mt() + "'where `id_voyage` = '" + id + "'";

        st.executeUpdate(query);

    }

    /**
     * **********************DELETE VOYAGE********************
     */
    @Override
    public void supprimer(Long id) throws SQLException {

        String query = "delete from voyag where id=" + id;
        st.executeUpdate(query);
    }

    /**
     * ******************** Voyage Search By ID*******************
     */
    public Voyage GetVoyageById(Long id_voyage) {

        Voyage voyage = new Voyage();
        String query = "select * from voyage where id_voyage= ?";

        try {

            PreparedStatement ps
                    = cnx.prepareStatement(query);

            ps.setLong(1, id_voyage);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                voyage.setId(rs.getLong("id_voyage"));
                voyage.setPosition_depart(new Position(rs.getString("position_depart").split(",")[0], rs.getString("position_depart").split(",")[1].trim()));
                voyage.setPosition_arrive(new Position(rs.getString("position_arrive").split(",")[0], rs.getString("position_arrive").split(",")[1].trim()));
                voyage.setDate_de_voyage(rs.getTimestamp(4).toLocalDateTime());

                //get the station that contains the specific id 
                voyage.setStation(new Station());
                voyage.getStation().setRefStation(rs.getString("station"));
                voyage.setMoyen_transport(new MoyenTransport());
                voyage.getMoyen_transport().setRef_mt(rs.getString("moyen_transport"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return voyage;

    }

    /**
     * *****************GET VOYAGE BY REF STATION*********************
     */
    //retourne une list de voyage qui partage la meme station
    public List<Voyage> getVoyagesByRefStation(String refStation) {

        List<Voyage> voyages = new ArrayList<Voyage>();
        String query = "select * from voyage where station= ?";

        try {

            PreparedStatement ps
                    = cnx.prepareStatement(query);

            ps.setString(1, refStation);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Voyage voyage = new Voyage();
                voyage.setId(rs.getLong("id_voyage"));
                voyage.setPosition_depart(new Position(rs.getString("position_depart").split(",")[0], rs.getString("position_depart").split(",")[1].trim()));
                voyage.setPosition_arrive(new Position(rs.getString("position_arrive").split(",")[0], rs.getString("position_arrive").split(",")[1].trim()));
                voyage.setDate_de_voyage(rs.getTimestamp(4).toLocalDateTime());

                //get the station that contains the specific id 
                voyage.setStation(new Station());
                voyage.getStation().setRefStation(rs.getString("station"));
                voyage.setMoyen_transport(new MoyenTransport());
                voyage.getMoyen_transport().setRef_mt(rs.getString("moyen_transport"));
                voyages.add(voyage);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return voyages;

    }

    /**
     * *******************GET VOYAGES BY DATE ***********************
     */

    //retourn une liste des voyages qui partage le meme date
    public List<Voyage> getVoyagesByDate(LocalDateTime date) {

        List<Voyage> voyages = new ArrayList<Voyage>();
        String query = "select * from voyage where date_voyage= ?";

        try {

            PreparedStatement ps
                    = cnx.prepareStatement(query);

            ps.setDate(1, java.sql.Date.valueOf(date.toLocalDate()));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Voyage voyage = new Voyage();
                voyage.setId(rs.getLong("id_voyage"));
                voyage.setPosition_depart(new Position(rs.getString("position_depart").split(",")[0], rs.getString("position_depart").split(",")[1].trim()));
                voyage.setPosition_arrive(new Position(rs.getString("position_arrive").split(",")[0], rs.getString("position_arrive").split(",")[1].trim()));
                voyage.setDate_de_voyage(rs.getTimestamp(4).toLocalDateTime());

                //get the station that contains the specific id 
                voyage.setStation(new Station());
                voyage.getStation().setRefStation(rs.getString("station"));
                voyage.setMoyen_transport(new MoyenTransport());
                voyage.getMoyen_transport().setRef_mt(rs.getString("moyen_transport"));
                voyages.add(voyage);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return voyages;

    }

}
