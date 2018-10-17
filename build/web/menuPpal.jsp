<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <nav class="navbar navbar-white navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html"><b>Voluntarius</b></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="actives">
                
            </li>
            <li><a href="home.html">Home</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                Pages <span class="caret"></span>
              </a>
              <ul class="dropdown-menu">
                <li><a href="ControladoraPost?action=timeline">Timeline</a></li>  
                <li><a href="ControladoraServlet?action=pesquisarPessoa">Pesquisar Pessoas</a></li>
                <li><a href="ControladoraServlet?action=pesquisarInstituicao">Pesquisar Instituições</a></li>
                <li><a href="ControladoraServlet?action=pesquisarOportunidade">Pesquisar Oportunidades</a></li>
              </ul>
            </li>
            <li>
              <a class="btn-menu btn btn-azure btn-toggle-menu" href="#">
                <i class="fa fa-bars"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>