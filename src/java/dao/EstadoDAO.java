/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UF;

/**
 *
 * @author Avell B155 FIRE
 */
public class EstadoDAO {

    public List<UF> listaEstados() {
        Connection connection = null;
        List<UF> estados = new ArrayList<>();
        ResultSet rs1 = null;
        PreparedStatement stmt1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmt = "select uf, descricao from uf order by uf";
            stmt1 = connection.prepareStatement(stmt);
            rs1 = stmt1.executeQuery();
            while(rs1.next()){
                UF uf = new UF();
                uf.setSigla(rs1.getString("uf"));
                uf.setDescricao(rs1.getString("descricao"));
                estados.add(uf);
            } 
            return estados;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{
                rs1.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());
            };
            try{
                stmt1.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
            };
            try{
                connection.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            };
        }
    }
    
}
