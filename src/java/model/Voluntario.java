/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ciro
 */
public class Voluntario extends Usuario implements Serializable {
    private String cpf, comentario;
    private int conquistas;
    private List<Causa> causas;
    private List<Habilidade> habilidades;
    
    public Voluntario() {
        
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getConquistas() {
        return conquistas;
    }

    public void setConquistas(int conquistas) {
        this.conquistas = conquistas;
    }

    public List<Causa> getCausas() {
        return causas;
    }

    public void setCausas(List<Causa> causas) {
        this.causas = causas;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}


