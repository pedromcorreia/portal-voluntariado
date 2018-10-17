/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comentario;
import facade.Facade;
import facade.FacadePost;
import model.Instituicao;
import model.Post;
import model.Usuario;
import model.Voluntario;

/**
 *
 * @author Ciro
 */
public class ComentarioDAO {
    
    public Comentario inserirComentario(Comentario c) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        Comentario comentario = new Comentario();
        String stmtInserirPost = "INSERT INTO post (usuario, postpai, descricao, data) values (?, ?, ?, ?)";
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            
            stmt1 = connection.prepareStatement(stmtInserirPost, Statement.RETURN_GENERATED_KEYS);
            stmt1.setInt(1, c.getUsuario().getUsuario());
            stmt1.setInt(2, c.getPostPai().getId());
            stmt1.setString(3, c.getDescricao());
            Calendar cal = c.getData();
            stmt1.setTimestamp(4, new java.sql.Timestamp(cal.getTimeInMillis()));

            stmt1.execute();

            try (ResultSet pk = stmt1.getGeneratedKeys()){
                if (pk.next()) {
                    comentario.setId(pk.getInt(1));
                }
                else {
                    throw new SQLException("A inserção do usuário falhou, ID não obtida.");
                }
            }
            return comentario;
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir comentario no banco de dados. Origem="+ex.getMessage());
        } 
        finally{
            try{
                stmt1.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
            }
            try{
                connection.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            }
        }
    }        
    
    public List<Comentario> consultarComentarioPost(int id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            String stmtListarPosts = "select c.*, coalesce(v.nome, i.razao) nome, u.foto from post c\n" +
                                        "inner join post p\n" +
                                        "on c.postpai = p.post\n" +
                                        "inner join usuario u\n" +
                                        "on c.usuario = u.usuario\n" +
                                        "left join voluntario v\n" +
                                        "on u.usuario = v.usuario\n" +
                                        "left join instituicao i\n" +
                                        "on u.usuario = i.usuario\n" +
                                        "where c.postpai = ?\n" + 
                                        "order by c.data desc";
            connection = new ConnectionFactorySemProperties().getConnection();
            stmt1 = connection.prepareStatement(stmtListarPosts);
            stmt1.setInt(1, id);
            rs1 = stmt1.executeQuery();
            List<Comentario> comentarios;
            comentarios = new ArrayList<>();
            while(rs1.next()){
                Comentario comentario;
                comentario = new Comentario();
                comentario.setId(rs1.getInt("post"));
                comentario.setDescricao(rs1.getString("descricao"));
                
                Usuario usuario = new Usuario();
                usuario.setUsuario(rs1.getInt("usuario"));
                usuario.setNome(rs1.getString("nome"));
                usuario.setFoto(rs1.getString("foto"));
                comentario.setUsuario(usuario);
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs1.getTimestamp("data"));
                comentario.setData(cal);
                
                Post postPai = new Post();
                postPai.setId(rs1.getInt("postpai"));
                comentario.setPostPai(postPai);
                
                comentarios.add(comentario);
            } 
            return comentarios;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar lista de comentários no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{
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
            }
        }
    }
    
}
