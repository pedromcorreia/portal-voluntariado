<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Voluntarius</title>
        <jsp:include page="cabecalho.jsp"/>
        <link href="./css/cover.css" rel="stylesheet">
        <link href="./css/friends.css" rel="stylesheet">
        <link href="./css/sidebar_profile.css" rel="stylesheet">
    </head>
  <body class="animated fadeIn">

    <!-- Fixed navbar -->
    <jsp:include page="menuPpal.jsp"/>

    <!-- Begin page content -->
    <div id="wrapper" class="wrapper-content">
	<form action="ControladoraServlet?action=pesquisarInstituicao&pesquisar=1" method="POST">
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav"  style="margin-top:40px;">
                <li class="active">
                    <div>Estado:</div>
                    <div style="padding:0 20px 0 20px;">
                        <select name="uf" id="uf" class="form-control">
                            <option></option>
                            <c:forEach var="estado" items="${estados}">
                                <option ${estado.sigla == ufSelecionada ? "selected": ""} value="<c:out value="${estado.sigla}" />"><c:out value="${estado.descricao}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </li>
                <li>
                    <div>Cidade:</div>
                    <div style="padding:0 20px 0 20px;">
                        <select name="cidade" id="cidade" class="form-control">
                            <option></option>
                            <c:forEach var="cidade" items="${cidades}">
                                <option ${cidade.id == cidadeSelecionada ? "selected": ""} value="<c:out value="${cidade.id}" />"><c:out value="${cidade.descricao}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </li>
                <li>
                    <div>Causa:</div>
                    <div style="padding:0 20px 0 20px;">
                        <select class="form-control" name="causa" id="causa">
                            <option></option>
                            <c:forEach var="causa" items="${causas}">
                                <option ${causa.id == causaSelecionada? "selected" : ""} value="<c:out  value="${causa.id}" />"><c:out value="${causa.descricao}" /></option>  
                            </c:forEach>
                        </select>
                    </div>
                </li>
            </ul>
        </div>
		
		<div id="page-content-wrapper">
		  <div>
			<div class="row" style="padding-bottom:20px;">
				<div class="col-md-12">
				<div class="form-group">
					<span class="input-icon">
                                                <input type="text" class="form-control input-sm" name="nome" value="<c:out value="${nomeSelecionado}" />" placeholder="Insira o nome ou parte do nome">
						<i class="glyphicon glyphicon-search danger circular"></i>
					</span>
				</div>
				<div><button type="submit" class="btn btn-maroon shiny">Pesquisar</button></div>
				</div>
			</div>
			<div class="row">
			  <c:forEach var="friend" items="${amigos}">
                            <div class="col-md-3">
                                <div class="contact-box center-version">
                                  <a href="ControladoraServlet?action=perfilInstituicao&perfil=<c:out value="${friend.usuario}" />">

                                    <c:choose>
                                    <c:when test="${friend.foto!=null}">    
                                      <img alt="image" class="img-circle" src="images/Friends/<c:out value="${friend.foto}" />" >
                                    </c:when>
                                    <c:otherwise>
                                      <img alt="image" class="img-circle" src="images/Friends/fa-user.png" >
                                    </c:otherwise>
                                    </c:choose>

                                    <h3 class="m-b-xs"><strong><c:out value="${friend.nome}" /></strong></h3>

                                    <!--div class="font-bold">Graphics designer</div-->
                                  </a>
                                  <div class="contact-box-footer">
                                    <div class="m-t-xs btn-group">
                                      <!--a href="messages1.html" class="btn btn-xs btn-white"><i class="fa fa-envelope"></i>Send Messages</a>
                                      <a class="btn btn-xs btn-white"><i class="fa fa-user-plus"></i> Follow</a-->
                                      <a class="btn btn-xs btn-white"><i class="fa fa-user-times"></i> Desfazer Amizade</a>
                                    </div>
                                  </div>
                                </div>
                            </div>
                          </c:forEach>
                        </div>
		  </div>
		</div>
        </form>
		<footer class="footer">
		  <div class="container">
			<p class="text-muted"> Copyright &copy; Company - All rights reserved </p>
		  </div>
		</footer>
	</div>

    <!-- Modal -->
    <div class="modal fade" id="modalShow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
          </div>
          <div class="modal-body text-centers">
            ...
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>

    
  </body>
</html>