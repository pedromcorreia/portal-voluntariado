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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Causa;
import model.Cidade;
import model.Endereco;
import facade.Facade;
import model.Habilidade;
import model.UF;
import model.Usuario;
import model.Voluntario;

/**
 *
 * @author Ciro
 */
public class VoluntarioDAO {
    private final String stmtConsultarVoluntarioHabilidade = 
        "select vh.usuario, vh.habilidade, vh.ordem, vh.comentario, h.descricao, h.img \n" +
        "from voluntariohabilidade vh\n" +
        "join habilidade h on h.habilidade = vh.habilidade\n" +
        "where vh.usuario = ?\n" +
        "order by vh.ordem";
    private final String stmtConsultarVoluntarioCausa = 
        "select vc.usuario, vc.causa, vc.ordem, vc.comentario, c.descricao, c.img \n" +
        "from voluntariocausa vc\n" +
        "join causa c on c.causa = vc.causa\n" +
        "where vc.usuario = ?\n" +
        "order by vc.ordem";
    
    private final String stmtAtualizarVoluntarioHabilidade = 
        "UPDATE voluntariohabilidade set habilidade = ? "
        + "WHERE voluntario = ?";
    
    public Voluntario consultarVoluntario(int id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarVoluntario =    "SELECT \n" +
                                                "u.usuario, u.email, u.foto, \n" +
                                                "v.comentario, v.nome, v.cpf, v.conquistas,\n" +
                                                "e.bairro, e.CEP, e.numero, e.rua, e.telefone,\n" +
                                                "c.cidade cidade_id, c.descricao cidade_descr,\n" +
                                                "uf.uf uf_sigla, uf.descricao uf_descr\n" +
                                                "FROM usuario u\n" +
                                                "JOIN voluntario v on u.usuario = v.usuario\n" +
                                                "LEFT JOIN endereco e on e.usuario = u.usuario\n" +
                                                "LEFT JOIN cidade c on c.cidade = e.cidade\n" +
                                                "LEFT JOIN uf on uf.uf = c.uf\n" +
                                                "WHERE u.usuario = ?";
            stmt1 = connection.prepareStatement(stmtConsultarVoluntario);
            stmt1.setInt(1, id);
            rs1 = stmt1.executeQuery();
            Voluntario voluntario = new Voluntario();
            UF uf = new UF();
            Cidade cidade = new Cidade();
            Endereco endereco = new Endereco();
            if(rs1.next()){
                voluntario.setUsuario(rs1.getInt("usuario"));
                voluntario.setEmail(rs1.getString("email"));
                voluntario.setNome(rs1.getString("nome"));
                voluntario.setCpf(rs1.getString("cpf"));
                voluntario.setComentario(rs1.getString("comentario"));
                voluntario.setConquistas(rs1.getInt("conquistas"));
                voluntario.setFoto(rs1.getString("foto"));
                uf.setSigla(rs1.getString("uf_sigla"));
                uf.setDescricao(rs1.getString("uf_descr"));
                cidade.setId(rs1.getInt("cidade_id"));
                cidade.setDescricao(rs1.getString("cidade_descr"));
                cidade.setUf(uf);
                endereco.setBairro(rs1.getString("bairro"));
                endereco.setCep(rs1.getString("CEP"));
                endereco.setNumero(rs1.getString("numero"));
                endereco.setRua(rs1.getString("rua"));
                endereco.setTelefone(rs1.getString("telefone"));
                endereco.setCidade(cidade);
                voluntario.setEndereco(endereco);
                
                stmt2 = connection.prepareStatement(stmtConsultarVoluntarioHabilidade);
                stmt2.setInt(1, id);
                rs2 = stmt2.executeQuery();
                List<Habilidade> habilidades= new ArrayList<>();
                while(rs2.next()){
                    Habilidade habilidade = new Habilidade();
                    habilidade.setId(rs2.getInt("habilidade"));
                    habilidade.setDescricao(rs2.getString("descricao"));
                    habilidade.setComentario(rs2.getString("comentario"));
                    habilidade.setImg(rs2.getString("img"));
                    habilidade.setOrdem(rs2.getInt("ordem"));
                    habilidades.add(habilidade);
                }
                voluntario.setHabilidades(habilidades);
                rs2.close();
                stmt2.close();
                
                stmt3 = connection.prepareStatement(stmtConsultarVoluntarioCausa);
                stmt3.setInt(1, id);
                rs3 = stmt3.executeQuery();
                List<Causa> causas = new ArrayList<>();
                while(rs3.next()){
                    Causa causa = new Causa();
                    causa.setId(rs3.getInt("causa"));
                    causa.setDescricao(rs3.getString("descricao"));
                    causa.setComentario(rs3.getString("comentario"));
                    causa.setImg(rs3.getString("img"));
                    causa.setOrdem(rs3.getInt("ordem"));
                    causas.add(causa);
                }
                voluntario.setCausas(causas);        
                rs3.close();
                stmt3.close();
            } 
            rs1.close();
            stmt1.close();
            return voluntario;
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
    
    public List<Voluntario> listaVoluntarios(int id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Voluntario> voluntarios = new ArrayList<>();
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String query = 
            "select a.usuario_solicitante as voluntario, v.nome\n" +
            "from amizade a\n" +
            "join voluntario v on v.usuario = a.usuario_solicitante\n" +
            "join instituicao i on i.usuario = a.usuario_solicitado\n" +
            "where a.usuario_solicitado = ?\n" +
            "and a.usuario_solicitante <> ?\n" +
            "and a.status = 'C'\n" +
            "order by v.nome;";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                Voluntario vol = consultarVoluntario(rs.getInt("voluntario"));           
                voluntarios.add(vol);
            } 
            return voluntarios;
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
    
    public Voluntario inserirVoluntario(Voluntario v) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        Voluntario vol = new Voluntario();
        
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            
            String stmtInserirUsuario = "INSERT INTO usuario (email, senha) values (?, ?)";
            stmt1 = connection.prepareStatement(stmtInserirUsuario, Statement.RETURN_GENERATED_KEYS);
            stmt1.setString(1, v.getEmail());
            stmt1.setString(2, v.getSenha());
            stmt1.execute();
            try (ResultSet pk = stmt1.getGeneratedKeys()){
                if (pk.next()) {
                    vol.setUsuario(pk.getInt(1));
                }
                else {
                    throw new SQLException("A inserção do usuário falhou, ID não obtida.");
                }
            }
            
            String stmtInserirVoluntario  = "INSERT INTO voluntario (usuario, nome, cpf, conquistas) values " + "(?, ?, ?, ?)";
            stmt2 = connection.prepareStatement(stmtInserirVoluntario);
            stmt2.setInt(1, vol.getUsuario());
            stmt2.setString(2, v.getNome());
            stmt2.setString(3, v.getCpf());
            stmt2.setInt(4, v.getConquistas());
            stmt2.execute();
            
            return vol;
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir usuario no banco de dados. Origem="+ex.getMessage());
        } 
        finally{
            try{
                stmt1.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());
            }
            try{
                stmt2.close();
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
    
    public void atualizarVoluntario(Voluntario voluntario) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        String query = null;
        Integer flag_habilidade = 0, flag_causa = 0;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            query = "UPDATE voluntario SET nome = ?, cpf = ?, conquistas = ?, comentario = ? WHERE usuario = ?";
            stmt1 = connection.prepareStatement(query);
            stmt1.setString(1, voluntario.getNome());
            stmt1.setString(2, voluntario.getCpf());
            stmt1.setInt(3, voluntario.getConquistas());
            stmt1.setString(4, voluntario.getComentario());
            stmt1.setInt(5, voluntario.getUsuario());
            stmt1.executeUpdate();
            stmt1.close();
            
            //ENDERECO
            Facade.excluiEndereco(voluntario.getUsuario()); //exclui tudo
            Facade.inserirEndereco(voluntario.getUsuario(), voluntario.getEndereco());
            
            //HABILIDADES
            Facade.excluiVoluntarioHabilidade(voluntario.getUsuario()); //exclui tudo
            List<Habilidade> habilidades = voluntario.getHabilidades();
            for(Habilidade habilidade:habilidades){
                if(habilidade.getId() != flag_habilidade){
                    Facade.inserirVoluntarioHabilidade(habilidade, voluntario.getUsuario());
                }
                flag_habilidade = habilidade.getId();
            }
            
            //CAUSAS
            Facade.excluiVoluntarioCausa(voluntario.getUsuario()); //exclui tudo
            List<Causa> causas = voluntario.getCausas();
            for(Causa causa:causas){
                if(causa.getId() != flag_causa){
                    Facade.inserirVoluntarioCausa(causa, voluntario.getUsuario());
                }
                flag_causa = causa.getId();
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar contato no banco de dados. Origem="+ex.getMessage());
        } finally{
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
    
    public void inserirVoluntarioHabilidade(Habilidade h, int usuario) {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        Integer flag_habilidade = 0;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtAtualizarVoluntario = "insert into voluntariohabilidade (habilidade, comentario, usuario, ordem) values (?, ?, ?, ?)";
            stmt1 = connection.prepareStatement(stmtAtualizarVoluntario);
            stmt1.setInt(1, h.getId());
            stmt1.setString(2, h.getComentario());
            stmt1.setInt(3, usuario);
            stmt1.setInt(4, h.getOrdem());
            stmt1.execute();
            stmt1.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar contato no banco de dados. Origem="+ex.getMessage());
        } finally{
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

    public void excluirVoluntarioHabilidade(int usuario) {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        Integer flag_habilidade = 0;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            //String stmtAtualizarVoluntario = "delete from voluntariohabilidade where usuario = ? and habilidade = ? and ordem = ?";
            String stmtAtualizarVoluntario = "delete from voluntariohabilidade where usuario = ?";
            stmt1 = connection.prepareStatement(stmtAtualizarVoluntario);
            stmt1.setInt(1, usuario);
            //stmt1.setInt(2, h.getId());
            //stmt1.setInt(3, h.getOrdem());
            stmt1.execute();
            stmt1.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar contato no banco de dados. Origem="+ex.getMessage());
        } finally{
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

    public void excluirVoluntarioCausa(int usuario) {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtAtualizarVoluntario = "delete from voluntariocausa where usuario = ?";
            stmt1 = connection.prepareStatement(stmtAtualizarVoluntario);
            stmt1.setInt(1, usuario);
            stmt1.execute();
            stmt1.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar contato no banco de dados. Origem="+ex.getMessage());
        } finally{
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

    public void inserirVoluntarioCausa(Causa c, int usuario) {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtAtualizarVoluntario = "insert into voluntariocausa (causa, comentario, usuario, ordem) values (?, ?, ?, ?)";
            stmt1 = connection.prepareStatement(stmtAtualizarVoluntario);
            stmt1.setInt(1, c.getId());
            stmt1.setString(2, c.getComentario());
            stmt1.setInt(3, usuario);
            stmt1.setInt(4, c.getOrdem());
            stmt1.execute();
            stmt1.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar contato no banco de dados. Origem="+ex.getMessage());
        } finally{
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
    
    public List<Voluntario> listaAmigos(Integer usuarioId) throws IOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Voluntario> friends = new ArrayList<>();
        String query = null;
        try{
            con = new ConnectionFactorySemProperties().getConnection();
            
            query = 
            "select a.usuario_solicitado as amigo, v.nome\n" +
            "from amizade a\n" +
            "join voluntario v on v.usuario = a.usuario_solicitado\n" +
            "where a.usuario_solicitante = ?\n" +
            "and a.usuario_solicitado <> ?\n" +
            "and a.status = 'C'\n" +
            "union\n" +
            "select a.usuario_solicitante as amigo, v.nome\n" +
            "from amizade a\n" +
            "join voluntario v on v.usuario = a.usuario_solicitante\n" +
            "where a.usuario_solicitado = ?\n" +
            "and a.usuario_solicitante <> ?\n" +
            "and a.status = 'C'\n" +
            "order by nome";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, usuarioId);
            stmt.setInt(3, usuarioId);
            stmt.setInt(4, usuarioId);
            rs = stmt.executeQuery();
            while(rs.next()){
                Voluntario vol = Facade.consultarVoluntario(rs.getInt("amigo"));
                friends.add(vol);
            }
            return friends;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar a lista de usuarios. Origem="+ex.getMessage());
        }finally{
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
                con.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());
            }               
        }
    }

    public List<Voluntario> pesquisarVoluntarios(String vol_nome, String uf_sigla, Integer cidade_id, Integer causa_id, Integer habilidade_id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Voluntario> voluntarios = new ArrayList<>();
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String query = 
            "select u.usuario\n" +
            "from usuario u\n" +
            "join voluntario v on v.usuario = u.usuario\n" +
            "left join endereco e on e.usuario = u.usuario\n" +
            "left join cidade c on c.cidade = e.cidade\n" +
            "left join voluntariocausa vc on vc.usuario = u.usuario\n" +
            "left join voluntariohabilidade vh on vh.usuario = u.usuario\n" + 
            "where 1=1\n";
            
            if(vol_nome != null && !vol_nome.isEmpty()){
                query += "and upper(v.nome) like upper(?)\n";
            }
            if(cidade_id != null){
                query += "and e.cidade = ?\n";
            }
            if(uf_sigla != null && !uf_sigla.isEmpty()){
                query += "and c.uf = ?\n";
            }
            if(causa_id != null){
                query += "and vc.causa = ?\n";
            }
            if(habilidade_id != null){
                query += "and vh.habilidade = ?\n";
            }
            
            query += "group by u.usuario";
            
            stmt = connection.prepareStatement(query);
            Integer i = 0;
            if(vol_nome != null && !vol_nome.isEmpty()){
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
            rs = stmt.executeQuery();
            while(rs.next()){
                Voluntario vol = consultarVoluntario(rs.getInt("usuario"));           
                voluntarios.add(vol);
            } 
            return voluntarios;
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
}
