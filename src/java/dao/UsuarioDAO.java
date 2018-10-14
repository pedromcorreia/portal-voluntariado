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
import model.Endereco;
import model.Facade;
import model.Habilidade;
import model.Usuario;
import model.Voluntario;

/**
 *
 * @author Ciro
 */
public class UsuarioDAO {
    private final String stmtAutenticarUsuario = 
        "SELECT usuario, email FROM usuario WHERE email = ? and senha = ?";
    private final String stmtConsultarUsuarioId = 
        "SELECT usuario, email,foto FROM usuario WHERE usuario = ?";
    private final String stmtConsultarUsuarioEmail = 
        "SELECT usuario, email FROM usuario WHERE email = ?";
    private final String stmtListarTodosUsuarios = 
        "SELECT usuario, email FROM usuario";
    
    public Usuario autenticarUsuario(String login, String senha) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            stmt = connection.prepareStatement(stmtAutenticarUsuario);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            Usuario usuario = new Usuario();
            
            if(rs.next()){                
                usuario.setUsuario(rs.getInt("usuario"));
                usuario.setEmail(rs.getString("email"));
            } 
            return usuario;
        } 
        catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar contato no banco de dados. Origem="+ex.getMessage());
        } 
        finally{
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
    
    public Usuario consultarUsuarioId(int id) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            stmt = connection.prepareStatement(stmtConsultarUsuarioId);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Usuario usuario;
            usuario = new Usuario();
            if(rs.next()){
                usuario.setUsuario(rs.getInt("usuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setFoto(rs.getString("foto"));
            } 
            return usuario;
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

    public boolean consultarUsuarioExistente(String email) throws IOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            stmt = connection.prepareStatement(stmtConsultarUsuarioEmail);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if(rs.next()){
                try{
                    rs.close();
                } catch (SQLException ex){
                    System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());
                };
                return true; 
            }
            else {
                return false;
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar usuario no banco de dados. Origem="+ex.getMessage());
        } finally{
            /*try{
                rs.close();
            } catch (SQLException ex){
                System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());
            };*/
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
    
    public List<Usuario> listarTodosUsuarios() throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<>();
        try{
            con = new ConnectionFactorySemProperties().getConnection();
            stmt = con.prepareStatement(stmtListarTodosUsuarios);
            rs = stmt.executeQuery();
            while(rs.next()){
                Usuario usuario = Facade.consultarUsuarioId(rs.getInt("usuario"));
                lista.add(usuario);
            }
            return lista;
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

    public String consultaUsuarioTipo(int usuario) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String tipo = "";
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtConsultarUsuarioId = "select (case when v.usuario is not null then 'V' else null end) as tipo\n" +
                                            "from voluntario v\n" +
                                            "where v.usuario = ?\n" +
                                            "union\n" +
                                            "select (case when i.usuario is not null then 'I' else null end) as tipo\n" +
                                            "from instituicao i\n" +
                                            "where i.usuario = ?";
            stmt = connection.prepareStatement(stmtConsultarUsuarioId);
            stmt.setInt(1, usuario);
            stmt.setInt(2, usuario);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                tipo = rs.getString("tipo");
            } 
            return tipo;
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

    public void atualizaUsuario(Usuario u, String fileName) {
        Connection connection = null;
        PreparedStatement stmt1 = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String stmtAtualizarVoluntario = "update usuario set foto = ? where usuario = ?";
            stmt1 = connection.prepareStatement(stmtAtualizarVoluntario);
            stmt1.setString(1, fileName);
            stmt1.setInt(2, u.getUsuario());
            stmt1.executeUpdate();
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

    public void excluiEndereco(int usuario) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String query = "delete from endereco where usuario = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, usuario);
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar contato no banco de dados. Origem="+ex.getMessage());
        } finally{
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

    public void inserirEndereco(int usuario, Endereco end) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try{
            connection = new ConnectionFactorySemProperties().getConnection();
            String query = "insert into endereco (usuario, cep, rua, numero, bairro, telefone, cidade) values (?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, usuario);
            stmt.setString(2, end.getCep());
            stmt.setString(3, end.getRua());
            stmt.setString(4, end.getNumero());
            stmt.setString(5, end.getBairro());
            stmt.setString(6, end.getTelefone());
            stmt.setInt(7, end.getCidade().getId());
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar contato no banco de dados. Origem="+ex.getMessage());
        } finally{
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
