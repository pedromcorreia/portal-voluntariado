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
import model.Causa;

/**
 *
 * @author Avell B155 FIRE
 */
public class CausaDAO {
    
    public Causa consultarCausa(int id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarVoluntario =    "select causa, descricao, img from causa where causa = ?";
            stmt1 = connection.prepareStatement(stmtConsultarVoluntario);
            stmt1.setInt(1, id);
            rs1 = stmt1.executeQuery();
            Causa causa = new Causa();
            if(rs1.next()){
                causa.setId(rs1.getInt("causa"));
                causa.setImg(rs1.getString("img"));
                causa.setDescricao(rs1.getString("descricao"));
            }
            rs1.close();
            stmt1.close();
            return causa;
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
    
    public List<Causa> listaCausas() throws SQLException, IOException{
        ResultSet rs = null;
        Connection connection = new ConnectionFactorySemProperties().getConnection();
        PreparedStatement stmtLista = 
        connection.prepareStatement("select causa, descricao from causa order by descricao");
        try {
            rs = stmtLista.executeQuery();
            List<Causa> causas = new ArrayList<Causa>();
            while(rs.next()){
                Causa causa = new Causa();
                causa.setId(rs.getInt("causa"));
                causa.setDescricao(rs.getString("descricao"));
                causas.add(causa);
            }
            rs.close();
            return causas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            connection.close();
        }
    }
}
