/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author pedro
 */
public class Notificacao {
    private String flag;    
    private Timestamp hora_envio;

    public Timestamp getHora_envio() {
        return hora_envio;
    }

    public void setHora_envio(Timestamp hora_envio) {
        this.hora_envio = hora_envio;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
