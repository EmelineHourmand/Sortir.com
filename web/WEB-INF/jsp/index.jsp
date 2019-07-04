<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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


<fmt:parseDate value="${beginning}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="parsedDateTime"  />
<fmt:formatDate value="${ parsedDateTime }" pattern="yyyy-MM-dd" var="beginningDate"  />

<fmt:parseDate value="${end}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="parsedDateTime"  />
<fmt:formatDate value="${ parsedDateTime }" pattern="yyyy-MM-dd" var="endDate"  />

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

    <form method="post" action="${pageContext.request.contextPath}/search">
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
                <div class="input-group mb-3">
                    <input class="form-control" type="search" id="site-search" name="q"
                           placeholder="Le nom de la sortie contient:" aria-label="Search" value="${recherche}">
                </div>
                <br>


                Entre le<input type="date" class="form-control" name="beginning" value="${beginningDate}">
                et le<input type="date" class="form-control" name="end" value="${endDate}">

            </div>

            <div class="col-sm-5 card py-5">

                <%--check-box Organisateur?--%>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="organistor" name="isOrganisator">
                    <label class="form-check-label" for="defaultCheck1">
                        Sorties dont je suis l'organisateur/trice
                    </label>
                </div>
                <%--check-box inscrit?--%>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="singnedUp" name="isInscrit">
                    <label class="form-check-label" for="defaultCheck1">
                        Sorties auxquelles je suis inscrit/e
                    </label>
                </div>
                <%--check-box pas inscrit?--%>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="notSignedUp" name="isNotInscrit">
                    <label class="form-check-label" for="defaultCheck1">
                        Sorties auxquelles je ne suis pas inscrit/e
                    </label>
                </div>
                <%--check-box Sortie passée?--%>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="passedEvent" name="isSortiePassee">
                    <label class="form-check-label" for="defaultCheck1">
                        Sorties passées
                    </label>
                </div>


            </div>
            <div class="col-sm-2">
                <!--Bouton rechercher-->
                <input class="btn btn-secondary" type="submit" value="Rechercher"/> <br> <br>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/editEvent">Créer une sortie</a>
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

        <c:forEach var="event" items="${listeEvent}">
            <tr>
                <th scope="row">${event.getName()}</th>
                <td>${event.getEventBeginning().getDayOfMonth()}/${event.getEventBeginning().getMonth()}/${event.getEventBeginning().getYear()}</td>
                <td>${event.getRegistrationLimit().getDayOfMonth()}/${event.getEventBeginning().getMonth()}/${event.getEventBeginning().getYear()}</td>
                <td>?/?</td>
                <td>${event.getState().getLabel()}</td>
                <td>?</td>
                <td><a href="${pageContext.request.contextPath}/profile?id=${event.getOrganizer().getIdParticipant()}">${event.getOrganizer().getUsername()}</a></td>
                <td>
                    <%-- Actions--%>
                        <%-- Modifier--%>
                    <c:if test="${((event.state.label == 'Créée') && (sessionScope.user.idParticipant == (event.organizer.idParticipant)))}">
                        <a href="${pageContext.request.contextPath}/editEvent?id=${event.idEvent}">Modifier</a>
                        <a href="${pageContext.request.contextPath}/editEvent?id=${event.idEvent}">Publier</a>
                    </c:if>

                        <%--                    // Annuler--%>
                    <c:if test="${((event.state.label == 'Ouverte') && (sessionScope.user.idParticipant == (event.organizer.idParticipant)))}">
                        <a href="${pageContext.request.contextPath}/editEvent?id=${event.idEvent}">Annuler</a>
                    </c:if>
                        <%--                    // Afficher--%>
                    <c:if test="${event.state.label == 'Activité en cours' || event.state.label == 'Clôturée' || event.state.label == 'Ouverte'}">
                        <a href="${pageContext.request.contextPath}/showEvent?id=${event.idEvent}">Afficher</a>
                    </c:if>
                        <%--                    @TODO--%>
                        <%--                    Se désister--%>
                        <%--                    <c:if test="${((event.state.label == 'Ouverte') && (sessionScope.user.idParticipant != (event.organizer.idParticipant)))}">--%>
                        <%--                        <a href="${pageContext.request.contextPath}/event?id=${event.idEvent}">Publier</a>--%>
                        <%--                    </c:if>--%>
                </td>
            </tr>

        </c:forEach>

        </tbody>
    </table>


</div>


</body>
</html>
