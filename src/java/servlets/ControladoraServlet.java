/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import util.Utilitarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Causa;
import model.Cidade;
import model.Endereco;
import facade.Facade;
import java.io.File;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import model.Habilidade;
import model.Instituicao;
import model.Oportunidade;
import model.UF;
import model.Usuario;
import model.Voluntario;

/**
 *
 * @author Ciro e Juan
 */
@WebServlet(name = "ControladoraServlet", urlPatterns = {"/ControladoraServlet"})
@MultipartConfig(fileSizeThreshold = 1024*1024*2, //2mB
                 maxFileSize = 1024*1024*10, //10mB
                 maxRequestSize = 1024*1024*50,
                 location = "\\c:\\")
public class ControladoraServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Usuario u;
        RequestDispatcher rd;
        Habilidade hab;
        Causa cau;
        List<Habilidade> habilidades;
        List<Causa> causas;
        String email;
        String senha;
        String nome;
        String cpf;
        Voluntario vol;
        Voluntario voluntarioLogado;
        Instituicao ins;
        Instituicao instituicaoLogada;
        Integer usuarioId;
        Cidade cidade = new Cidade();
        Endereco end = new Endereco();
        List<UF> estados = new ArrayList<>();
        List<Cidade> cidades = new ArrayList<>();
        List<Voluntario> voluntarios = new ArrayList<>();
        List<Instituicao> instituicoes = new ArrayList<>();
        
        switch (action) {
            case "home":
                rd = getServletContext().getRequestDispatcher("/portal.jsp");
                rd.forward(request, response);
            break;            
            case "login":
                email = request.getParameter("email");
                senha = Utilitarios.getMD5(request.getParameter("senha"));
                u = Facade.autenticarUsuario(email, senha);
                if (u.getUsuario() == 0) {
                    response.sendRedirect("ControladoraServlet?action=home");
                }
                else{
                    u.setTipo(Facade.consultaUsuarioTipo(u.getUsuario()));
                    session.setAttribute("usuarioLogado", u.getUsuario());
                    if("V".equals(u.getTipo())){
                        response.sendRedirect("ControladoraServlet?action=perfilVoluntario");
                    }
                    else if("I".equals(u.getTipo())){
                        response.sendRedirect("ControladoraServlet?action=perfilInstituicao");
                    }
                    else{
                        response.sendRedirect("/erro.jsp");
                    }
                }
                break;
            case "novoCadastro":
                email = request.getParameter("email");
                if (Facade.consultarUsuarioExistente(email)){
                        rd = getServletContext().getRequestDispatcher("/portal.jsp");
                        request.setAttribute("msg", "Usário já existe!");
                        rd.forward(request, response);
                }
                else{
                    senha = Utilitarios.getMD5(request.getParameter("senha"));
                    nome  = request.getParameter("nome");

                    if( ("S").equals(request.getParameter("instituicao")) ){
                        ins = new Instituicao();
                        ins.setNome(nome);
                        ins.setEmail(email);
                        ins.setSenha(senha);

                        instituicaoLogada = Facade.inserirInstituicao(ins);
                        //session.setAttribute("usuarioLogado", instituicaoLogada.getUsuario());
                    }
                    else{
                        vol = new Voluntario();
                        vol.setNome(nome);
                        vol.setEmail(email);
                        vol.setSenha(senha);

                        voluntarioLogado = Facade.inserirVoluntario(vol);
                        //session.setAttribute("usuarioLogado", voluntarioLogado.getUsuario());
                    }
                    rd = getServletContext().getRequestDispatcher("/portal.jsp");
                    request.setAttribute("msg", "Usário cadastrado com sucesso!");
                    rd.forward(request, response);
                }
                break;
            case "inserirVoluntario":
                email = request.getParameter("email");
                senha = Utilitarios.getMD5(request.getParameter("senha"));
                nome  = request.getParameter("nome");
                cpf   = request.getParameter("cpf");
                vol = new Voluntario();
                vol.setEmail(email);
                vol.setSenha(senha);
                vol.setNome(nome);
                vol.setCpf(cpf);

                if (Facade.consultarUsuarioExistente(email)){
                    response.sendRedirect("portal.jsp");
                } else {
                    Facade.inserirVoluntario(vol);
                    //session.setAttribute("usuarioLogado", u); so um exemplo de setatribute
                    response.sendRedirect("portal.jsp");                    
                }
                break;
            case "alterarVoluntario":
                habilidades = new ArrayList<>();
                causas = new ArrayList<>();
                
                hab = new Habilidade();
                hab.setId(Integer.parseInt(request.getParameter("habilidade1")));
                hab.setComentario(request.getParameter("comentHab1"));
                hab.setOrdem(1);
                habilidades.add(hab);
                
                hab = new Habilidade();
                hab.setId(Integer.parseInt(request.getParameter("habilidade2")));
                hab.setComentario(request.getParameter("comentHab2"));
                hab.setOrdem(2);
                habilidades.add(hab);
                
                hab = new Habilidade();
                hab.setId(Integer.parseInt(request.getParameter("habilidade3")));
                hab.setComentario(request.getParameter("comentHab3"));
                hab.setOrdem(3);
                habilidades.add(hab);
                
                cau = new Causa();   
                cau.setId(Integer.parseInt(request.getParameter("causa1")));
                cau.setComentario(request.getParameter("comentCau1"));
                cau.setOrdem(1);
                causas.add(cau);

                cau = new Causa();
                cau.setId(Integer.parseInt(request.getParameter("causa2")));
                cau.setComentario(request.getParameter("comentCau2"));
                cau.setOrdem(2);
                causas.add(cau);

                cau = new Causa();
                cau.setId(Integer.parseInt(request.getParameter("causa3")));
                cau.setComentario(request.getParameter("comentCau3"));
                cau.setOrdem(3);
                causas.add(cau);
                
                usuarioId = (Integer)session.getAttribute("usuarioLogado");
                vol = Facade.consultarVoluntario(usuarioId);
                vol.setCpf(request.getParameter("cpf"));
                vol.setHabilidades(habilidades);
                vol.setCausas(causas);
                vol.setComentario(request.getParameter("comentario"));
                
                cidade.setId(Integer.parseInt(request.getParameter("cidade")));
                end.setCidade(cidade);
                end.setBairro(request.getParameter("bairro"));
                end.setCep(request.getParameter("cep"));
                end.setNumero(request.getParameter("numero"));
                end.setRua(request.getParameter("rua"));
                end.setTelefone(request.getParameter("telefone"));
                vol.setEndereco(end);
                
                
                Facade.alterarVoluntario(vol);
                response.sendRedirect("ControladoraServlet?action=perfilVoluntario");
                
                break;
            case "perfilInstituicaoEditar":
                usuarioId = (Integer) session.getAttribute("usuarioLogado");
                instituicaoLogada = Facade.consultarInstituicao(usuarioId);
                estados = Facade.listarEstados();
                cidades = Facade.listarCidades(instituicaoLogada.getEndereco().getCidade().getUf().getSigla());
                habilidades = Facade.listarHabilidades();
                causas = Facade.listarCausas();
                rd = getServletContext().getRequestDispatcher("/perfilEditarInst.jsp");
                request.setAttribute("causas", causas);
                request.setAttribute("instituicao", instituicaoLogada);
                request.setAttribute("estados", estados);
                request.setAttribute("cidades", cidades);
                rd.forward(request, response);
                break;
            case "perfilInstituicao":
                rd = getServletContext().getRequestDispatcher("/perfilInst.jsp");
                if(request.getParameter("perfil")!=null){
                    ins = Facade.consultarInstituicao(Integer.parseInt(request.getParameter("perfil")));
                    request.setAttribute("instituicao", ins);
                    request.setAttribute("perfil", "alheio");
                }
                else{
                    usuarioId = (Integer) session.getAttribute("usuarioLogado");
                    instituicaoLogada = Facade.consultarInstituicao(usuarioId);
                    request.setAttribute("instituicao", instituicaoLogada);
                    request.setAttribute("perfil", "proprio");
                }
                rd.forward(request, response);
                break;               
            case "inserirInstituicao":
                String emailInstituicao = request.getParameter("email");
                String senhaInstituicao = Utilitarios.getMD5(request.getParameter("senha"));
                String razaoSocialInstituicao  = request.getParameter("razao");
                String cnpjInstituicao   = request.getParameter("cnpj");
                ins = new Instituicao();
                ins.setEmail(emailInstituicao);
                ins.setSenha(senhaInstituicao);
                ins.setNome(razaoSocialInstituicao);
                ins.setCnpj(cnpjInstituicao);
                if (Facade.consultarUsuarioExistente(emailInstituicao)){
                    response.sendRedirect("portal.jsp");
                } else {
                    Facade.inserirInstituicao(ins);
                    response.sendRedirect("portal.jsp");                    
                }
                break;
            case "alterarInstituicao":
                Causa causa = Facade.consultarCausa(Integer.parseInt(request.getParameter("causa")));
                
                usuarioId = (Integer)session.getAttribute("usuarioLogado");
                ins = Facade.consultarInstituicao(usuarioId);
                ins.setCnpj(request.getParameter("cnpj"));
                ins.setResponsavel(request.getParameter("responsavel"));
                ins.setCausa(causa);
                ins.setComentario(request.getParameter("comentario"));
                
                cidade.setId(Integer.parseInt(request.getParameter("cidade")));
                end.setCidade(cidade);
                end.setBairro(request.getParameter("bairro"));
                end.setCep(request.getParameter("cep"));
                end.setNumero(request.getParameter("numero"));
                end.setRua(request.getParameter("rua"));
                end.setTelefone(request.getParameter("telefone"));
                ins.setEndereco(end);
                
                Facade.alterarInstituicao(ins);
                response.sendRedirect("ControladoraServlet?action=perfilInstituicao");
                
                break;
            case "logout":
                request.getSession().invalidate();//limpo a sessao e recarrego a page
                response.sendRedirect("portal.jsp");
                break;
            case "perfilVoluntarioEditar":
                usuarioId = (Integer) session.getAttribute("usuarioLogado");
                voluntarioLogado = Facade.consultarVoluntario(usuarioId);
                estados = Facade.listarEstados();
                cidades = Facade.listarCidades(voluntarioLogado.getEndereco().getCidade().getUf().getSigla());
                habilidades = Facade.listarHabilidades();
                causas = Facade.listarCausas();
                rd = getServletContext().getRequestDispatcher("/perfilEditar.jsp");
                request.setAttribute("habilidades", habilidades);
                request.setAttribute("causas", causas);
                request.setAttribute("voluntario", voluntarioLogado);
                request.setAttribute("estados", estados);
                request.setAttribute("cidades", cidades);
                rd.forward(request, response);
                break;
            case "perfilVoluntario":         
                rd = getServletContext().getRequestDispatcher("/perfil.jsp");
                if(request.getParameter("perfil")!=null){
                    vol = Facade.consultarVoluntario(Integer.parseInt(request.getParameter("perfil")));
                    request.setAttribute("voluntario", vol);
                    request.setAttribute("perfil", "alheio");
                }
                else{
                    usuarioId = (Integer) session.getAttribute("usuarioLogado");
                    voluntarioLogado = Facade.consultarVoluntario(usuarioId);
                    request.setAttribute("voluntario", voluntarioLogado);
                    request.setAttribute("perfil", "proprio");
                }
                rd.forward(request, response);
                break;
            case "amigosVisualizar":
                usuarioId = (Integer) session.getAttribute("usuarioLogado");
                voluntarioLogado = Facade.consultarVoluntario(usuarioId);
                
                voluntarios = Facade.listaAmigos(usuarioId);
                
                rd = getServletContext().getRequestDispatcher("/amigos.jsp");
                request.setAttribute("voluntario", voluntarioLogado);
                request.setAttribute("amigos", voluntarios);
                rd.forward(request, response);
                break;
            case "instituicoesVisualizar":
                usuarioId = (Integer) session.getAttribute("usuarioLogado");
                voluntarioLogado = Facade.consultarVoluntario(usuarioId);
                
                instituicoes = Facade.listaInstituicoes(usuarioId);
                
                rd = getServletContext().getRequestDispatcher("/instituicoes.jsp");
                request.setAttribute("voluntario", voluntarioLogado);
                request.setAttribute("instituicoes", instituicoes);
                rd.forward(request, response);
                break;
            case "voluntariosVisualizar":
                usuarioId = (Integer) session.getAttribute("usuarioLogado");
                instituicaoLogada = Facade.consultarInstituicao(usuarioId);
                
                voluntarios = Facade.listaVoluntarios(usuarioId);
                
                rd = getServletContext().getRequestDispatcher("/voluntarios.jsp");
                request.setAttribute("instituicao", instituicaoLogada);
                request.setAttribute("voluntarios", voluntarios);
                rd.forward(request, response);
                break;
            case "pesquisarPessoa":
                rd = getServletContext().getRequestDispatcher("/pesquisarPessoa.jsp");
                
                String pesquisar = request.getParameter("pesquisar");
                if("1".equals(pesquisar)){
                    String vol_nome = request.getParameter("nome");
                    request.setAttribute("nomeSelecionado", vol_nome);
                    String uf_sigla = request.getParameter("uf");
                    request.setAttribute("ufSelecionada", uf_sigla);
                    
                    Integer cidade_id = null;
                    try{
                        cidade_id = Integer.parseInt(request.getParameter("cidade"));
                        cidades = Facade.listarCidades(uf_sigla);
                        request.setAttribute("cidadeSelecionada", cidade_id);
                        request.setAttribute("cidades", cidades);
                    }catch(NumberFormatException ex){
                        cidade_id = null;
                    }
                    
                    Integer causa_id = null;
                    try{
                        causa_id = Integer.parseInt(request.getParameter("causa"));
                        request.setAttribute("causaSelecionada", causa_id);
                    }catch(NumberFormatException ex){
                        causa_id = null;
                    }
                    
                    Integer habilidade_id = null;
                    try{
                        habilidade_id = Integer.parseInt(request.getParameter("habilidade"));
                        request.setAttribute("habilidadeSelecionada", habilidade_id);
                    }catch(NumberFormatException ex){
                        habilidade_id = null;
                    }

                    voluntarios = Facade.pesquisarVoluntarios(vol_nome, uf_sigla, cidade_id, causa_id, habilidade_id);
                    request.setAttribute("amigos", voluntarios);
                }
                
                estados = Facade.listarEstados();
                habilidades = Facade.listarHabilidades();
                causas = Facade.listarCausas();
                
                request.setAttribute("habilidades", habilidades);
                request.setAttribute("causas", causas);
                request.setAttribute("estados", estados);
                rd.forward(request, response);
                break;
            case "pesquisarInstituicao":
                rd = getServletContext().getRequestDispatcher("/pesquisarInstituicao.jsp");
                
                pesquisar = request.getParameter("pesquisar");
                if("1".equals(pesquisar)){
                    String vol_nome = request.getParameter("nome");
                    request.setAttribute("nomeSelecionado", vol_nome);
                    String uf_sigla = request.getParameter("uf");
                    request.setAttribute("ufSelecionada", uf_sigla);
                    
                    Integer cidade_id = null;
                    try{
                        cidade_id = Integer.parseInt(request.getParameter("cidade"));
                        cidades = Facade.listarCidades(uf_sigla);
                        request.setAttribute("cidadeSelecionada", cidade_id);
                        request.setAttribute("cidades", cidades);
                    }catch(NumberFormatException ex){
                        cidade_id = null;
                    }
                    
                    Integer causa_id = null;
                    try{
                        causa_id = Integer.parseInt(request.getParameter("causa"));
                        request.setAttribute("causaSelecionada", causa_id);
                    }catch(NumberFormatException ex){
                        causa_id = null;
                    }

                    instituicoes = Facade.pesquisarInstituicoes(vol_nome, uf_sigla, cidade_id, causa_id);
                    request.setAttribute("amigos", instituicoes);
                }
                
                estados = Facade.listarEstados();
                causas = Facade.listarCausas();
                
                request.setAttribute("causas", causas);
                request.setAttribute("estados", estados);
                rd.forward(request, response);
                break;
            case "pesquisarOportunidade":
                rd = getServletContext().getRequestDispatcher("/pesquisarOportunidade.jsp");
                
                pesquisar = request.getParameter("pesquisar");
                if("1".equals(pesquisar)){
                    String vol_nome = request.getParameter("nome");
                    request.setAttribute("nomeSelecionado", vol_nome);
                    String uf_sigla = request.getParameter("uf");
                    request.setAttribute("ufSelecionada", uf_sigla);
                    
                    Integer cidade_id = null;
                    try{
                        cidade_id = Integer.parseInt(request.getParameter("cidade"));
                        cidades = Facade.listarCidades(uf_sigla);
                        request.setAttribute("cidadeSelecionada", cidade_id);
                        request.setAttribute("cidades", cidades);
                    }catch(NumberFormatException ex){
                        cidade_id = null;
                    }
                    
                    Integer causa_id = null;
                    try{
                        causa_id = Integer.parseInt(request.getParameter("causa"));
                        request.setAttribute("causaSelecionada", causa_id);
                    }catch(NumberFormatException ex){
                        causa_id = null;
                    }
                    
                    Integer habilidade_id = null;
                    try{
                        habilidade_id = Integer.parseInt(request.getParameter("habilidade"));
                        request.setAttribute("habilidadeSelecionada", habilidade_id);
                    }catch(NumberFormatException ex){
                        habilidade_id = null;
                    }
                    
                    String presencial = request.getParameter("presencial");

                    List<Oportunidade> oportunidades = Facade.pesquisarOportunidades(vol_nome, uf_sigla, cidade_id, causa_id, habilidade_id, presencial);
                    request.setAttribute("oportunidades", oportunidades);
                }
                
                estados = Facade.listarEstados();
                habilidades = Facade.listarHabilidades();
                causas = Facade.listarCausas();
                
                request.setAttribute("habilidades", habilidades);
                request.setAttribute("causas", causas);
                request.setAttribute("estados", estados);
                rd.forward(request, response);
                break;
            case "alterarVoluntarioFoto":
                /* inicio arquivo*/
                Part part = request.getPart("imagem");
                String fileName = extractFileName(part);
                //String savePath = "\\Users\\Ciro\\Documents\\NetBeansProjects\\tcc\\web\\images\\Post\\" + fileName;
                String savePath = "\\Users\\Avell B155 FIRE.Avell-B155FIRE\\Documents\\GitHub\\portal-voluntariado\\web\\images\\Friends\\" + fileName;
                //String savePath = "\\Android\\" + fileName;
                //get real path. serverlet context
                File fileSaveDir = new File(savePath);
                part.write(savePath + File.separator);
                //newPost.setImagem("c:"+savePath);
                String armazenar = ".\\images\\Post\\"+fileName;
                
                usuarioId = (Integer)session.getAttribute("usuarioLogado");
                u = Facade.consultarUsuarioId(usuarioId);
                String tipo = Facade.consultaUsuarioTipo(u.getUsuario());
                u.setTipo(tipo);
                Facade.perfilFoto(u, fileName);
                if("V".equals(tipo)){
                    response.sendRedirect("ControladoraServlet?action=perfilVoluntario");
                }
                else if("I".equals(tipo)){
                    response.sendRedirect("ControladoraServlet?action=perfilInstituicao");
                }
                /* termino arquivo*/
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
