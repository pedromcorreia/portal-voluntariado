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
import model.Habilidade;

/**
 *
 * @author Avell B155 FIRE
 */
public class HabilidadeDAO {
    public List<Habilidade> listaHabilidades() throws SQLException, IOException{
        ResultSet rs = null;
        Connection connection = new ConnectionFactorySemProperties().getConnection();
        PreparedStatement stmtLista = 
        connection.prepareStatement("select habilidade, descricao from habilidade order by descricao");
        try {
            rs = stmtLista.executeQuery();
            List<Habilidade> habilidades = new ArrayList<Habilidade>();
            while(rs.next()){
                Habilidade habilidade = new Habilidade();
                habilidade.setId(rs.getInt("habilidade"));
                habilidade.setDescricao(rs.getString("descricao"));
                habilidades.add(habilidade);
            }
            rs.close();
            return habilidades;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            connection.close();
        }
    }
}
