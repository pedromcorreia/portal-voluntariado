<%-- 
    Document   : timeline
    Created on : Sep 15, 2018, 3:49:47 PM
    Author     : Ciro
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page errorPage="erro.jsp" %>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Voluntarius</title>
            <jsp:include page="cabecalho.jsp"/>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/profile2.css" rel="stylesheet">
    </head>
    <body class="animated fadeIn">
        <!-- Fixed navbar -->
        <nav class="navbar navbar-white navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html"><b>DayDay</b></a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="actives"><a href="profile.html">Profile</a></li>
                        <li><a href="home.html">Home</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                Pages <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="timeline.jsp">Timeline</a></li>
                                <li><a href="profile2.html">Profile 2</a></li>
                                <li><a href="profile3.html">Profile 3</a></li>
                                <li><a href="profile4.html">Profile 4</a></li>
                                <li><a href="sidebar_profile.html">Sidebar profile</a></li>
                                <li><a href="user_detail.html">User detail</a></li>
                                <li><a href="edit_profile.html">Edit profile</a></li>
                                <li><a href="about.html">About</a></li>
                                <li><a href="friends.html">Friends</a></li>
                                <li><a href="friends2.html">Friends 2</a></li>
                                <li><a href="profile_wall.html">Profile wall</a></li>
                                <li><a href="photos1.html">Photos 1</a></li>
                                <li><a href="photos2.html">Photos 2</a></li>
                                <li><a href="view_photo.html">View photo</a></li>
                                <li><a href="messages1.html">Messages 1</a></li>
                                <li><a href="messages2.html">Messages 2</a></li>
                                <li><a href="group.html">Group</a></li>
                                <li><a href="list_users.html">List users</a></li>
                                <li><a href="file_manager.html">File manager</a></li>
                                <li><a href="people_directory.html">People directory</a></li>
                                <li><a href="list_posts.html">List posts</a></li>
                                <li><a href="grid_posts.html">Grid posts</a></li>
                                <li><a href="forms.html">Forms</a></li>
                                <li><a href="buttons.html">Buttons</a></li>
                                <li><a href="error404.html">Error 404</a></li>
                                <li><a href="error500.html">Error 500</a></li>
                                <li><a href="recover_password.html">Recover password</a></li>
                                <li><a href="registration_mail.html">Registration mail</a></li>
                            </ul>
                        </li>
                        <li><a href="#" class="nav-controller"><i class="fa fa-user"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Begin page content -->
        <div class="container page-content">
            <div class="row" id="user-profile">
                <div class="col-md-12 col-xs-12">
                    <div class="row-xs">
                        <div class="main-box clearfix">
                            <div class="tabs-wrapper profile-tabs">                
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="tab-timeline">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <!--   posts -->
                                                <div class="box box-widget">
                                                    <!-- bloco para postar -->
                                                    <div class="box profile-info n-border-top">
                                                        <form action="ControladoraPost?action=newpost" method="post" id="newpost" name="newpost" enctype="multipart/form-data">
                                                            <textarea class="form-control input-lg p-text-area" id="post" name="post" rows="2" placeholder="Whats in your mind today?" required></textarea>
                                                                <div class="box-footer box-form">
                                                                    <button type="submit" class="btn btn-azure pull-right">Publicar</button>
                                                                    <ul class="nav nav-pills">
                                                                        <div style="height:0px;overflow:hidden">
                                                                            <input type="file" accept="image/*" id="imagem" name="imagem" />
                                                                        </div>
                                                                        <li><button type="button" class="btn-blue" onclick="chooseFile();">
                                                                                <i class="fa fa-camera"></i>
                                                                            </button>
                                                                        </li>
                                                                    </ul>                                                           
                                                                </div>                                                           
                                                            <div id="preview"><img class="img-responsive show-in-modal" src="" alt=""></div>
                                                        </form>
                                                    </div>
                                                    <!-- término bloco para postar -->
                                                    <c:forEach items="${listaPosts}" var="timeline">
                                                    <!--Bloco conjunto do post-->
                                                        <!-- bloco cabecalho do post-->
                                                        <div class="box-header with-border">
                                                            <div class="user-block">
                                                                <img class="img-circle" src="${timeline.usuario.foto}" alt="User Image">
                                                                <span class="username"><a href="#"><c:out value="${timeline.usuario.nome}"/></a></span>
                                                                <fmt:formatDate type="both" value="${timeline.data.time}" var="dataFormatada" pattern="dd/MM/yyyy HH:mm:ss"/>
                                                                <span class="description">Em <c:out value="${dataFormatada}"/></span>
                                                                
                                                            </div>
                                                        </div>
                                                        <div class="box-body" style="display: block;">
                                                            <c:if test="${timeline.imagem != null}">
                                                                <img class="img-responsive show-in-modal" src="${timeline.imagem}" alt="Photo">
                                                            </c:if>
                                                            <p><c:out value="${timeline.descricao}"/></p>
                                                            <c:if test="${timeline.oportunidade.id > 0}">
                                                                <div class="widget">
                                                                    <div class="widget-header">
                                                                        <h3 class="widget-caption">Dados da Oportunidade</h3>
                                                                    </div>
                                                                    <div class="widget-body bordered-top bordered-sky">
                                                                        <ul class="list-unstyled profile-about margin-none">
                                                                            <li class="padding-v-5">
                                                                                <div class="row">
                                                                                    <div class="col-sm-4"><span class="text-muted">Número de Vagas</span></div>
                                                                                    <div class="col-sm-8"><c:out value="${timeline.oportunidade.vagasTotal}"/></div>
                                                                                </div>
                                                                            </li>
                                                                            <li class="padding-v-5">
                                                                                <div class="row">
                                                                                    <div class="col-sm-4"><span class="text-muted">Presencial</span></div>
                                                                                    <c:choose>
                                                                                        <c:when test="${timeline.oportunidade.presencial==\"S\"}">
                                                                                            <div class="col-sm-8"><c:out value="Sim"/></div>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <div class="col-sm-8"><c:out value="Não"/></div>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </div>
                                                                            </li>
                                                                            <li class="padding-v-5">
                                                                                <div class="row">
                                                                                    <div class="col-sm-4"><span class="text-muted">Início</span></div>
                                                                                    <fmt:formatDate type="date" value="${timeline.oportunidade.inicio}" var="inicio" pattern="dd/MM/yyyy"/>
                                                                                    <div class="col-sm-8"><c:out value = "${inicio}"/></div>
                                                                                </div>
                                                                            </li>
                                                                            <li class="padding-v-5">
                                                                                <div class="row">
                                                                                    <div class="col-sm-4"><span class="text-muted">Término</span></div>
                                                                                    <fmt:formatDate type="date" value="${timeline.oportunidade.termino}" var="termino" pattern="dd/MM/yyyy"/>
                                                                                    <div class="col-sm-8"><c:out value = "${termino}"/></div>
                                                                                </div>
                                                                            </li>
                                                                            <li class="padding-v-5">
                                                                                <div class="row">
                                                                                    <div class="col-sm-4"><span class="text-muted">Carga Horária</span></div>
                                                                                    <div class="col-sm-8"><c:out value="${timeline.oportunidade.cargaHorariaTotal}"/> horas</div>
                                                                                </div>
                                                                            </li>
                                                                            <li class="padding-v-5">
                                                                                <div class="row">
                                                                                    <div class="col-sm-4"><span class="text-muted">Status</span></div>
                                                                                    <c:choose>
                                                                                        <c:when test="${timeline.oportunidade.status==\"A\"}">
                                                                                            <div class="col-sm-8"><c:out value="Aberta"/></div>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <div class="col-sm-8"><c:out value="Encerrada"/></div>
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </div>
                                                                            </li>
                                                                        </ul>
                                                                  </div>
                                                                </div>
                                                            </c:if>
                                                            <button type="button" id="p${timeline.id}" name="curtir" onclick="handleBtnClick(event); setCurtir(${timeline.id})" 
                                                                    <c:choose>
                                                                        <c:when test="${timeline.qtdeCurtidas >= 1}">
                                                                            class="btn btn-blue btn-xs"
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            class="btn btn-default btn-xs"
                                                                        </c:otherwise>
                                                                    </c:choose> value="${timeline.id}">
                                                                <i class="fa fa-thumbs-o-up"></i>Curtir</button>
                                                            <span id="c${timeline.id}" class="pull-right text-muted"><c:out value="${timeline.qtdeCurtidas}"/> curtidas - <c:out value="${timeline.qtdeComentarios}"/> comentários</span>
                                                        </div>
                                                        
                                                        <!-- término bloco cabecalho do post -->  
                                                        <!-- bloco comentários do post-->  
                                                        <c:forEach items="${timeline.comentarios}" var="comentarios">
                                                            <div class="box-footer box-comments" style="display: block;">
                                                                <div class="box-comment">
                                                                    <img class="img-circle img-sm" src="${comentarios.usuario.foto}" alt="User Image">
                                                                    <div class="comment-text">
                                                                        <span class="username">
                                                                        <c:out value="${comentarios.usuario.nome}"/>
                                                                            <fmt:formatDate type="both" value="${comentarios.data.time}" var="dataComentario" pattern="dd/MM/yyyy HH:mm:ss"/>
                                                                            <span class="text-muted pull-right"><c:out value="${dataComentario}"/></span>
                                                                        </span>
                                                                        <c:out value="${comentarios.descricao}"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                        <!-- término bloco comentários do post-->
                                                        <!-- bloco comentar meu post-->  
                                                        <div class="box-footer" style="display: block;">
                                                            <form action="ControladoraPost?action=newcomment&id=${timeline.id}" method="post" id="newcomment" name="newcomment">
                                                                <img class="img-responsive img-circle img-sm" src="${usuarioConectado.foto}" alt="Alt Text">
                                                                <div class="img-push">
                                                                    <input type="text" class="form-control input-sm" id="comment" name="comment" placeholder="Press enter to post comment">
                                                                    
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <!-- término bloco comentar meu post-->
                                                    <!--Bloco conjunto do post-->
                                                    </c:forEach>
                                                </div><!--  end posts-->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
        <footer class="footer">
            <div class="container">
                <p class="text-muted"> Copyright &copy; Company - All rights reserved </p>
            </div>
        </footer>
    </body>
</html>
