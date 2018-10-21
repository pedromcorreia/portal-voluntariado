/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
//import java.util.logging.Logger;
import dao.CausaDAO;
import dao.CidadeDAO;
import dao.EstadoDAO;
import dao.HabilidadeDAO;
import dao.InstituicaoDAO;
import dao.MensagemDAO;
import dao.OportunidadeDAO;
import dao.UsuarioDAO;
import dao.VoluntarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import model.Causa;
import model.Cidade;
import model.Endereco;
import model.Habilidade;
import model.Instituicao;
import model.Mensagem;
import model.Oportunidade;
import model.UF;
import model.Usuario;
import model.Voluntario;

/**
 *
 * @author Avell B155 FIRE
 */
public class Facade {
               
    public static List<Usuario> listarUsuarios(){
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
           listaUsuarios = usuariosDAO.listarTodosUsuarios();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listaUsuarios;       
    }
    
    public static Usuario autenticarUsuario(String login, String senha) throws IOException{
        Usuario u = new Usuario();
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        try{
            u = usuariosDAO.autenticarUsuario(login, senha);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return u;
    }
    
    public static boolean consultarUsuarioExistente (String email) throws IOException{
        UsuarioDAO usuariosDAO = new UsuarioDAO();
        boolean existe = false;
        try{
            existe = usuariosDAO.consultarUsuarioExistente(email);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return existe;
    }
    
    public static Usuario consultarUsuarioId (int id) throws IOException{
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = new Usuario();
        try{
            u = dao.consultarUsuarioId(id);
        } catch(IOException ex){
            System.out.println(ex);
        }
        return u;
    }

    public static Voluntario inserirVoluntario (Voluntario v) throws IOException{
        VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
        Voluntario vol = voluntarioDAO.inserirVoluntario(v);
        return vol;
    }
    
    public static Voluntario consultarVoluntario(int id) throws IOException{
        VoluntarioDAO dao = new VoluntarioDAO();
        Voluntario vol = dao.consultarVoluntario(id);
        return vol;
    }
    
    public static List<Voluntario> listaVoluntarios(int id){
        VoluntarioDAO voluntarioDAO = new VoluntarioDAO();
        List<Voluntario> listaVoluntarios = new ArrayList<>();
        try {
            listaVoluntarios = voluntarioDAO.listaVoluntarios(id);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return listaVoluntarios;
    }
    
    public static Instituicao inserirInstituicao (Instituicao i) throws IOException{
        InstituicaoDAO dao = new InstituicaoDAO();
        Instituicao ins = dao.inserirInstituicao(i);
        return ins;
    }
    
    public static void atualizarInstituicao (Instituicao i) throws IOException{
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        instituicaoDAO.atualizarInstituicao(i);
    }

    public static Instituicao consultarInstituicao(int id) throws IOException{
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        Instituicao instituicao = instituicaoDAO.consultarInstituicao(id);
        return instituicao;
    }
    
    public static List<Instituicao> listarInstituicoes(){
        InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
        List<Instituicao> listaInstituicaos = new ArrayList<>();
        try {
            listaInstituicaos = instituicaoDAO.listarInstituicoes();
        } catch (Exception ex){
            System.out.println(ex);
        }
        return listaInstituicaos;
    }

    public static List<Habilidade> listarHabilidades() {
        HabilidadeDAO dao = new HabilidadeDAO();
        List<Habilidade> habilidades = new ArrayList<>();
        try {
            habilidades = dao.listaHabilidades();
        } catch (Exception ex){
            System.out.println(ex);
        }
        return habilidades;
    }

    public static void alterarVoluntario(Voluntario vol) {
        VoluntarioDAO dao = new VoluntarioDAO();
        try {
            dao.atualizarVoluntario(vol);
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static String consultaUsuarioTipo(int usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        String tipo = dao.consultaUsuarioTipo(usuario);
        return tipo;
    }

    public static void inserirVoluntarioHabilidade(Habilidade h, int usuario) {
        VoluntarioDAO dao = new VoluntarioDAO();
        dao.inserirVoluntarioHabilidade(h, usuario);
    }

    public static void excluiVoluntarioHabilidade(int usuario) {
        VoluntarioDAO dao = new VoluntarioDAO();
        dao.excluirVoluntarioHabilidade(usuario);
    }

    public static List<Causa> listarCausas() {
        CausaDAO dao = new CausaDAO();
        List<Causa> causas = new ArrayList<>();
        try {
            causas = dao.listaCausas();
        } catch (Exception ex){
            System.out.println(ex);
        }
        return causas;
    }

    public static void excluiVoluntarioCausa(int usuario) {
        VoluntarioDAO dao = new VoluntarioDAO();
        dao.excluirVoluntarioCausa(usuario);
    }

    public static void inserirVoluntarioCausa(Causa c, int usuario) {
        VoluntarioDAO dao = new VoluntarioDAO();
        dao.inserirVoluntarioCausa(c, usuario);
    }

    public static String getFileName(final Part part) {
        //Logger LOGGER = null;
        final String partHeader = part.getHeader("content-disposition");
        //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public static void perfilFoto(Usuario u, String fileName) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.atualizaUsuario(u, fileName);
    }

    public static List<UF> listarEstados() {
        EstadoDAO dao = new EstadoDAO();
        List<UF> estados = new ArrayList<>();
        estados = dao.listaEstados();
        return estados;
    }

    public static List<Cidade> listarCidades(String uf) {
        CidadeDAO dao = new CidadeDAO();
        List<Cidade> cidades = new ArrayList<>();
        cidades = dao.listaCidades(uf);
        return cidades;
    }

    public static void excluiEndereco(int usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.excluiEndereco(usuario);
    }

    public static void inserirEndereco(int usuario, Endereco endereco) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserirEndereco(usuario, endereco);
    }

    public static Causa consultarCausa(int id) {
        CausaDAO dao = new CausaDAO();
        Causa causa = new Causa();
        try {
            causa = dao.consultarCausa(id);
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return causa;
    }

    public static void alterarInstituicao(Instituicao ins) {
        InstituicaoDAO dao = new InstituicaoDAO();
        try {
            dao.atualizarInstituicao(ins);
        } 
        catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Voluntario> listaAmigos(Integer usuarioId) {
        List<Voluntario> amigos = new ArrayList<>();
        VoluntarioDAO dao = new VoluntarioDAO();
        try {
            amigos = dao.listaAmigos(usuarioId);
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amigos;
    }
    
    public static List<Instituicao> listaInstituicoes(Integer usuarioId) {
        List<Instituicao> instituicoes = new ArrayList<>();
        InstituicaoDAO dao = new InstituicaoDAO();
        try {
            instituicoes = dao.listaInstituicoes(usuarioId);
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instituicoes;
    }

    public static List<Voluntario> pesquisarVoluntarios(String vol_nome, String uf_sigla, Integer cidade_id, Integer causa_id, Integer habilidade_id) {
        List<Voluntario> voluntarios = new ArrayList<>();
        VoluntarioDAO  dao = new VoluntarioDAO();
        try {
            voluntarios = dao.pesquisarVoluntarios(vol_nome, uf_sigla, cidade_id, causa_id, habilidade_id);
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voluntarios;
    }

    public static List<Instituicao> pesquisarInstituicoes(String vol_nome, String uf_sigla, Integer cidade_id, Integer causa_id) {
        List<Instituicao> instituicoes = new ArrayList<>();
        InstituicaoDAO  dao = new InstituicaoDAO();
        try {
            instituicoes = dao.pesquisarInstituicoes(vol_nome, uf_sigla, cidade_id, causa_id);
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instituicoes;
    }

    public static List<Oportunidade> pesquisarOportunidades(String vol_nome, String uf_sigla, Integer cidade_id, Integer causa_id, Integer habilidade_id, String presencial) {
        List<Oportunidade> oportunidades = new ArrayList<>();
        OportunidadeDAO  dao = new OportunidadeDAO();
        try {
            oportunidades = dao.pesquisarOportunidades(vol_nome, uf_sigla, cidade_id, causa_id, habilidade_id, presencial);
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oportunidades;
    }
    
    public static List<Mensagem> listarMensagens(int id) {
        List<Mensagem> listMensagem = new ArrayList<Mensagem>();
        MensagemDAO  dao = new MensagemDAO();
        try {
            listMensagem = dao.listarMensagens(id);
        } catch (IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMensagem;
    }
    
    public static void inserirMensagem(int id_remetente, int id_destinatario, String comunicado) 
            throws IOException, SQLException {
        MensagemDAO dao = new MensagemDAO();
        dao.inserirMensagem(id_remetente, id_destinatario, comunicado);
    }
    
    public static void atualizarMensagem(int id_remetente, int id_mensagem) 
            throws IOException, SQLException {
        MensagemDAO dao = new MensagemDAO();
        dao.atualizarMensagem(id_remetente, id_mensagem);
    }
    
}
