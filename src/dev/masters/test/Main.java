

package dev.masters.test;

import dev.masters.entites.Voyage;
import dev.masters.services.ServiceUser;
import dev.masters.services.VoyageService;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mortadha
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("dev/masters/MoyenTransport/Gui/InterfaceMenuMoyenTransport.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
      @param args the command line arguments
     */
    public static void main(String[] args) {
       // TODO code application logic here
        
        
        
       
        
        
        
        
        VoyageService vs = new VoyageService();
        ServiceUser su = new ServiceUser();
        
        
       // System.out.println("voyage by id :  \n");
        // System.out.println(vs.GetVoyageById(17L));
         //System.out.println("recherche avancee by date");
         //System.out.println(vs.getVoyagesByRefStation("13b"));
         //System.out.println(vs.getVoyagesByDate(LocalDateTime.now()));
         
        try {
            //vs.modifier(17, voyage);
           // vs.archiver(voyage);
            //vs.desarchiver(voyage);
            System.out.println(vs.afficher());
            System.out.println(su.afficher());
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       
    }

    

}
  

