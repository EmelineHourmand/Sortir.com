<%--
  Created by IntelliJ IDEA.
  User: Emeline Hourmand
  Date: 01/07/2019
  Time: 14:06
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <jsp:include page="inc/CSS_JS_global.jsp"/>
</head>
<body>
<header>
    <%-- Inclusion de la NavBar --%>
    <jsp:include page="inc/NavBar_header.jsp"/>
</header>
<div class="container">
    <h1 align="center">Mon profil</h1>
    <form action="${pageContext.request.contextPath}/editProfile" method="POST">
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="userName">Pseudo</label>
                <input name="userName" type="text" class="form-control" id="userName" value="${participant.username}" required>
            </div>
            <div class="form-group col-md-4">
                <label for="firstName">Prénom</label>
                <input name="firstName" type="text" class="form-control" id="firstName" value="${participant.firstname}" required>
            </div>
            <div class="form-group col-md-4">
                <label for="lastName">Nom</label>
                <input name="lastName" type="text" class="form-control" id="lastName" value="${participant.lastname}" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="email">Email</label>
                <input name="email" type="text" class="form-control" id="email" value="${participant.mail}" required>
            </div>
            <div class="form-group col-md-4">
                <label for="phoneNumber">Prénom</label>
                <input name="phoneNumber" type="text" class="form-control" id="phoneNumber" value="${participant.phone}" required>
            </div>
            <div class="form-group col-md-4">
                <label for="password">Nom</label>
                <input name="password" type="password" class="form-control" id="password" placeholder="Mot de passe">
            </div>
        </div>
        <div class="container-btn" align="center">
            <button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Valider
            </button>
            <a class="btn btn-danger"
               href="${pageContext.request.contextPath}/profile" role="button">Annuler</a>
        </div>
    </form>
</div>
</body>
</html>