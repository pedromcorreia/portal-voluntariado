/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Ciro
 */
public class ConfigBean implements Serializable{
    private String administrador;
    
    public ConfigBean(String administrador) {
        this.administrador = administrador;
    }

    public ConfigBean() {
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}
