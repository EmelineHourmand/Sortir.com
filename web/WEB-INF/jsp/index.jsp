<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hwasier2019
  Date: 26/06/2019
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="inc/CSS_JS_global.jsp"></jsp:include>
    <title>Accueil</title>
</head>
<body>
<header><jsp:include page="inc/NavBar_header.jsp"></jsp:include></header>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3">

        </div>
        <div class="col-sm-3">

        </div>
        <div class="col-sm-3">

        </div>
        <div class="col-sm-3">
            Date du jour: <br>
            Participant:
        </div>
    </div>
<div class="row">
    <div class="col-sm-5">
        Filtrer les sorties <br>
        Site:
        <div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Dropdown link
            </a>

            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                <c:forEach var=""
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </div>
        Le nom de la sortie contient: <br>
        Entre et
    </div>
    <div class="col-sm-5">
        <form>

        </form>
    </div>
    <div class="col-sm-2">
        One of three columns
    </div>
</div>

</div>











<jsp:include page="inc/CSS_JS_global.jsp"></jsp:include>
</body>
</html>
