/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.masters.entites;

/**
 *
 * @author mouha
 */
public class MoyenTransport {
    
    
  private long id;
  
  public MoyenTransport(long id){
      this.id=id;
  }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
  
  
}
