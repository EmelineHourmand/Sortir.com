<%--
  Created by IntelliJ IDEA.
  User: Emeline Hourmand
  Date: 01/07/2019
  Time: 13:46
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mon profil</title>
    <jsp:include page="inc/CSS_JS_global.jsp"/>
</head>
<header>
    <%-- Inclusion de la NavBar --%>
    <jsp:include page="inc/NavBar_header.jsp"/>
</header>
<body>
<style><%@include file="/WEB-INF/css/showProfile.css"%></style>

<div class="container container-padd-top">
    <div class="form-register rounded">
        <h2 class="form-register-heading">${participant.username}</h2>
        <p><b>Nom prénom :</b> ${participant.lastname} ${participant.firstname}</p>
        <p><b>Email :</b> ${participant.mail}</p>
        <c:if test="${participant.phone != null}">
            <p><b>Téléphone:</b> ${participant.phone}</p>
        </c:if>
        <div class="btn-profile">
            <a class="btn btn-outline-info"
               href="${pageContext.request.contextPath}/index" role="button">
                <i class="fas fa-arrow-left"></i> Retour</a>
            <a class="btn btn-outline-info"
               href="${pageContext.request.contextPath}/editProfile" role="button">
                <i class="fas fa-user-edit"></i> Editer</a>
            <a class="btn btn-outline-danger"
               href="${pageContext.request.contextPath}/deleteProfile" role="button" onclick="return confirm('Êtes-vous sur de vouloir supprimer votre profil ?')">
                <i class="fas fa-user-minus"></i> Supprimer</a>
        </div>
    </div>
</div>
</body>
</html>