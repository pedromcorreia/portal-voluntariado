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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String action = request.getParameter("action");
        Usuario usuario = new Usuario();
        int usr = (Integer) session.getAttribute("usuarioLogado");
        usuario = Facade.consultarUsuarioId(usr);
        session.setAttribute("usuarioConectado", usuario);

        
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
                
                Post newPost = new Post();               

                Calendar dataPost = Calendar.getInstance();
                newPost.setData(dataPost);                
                newPost.setDescricao(request.getParameter("post"));
                newPost.setUsuario(usuario);
                

                /* inicio arquivo*/
                Part part = request.getPart("imagem");
                String fileName = extractFileName(part);
                String savePath = "\\Users\\Ciro\\Documents\\NetBeansProjects\\tcc\\web\\images\\Post\\" + fileName;
                //get real path. serverlet context
                File fileSaveDir = new File(savePath);
                part.write(savePath + File.separator);
                //newPost.setImagem("c:"+savePath);
                String armazenar = ".\\images\\Post\\"+fileName;
                newPost.setImagem(armazenar);
                /* termino arquivo*/                
                
                FacadePost.inserirPost(newPost);
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
                Post postPai = new Post();
                postPai.setId(Integer.parseInt(postP));
                com.setPostPai(postPai);
                
                FacadePost.inserirComentario(com);
                response.sendRedirect("ControladoraPost?action=timeline");
                
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
