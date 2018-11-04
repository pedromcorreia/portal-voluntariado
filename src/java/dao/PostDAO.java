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
import model.Comentario;
import facade.FacadePost;
import model.Oportunidade;
import model.Post;
import model.Usuario;

/**
 *
 * @author Ciro
 */
public class PostDAO {
    
    public Post inserirPost(Post p) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        Post post = new Post();
        Post postAux = new Post();
        Oportunidade op = new Oportunidade();
        String query = "INSERT INTO post (usuario, postpai, descricao, data, imagem) values (?, ?, ?, ?, ?)";
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            
            stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, p.getUsuario().getUsuario());
            stmt.setString(2, null);
            stmt.setString(3, p.getDescricao());
            Calendar cal = p.getData();
            stmt.setTimestamp(4, new java.sql.Timestamp(cal.getTimeInMillis()));
            stmt.setString(5, p.getImagem());
            stmt.execute();

            try (ResultSet pk = stmt.getGeneratedKeys()){
                if (pk.next()) {
                    post.setId(pk.getInt(1));
                    postAux.setId(pk.getInt(1));
                }
                else {
                    throw new SQLException("A inserção do post falhou, ID não obtida.");
                }
            }
            
            if(p.getOportunidade() != null){
                op = p.getOportunidade();
                op.setPostPai(postAux);
                op = FacadePost.inserirOportunidade(op);
            }
            
            post.setOportunidade(op);
            
            return post;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir post no banco de dados. Origem="+ex.getMessage());
        } 
        finally{
            try{
                stmt.close();
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
    
    public List<Post> listarPosts() throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            String stmtListarPosts = "select p.post, p.descricao, p.data, p.imagem, p.postpai, p.usuario, p.imagem, \n" +
                                        "c.curtidas, comentarios, coalesce(v.nome, i.razao) nome, r.*, u.foto \n" +
                                        "from post p  \n" +
                                        "left join \n" +
                                        "(select post, count(*) curtidas\n" +
                                        "from curtirpost\n" +
                                        "group by post) c\n" +
                                        "on  p.post = c.post \n" +
                                        "left join \n" +
                                        "(select postpai, count(*) comentarios \n" +
                                        "from post\n" +
                                        "group by postpai) o\n" +
                                        "on  p.post = o.postpai \n" +
                                        "inner join usuario u \n" +
                                        "on p.usuario = u.usuario \n" +
                                        "left join voluntario v \n" +
                                        "on u.usuario = v.usuario \n" +
                                        "left join instituicao i \n" +
                                        "on u.usuario = i.usuario \n" +
                                        "left join oportunidade r \n" +
                                        "on p.post = r.post \n" +
                                        "where p.postpai is null \n" +
                                        "group by  \n" +
                                        "p.post, p.descricao, p.data, p.imagem, p.postpai, p.usuario \n" +
                                        "order by p.data desc";
            connection = new ConnectionFactorySemProperties().getConnection();
            stmt1 = connection.prepareStatement(stmtListarPosts);
            rs1 = stmt1.executeQuery();
            List<Post> listaPosts = new ArrayList<>();
            while(rs1.next()){
                Post post;
                post = new Post();
                post.setId(rs1.getInt("post"));
                post.setDescricao(rs1.getString("descricao"));
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs1.getTimestamp("data"));
                post.setData(cal);
                post.setImagem(rs1.getString("imagem"));
                
                Usuario usuario = new Usuario();
                usuario.setUsuario(rs1.getInt("usuario"));
                usuario.setNome(rs1.getString("nome"));
                usuario.setFoto(rs1.getString("foto"));
                post.setUsuario(usuario);
                
                List<Comentario> comentarios;               
                comentarios = FacadePost.listarComentariosPost(rs1.getInt("post"));
                post.setComentarios(comentarios);
                
                post.setQtdeCurtidas(rs1.getInt("curtidas"));
                post.setQtdeComentarios(rs1.getInt("comentarios"));
                
                Oportunidade oportunidade = new Oportunidade();
                oportunidade = FacadePost.OportunidadePost(post);
                
                post.setOportunidade(oportunidade);
                
                listaPosts.add(post);
            } 
            return listaPosts;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar lista de posts no banco de dados. Origem="+ex.getMessage());
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
    
    public Post consultarPost(Post p) throws IOException {
        Connection connection = new ConnectionFactorySemProperties().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Calendar cal = null;
        String query = null;
        Post post = new Post();
        Oportunidade op = new Oportunidade();
        Usuario usuario = new Usuario();
        List<Comentario> comentarios;
        try{
            query = "select p.post, p.descricao, p.data, p.imagem, p.postpai, p.usuario,  \n" +
                    "c.curtidas, comentarios, coalesce(v.nome, i.razao) nome, \n" +
                    "op.oportunidade, op.vagas, op.presencial, op.inicio, op.termino, op.cargahoraria, op.status \n" +
                    "from post p  \n" +
                    "left join \n" +
                    "(select post, count(*) curtidas\n" +
                    "from curtirpost\n" +
                    "group by post) c on  p.post = c.post \n" +
                    "left join \n" +
                    "(select postpai, count(*) comentarios \n" +
                    "from post\n" +
                    "group by postpai) o on  p.post = o.postpai \n" +
                    "join usuario u on p.usuario = u.usuario \n" +
                    "left join voluntario v on u.usuario = v.usuario \n" +
                    "left join instituicao i on u.usuario = i.usuario \n" +
                    "left join oportunidade op on op.post = p.post \n" +
                    "where p.post = ? \n";
            
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, p.getId());
            rs = stmt.executeQuery();
            
            if(rs.next()){
                post.setId(rs.getInt("post"));
                post.setDescricao(rs.getString("descricao"));
                cal = Calendar.getInstance();
                cal.setTime(rs.getTimestamp("data"));
                post.setData(cal);
                
                usuario.setUsuario(rs.getInt("usuario"));
                usuario.setNome(rs.getString("nome"));               
                post.setUsuario(usuario);
                               
                comentarios = FacadePost.listarComentariosPost(rs.getInt("post"));
                post.setComentarios(comentarios);
                
                post.setQtdeCurtidas(rs.getInt("curtidas"));
                post.setQtdeComentarios(rs.getInt("comentarios"));
                
                post.setImagem(rs.getString("imagem"));
                
                if(rs.getInt("oportunidade")!=0){
                    op.setId(rs.getInt("oportunidade"));
                    op.setVagasTotal(rs.getInt("vagas"));
                    op.setPresencial(rs.getString("presencial").charAt(0));
                    cal = Calendar.getInstance();
                    cal.setTime(rs.getTimestamp("inicio"));
                    op.setInicio(cal);
                    cal = Calendar.getInstance();
                    cal.setTime(rs.getTimestamp("termino"));
                    op.setTermino(cal);
                    op.setCargaHorariaTotal(rs.getInt("cargahoraria"));
                    op.setStatus(rs.getString("status").charAt(0));
                    op.setPostPai(post);

                    post.setOportunidade(op);
                }
            } 
            return post;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar lista de posts no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{
                rs.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());
            }
            try{
                stmt.close();
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
        
    public Post inserirComentario(Post p) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        Post post = new Post();
        String stmtInserirPost = "INSERT INTO post (usuario, postpai, descricao, data) values (?, ?, ?, ?)";
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            
            stmt1 = connection.prepareStatement(stmtInserirPost, Statement.RETURN_GENERATED_KEYS);
            stmt1.setInt(1, 1);
            stmt1.setString(2, null);
            stmt1.setString(3, p.getDescricao());
            Calendar cal = p.getData();
            stmt1.setTimestamp(4, new java.sql.Timestamp(cal.getTimeInMillis()));

            stmt1.execute();

            try (ResultSet pk = stmt1.getGeneratedKeys()){
                if (pk.next()) {
                    post.setId(pk.getInt(1));
                }
                else {
                    throw new SQLException("A inserção do usuário falhou, ID não obtida.");
                }
            }
            return post;
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir comentário no banco de dados. Origem="+ex.getMessage());
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

    public int curtirPost(Post p, Usuario u) throws IOException{
        Connection connection = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet rs1 = null;
        int curtiu = -1;
        String stmtCheckLike = "select count(*) curtiu from tcc.curtirpost where post = ? and usuario = ?";
        String stmtLike = "insert into tcc.curtirpost (usuario, post) values (?, ?)";
        String stmtUnlike = "delete from tcc.curtirpost where usuario = ? and post = ?";

        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            stmt1 = connection.prepareStatement(stmtCheckLike);
            stmt1.setInt(1, p.getId());
            stmt1.setInt(2, u.getUsuario());
            rs1 = stmt1.executeQuery();
            if(rs1.next()){
                switch (rs1.getInt("curtiu")) {
                    case 0:
                        try{
                            stmt2 = connection.prepareStatement(stmtLike);
                            stmt2.setInt(1, u.getUsuario());
                            stmt2.setInt(2, p.getId());
                            stmt2.execute();
                            curtiu =1;                            
                        } catch (SQLException ex) {
                           throw new RuntimeException("Erro ao vreificar like/unlike no banco de dados. Origem="+ex.getMessage());
                        } finally {
                            try{
                                stmt2.close();
                            } catch (SQLException ex){
                                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
                            }                            
                        }
                        break;
                    case 1:
                        try{
                            stmt3 = connection.prepareStatement(stmtUnlike);
                            stmt3.setInt(1, u.getUsuario());
                            stmt3.setInt(2, p.getId());
                            stmt3.execute();
                            curtiu = 0;
                        } catch (SQLException ex) {
                            
                        } finally {
                            try{
                                stmt3.close();
                            } catch (SQLException ex){
                                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
                            }                            
                        }
                        break;
                    default:
                        curtiu = -1;
                        break;
                }
            }
            return curtiu;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao vreificar like/unlike no banco de dados. Origem="+ex.getMessage());
        } 
        finally{
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

    public Post atualizarPost(Post p) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Oportunidade op = new Oportunidade();
        String query =  "update post set \n" +
                        "usuario = ?,\n" +
                        "postpai = ?,\n" +
                        "descricao = ?,\n" +
                        "data = ?,\n" +
                        "imagem = ?\n" +
                        "where post  = ?";
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, p.getUsuario().getUsuario());
            stmt.setString(2, null);
            stmt.setString(3, p.getDescricao());
            Calendar cal = p.getData();
            stmt.setTimestamp(4, new java.sql.Timestamp(cal.getTimeInMillis()));
            stmt.setString(5, p.getImagem());
            stmt.setInt(6, p.getId());
            stmt.execute();
            
            if(p.getOportunidade() != null){
                op = p.getOportunidade();
                op.setPostPai(p);
                op = FacadePost.inserirOportunidade(op);
            }
            
            p.setOportunidade(op);
            
            return p;
        }
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir post no banco de dados. Origem="+ex.getMessage());
        } 
        finally{
            try{
                stmt.close();
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
