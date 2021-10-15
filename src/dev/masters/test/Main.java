/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.test;

import dev.masters.entites.AbonnementDisponible;
import dev.masters.services.ServiceAbonnementDisponible;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mortadha
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        ServiceAbonnementDisponible sad = new ServiceAbonnementDisponible();
        AbonnementDisponible res = sad.SearchById(4);
        System.out.println(res.toString());

        List<AbonnementDisponible> list = sad.rechercheParTypeOuDesc("train");
        for (AbonnementDisponible item : list) {
            System.out.println(item.getDesc_abonnement() + ' ' + item.getType_abonnement() + ' ' + item.getPrix_moins_abonnement());
        }

    }

}
