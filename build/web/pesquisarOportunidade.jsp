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
	<form action="ControladoraServlet?action=pesquisarOportunidade&pesquisar=1" method="POST">
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
                <li>
                    <div>Habilidade:</div>
                    <div style="padding:0 20px 0 20px;">
                        <select class="form-control" name="habilidade" id="habilidade"> <!--style="width:200px; overflow:hidden"-->
                            <option></option>
                            <c:forEach var="habilidade" items="${habilidades}">
                                <option ${habilidade.id == habilidadeSelecionada? "selected" : ""} value="<c:out  value="${habilidade.id}" />"><c:out value="${habilidade.descricao}" /></option>  
                            </c:forEach>
                        </select>
                    </div>
                </li>
                <li>
                    <div style="text-align: left;">
                        <label>
                            <input type="checkbox" checked class="colored-blue" value="S" name="presencial">
                            <span class="text">Presencial</span>
                        </label>
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
                                                <input type="text" class="form-control input-sm" name="nome" value="<c:out value="${nomeSelecionado}" />" placeholder="Insira o título da oportunidade de voluntariado ou o nome da ong anfitriã.">
						<i class="glyphicon glyphicon-search danger circular"></i>
					</span>
				</div>
				<div><button type="submit" class="btn btn-maroon shiny">Pesquisar</button></div>
				</div>
			</div>
			<div class="row">
			  <c:forEach var="oportunidade" items="${oportunidades}">
                            <div class="col-md-3">
                                <div class="contact-box center-version">
                                  <a href="ControladoraServlet?action=perfilInstituicao&perfil=<c:out value="${oportunidade.id}" />">

                                    <c:choose>
                                    <c:when test="${oportunidade.postPai.usuario.foto!=null}">    
                                      <img alt="image" class="img-circle" src="images/Friends/<c:out value="${oportunidade.postPai.usuario.foto}" />" >
                                    </c:when>
                                    <c:otherwise>
                                      <img alt="image" class="img-circle" src="images/Friends/fa-user.png" >
                                    </c:otherwise>
                                    </c:choose>

                                    <h3 class="m-b-xs"><strong><c:out value="${oportunidade.postPai.usuario.nome}" /> - <c:out value="${oportunidade.postPai.descricao}" /></strong></h3>
                                  </a>
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