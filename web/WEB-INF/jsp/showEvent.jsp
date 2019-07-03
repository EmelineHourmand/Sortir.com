<%--
  Created by IntelliJ IDEA.
  User: Emeline Hourmand
  Date: 02/07/2019
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sortie - ${event.name}</title>
    <jsp:include page="inc/CSS_JS_global.jsp"/>
</head>
<header>
    <%-- Inclusion de la NavBar --%>
    <jsp:include page="inc/NavBar_header.jsp"/>
</header>
<body>
<div class="container container-padd-top" align="center">
<div class="card" style="width: 18rem;" align="center">
    <div class="card-body">
        <h5 class="card-title">${event.name}</h5>
        <h6 class="card-subtitle mb-2 text-muted">${event.state.label}</h6>
        <c:if test="${event.state.label == 'Ouverte'}">
            <h6 class="card-subtitle mb-2 text-muted">Il reste
            <c:choose>
                <c:when test="${nbRegister == 0}">
                    ${event.maxRegistration} / ${event.maxRegistration} places.
                </c:when>
                <c:otherwise>
                    ${event.maxRegistration - nbRegister} / ${event.maxRegistration} places.
                </c:otherwise>
            </c:choose>
            </h6>
        </c:if>
        <p class="card-text">${event.description}</p>

        <p class="card-text">Lieu : <br> ${event.place.name} <br> ${event.place.street} <br> ${event.place.city.name} ${event.place.city.postalCode}</p>
        <c:if test="${event.place.latitude != null && event.place.longitude != null}">
            <p class="card-text">GPS : <br>LAT.${event.place.latitude} LONG.${event.place.longitude}</p>
        </c:if>
        <p class="card-text">Site de rattachement : <br>${event.site.name}</p>
        <p class="card-text">Organisateur : <br>
            <a href="${pageContext.request.contextPath}/profile?id=${event.organizer.idParticipant}"
               class="card-link"> ${event.organizer.username}</a><br>
        </p>
        <c:if test="${event.state.label == 'Ouverte'}">
            <p class="card-text">Date de fin des inscriptions : ${event.getRegistrationLimit().getDayOfMonth()}/${event.getRegistrationLimit().getMonth()}/${event.getRegistrationLimit().getYear()}</p>
        </c:if>
        <p class="card-text">Date de début de l'évènement : <br>${event.getEventBeginning().getDayOfMonth()}/${event.getEventBeginning().getMonth()}/${event.getEventBeginning().getYear()}</p>
        <p class="card-text">Date de fin de l'évènement : <br>${event.getEventEnd().getDayOfMonth()}/${event.getEventEnd().getMonth()}/${event.getEventEnd().getYear()}</p>

        <c:if test="${event.state.label == 'Ouverte' && (event.maxRegistration - nbnbRegister) > 0}">
            <a href="#" class="btn btn-info">S'inscrire</a>
        </c:if>
            <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary">Retour</a>
    </div>
</div>
</div>
</body>
</html>
