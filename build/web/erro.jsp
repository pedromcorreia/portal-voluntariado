<%-- 
    Document   : Erro
    Created on : 11/04/2018, 16:48:24
    Author     : Avell B155 FIRE
--%>
<%@ page isErrorPage="true" %>
<%
    String msg = (String)request.getAttribute("msg");
    String link = (String)request.getAttribute("page");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de Erro</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <style>
            footer {
                position: fixed;
                height: 100px;
                bottom: 0;
                width: 100%;
                text-align: center;
            }
        </style>
    </head>
    <body>
        
        <%--MENU--%>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Portal</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                  <a class="nav-link" href="ClientesServlet">Cadastro de Clientes</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="AtendimentoServlet?action=formNew">Efetuar Atendimento</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="AtendimentoServlet">Mostrar Atendimentos</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="LogoutServlet">Sair</a>
                </li>
              </ul>
            </div>
        </nav>
        
        <h1>Opa... Lamentamos, mas ocorreu um erro!</h1>
        <table class="table">
            <tr>
                <th>Item</th>
                <th>Conteúdo</th>
            </tr>
            <tr>
                <td>Mensagem</td>
                <td style="color:red;">${pageContext.exception.message}</td>
            </tr>
            <tr>
                <td>StackTrace</td>
                <td>${pageContext.out.flush()}${pageContext.exception.printStackTrace(pageContext.response.writer)}</td>
            </tr>
        </table>
        
        
    </body>
</html>
