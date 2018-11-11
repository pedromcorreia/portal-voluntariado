<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="erro.jsp" %>
<!--%@ page content Type="text /html; charset=ISO-8859-1" pageEncoding="UTF-8"%-->
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Voluntarius - Editar Oportunidade</title>
        <jsp:include page="cabecalho.jsp"/>
        <link href="./css/cover.css" rel="stylesheet">
        <link href="./css/sidebar_profile.css" rel="stylesheet">
    </head>
    <body>

    <!-- Fixed navbar -->
    <jsp:include page="menuPpal.jsp"/>

    <!-- Begin page content -->
        <div class="container page-content"><!--div id="wrapper" class="wrapper-content"-->
            <form action="ControladoraPost" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="action" value="gravarOportunidade">
                <input type="hidden" name="oportunidadeId" value="<c:out value="${post.id}" />">
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
                                      <div class="col-sm-8"><input type="text" class="form-control" placeholder="Título da Oportunidade" value="<c:out value="${post.descricao}"/>" name="descricao" id="descricao" > </div>
                                    </div>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Presencial</span></div>
                                      <div class="col-sm-8">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="presencial" value="S" checked="checked">
                                                <span class="text">Sim </span>
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="presencial" value="N">
                                                <span class="text">Não</span>
                                            </label>
                                        </div>
                                          
                                      </div>
                                    </div>
                                  </li>
                                  <li class="img-profile-content">
                                    <a id="FotoAltera" href="#" onclick="chooseFile();">
                                        <c:choose>
                                          <c:when test="${post.imagem != null}"> 
                                            <div id="preview"><img  src="images/Friends/<c:out value="${post.imagem}"/>" class="img-circle img-thumbnails"></div>
                                          </c:when>
                                          <c:otherwise>
                                            <div id="preview"><img  src="images/Friends/alterar.jpg" class="img-circle img-thumbnails"></div>
                                          </c:otherwise>
                                        </c:choose>
                                        <div id="perfilFotoAlt" style="display: none;"><img  src="images/Friends/alterar.jpg" class="img-circle img-thumbnails"></div>
                                    </a>
                                    <div style="height:0px;overflow:hidden"><input type="file" accept="image/*" id="imagem" name="imagem" ></div>
                                    <input type="hidden" id="alterouFoto" name="alterouFoto" value="<c:out value="${post.imagem}"/>">
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Data de Início</span></div>
                                      <div class="col-sm-8"><input type="text" name="dataIni" required="true" value="<fmt:formatDate value="${post.oportunidade.inicio.getTime()}" pattern="dd/MM/yyyy" />" class="calendario form-control" ></div>
                                    </div>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Data de Finalização</span></div>
                                      <div class="col-sm-8"><input type="text" name="dataFim" required="true" value="<fmt:formatDate value="${post.oportunidade.termino.getTime()}" pattern="dd/MM/yyyy" />" class="calendario form-control" ></div>
                                    </div>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Vagas</span></div>
                                      <div class="col-sm-8"><input type="text" class="form-control" placeholder="Indique a quantidade de vagas" value="<c:out value="${post.oportunidade.vagasTotal}"/>" name="vagasTotal" id="vagasTotal" ></div>
                                    </div>
                                  </li>
                                  
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Carga Horária</span></div>
                                      <div class="col-sm-8"><input type="text" class="form-control" placeholder="Indique a quantidade de horas totais desta oportunidade" value="<c:out value="${post.oportunidade.cargaHorariaTotal}"/>" name="cargaHorariaTotal" id="cargaHorariaTotal" ></div>
                                    </div>
                                  </li>
                                  <li class="padding-v-5">
                                    <div class="row">
                                      <div class="col-sm-4"><span class="text-muted">Status</span></div>
                                      <div class="col-sm-8">
                                          <select name="status">
                                              <option></option>
                                              <option ${post.oportunidade.status == "E" ? "selected" : ""} value="E">Editada</option>
                                              <option ${post.oportunidade.status == "P" ? "selected" : ""} value="P">Publicada</option>
                                              <option ${post.oportunidade.status == "F" ? "selected" : ""} value="F">Finalizada</option>
                                          </select>
                                      </div>
                                    </div>
                                  </li>
                                  
                                  
                                </ul>
                              </div>
                            </div>

                            <input type="submit" class="btn btn-primary shiny" value="Gravar" />
                            

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
