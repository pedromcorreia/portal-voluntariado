/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dao.ComentarioDAO;
import dao.OportunidadeDAO;
import dao.PostDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import model.Comentario;
import model.Oportunidade;
import model.Post;
import model.Usuario;

/**
 *
 * @author Ciro
 */
public class FacadePost {
    
    public static List<Post> listarPosts(){
        PostDAO postDAO = new PostDAO();
        List<Post> listaPosts = new ArrayList<>();
        try {
           listaPosts = postDAO.listarPosts();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return listaPosts;       
    }
    
    public static Post inserirPost(Post post) throws IOException{
        PostDAO dao = new PostDAO();
        Post p = new Post();
        p = dao.inserirPost(post);
        return p;
    }

    public static List<Comentario> listarComentariosPost(int post) throws IOException {
       ComentarioDAO comentarioDAO = new ComentarioDAO();
       List<Comentario> comentarios;
       comentarios = comentarioDAO.consultarComentarioPost(post);
       return comentarios;
    }

    public static void inserirComentario(Comentario com) throws IOException {
        ComentarioDAO comDao = new ComentarioDAO();
        comDao.inserirComentario(com);
    }

    public static int curtirPost(Post p, Usuario u) throws IOException {
        int curtiu;
        PostDAO postDao = new PostDAO();
        curtiu = postDao.curtirPost(p, u);
        return curtiu;
    }
    
    public static Post consultarPost(Post post) throws IOException{
        PostDAO postDAO = new PostDAO();
        Post atualPost = new Post();
        atualPost = postDAO.consultarPost(post);
        return atualPost;
    }    

    public static Oportunidade OportunidadePost(Post post) throws IOException {
        OportunidadeDAO oportunidadeDAO = new OportunidadeDAO();
        Oportunidade oportunidade = new Oportunidade();
        oportunidade = oportunidadeDAO.consultarOportunidade(post);
        return oportunidade;
    }

    public static Post atualizarPost(Post post) {
        PostDAO dao = new PostDAO();
        Post p = new Post();
        p = dao.atualizarPost(post);
        return p;
    }

    public static Oportunidade inserirOportunidade(Oportunidade oportunidade) {
        OportunidadeDAO dao = new OportunidadeDAO();
        Oportunidade op = new Oportunidade();
        try {
            dao.excluirOportunidade(oportunidade.getPostPai().getId()); //só pode haver uma oportunidade vinculada a um post.
            op = dao.inserirOportunidade(oportunidade);
        } catch (IOException ex) {
            Logger.getLogger(FacadePost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return op;
    }
    
    
}
