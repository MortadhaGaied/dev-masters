/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.test;

/**
 *
 * @author Mortadha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Position posisiton_depart=new Position("123","13154");
        Position position_arrive=new Position("123","1315");
        Station station = new Station("sousse", "123465,123465", "13b");
        MoyenTransport mt = new MoyenTransport(1,"123abc");
        
        Voyage voyage = new Voyage(posisiton_depart, position_arrive, LocalDateTime.now(), station, mt);
        
        
        VoyageService vs = new VoyageService();
        //vs.ajouter(voyage);
        System.out.println("voyage by id :  \n");
         //System.out.println(vs.GetVoyageById(14L));
         System.out.println("recherche avancee by date");
         //System.out.println(vs.getVoyagesByRefStation("13b"));
         System.out.println(vs.getVoyagesByDate(LocalDateTime.now()));
         /*
        try {
            vs.modifier(17, voyage);
            
            for(Voyage v:vs.afficher()){
                System.out.println( v.toString());
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       */
    }
    
}
