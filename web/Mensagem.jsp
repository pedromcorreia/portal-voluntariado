<%-- 
    Document   : Mensagem
    Created on : Nov 11, 2018, 2:53:55 PM
    Author     : pedro
--%>
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
    <body>
        <jsp:include page="menuPpal.jsp"/>
 <div class="container page-content">
      <div class="row">
        <div class="col-md-4 bg-white">
          <div class=" row border-bottom padding-sm" style="height: 40px;">
          Member
          </div>
          <!-- member list -->
          <ul class="friend-list">
          <li class="active animated bounceInDown">
            <a href="#" class="clearfix">
              <img src="img/Friends/guy-1.jpg" alt="" class="img-circle">
              <div class="friend-name"> 
                <strong>John Doe</strong>
              </div>
              <div class="last-message text-muted">Hello, Are you there?</div>
              <small class="time text-muted">Just now</small>
              <small class="chat-alert label label-danger">1</small>
            </a>
          </li>         
          </ul>
        </div>

        <!--=========================================================-->
        <!-- selected chat -->
        <div class="col-md-8 bg-white ">
          <div class="chat-message" style="max-height: 600px;overflow-y:auto ">
            <ul class="chat">
                <li class="left clearfix">
                  <span class="chat-img pull-left">
                    <img src="img/Friends/woman-1.jpg" alt="User Avatar">
                  </span>
                  <div class="chat-body clearfix">
                    <div class="header">
                      <strong class="primary-font">Jane</strong>
                      <small class="pull-right text-muted"><i class="fa fa-clock-o"></i> 12 mins ago</small>
                    </div>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                    </p>
                  </div>
                </li>
                <li class="right clearfix">
                  <span class="chat-img pull-right">
                    <img src="img/Friends/guy-3.jpg" alt="User Avatar">
                  </span>
                  <div class="chat-body clearfix">
                    <div class="header">
                      <strong class="primary-font">John</strong>
                      <small class="pull-right text-muted"><i class="fa fa-clock-o"></i> 13 mins ago</small>
                    </div>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales at. 
                    </p>
                  </div>
                </li>
                
            </ul>
          </div>
          <div class="chat-box_ bg-white">
            <div class="input-group">
              <input class="form-control border no-shadow no-rounded" placeholder="Mensagem">
              <span class="input-group-btn">
                <button class="btn btn-success no-rounded" type="button">Enviar</button>
              </span>
            </div><!-- /input-group --> 
          </div>            
        </div>        
      </div>
    </div>
    </body>
</html>
