/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.GUI.Controles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class InterfaceBienvenueMoyenTransportController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EntrerInterfaceMoyenTransport(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
          System.exit(0);
    }

    @FXML
    private void BackToMenu(MouseEvent event) {
    }

    
}
