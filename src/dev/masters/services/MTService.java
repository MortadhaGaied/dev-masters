
package dev.masters.services;

import java.sql.SQLException;
import java.util.List;


public interface MTService<MT> {
            
    public void ajouter(MT MoyenTransport);
    public List<MT> afficher()throws SQLException;
    public void supprimer(Long id_MoyenTransport ) throws SQLException;
    public void modifier(Long id_MoyenTransport ,MT MoyenTransport_modifier) throws SQLException;
}
