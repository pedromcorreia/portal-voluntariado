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
import model.Amizade;

/**
 *
 * @author Avell B155 FIRE
 */
public class AmizadeDAO {
     public List<Amizade> listarAmizades(int id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarAmizade = "select nome_amigos"
                    + " from amizade where usuario_destinatario = ?";
            stmt1 = connection.prepareStatement(stmtConsultarAmizade);
            stmt1.setInt(1, id);
            rs1 = stmt1.executeQuery();
            List<Amizade> listAmigos = new ArrayList<Amizade>();
            while(rs1.next()){           
                 
                Amizade amizade = new Amizade();
                amizade.setNome_amigo(rs1.getString("nome_amigos"));
            }
            rs1.close();
            stmt1.close();
            return listAmigos;
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
     
    public void inserirAmigo(String nome_amigo, int id)
            throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmt = "INSERT INTO amizade"
                    + "(nome_amigo, id)"
                    + "VALUES"
                    + "(?, ?)";
            stmt1 = connection.prepareStatement(stmt);
            stmt1.setString(1, nome_amigo);
            stmt1.setInt(2, id);
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
    
    public void deletarAmigo(int id, String nome_amigo)
            throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarNotificacao = "UPDATE amizade set"
                    + "amizade = false"
                    + "WHERE"
                    + "id = ?"
                    + "and nome_amigo = ?";
            stmt1 = connection.prepareStatement(stmtConsultarNotificacao);
            stmt1.setInt(1, id);
            stmt1.setString(2, nome_amigo);
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
