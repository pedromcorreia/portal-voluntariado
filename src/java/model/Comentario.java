/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Ciro
 */
public class Comentario implements Serializable {
    private int id;
    private String descricao;
    private Calendar data;
    private Usuario usuario;
    private Post postPai;
    
    public Comentario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }   

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Post getPostPai() {
        return postPai;
    }

    public void setPostPai(Post postPai) {
        this.postPai = postPai;
    }
    
    
}
