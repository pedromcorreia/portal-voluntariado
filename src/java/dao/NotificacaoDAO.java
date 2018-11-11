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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Notificacao;

/**
 *
 * @author pedro
 */
public class NotificacaoDAO {
      
    public List<Notificacao> listarNotificacoes(int id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarMensagem = "select flag, hora_envio"
                    + " from notificacao where usuario_destinatario = ?";
            stmt1 = connection.prepareStatement(stmtConsultarMensagem);
            stmt1.setInt(1, id);
            rs1 = stmt1.executeQuery();
            List<Notificacao> listNotificacao = new ArrayList<Notificacao>();
            while(rs1.next()){           
                 
                Notificacao notificacao = new Notificacao();
                notificacao.setFlag(rs1.getString("flag"));
                notificacao.setHora_envio(rs1.getTimestamp("hora_envio"));
            }
            rs1.close();
            stmt1.close();
            return listNotificacao;
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
     
    public void inserirNotificacao(String flag, int id_destinatario)
            throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmt = "INSERT INTO notificacao"
                    + "(flag, usuario_destinatario)"
                    + "VALUES"
                    + "(?, ?)";
            stmt1 = connection.prepareStatement(stmt);
            stmt1.setString(1, flag);
            stmt1.setInt(2, id_destinatario);
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
    
    public void deletarNotificacao(int id)
            throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarNotificacao = "UPDATE notificacao set"
                    + "notificacao = false"
                    + "WHERE"
                    + "usuario_destinatario = ?";
            stmt1 = connection.prepareStatement(stmtConsultarNotificacao);
            stmt1.setInt(1, id);
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
