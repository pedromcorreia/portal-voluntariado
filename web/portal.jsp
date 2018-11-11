<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Voluntarius - portal</title>
        <jsp:include page="cabecalho.jsp"/>
        <link href="./css/login_register.css" rel="stylesheet">
    </head>
  <body>

    <!-- Fixed navbar -->
    <nav class="navbar navbar-fixed-top navbar-transparent" role="navigation">
        <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button id="menu-toggle" type="button" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar bar1"></span>
            <span class="icon-bar bar2"></span>
            <span class="icon-bar bar3"></span>
          </button>
          <!--a class="navbar-brand" href="profile.html">Day-Day</a-->
          <c:if test="${msg != null}">
            <p class="navbar-brand">${msg}</p>
          </c:if>
        </div>
      </div>
    </nav>
    <div class="wrapper">
      <div class="parallax filter-black">
          <div class="parallax-image"></div>             
          <div class="small-info">
            <div class="col-sm-10 col-sm-push-1 col-md-6 col-md-push-3 col-lg-6 col-lg-push-3">
              <div class="card-group animated flipInX">
                <div class="card">
                  <div class="card-block">
                    <div class="center">
                      <h4 class="m-b-0"><span class="icon-text">Login</span></h4>
                      <p class="text-muted">Acesse sua conta</p>
                    </div>
                    <form action="ControladoraServlet?action=login" method="POST">
                      <div class="form-group">
                        <input type="email" class="form-control" placeholder="Endereço de Email" name="email">
                      </div>
                      <div class="form-group">
                        <input type="password" class="form-control" placeholder="Senha" name="senha">
                        <a href="#" class="pull-xs-right">
                          <small>Esqueceu?</small>
                        </a>
                        <div class="clearfix"></div>
                      </div>
                      <button type="submit" class="btn btn-azure">Acessar</button>
                    </form>
                  </div>
                </div>
                <div class="card">
                  <div class="card-block center">
                    <h4 class="m-b-0">
                      <span class="icon-text">Cadastre-se</span>
                    </h4>
                    <p class="text-muted">Crie uma nova conta</p>
                    <form action="ControladoraServlet?action=novoCadastro" method="POST">
                      <div class="form-group">
                        <input type="text" class="form-control" placeholder="Nome Completo" name="nome">
                      </div>
                      <!--div class="form-group">
                        <input type="text" class="form-control" placeholder="Sobrenome" name="sobrenome">
                      </div-->
                      <div class="form-group">
                        <input type="email" class="form-control" placeholder="Email" name="email">
                      </div>
                      <!--div class="form-group">
                        <input type="text" class="form-control" placeholder="CEP" name="cep">
                      </div-->
                      <div class="form-group">
                        <input type="password" class="form-control" placeholder="Senha" name="senha">
                      </div>
                      <div class="form-group" style="text-align: left;">
                        <label>
                            <input type="checkbox" class="colored-blue" value="S" name="instituicao">
                            <span class="text">Quero cadastrar uma instituição</span>
                        </label>
                      </div>
                      <!--div class="form-group">
                        <input type="password" class="form-control" placeholder="Confirm Password">
                      </div-->
                      <button type="submit" class="btn btn-azure">Cadastrar</button>
                    </form>
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