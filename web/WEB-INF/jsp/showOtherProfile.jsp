<%--
  Created by IntelliJ IDEA.
  User: Emeline Hourmand
  Date: 03/07/2019
  Time: 09:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil - ${participant.username}</title>
    <jsp:include page="inc/CSS_JS_global.jsp"/>
</head>
<header>
    <%-- Inclusion de la NavBar --%>
    <jsp:include page="inc/NavBar_header.jsp"/>

</header>
<body>
<style><%@include file="/WEB-INF/css/showProfile.css"%></style>
<c:set var="user" value="${userLog}"/>
    <div class="container container-padd-top">
        <div class="form-register rounded">
            <h2 class="form-register-heading">${participant.username}</h2>
            <p><b>Nom prénom :</b> ${participant.lastname} ${user.firstname}</p>
            <c:choose>
                <c:when test="${participant.username != user.username}">
                     <p><b>Email :</b> <a href="mailto:participant.mail">${participant.mail}</a></p>
                </c:when>
                <c:otherwise>
                    <p><b>Email :</b> ${participant.mail}</p>
                </c:otherwise>
            </c:choose>
                <c:if test="${participant.phone != null}">
            <p><b>Téléphone:</b> ${participant.phone}</p>
            </c:if>
            <div class="btn-profile">
                <a class="btn btn-outline-secondary"
                   href="${pageContext.request.contextPath}/index" role="button">
                    <i class="fas fa-arrow-left"></i> Retour</a>
                <c:if test="${participant.username == user.username}">
                    <a class="btn btn-outline-info"
                       href="${pageContext.request.contextPath}/editProfile" role="button">
                        <i class="fas fa-user-edit"></i> Editer</a>
                    <a class="btn btn-outline-danger"
                       href="${pageContext.request.contextPath}/deleteProfile" role="button"
                       onclick="return confirm('Êtes-vous sur de vouloir supprimer votre profil ?')">
                        <i class="fas fa-user-minus"></i> Supprimer</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
