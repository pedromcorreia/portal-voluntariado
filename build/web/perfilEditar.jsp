<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="erro.jsp" %>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Perfil</title>
        <jsp:include page="cabecalho.jsp"/>
        <link href="./css/cover.css" rel="stylesheet">
        <link href="./css/sidebar_profile.css" rel="stylesheet">
    </head>
    <body>

    <!-- Fixed navbar -->
    <jsp:include page="menuPpal.jsp"/>

    <!-- Begin page content -->
    
    <div id="wrapper" class="wrapper-content">
        <!--form id="dataPhoto" method="post" enctype="multipart/form-data"-->
        <form action="ControladoraServletUpload?action=alterarVoluntarioFoto" method="POST" enctype="multipart/form-data">
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav"  style="margin-top:40px;">
                    <li class="img-profile-content">
                      <a id="FotoAltera" href="#" onclick="chooseFile();">
                          <c:choose>
                            <c:when test="${voluntario.foto != null}"> 
                              <div id="perfilFoto"><img  src="images/Friends/<c:out value="${voluntario.foto}"/>" class="img-circle img-thumbnails"></div>
                            </c:when>
                            <c:otherwise>
                              <div id="perfilFoto"><img  src="images/Friends/alterar.jpg" class="img-circle img-thumbnails"></div>
                            </c:otherwise>
                          </c:choose>
                          <div id="perfilFotoAlt" style="display: none;"><img  src="images/Friends/alterar.jpg" class="img-circle img-thumbnails"></div>
                      </a>
                      <div style="height:0px;overflow:hidden"><input type="file" accept="image/*" id="imagem" name="imagem" ></div>
                    </li>
                    <li>
                        <button type="submit" class="btn btn-primary" value="foto">Enviar Foto</button>
                    </li>
                    <li class="active">
                        <a href="#">Instituições</a>
                    </li>
                    <li>
                        <a href="#">Amigos</a>
                    </li>

                </ul>
            </div>
        </form>
        
        <form action="ControladoraServlet?action=alterarVoluntario" method="POST">
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                  <div class="col-md-12">
                    
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Sobre mim</h3>
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
                              <div class="col-sm-4"><span class="text-muted">Cpf</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira seu CPF" value="<c:out value="${voluntario.cpf}"/>" name="cpf" id="cpf" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">CEP</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o CEP" value="<c:out value="${voluntario.endereco.cep}"/>" name="cep" id="cep" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Rua</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o nome da rua" value="<c:out value="${voluntario.endereco.rua}"/>" name="rua" id="rua" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Numero</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o número" value="<c:out value="${voluntario.endereco.numero}"/>" name="numero" id="numero" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Bairro</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o bairro" value="<c:out value="${voluntario.endereco.bairro}"/>" name="bairro" id="bairro" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Telefone</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o telefone" value="<c:out value="${voluntario.endereco.telefone}"/>" name="telefone" id="telefone" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">UF</span></div>
                              <div class="col-sm-8">
                                <select name="uf" id="uf" class="form-control">
                                    <option></option>
                                    <c:forEach var="estado" items="${estados}">
                                        <option ${estado.sigla == voluntario.endereco.cidade.uf.sigla ? "selected": ""} value="<c:out value="${estado.sigla}" />"><c:out value="${estado.descricao}" /></option>
                                    </c:forEach>
                                </select>
                              </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Cidade</span></div>
                              <div class="col-sm-8">
                                <select name="cidade" id="cidade" class="form-control">
                                    <option></option>
                                    <c:forEach var="cidade" items="${cidades}">
                                        <option ${cidade.id == voluntario.endereco.cidade.id ? "selected": ""} value="<c:out value="${cidade.id}" />"><c:out value="${cidade.descricao}" /></option>
                                    </c:forEach>
                                </select>
                              </div>
                            </div>
                          </li>
                        </ul>
                      </div>
                    </div>
                      
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Causas que eu abraço</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <c:forEach var="volCausa" items="${voluntario.causas}">
                                <c:choose>
                                    <c:when test="${volCausa.ordem == 1}">
                                        <jsp:useBean id="volCausa1" class="model.Causa" scope="request" />
                                        <jsp:setProperty name="volCausa1" property="id" value="${volCausa.id}" />
                                        <jsp:setProperty name="volCausa1" property="comentario" value="${volCausa.comentario}" />
                                    </c:when>
                                    <c:when test="${volCausa.ordem == 2}">
                                        <jsp:useBean id="volCausa2" class="model.Causa" scope="request" />
                                        <jsp:setProperty name="volCausa2" property="id" value="${volCausa.id}" />
                                        <jsp:setProperty name="volCausa2" property="comentario" value="${volCausa.comentario}" />
                                    </c:when>
                                    <c:when test="${volCausa.ordem == 3}">
                                        <jsp:useBean id="volCausa3" class="model.Causa" scope="request" />
                                        <jsp:setProperty name="volCausa3" property="id" value="${volCausa.id}" />
                                        <jsp:setProperty name="volCausa3" property="comentario" value="${volCausa.comentario}" />
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <ul class="list-unstyled team-members">
                              <li>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select class="form-control" name="causa1" id="causa1"> <!--style="width:200px; overflow:hidden"-->
                                            <option></option>
                                            <c:forEach var="causa" items="${causas}">
                                                <option ${volCausa1.id == causa.id ? "selected" : ""}  value="<c:out  value="${causa.id}" />"><c:out value="${causa.descricao}" /></option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                        
                                    <div class="col-xs-8 text-right">
                                        <input type="text" class="form-control" placeholder="Fale sobre sua causa" value="<c:out value="${volCausa1.comentario}"/>" name="comentCau1" id="comentCau1">
                                    </div>
                                </div>
                              </li>
                              <li>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select class="form-control" name="causa2" id="causa2"> <!--style="width:200px; overflow:hidden"-->
                                            <option></option>
                                            <c:forEach var="causa" items="${causas}">
                                                <option ${volCausa2.id == causa.id ? "selected" : ""} value="<c:out value="${causa.id}" />"><c:out value="${causa.descricao}" /></option>  <!--$//{atendimento.tipoAtendimento.id == tipoAtendimento.id ? "selected": ""}-->
                                            </c:forEach>
                                        </select>
                                    </div>
                        
                                    <div class="col-xs-8 text-right">
                                        <input type="text" class="form-control" placeholder="Fale sobre sua causa" value="<c:out value="${volCausa2.comentario}"/>" name="comentCau2" id="comentCau2">
                                    </div>
                                </div>
                              </li>
                              <li>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select class="form-control" name="causa3" id="causa3"> <!--style="width:200px; overflow:hidden"-->
                                            <option></option>
                                            <c:forEach var="causa" items="${causas}">
                                                <option ${volCausa3.id == causa.id ? "selected" : ""} value="<c:out value="${causa.id}" />"><c:out value="${causa.descricao}" /></option>  <!--$//{atendimento.tipoAtendimento.id == tipoAtendimento.id ? "selected": ""}-->
                                            </c:forEach>
                                        </select>
                                    </div>
                        
                                    <div class="col-xs-8 text-right">
                                        <input type="text" class="form-control" placeholder="Fale sobre sua causa" value="<c:out value="${volCausa3.comentario}"/>" name="comentCau3" id="comentCau3">
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
                        <h3 class="widget-caption">Minhas Habilidades</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <c:forEach var="volHab" items="${voluntario.habilidades}">
                                <c:choose>
                                    <c:when test="${volHab.ordem == 1}">
                                        <jsp:useBean id="volHab1" class="model.Habilidade" scope="request" />
                                        <jsp:setProperty name="volHab1" property="id" value="${volHab.id}" />
                                        <jsp:setProperty name="volHab1" property="comentario" value="${volHab.comentario}" />
                                    </c:when>
                                    <c:when test="${volHab.ordem == 2}">
                                        <jsp:useBean id="volHab2" class="model.Habilidade" scope="request" />
                                        <jsp:setProperty name="volHab2" property="id" value="${volHab.id}" />
                                        <jsp:setProperty name="volHab2" property="comentario" value="${volHab.comentario}" />
                                    </c:when>
                                    <c:when test="${volHab.ordem == 3}">
                                        <jsp:useBean id="volHab3" class="model.Habilidade" scope="request" />
                                        <jsp:setProperty name="volHab3" property="id" value="${volHab.id}" />
                                        <jsp:setProperty name="volHab3" property="comentario" value="${volHab.comentario}" />
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <ul class="list-unstyled team-members">
                              <li>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select class="form-control" name="habilidade1" id="habilidade1"> <!--style="width:200px; overflow:hidden"-->
                                            <option></option>
                                            <c:forEach var="habilidade" items="${habilidades}">
                                                <option ${volHab1.id == habilidade.id ? "selected" : ""}  value="<c:out  value="${habilidade.id}" />"><c:out value="${habilidade.descricao}" /></option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                        
                                    <div class="col-xs-8 text-right">
                                        <input type="text" class="form-control" placeholder="Fale sobre sua habilidade" value="<c:out value="${volHab1.comentario}"/>" name="comentHab1" id="comentHab1">
                                    </div>
                                </div>
                              </li>
                              <li>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select class="form-control" name="habilidade2" id="habilidade2"> <!--style="width:200px; overflow:hidden"-->
                                            <option></option>
                                            <c:forEach var="habilidade" items="${habilidades}">
                                                <option ${volHab2.id == habilidade.id ? "selected" : ""} value="<c:out value="${habilidade.id}" />"><c:out value="${habilidade.descricao}" /></option>  <!--$//{atendimento.tipoAtendimento.id == tipoAtendimento.id ? "selected": ""}-->
                                            </c:forEach>
                                        </select>
                                    </div>
                        
                                    <div class="col-xs-8 text-right">
                                        <input type="text" class="form-control" placeholder="Fale sobre sua habilidade" value="<c:out value="${volHab2.comentario}"/>" name="comentHab2" id="comentHab2">
                                    </div>
                                </div>
                              </li>
                              <li>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select class="form-control" name="habilidade3" id="habilidade3"> <!--style="width:200px; overflow:hidden"-->
                                            <option></option>
                                            <c:forEach var="habilidade" items="${habilidades}">
                                                <option ${volHab3.id == habilidade.id ? "selected" : ""} value="<c:out value="${habilidade.id}" />"><c:out value="${habilidade.descricao}" /></option>  <!--$//{atendimento.tipoAtendimento.id == tipoAtendimento.id ? "selected": ""}-->
                                            </c:forEach>
                                        </select>
                                    </div>
                        
                                    <div class="col-xs-8 text-right">
                                        <input type="text" class="form-control" placeholder="Fale sobre sua habilidade" value="<c:out value="${volHab3.comentario}"/>" name="comentHab3" id="comentHab3">
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
                        <h3 class="widget-caption">Minha experiência como voluntário</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <ul class="list-unstyled team-members">
                              <li>
                                <div class="row">
                                    <div class="col-xs-12 text-left">
                                        <textarea rows="4" class="form-control" placeholder="Fale um pouco sobre sua experiência" name="comentario" id="comentario"><c:out value="${voluntario.comentario}"/></textarea>
                                    </div>
                                </div>
                              </li>
                            </ul>
                          </div>
                        </div>  
                      </div>
                    </div>
                    
                    <input type="submit" class="btn btn-primary shiny" value="Gravar" />
                    <input type="button" class="btn btn-primary shiny" onclick="javascript:location.href='ControladoraServlet?action=perfilVoluntario'" value="Voltar" />
                    
                  </div>
                </div>
                
                
                
            </div>
            
            
            
        </div>
        <footer class="footer">
          <div class="container">
            <p class="text-muted"> Copyright &copy; Company - All rights reserved </p>
          </div>
        </footer>
    </form>
    </div>
    
  </body>
</html>
