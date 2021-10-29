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
public class Position {
    private String longitude ;
    private String latitude;

    
    
    
    
    public Position(){
        
    }

    @Override
    public String toString() {
        return this.latitude+", "+this.longitude;
    }
    
    
    
    public Position(String longitude,String latitude){
        this.longitude=longitude;
        this.latitude=latitude;
        
    }
    
    
    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    
    
    public String getGoogleMapsPostionFormat(){
        return this.latitude+", "+this.longitude;
    }
    
    
}