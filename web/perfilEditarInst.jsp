<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="erro.jsp" %>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Voluntarius - Editar Perfil</title>
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
                            <c:when test="${instituicao.foto != null}"> 
                              <div id="perfilFoto"><img  src="images/Friends/<c:out value="${instituicao.foto}"/>" class="img-circle img-thumbnails"></div>
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
        
        <form action="ControladoraServlet?action=alterarInstituicao" method="POST">
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                  <div class="col-md-12">
                    
                    <div class="widget">
                      <div class="widget-header">
                        <h3 class="widget-caption">Sobre a Instituição</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <ul class="list-unstyled profile-about margin-none">
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Razao</span></div>
                              <div class="col-sm-8"><c:out value="${instituicao.nome}" /></div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">CNPJ</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira seu CNPJ" value="<c:out value="${instituicao.cnpj}"/>" name="cnpj" id="cnpj" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Responsável</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o nome do Responsável" value="<c:out value="${instituicao.responsavel}"/>" name="responsavel" id="responsavel" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">CEP</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o CEP" value="<c:out value="${instituicao.endereco.cep}"/>" name="cep" id="cep" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Rua</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o nome da rua" value="<c:out value="${instituicao.endereco.rua}"/>" name="rua" id="rua" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Numero</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o número" value="<c:out value="${instituicao.endereco.numero}"/>" name="numero" id="numero" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Bairro</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o bairro" value="<c:out value="${instituicao.endereco.bairro}"/>" name="bairro" id="bairro" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">Telefone</span></div>
                              <div class="col-sm-8"><input type="text" class="form-control" placeholder="Insira o telefone" value="<c:out value="${instituicao.endereco.telefone}"/>" name="telefone" id="telefone" > </div>
                            </div>
                          </li>
                          <li class="padding-v-5">
                            <div class="row">
                              <div class="col-sm-4"><span class="text-muted">UF</span></div>
                              <div class="col-sm-8">
                                <select name="uf" id="uf" class="form-control">
                                    <option></option>
                                    <c:forEach var="estado" items="${estados}">
                                        <option ${estado.sigla == instituicao.endereco.cidade.uf.sigla ? "selected": ""} value="<c:out value="${estado.sigla}" />"><c:out value="${estado.descricao}" /></option>
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
                                        <option ${cidade.id == instituicao.endereco.cidade.id ? "selected": ""} value="<c:out value="${cidade.id}" />"><c:out value="${cidade.descricao}" /></option>
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
                        <h3 class="widget-caption">Nossa Causa</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <ul class="list-unstyled team-members">
                              <li>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select class="form-control" name="causa" id="causa">
                                            <option></option>
                                            <c:forEach var="causa" items="${causas}">
                                                <option ${instituicao.causa.id == causa.id ? "selected" : ""}  value="<c:out  value="${causa.id}" />"><c:out value="${causa.descricao}" /></option>
                                            </c:forEach>
                                        </select>
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
                        <h3 class="widget-caption">Sobre Nós</h3>
                      </div>
                      <div class="widget-body bordered-top bordered-sky">
                        <div class="card">
                          <div class="content">
                            <ul class="list-unstyled team-members">
                              <li>
                                <div class="row">
                                    <div class="col-xs-12 text-left">
                                        <textarea rows="4" class="form-control" placeholder="Fale um pouco sobre sua instituição" name="comentario" id="comentario"><c:out value="${instituicao.comentario}"/></textarea>
                                    </div>
                                </div>
                              </li>
                            </ul>
                          </div>
                        </div>  
                      </div>
                    </div>
                    
                    <input type="submit" class="btn btn-primary shiny" value="Gravar" />
                    <input type="button" class="btn btn-primary shiny" onclick="javascript:location.href='ControladoraServlet?action=perfilInstituicao'" value="Voltar" />
                    
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
