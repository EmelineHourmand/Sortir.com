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
<div class="container container-padd-top">
    <!-- TITRE DE LA SORTIE -->
    <h3 align="center">${event.name}</h3>

    <!-- LABEL DE L'ETAT DE LA SORTIE -->
    <h6 align="center">${event.state.label}</h6>

    <!-- LE NOMBRE DE PLACE RESTANTE SI LA SORTIE EST OUVERTE AUX INSCRIPTION -->
    <c:if test="${event.state.label == 'Ouverte'}">
        <h6 align="center">Il reste
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

    <!-- SI L'UTILISATEUR CONNECTER EST DEJA INSCRIT, ON LE LUI NOTIFI -->
    <c:if test="${userIsRegister == true}">
        <h6 class="text-muted" align="center">Vous êtes inscrit à cette sortie.</h6>
    </c:if>

        <div class="row">
            <div class="col-sm">
                <!-- DATE DE DEBUT DE LA SORTIE -->
                <p>Date de début de l'évènement : <br>${event.getEventBeginning().getDayOfMonth()}/${event.getEventBeginning().getMonth()}/${event.getEventBeginning().getYear()}</p>

                <!-- SI LA SORTIE EST OUVERTE, ON AFFICHE LA DATE DE FIN DES INSCRIPTIONS -->
                <c:if test="${event.state.label == 'Ouverte'}">
                    <p>Date de fin des inscriptions : ${event.getRegistrationLimit().getDayOfMonth()}/${event.getRegistrationLimit().getMonth()}/${event.getRegistrationLimit().getYear()}</p>
                </c:if>

                <!-- DATE DE FIN DE LA SORTIE -->
                <p>Date de fin de l'évènement : <br>${event.getEventEnd().getDayOfMonth()}/${event.getEventEnd().getMonth()}/${event.getEventEnd().getYear()}</p>

                <!-- DESCRIPTION DE LA SORTIE -->
                <p>${event.description}</p>

                <!-- LISTE DES INSCRITS A LA SORTIE -->
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Pseudo</th>
                        <th scope="col">Nom</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="registration" items="${registrations}">
                        <tr>
                            <td>${registration.participant.username}</td>
                            <td>${registration.participant.firstname} ${registration.participant.lastname}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-sm">
                <!-- AFFICHE LE SITE DE RATACHEMENT DE LA SORTIE -->
                <p>Ville organisatrice : <br>${event.site.name}</p>

                <!-- LE LIEU DE LA SORTIE -->
                <p>Lieu : <br> ${event.place.name} <br> ${event.place.street} <br> ${event.place.city.name} ${event.place.city.postalCode}</p>

                <!-- AFFICHE LES COORDONNEE GSP DE LA SORTIE SI ELLES SONT DISPONIBLE -->
                <c:if test="${event.place.latitude != null && event.place.longitude != null}">
                    <p>GPS : <br>LAT.${event.place.latitude} LONG.${event.place.longitude}</p>
                </c:if>

                <!-- AFFICHE L'ORGANISATEUR DE LA SORTIE AVEC UN LIEN QUI REDIRIGE VERS SON PROFIL -->
                <p>Organisateur : <br>
                    <a href="${pageContext.request.contextPath}/profile?id=${event.organizer.idParticipant}">
                        ${event.organizer.username}</a><br>
                </p>
            </div>
        </div>
    <!-- ON AFFICHE LA BOUTON D'INSCRIPTION S'IL RESTE DES PLACES ET SI L'UTILISATEUR CONNECTER EST PAS INSCRIT -->
        <c:if test="${event.state.label == 'Ouverte' && (event.maxRegistration - nbnbRegister) > 0}">
            <c:if test="${userIsRegister == false}">
                 <a href="${pageContext.request.contextPath}/registerEvent?id=${event.idEvent}" class="btn btn-info">S'inscrire</a>
            </c:if>
        </c:if>

        <!-- ON AFFICHE LA BOUTON DE DESISTEMENT SI L'UTILISATEUR CONNECTER EST INSCRIT -->
        <c:if test="${event.state.label == 'Ouverte'}">
            <c:if test="${userIsRegister == true}">
                <a href="${pageContext.request.contextPath}/withdrawEvent?id=${nbRegister}"
                   class="btn btn-danger" onclick="return confirm('Êtes-vous sur de vouloir vous désinscrire ?')">Se désinscrire</a>
            </c:if>
        </c:if>

     <!-- BOUTON DE RETOUR A LA PAGE D'ACCEUIL -->
            <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary">Retour</a>
    </div>
</div>
</div>
</body>
</html>
