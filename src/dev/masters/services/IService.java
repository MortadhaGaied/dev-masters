/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mahdi
 */
public interface IService <T>{
        public void ajouter(T t);

        public List<T> afficher() throws SQLException;

        public void supprimer(long id) throws SQLException;

        public void modifier(long id, T t) throws SQLException;
}
