/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mensagem;

/**
 *
 * @author pedro
 */
public class MensagemDAO {
      
    public List<Mensagem> listarMensagens(int id) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarMensagem = "select comunicado, notificacao, usuario_remetente, hora_envio"
                    + " from mensagem where usuario_destinatario = ?";
            stmt1 = connection.prepareStatement(stmtConsultarMensagem);
            stmt1.setInt(1, id);
            rs1 = stmt1.executeQuery();
            List<Mensagem> listMensagem = new ArrayList<Mensagem>();
            while(rs1.next()){           
                 
                Mensagem mensagem = new Mensagem();
                mensagem.setComunicado(rs1.getString("comunicado"));
                mensagem.setNotificacao(rs1.getBoolean("notificacao"));
                mensagem.setUsuario_remetente(rs1.getInt("usuario_remetente"));
                mensagem.setHora_envio(rs1.getTimestamp("hora_envio"));
            }
            rs1.close();
            stmt1.close();
            return listMensagem;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{
                connection.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            };
        }
    }
     
    public void enviarMensagem(int id_remetente, int id_destinatario, String comunicado)
            throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarMensagem = "INSERT INTO mensagem"
                    + "(comunicado, usuario_remetente, usuario_destinatario)"
                    + "VALUES"
                    + "(?, ?, ?)";
            stmt1 = connection.prepareStatement(stmtConsultarMensagem);
            stmt1.setString(1, comunicado);
            stmt1.setInt(2, id_remetente);
            stmt1.setInt(3, id_destinatario);
            stmt1.executeUpdate();
            
            rs1.close();
            stmt1.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{
                connection.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            };
        }
    }
    
    public void updateMensagem(int id_remetente, int id_mensagem)
            throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarMensagem = "UPDATE mensagem set"
                    + "notificacao = false"
                    + "WHERE"
                    + "usuario_destinatario = ?,"
                    + "id_mensagem = ?";
            stmt1 = connection.prepareStatement(stmtConsultarMensagem);
            stmt1.setInt(1, id_remetente);
            stmt1.setInt(2, id_mensagem);
            stmt1.execute();
            
            rs1.close();
            stmt1.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{
                connection.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            };
        }
    }
}
