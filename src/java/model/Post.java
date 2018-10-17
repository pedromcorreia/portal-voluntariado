/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Ciro
 */
public class Post implements Serializable {
    private int id, qtdeCurtidas, qtdeComentarios;
    private String descricao, imagem;
    private Calendar data;
    private Usuario usuario;
    private List<Comentario> comentarios;
    private Oportunidade oportunidade;
    
    public Post() {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    public int getQtdeCurtidas() {
        return qtdeCurtidas;
    }

    public void setQtdeCurtidas(int qtdeCurtidas) {
        this.qtdeCurtidas = qtdeCurtidas;
    }

    public int getQtdeComentarios() {
        return qtdeComentarios;
    }

    public void setQtdeComentarios(int qtdeComentarios) {
        this.qtdeComentarios = qtdeComentarios;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(Oportunidade oportunidade) {
        this.oportunidade = oportunidade;
    }
    
    
    
}
