/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Comentario;
import facade.Facade;
import facade.FacadePost;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import model.Instituicao;
import model.Oportunidade;
import model.Post;
import model.Usuario;

/**
 *
 * @author Ciro
 */
@WebServlet(name = "ControladoraPost", urlPatterns = {"/ControladoraPost"})
@MultipartConfig(fileSizeThreshold = 1024*1024*2, //2mB
                 maxFileSize = 1024*1024*10, //10mB
                 maxRequestSize = 1024*1024*50,
                 location = "\\c:\\")

public class ControladoraPost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String action = request.getParameter("action");
        Usuario usuario = new Usuario();
        int usr = (Integer) session.getAttribute("usuarioLogado");
        usuario = Facade.consultarUsuarioId(usr);
        session.setAttribute("usuarioConectado", usuario);
        Post post = new Post();
        Post postPai = new Post();
        Oportunidade opo = new Oportunidade();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        Calendar cal = Calendar.getInstance();
        Integer usuarioId;
        Instituicao instituicaoLogada;
        Calendar dataPost;
        
        switch (action) {
            case "timeline":
                List<Post> listaPosts;
                listaPosts = FacadePost.listarPosts();
                request.setAttribute("listaPosts", listaPosts);   
                rd = getServletContext().getRequestDispatcher("/timeline.jsp");
                rd.forward(request, response);

                break;
            case "newpost":
                //int usr = (int)session.getAttribute("usuarioLogado");
                //usuario.setUsuario(usr);
                
                dataPost = Calendar.getInstance();
                post.setData(dataPost);                
                post.setDescricao(request.getParameter("post"));
                post.setUsuario(usuario);
                

                /* inicio arquivo*/
                Part part = request.getPart("imagem");
                String fileName = extractFileName(part);
                String savePath = "\\Users\\Ciro\\Documents\\NetBeansProjects\\tcc\\web\\images\\Post\\" + fileName;
                //get real path. serverlet context
                File fileSaveDir = new File(savePath);
                part.write(savePath + File.separator);
                //newPost.setImagem("c:"+savePath);
                String armazenar = ".\\images\\Post\\"+fileName;
                post.setImagem(armazenar);
                /* termino arquivo*/                
                
                FacadePost.inserirPost(post);
                response.sendRedirect("ControladoraPost?action=timeline");
                break;
            case "newcomment":
                Comentario com = new Comentario();

                Calendar dataCom = Calendar.getInstance();
                com.setData(dataCom);

                com.setDescricao(request.getParameter("comment"));

                //int usrCom = (int)session.getAttribute("usuarioLogado");
                //usuario.setUsuario(usrCom);
                com.setUsuario(usuario);
                
                String postP;
                postP = request.getParameter("id");
                
                postPai.setId(Integer.parseInt(postP));
                com.setPostPai(postPai);
                
                FacadePost.inserirComentario(com);
                response.sendRedirect("ControladoraPost?action=timeline");
                
                break;
            case "oportunidadeEditar":
                rd = getServletContext().getRequestDispatcher("/oportunidadeEditar.jsp");
                String postId = request.getParameter("postId");
                if(postId != null){
                    post.setId(Integer.parseInt(postId));
                    post = FacadePost.consultarPost(post);
                    request.setAttribute("post", post);
                }
                rd.forward(request, response);
                break;
            case "gravarOportunidade":
                usuarioId = (Integer)session.getAttribute("usuarioLogado");
                instituicaoLogada = Facade.consultarInstituicao(usuarioId);
                
                post.setUsuario(instituicaoLogada);
                
                String op = request.getParameter("oportunidadeId");
                if(!op.isEmpty() && op != null){
                    post.setId(Integer.parseInt(op));
                }
                
                String descricao = request.getParameter("descricao");
                post.setDescricao(descricao);
                
                String atualizaFoto = request.getParameter("alterouFoto");
                if("S".equals(atualizaFoto)){
                    part = request.getPart("imagem");
                    fileName = extractFileName(part);
                    savePath = "\\Users\\Avell B155 FIRE.Avell-B155FIRE\\Documents\\GitHub\\portal-voluntariado\\web\\images\\Friends\\" + fileName;
                    fileSaveDir = new File(savePath);
                    part.write(savePath + File.separator);
                    post.setImagem(fileName);
                }
                else{
                    post.setImagem(atualizaFoto);
                }
                
                dataPost = Calendar.getInstance();
                post.setData(dataPost);
                
                String presencial = request.getParameter("presencial");
                opo.setPresencial(presencial.charAt(0));
                
                String dataIni = request.getParameter("dataIni");
                if(!dataIni.isEmpty() && dataIni != null){
                    try {
                        date = formatoData.parse(dataIni);
                        cal.setTime(date);
                        opo.setInicio(cal);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladoraPost.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                String dataFim = request.getParameter("dataFim");
                if(!dataFim.isEmpty() && dataFim != null){
                    try {
                        Date date2 = formatoData.parse(dataFim);
                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(date2);
                        opo.setTermino(cal2);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControladoraPost.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                String vagasTotal = request.getParameter("vagasTotal");
                if(!vagasTotal.isEmpty() && vagasTotal != null){
                    opo.setVagasTotal(Integer.parseInt(vagasTotal));
                }
                
                String cargaHorariaTotal = request.getParameter("cargaHorariaTotal");
                if(!cargaHorariaTotal.isEmpty() && cargaHorariaTotal != null){
                    opo.setCargaHorariaTotal(Integer.parseInt(cargaHorariaTotal));
                }
                
                String status = request.getParameter("status");
                opo.setStatus(status.charAt(0));
                
                post.setOportunidade(opo);
                
                if(op.isEmpty() || op == null){
                    post = FacadePost.inserirPost(post);
                }
                else{
                    post = FacadePost.atualizarPost(post);
                }
        
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControladoraPost.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                response.sendRedirect("ControladoraPost?action=visualizarOportunidade&postId="+post.getId());
                
                break;
            case "visualizarOportunidade":
                postId = request.getParameter("postId");
                post.setId(Integer.parseInt(postId));
                post = FacadePost.consultarPost(post);
                
                rd = getServletContext().getRequestDispatcher("/oportunidade.jsp");
                request.setAttribute("post", post);
                rd.forward(request, response);
                break;
        }
    }
    
    private String extractFileName(Part part){
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        
        for(String s:items){
            if(s.trim().startsWith("filename")){
                System.out.println("o que está no s: " + s);
                return s.substring(s.indexOf("=") +2, s.length()-1);
            }
        }
        return "";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
