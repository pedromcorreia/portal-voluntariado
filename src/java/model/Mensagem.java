/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pedro
 */
public class Mensagem {
    private int usuario_remetente, usuario_destinatario;
    private String comunicado;

    public String getComunicado() {
        return comunicado;
    }

    public void setComunicado(String comunicado) {
        this.comunicado = comunicado;
    }
    private boolean notificacao;


    public int getUsuario_remetente() {
        return usuario_remetente;
    }

    public void setUsuario_remetente(int usuario_remetente) {
        this.usuario_remetente = usuario_remetente;
    }

    public int getUsuario_destinatario() {
        return usuario_destinatario;
    }

    public void setUsuario_destinatario(int usuario_destinatario) {
        this.usuario_destinatario = usuario_destinatario;
    }

    public boolean isNotificacao() {
        return notificacao;
    }

    public void setNotificacao(boolean notificacao) {
        this.notificacao = notificacao;
    }
}