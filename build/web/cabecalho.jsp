<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="erro.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!--c:if test="$ {usuarioLogado.usuario == null}">
    <j sp:forward page="/portal.jsp">
        <j sp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
    </ jsp:forward>
</c :if-->

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<link rel="icon" href="images/favicon.png">
<title>DayDay</title>
<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/font-awesome.4.6.1/css/font-awesome.min.css" rel="stylesheet">
<link href="./css/animate.min.css" rel="stylesheet">
<link href="./css/timeline.css" rel="stylesheet">
<link href="./css/forms.css" rel="stylesheet">
<link href="./css/buttons.css" rel="stylesheet">
<script src="./js/jquery.1.11.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/custom.js"></script>
<script src="./js/voluntarius.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->