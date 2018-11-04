/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import facade.Facade;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import facade.FacadePost;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Instituicao;
import model.Oportunidade;
import model.Post;

/**
 *
 * @author Ciro
 */
public class OportunidadeDAO {
        
    public Oportunidade consultarOportunidade(Post p) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        Calendar cal = Calendar.getInstance();
        try{
            String stmtConsultarPost = "select u.foto, i.razao, p.descricao, o.*  \n" +
                                        "from post p\n" +
                                        "join usuario u on u.usuario = p.usuario\n" +
                                        "join instituicao i on i.usuario = u.usuario\n" +
                                        "join oportunidade o on o.post = p.post\n" +
                                        "where o.post = ?";
            connection = new ConnectionFactorySemProperties().getConnection();
            stmt1 = connection.prepareStatement(stmtConsultarPost);
            stmt1.setInt(1, p.getId());
            rs1 = stmt1.executeQuery();
            Oportunidade o = new Oportunidade();
            if(rs1.next()){
                o.setId(rs1.getInt("oportunidade"));
                o.setVagasTotal(rs1.getInt("vagas"));
                o.setPresencial(rs1.getString("presencial").charAt(0));
                cal.setTime(rs1.getDate("inicio"));
                o.setInicio(cal);
                cal.setTime(rs1.getDate("termino"));
                o.setTermino(cal);
                o.setCargaHorariaTotal(rs1.getInt("cargahoraria"));
                o.setStatus(rs1.getString("status").charAt(0));
                Post post = new Post();
                post = FacadePost.consultarPost(p);
                Instituicao ins = new Instituicao();
                ins = Facade.consultarInstituicao(post.getUsuario().getUsuario());
                o.setInstituicao(ins);
                o.setPostPai(post);
            }
            return o;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar oportunidade no banco de dados. Origem="+ex.getMessage());
        } finally{
            /*try{
                rs1.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());
            }
            try{
                stmt1.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
            }
            try{
                connection.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            }*/
        }
    }

    public List<Oportunidade> pesquisarOportunidades(String vol_nome, String uf_sigla, Integer cidade_id, Integer causa_id, Integer habilidade_id, String presencial) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Oportunidade> oportunidades = new ArrayList<>();
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String query = 
            "select p.post\n" +
            "from post p\n" +
            "join usuario u on u.usuario = p.usuario\n" +
            "join instituicao i on i.usuario = u.usuario\n" +
            "join endereco e on e.usuario = p.usuario\n" +
            "join cidade c on c.cidade = e.cidade\n" +
            "join oportunidade o on o.post = p.post\n" +
            "left join oportunidadehabilidade oh on oh.oportunidade = o.oportunidade\n" +
            "where 1=1\n";
            
            if(vol_nome != null && !vol_nome.isEmpty()){
                query += "and (upper(p.descricao) like upper(?) or upper(i.razao) like upper(?))\n";
            }
            if(cidade_id != null){
                query += "and e.cidade = ?\n";
            }
            if(uf_sigla != null && !uf_sigla.isEmpty()){
                query += "and c.uf = ?\n";
            }
            if(causa_id != null){
                query += "and i.causa = ?\n";
            }
            if(habilidade_id != null){
                query += "and oh.habilidade = ?\n";
            }
            
            query += "and o.presencial = ?\n";
            
            query += "group by p.post";
            
            System.out.println("query = " + query);
            
            stmt = connection.prepareStatement(query);
            Integer i = 0;
            if(vol_nome != null && !vol_nome.isEmpty()){
                i++;
                stmt.setString(i, '%' + vol_nome + '%');
                i++;
                stmt.setString(i, '%' + vol_nome + '%');
            }
            if(cidade_id != null){
                i++;
                stmt.setInt(i, cidade_id);
            }
            if(uf_sigla != null && !uf_sigla.isEmpty()){
                i++;
                stmt.setString(i, uf_sigla);
            }
            if(causa_id != null){
                i++;
                stmt.setInt(i, causa_id);
            }
            if(habilidade_id != null){
                i++;
                stmt.setInt(i, habilidade_id);
            }
            if(presencial != null && !presencial.isEmpty()){
                i++;
                stmt.setString(i, presencial);
            }
            else{
                i++;
                stmt.setString(i, "N");
            }
            rs = stmt.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setId(rs.getInt("post"));
                Oportunidade op = consultarOportunidade(post);           
                oportunidades.add(op);
            } 
            return oportunidades;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
            /*try{
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
            };*/
        }
    }

    public Oportunidade inserirOportunidade(Oportunidade op) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        Calendar cal;
        String query = "insert into oportunidade (post, vagas, presencial, inicio, termino, cargahoraria, status) values(?, ?, ?, ?, ?, ?, ?)";
        connection = new ConnectionFactorySemProperties().getConnection();

        try {
            stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, op.getPostPai().getId());
            stmt.setInt(2, op.getVagasTotal());
            stmt.setString(3, String.valueOf(op.getPresencial()));
            cal = op.getInicio();
            stmt.setTimestamp(4, new java.sql.Timestamp(cal.getTimeInMillis()));
            cal = op.getTermino();
            stmt.setTimestamp(5, new java.sql.Timestamp(cal.getTimeInMillis()));
            stmt.setInt(6, op.getCargaHorariaTotal());
            stmt.setString(7, String.valueOf(op.getStatus()));
            stmt.execute();

            try (ResultSet pk = stmt.getGeneratedKeys()){
                if (pk.next()) {
                    op.setId(pk.getInt(1));
                }
                else {
                    throw new SQLException("A inserção da oportunidade falhou, ID não obtida.");
                }
            }
            return op;
        }
        catch (SQLException ex){
            throw new RuntimeException("Erro ao inserir post no banco de dados. Origem="+ex.getMessage());
        }
        finally{
            try{
                stmt.close();
            } 
            catch (SQLException ex){
                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
            }
            try{
                connection.close();
            } 
            catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            }
        }
    }

    public void excluirOportunidade(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        String query = "";

        try {
            connection = new ConnectionFactorySemProperties().getConnection();
            
            query = "delete from oportunidadehabilidade where oportunidade in (select oportunidade from oportunidade where post = ?)";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            
            query = "delete from oportunidade where post = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
        } 
        catch (SQLException ex){
            throw new RuntimeException("Erro ao inserir post no banco de dados. Origem="+ex.getMessage());
        }
        finally{
            try{
                stmt.close();
            } 
            catch (SQLException ex){
                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
            }
            try{
                connection.close();
            } 
            catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            }
        }
    }

}
