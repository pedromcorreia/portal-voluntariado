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
    </head>
  <body class="animated fadeIn">

    <!-- Fixed navbar -->
    <jsp:include page="menuPpal.jsp"/>

    <!-- Begin page content -->
    <div class="row page-content">
      <div class="col-md-8 col-md-offset-2">
        <div class="row">
          <div class="col-md-12">
            <div class="cover profile">
              <div class="wrapper">
                <div class="image">
                  <img src="images/Cover/profile-cover.jpg" class="show-in-modal" alt="people">
                </div>
                <ul class="friends">
                  <li>
                    <a href="#">
                      <img src="images/Friends/guy-6.jpg" alt="people" class="img-responsive">
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <img src="images/Friends/woman-3.jpg" alt="people" class="img-responsive">
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <img src="images/Friends/guy-2.jpg" alt="people" class="img-responsive">
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <img src="images/Friends/guy-9.jpg" alt="people" class="img-responsive">
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <img src="images/Friends/woman-9.jpg" alt="people" class="img-responsive">
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <img src="images/Friends/guy-4.jpg" alt="people" class="img-responsive">
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <img src="images/Friends/guy-1.jpg" alt="people" class="img-responsive">
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <img src="images/Friends/woman-4.jpg" alt="people" class="img-responsive">
                    </a>
                  </li>
                  <li><a href="#" class="group"><i class="fa fa-group"></i></a></li>
                </ul>
              </div>
              <div class="cover-info">
                <div class="avatar">
                  <!--img src="images/Friends/guy-5.jpg" alt="people"-->
                  <c:choose>
                    <c:when test="${voluntario.foto != null}"> 
                      <img src="images/Friends/<c:out value="${voluntario.foto}"/>" alt="people">
                    </c:when>
                    <c:otherwise>
                      <img src="images/Friends/fa-user.png" alt="people">
                    </c:otherwise>
                  </c:choose>
                </div>
                <div class="name"><a href="#"><c:out value="${voluntario.nome}"/></a></div>
                <ul class="cover-nav">
                  <li><a href="profile.html"><i class="fa fa-fw fa-bars"></i> Timeline</a></li>
                  <li><a href="ControladoraServlet?action=perfilVoluntario"><i class="fa fa-fw fa-user"></i> Meu Perfil</a></li>
                  <li class="active"><a href="#"><i class="fa fa-fw fa-users"></i> Amigos</a></li>
                  <!--li><a href="photos1.html"><i class="fa fa-fw fa-image"></i> Photos</a></li-->
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          
          <c:forEach var="friend" items="${amigos}">
          <div class="col-md-3">
              <div class="contact-box center-version">
                <a href="ControladoraServlet?action=perfilVoluntario&perfil=<c:out value="${friend.usuario}" />">
                  
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