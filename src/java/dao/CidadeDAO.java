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
import model.Cidade;
import model.UF;

/**
 *
 * @author Avell B155 FIRE
 */
public class CidadeDAO {

    public List<Cidade> listaCidades(String uf) {
        Connection connection = null;
        List<Cidade> cidades = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String query =  "select c.cidade, c.descricao cidade_descr, uf.uf, uf.descricao uf_descr\n" +
                            "from cidade c\n" +
                            "join uf on uf.uf = c.uf\n" +
                            "where c.uf = ? \n" +
                            "order by c.descricao";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, uf);
            rs = stmt.executeQuery();
            while(rs.next()){
                UF estado = new UF();
                estado.setSigla(rs.getString("uf"));
                estado.setDescricao(rs.getString("uf_descr"));
                Cidade cidade = new Cidade();
                cidade.setUf(estado);
                cidade.setId(rs.getInt("cidade"));
                cidade.setDescricao(rs.getString("cidade_descr"));
                cidades.add(cidade);
            } 
            return cidades;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{
                rs.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());
            };
            try{
                stmt.close();
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
