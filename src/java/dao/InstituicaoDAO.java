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
import model.Facade;
import model.Instituicao;
import model.UF;


/**
 *
 * @author Ciro
 */
public class InstituicaoDAO {
    private final String stmtListarInstituicoes = 
        "SELECT a.usuario, a.email, b.razao, b.cnpj, b.responsavel, b.causa "
        + "FROM usuario a INNER JOIN instituicao on "
        + "a.usuario = b.instituicao ";

    public Instituicao consultarInstituicao(int id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        ResultSet rs1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarVoluntario =    "SELECT\n" +
                                                "u.usuario, u.email, u.foto, \n" +
                                                "i.razao, i.cnpj, i.responsavel, i.causa, i.comentario, \n" +
                                                "e.bairro, e.CEP, e.numero, e.rua, e.telefone,\n" +
                                                "c.cidade cidade_id, c.descricao cidade_descr,\n" +
                                                "uf.uf uf_sigla, uf.descricao uf_descr\n" +
                                                "FROM usuario u\n" +
                                                "JOIN instituicao i on u.usuario = i.usuario\n" +
                                                "LEFT JOIN endereco e on e.usuario = u.usuario\n" +
                                                "LEFT JOIN cidade c on c.cidade = e.cidade\n" +
                                                "LEFT JOIN uf on uf.uf = c.uf\n" +
                                                "WHERE u.usuario = ?";
            stmt1 = connection.prepareStatement(stmtConsultarVoluntario);
            stmt1.setInt(1, id);
            rs1 = stmt1.executeQuery();
            Instituicao instituicao = new Instituicao();
            UF uf = new UF();
            Cidade cidade = new Cidade();
            Endereco endereco = new Endereco();
            if(rs1.next()){
                instituicao.setUsuario(rs1.getInt("usuario"));
                instituicao.setEmail(rs1.getString("email"));
                instituicao.setFoto(rs1.getString("foto"));
                instituicao.setNome(rs1.getString("razao"));
                instituicao.setCnpj(rs1.getString("cnpj"));
                instituicao.setComentario(rs1.getString("comentario"));
                instituicao.setResponsavel(rs1.getString("responsavel"));
                Causa causa = new Causa();
                causa = Facade.consultarCausa(rs1.getInt("causa"));
                instituicao.setCausa(causa);
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
                instituicao.setEndereco(endereco);
            }
            rs1.close();
            stmt1.close();
            return instituicao;
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

    public List<Instituicao> listarInstituicoes() throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        List<Instituicao> instituicoes = new ArrayList<>();
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            stmt1 = connection.prepareStatement(stmtListarInstituicoes);
            rs1 = stmt1.executeQuery();
            while(rs1.next()){
                Instituicao instituicao;
                instituicao = new Instituicao();
                instituicao.setUsuario(rs1.getInt("usuario"));
                instituicao.setEmail(rs1.getString("email"));
                instituicao.setNome(rs1.getString("razao"));
                instituicao.setCnpj(rs1.getString("cnpj"));
                instituicao.setResponsavel(rs1.getString("responsavel"));
                
                Causa causa = new Causa();
                causa.setId(rs1.getInt("causa"));
                instituicao.setCausa(causa);
                
                instituicoes.add(instituicao);
            } 
            return instituicoes;
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
    
    public Instituicao inserirInstituicao(Instituicao instituicao) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        String query = null;
        Instituicao ins = new Instituicao();
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            
            query = "INSERT INTO usuario (email, senha) values (?, ?)";
            stmt1 = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt1.setString(1, instituicao.getEmail());
            stmt1.setString(2, instituicao.getSenha());
            stmt1.executeUpdate();
            try (ResultSet pk = stmt1.getGeneratedKeys()){
                if (pk.next()) {
                    ins.setUsuario(pk.getInt(1));
                }
                else {
                    throw new SQLException("A inserção do usuário falhou, ID não obtida.");
                }
            }
            
            query = "INSERT INTO instituicao (razao, cnpj, responsavel, usuario) values (?, ?, ?, ?)";
            stmt2 = connection.prepareStatement(query);
            stmt2.setString(1, instituicao.getNome());
            stmt2.setString(2, instituicao.getCnpj());
            stmt2.setString(3, instituicao.getResponsavel());
            stmt2.setInt(4, ins.getUsuario());
            stmt2.execute();
            return ins;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
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
    
    public void atualizarInstituicao(Instituicao ins) throws IOException {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        String query = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            
            query = "UPDATE instituicao SET razao = ?, cnpj = ?, responsavel = ?, causa = ?, comentario = ? WHERE usuario = ?";
            stmt1 = connection.prepareStatement(query);
            stmt1.setString(1, ins.getNome());
            stmt1.setString(2, ins.getCnpj());
            stmt1.setString(3, ins.getResponsavel());
            stmt1.setInt(4, ins.getCausa().getId());
            stmt1.setString(5, ins.getComentario());
            stmt1.setInt(6, ins.getUsuario());
            stmt1.execute();
            stmt1.close();
            
            //ENDERECO
            Facade.excluiEndereco(ins.getUsuario()); //exclui tudo
            Facade.inserirEndereco(ins.getUsuario(), ins.getEndereco());
                        
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

    public List<Instituicao> listaInstituicoes(Integer usuarioId) throws IOException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Instituicao> instituicoes = new ArrayList<>();
        String query = null;
        try{
            con = new ConnectionFactorySemProperties().getConnection();
            
            query = 
            "select a.usuario_solicitado as instituicao, i.razao\n" +
            "from amizade a\n" +
            "join instituicao i on i.usuario = a.usuario_solicitado\n" +
            "where a.usuario_solicitante = ?\n" +
            "and a.usuario_solicitado <> ?\n" +
            "and a.status = 'C'\n" +
            "union\n" +
            "select a.usuario_solicitante as instituicao, i.razao\n" +
            "from amizade a\n" +
            "join instituicao i on i.usuario = a.usuario_solicitante\n" +
            "where a.usuario_solicitado = ?\n" +
            "and a.usuario_solicitante <> ?\n" +
            "and a.status = 'C'\n" +
            "order by razao";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, usuarioId);
            stmt.setInt(3, usuarioId);
            stmt.setInt(4, usuarioId);
            rs = stmt.executeQuery();
            while(rs.next()){
                Instituicao ins = Facade.consultarInstituicao(rs.getInt("instituicao"));
                instituicoes.add(ins);
            }
            return instituicoes;
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

}
