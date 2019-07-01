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
<header>
    <jsp:include page="inc/NavBar_header.jsp"></jsp:include>
</header>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3">

        </div>
        <div class="col-sm-3">

        </div>
        <div class="col-sm-3">

        </div>
        <div class="col-sm-3">
            Date du jour: ${requestScope.today}<br>
            Participant: ${sessionScope.user.getFirstname()}
        </div>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/SearchServlet">
        Filtrer les sorties <br>
        <div class="row">

            <div class="col-sm-5 card py-5">

                <label for="site">Choix du site:</label>
                <div class="dropdown dropright">
                    <select class="custom-select" name="site" id="site">
                        <c:forEach var="Site" items="${listeSite}">
                            <option class="dropdown-item" value="${Site.getName()}"
                                    name="site">${Site.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <br>

                <%--formulaire de recherche--%>
                <%--                <label for="site"> Le nom de la sortie contient:</label>--%>

                <div class="input-group mb-3">
                    <input class="form-control" type="search" id="site-search" name="q"
                           placeholder="Le nom de la sortie contient:" aria-label="Search">
                </div>
                <br>


                Entre le<input type="date" class="form-control">
                et le<input type="date" class="form-control">

            </div>

            <div class="col-sm-5 card py-5">

                <%--check-box Organisateur?--%>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="organistor">
                    <label class="form-check-label" for="defaultCheck1">
                        Sorties dont je suis l'organisateur/trice
                    </label>
                </div>
                <%--check-box inscrit?--%>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="singnedUp">
                    <label class="form-check-label" for="defaultCheck1">
                        Sorties auxquelles je suis inscrit/e
                    </label>
                </div>
                <%--check-box pas inscrit?--%>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="notSignedUp">
                    <label class="form-check-label" for="defaultCheck1">
                        Sorties auxquelles je ne suis pas inscrit/e
                    </label>
                </div>
                <%--check-box Sortie passée?--%>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="passedEvent">
                    <label class="form-check-label" for="defaultCheck1">
                        Sorties passées
                    </label>
                </div>


            </div>
            <div class="col-sm-2">
                <!--Bouton rechercher-->
                <input type="submit" value="Rechercher"/>
            </div>
        </div>
    </form>

    <%--    Affichage tableau--%>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Nom de la sortie</th>
            <th scope="col">Date de la sortie</th>
            <th scope="col">Clôture</th>
            <th scope="col">inscrits/places</th>
            <th scope="col">Etat</th>
            <th scope="col">Inscrit</th>
            <th scope="col">Organisateur</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>

<%--        <c:forEach var="Site" items="${listeSite}">--%>
<%--            <option class="dropdown-item" value="${Site.getName()}"--%>
<%--                    name="site">${Site.getName()}</option>--%>
<%--        </c:forEach>--%>
        <c:forEach var="Event" items="${listeEvent}">
            <tr>
                <th scope="row">${Event.getName()}</th>
                <td>${Event.getEventBeginning().getDayOfMonth()}/${Event.getEventBeginning().getMonth()}/${Event.getEventBeginning().getYear()}</td>
                <td>${Event.getRegistrationLimit().getDayOfMonth()}/${Event.getEventBeginning().getMonth()}/${Event.getEventBeginning().getYear()}</td>
                <td>?/?</td>
                <td>${Event.getState().getLabel()}</td>
                <td>?</td>
                <td><a href="mailto:${Event.getOrganizer().getMail()}">${Event.getOrganizer().getUsername()}</a></td>
                <td>${actions}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>


</div>


</body>
</html>
