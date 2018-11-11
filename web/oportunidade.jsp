<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="erro.jsp" %>
<!--%@ page content Type="text /html; charset=ISO-8859-1" pageEncoding="UTF-8"%-->
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Voluntarius - Visualizar Oportunidade</title>
        <jsp:include page="cabecalho.jsp"/>
        <link href="./css/cover.css" rel="stylesheet">
        <link href="./css/sidebar_profile.css" rel="stylesheet">
    </head>
    <body>

    <!-- Fixed navbar -->
    <jsp:include page="menuPpal.jsp"/>

    <!-- Begin page content -->
        <div class="container page-content"><!--div id="wrapper" class="wrapper-content"-->
            <form action="ControladoraPost?action=gravarOportunidade" method="POST">
                <input type="hidden" name="oportunidadeId" value="<c:out value="${post.oportunidade.id}" />">
                <!--div id="page-content-wrapper"-->
                    <!--div class="container-fluid"-->
                        <div class="row">
                          <div class="col-md-12">

                            <div class="widget">
                              <div class="widget-header">
                                <h3 class="widget-caption">Criar Oportunidade</h3>
                              </div>
                              <div class="widget-body bordered-top bordered-sky">
                                <ul class="list-unstyled profile-about margin-none">
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Descrição</span></div>
                                      <div class="col-sm-8"><c:out value="${post.descricao}"/></div>
                                    </div>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Presencial</span></div>
                                      <div class="col-sm-8">${post.oportunidade.presencial == "S" ? "Sim" : "Não"}</div>
                                    </div>
                                  </li>
                                  <li class="img-profile-content">
                                    <c:choose>
                                      <c:when test="${post.imagem != null}"> 
                                        <div id="preview"><img  src="images/Friends/<c:out value="${post.imagem}"/>" class="img-circle img-thumbnails"></div>
                                      </c:when>
                                      <c:otherwise>
                                        <div id="perfilFoto"><img  src="images/Friends/empty.jpg" class="img-circle img-thumbnails"></div>
                                      </c:otherwise>
                                    </c:choose>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Data de Início</span></div>
                                      <div class="col-sm-8"><fmt:formatDate value="${post.oportunidade.inicio.getTime()}" pattern="dd/MM/yyyy" /></div>
                                    </div>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Data de Finalização</span></div>
                                      <div class="col-sm-8"><fmt:formatDate value="${post.oportunidade.termino.getTime()}" pattern="dd/MM/yyyy" /></div>
                                    </div>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Vagas</span></div>
                                      <div class="col-sm-8"><c:out value="${post.oportunidade.vagasTotal}"/></div>
                                    </div>
                                  </li>
                                  
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Carga Horária</span></div>
                                      <div class="col-sm-8"><c:out value="${post.oportunidade.cargaHorariaTotal}"/></div>
                                    </div>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Status</span></div>
                                      <div class="col-sm-8">
                                      <c:choose>
                                          <c:when test="${post.oportunidade.status == 'E'}">
                                              <c:out value="Editada"/>
                                          </c:when>
                                          <c:when test="${post.oportunidade.status == 'P'}">
                                              <c:out value="Publicada"/>
                                          </c:when>
                                          <c:when test="${post.oportunidade.status == 'F'}">
                                              <c:out value="Finalizada"/>
                                          </c:when>                                          
                                      </c:choose>
                                      </div>
                                    </div>
                                  </li>
                                  
                                  
                                </ul>
                              </div>
                            </div>
                                    <input type="button" onclick="location.href='ControladoraPost?action=oportunidadeEditar&postId=<c:out value="${post.id}"/>';" class="btn btn-primary shiny" value="Editar Oportunidade" />
                          </div>
                        </div>
                    <!--/div-->
                <!--/div-->
            </form>
    </div>
    <footer class="footer">
      <div class="container">
        <p class="text-muted"> Copyright &copy; Company - All rights reserved </p>
      </div>
    </footer>
    </body>
</html>
