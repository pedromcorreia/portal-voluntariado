<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Voluntarius -Perfil</title>
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
                    <c:when test="${instituicao.foto != null}"> 
                      <div id="perfilFoto"><img  src="images/Friends/<c:out value="${instituicao.foto}"/>" class="img-circle img-thumbnails"></div>
                    </c:when>
                    <c:otherwise>
                      <div id="perfilFoto"><img  src="images/Friends/empty.jpg" class="img-circle img-thumbnails"></div>
                    </c:otherwise>
                  </c:choose>
                </li>
                <c:if test="${perfil == 'proprio'}">
                <li class="active">
                    <a href="ControladoraServlet?action=voluntariosVisualizar">Voluntários</a>
                </li>
                <li class="active">
                    <input type="button" class="btn btn-primary shiny" onclick="javascript:location.href='ControladoraPost?action=oportunidadeEditar'" value="Criar Oportunidade" />
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
                        <h3 class="widget-caption">Instituição - Detalhes 
                          <c:if test="${perfil == 'proprio'}">
                            - <a href="ControladoraServlet?action=perfilInstituicaoEditar">(Editar Perfil)</a>
                          </c:if>
                        
                        </h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <ul class="list-unstyled profile-about margin-none">
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Nome</span></div>
                              <div class="col-sm-8"><c:out value="${instituicao.nome}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">CNPJ</span></div>
                              <div class="col-sm-8"><c:out value="${instituicao.cnpj}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Responsável</span></div>
                              <div class="col-sm-8"><c:out value="${instituicao.responsavel}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Endereço</span></div>
                              <div class="col-sm-8"><c:out value="Rua ${instituicao.endereco.rua} nº ${instituicao.endereco.numero}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">CEP</span></div>
                              <div class="col-sm-8"><c:out value="${instituicao.endereco.cep}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Estado</span></div>
                              <div class="col-sm-8"><c:out value="${instituicao.endereco.cidade.uf.descricao}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Cidade</span></div>
                              <div class="col-sm-8"><c:out value="${instituicao.endereco.cidade.descricao}" /></div>
                            </div>
                          </li>
                        </ul>
                      </div>
                    </div>
                      
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Nossa Causa</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <ul class="list-unstyled team-members">
                                <li>
                                  <div class="row">
                                      <div class="col-xs-3">
                                          <div class="avatar">
                                              <img src="images/Likes/<c:out value="${instituicao.causa.img}" />" alt="Circle Image" class="img-circle img-no-padding img-responsive">
                                          </div>
                                      </div>
                                      <div class="col-xs-3">
                                         <c:out value="${instituicao.causa.descricao}" />
                                      </div>
                                  </div>
                                </li>
                            </ul>
                          </div>
                        </div>  
                      </div>
                    </div>
                      
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Quem Somos</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <ul class="list-unstyled team-members">
                              <li>
                                <div class="row">
                                    <div class="col-xs-12 text-left">
                                        <c:out value="${instituicao.comentario}" />
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
