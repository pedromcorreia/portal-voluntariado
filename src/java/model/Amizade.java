/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author Avell B155 FIRE
 */
public class Amizade {
    //Usuario uSolicitante;
    //Usuario uSolicitado;
    Usuario amigo;
    private String nome_amigo;

    public String getNome_amigo() {
        return nome_amigo;
    }

    public void setNome_amigo(String nome_amigo) {
        this.nome_amigo = nome_amigo;
    }
    Calendar data;
    String status;

    public Amizade() {
    }

    public Usuario getAmigo() {
        return amigo;
    }

    public void setAmigo(Usuario amigo) {
        this.amigo = amigo;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
