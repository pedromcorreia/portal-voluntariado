<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Voluntarius - Perfil</title>
        <jsp:include page="cabecalho.jsp"/>
        <link href="./css/cover.css" rel="stylesheet">
        <link href="./css/sidebar_profile.css" rel="stylesheet">
    </head>
    <body>

    <!-- Fixed navbar -->
    <jsp:include page="menuPpal.jsp"/>

    <!-- Begin page content -->
    <div id="wrapper" class="wrapper-content">
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav"  style="margin-top:40px;">
                <li class="img-profile-content">
                  <c:choose>
                    <c:when test="${voluntario.foto != null}"> 
                      <div id="perfilFoto"><img  src="images/Friends/<c:out value="${voluntario.foto}"/>" class="img-circle img-thumbnails"></div>
                    </c:when>
                    <c:otherwise>
                      <div id="perfilFoto"><img  src="images/Friends/empty.jpg" class="img-circle img-thumbnails"></div>
                    </c:otherwise>
                  </c:choose>
                </li>
                <c:if test="${perfil == 'proprio'}">
                <li class="active">
                    <a href="ControladoraServlet?action=instituicoesVisualizar">Instituições</a>
                </li>
                <li>
                    <a href="ControladoraServlet?action=amigosVisualizar">Amigos</a>
                </li>
                </c:if>
            </ul>
        </div>

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                  <div class="col-md-12">
                    
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Sobre mim 
                          <c:if test="${perfil == 'proprio'}">
                            - <a href="ControladoraServlet?action=perfilVoluntarioEditar">(Editar Perfil)</a>
                          </c:if>
                        </h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <ul class="list-unstyled profile-about margin-none">
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Nome</span></div>
                              <div class="col-sm-8"><c:out value="${voluntario.nome}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Estado</span></div>
                              <div class="col-sm-8"><c:out value="${voluntario.endereco.cidade.uf.descricao}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Cidade</span></div>
                              <div class="col-sm-8"><c:out value="${voluntario.endereco.cidade.descricao}" /></div>
                            </div>
                          </li>
                        </ul>
                      </div>
                    </div>
                      
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Causas que me Inspiram</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <ul class="list-unstyled team-members">
                              
                              <c:forEach var="causa" items="${voluntario.causas}">
                                <li>
                                  <div class="row">
                                      <div class="col-xs-3">
                                          <div class="avatar">
                                              <img src="images/Likes/<c:out value="${causa.img}" />" alt="Circle Image" class="img-circle img-no-padding img-responsive">
                                          </div>
                                      </div>
                                      <div class="col-xs-3">
                                         <c:out value="${causa.descricao}" />
                                      </div>

                                      <div class="col-xs-6 text-right">
                                          <c:out value="${causa.comentario}" />
                                      </div>
                                  </div>
                                </li>
                              </c:forEach>
                              
                            </ul>
                          </div>
                        </div>  
                      </div>
                    </div>
                            
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Minhas Habilidades</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <ul class="list-unstyled team-members">
                              
                              <c:forEach var="habilidade" items="${voluntario.habilidades}">
                                <li>
                                  <div class="row">
                                      <div class="col-xs-3">
                                          <div class="avatar">
                                              <img src="images/Likes/<c:out value="${habilidade.img}" />" alt="Circle Image" class="img-circle img-no-padding img-responsive">
                                          </div>
                                      </div>
                                      <div class="col-xs-3">
                                         <c:out value="${habilidade.descricao}" />
                                      </div>

                                      <div class="col-xs-6 text-right">
                                          <c:out value="${habilidade.comentario}" />
                                      </div>
                                  </div>
                                </li>
                              </c:forEach>
                              
                            </ul>
                          </div>
                        </div>  
                      </div>
                    </div>
                      
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Minha experiência como voluntário</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <ul class="list-unstyled team-members">
                              <li>
                                <div class="row">
                                    <div class="col-xs-12 text-left">
                                        <c:out value="${voluntario.comentario}" />
                                    </div>
                                </div>
                              </li>
                            </ul>
                          </div>
                        </div>  
                      </div>
                    </div>
                    
                      
                  </div>
                </div>
            </div>
        </div>
        <footer class="footer">
          <div class="container">
            <p class="text-muted"> Copyright &copy; Company - All rights reserved </p>
          </div>
        </footer>
    </div>

  </body>
</html>
