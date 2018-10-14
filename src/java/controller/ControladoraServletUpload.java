/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
//import java.util.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import model.Facade;
import model.Usuario;

/**
 *
 * @author Avell B155 FIRE
 */
@WebServlet(name = "ControladoraServletUpload", urlPatterns = {"/ControladoraServletUpload"})
@MultipartConfig
public class ControladoraServletUpload extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("null")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        String applicationPath = "C:/Users/Avell B155 FIRE.Avell-B155FIRE/Documents/UFPR/PERIODO 6/TCC/tcc/web/images/Friends";
        System.out.println("applicationPath = " + applicationPath);
        final String path = "/images/Friends";
        final Part filePart = request.getPart("imagem");
        final String fileName = Facade.getFileName(filePart);
        
        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();
        //Logger LOGGER;
        try {
            //out = new FileOutputStream(new File(path + File.separator + fileName));
            out = new FileOutputStream(new File(applicationPath + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0} being uploaded to {1}", new Object[]{fileName, path});
            
            Integer usuarioId = (Integer)session.getAttribute("usuarioLogado");
            Usuario u = Facade.consultarUsuarioId(usuarioId);
            String tipo = Facade.consultaUsuarioTipo(u.getUsuario());
            u.setTipo(tipo);
            Facade.perfilFoto(u, fileName);
            if("V".equals(tipo)){
                response.sendRedirect("ControladoraServlet?action=perfilVoluntario");
            }
            else if("I".equals(tipo)){
                response.sendRedirect("ControladoraServlet?action=perfilInstituicao");
            }    
        } 
        catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
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
