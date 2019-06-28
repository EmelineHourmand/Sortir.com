<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Pour inclure la navbar :
<jsp:include page="inc/NavBar_header.jsp"></jsp:include>
--%>
<%--    <nav>--%>

    <c:choose>
        <c:when test="${requestScope.navType != null}">
            <%--  NavBar Logo seulement  --%>

        </c:when>
        <c:when test="${requestScope.navType != null}">
            <%--  NavBar Acceuil (index) "Ville / Sites / Accueil / Mon Profil / Se déconnecter"   --%>

        </c:when>
        <c:otherwise>
            <%-- NavBar autres cas  "Accueil / Mon Profil / Se déconnecter"  --%>

        </c:otherwise>
    </c:choose>

<%--    </nav>--%>

<%--TODO revoir pourquoi le toggler ne fonctionne pas --%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="d-flex flex-grow-1">
        <span class="w-100 d-lg-none d-block"><!-- hidden spacer to center brand on mobile --></span>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
            <img class="logo w-25" src="${pageContext.request.contextPath}/img/logo_300x300.png" alt="logo">
            <span class="logo-text"> Sortir.com</span>
        </a>
        <div class="w-100 text-right">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#myNavbar7">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
    <div class="collapse navbar-collapse flex-grow-1 text-right" id="myNavbar7">
        <ul class="navbar-nav ml-auto flex-nowrap">

            <%--Ville--%>
            <li class="nav-item h5">
                <a class="nav-link" href="${pageContext.request.contextPath}/ville">Ville<span class="sr-only">(current)</span></a>
            </li>

            <%--Sites--%>
            <li class="nav-item h5">
                <a class="nav-link" href="${pageContext.request.contextPath}/sites">Sites</a>
            </li>
            <%--Accueil--%>
            <li class="nav-item h5">
                <a class="nav-link" href="${pageContext.request.contextPath}/index">Accueil</a>
            </li>

            <%--Mon Profil--%>
            <li class="nav-item h5">
                <a class="nav-link" href="${pageContext.request.contextPath}/profil">Mon Profil</a>
            </li>

            <%--Se déconnecter--%>
            <li class="nav-item h5">
                <a class="nav-link" href="${pageContext.request.contextPath}/login">Se déconnecter</a>
            </li>

        </ul>
    </div>
</nav>

