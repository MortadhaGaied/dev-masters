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
    private String ref_mt;

    public MoyenTransport() {

    }

    public MoyenTransport(long id, String ref_mt) {
        this.id = id;
        this.ref_mt = ref_mt;
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

    /**
     * @return the ref_mt
     */
    public String getRef_mt() {
        return ref_mt;
    }

    /**
     * @param ref_mt the ref_mt to set
     */
    public void setRef_mt(String ref_mt) {
        this.ref_mt = ref_mt;
    }

}
