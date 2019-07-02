<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jbruneau2019
  Date: 28/06/2019
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="inc/CSS_JS_global.jsp"/>
</head>
<header>
    <%-- Inclusion de la NavBar --%>
    <jsp:include page="inc/NavBar_header.jsp"/>
</header>
<body>


<c:set var="eventName" 	    value="${event.name}" scope="page" />
<c:set var="dateTimeEvent" value="${event.eventBeginning}" scope="page" />
<c:set var="dateLimit" 	    value="${event.eventEnd}" scope="page" />
<c:set var="nbPlace" 	    value="${event.eventEnd}" scope="page" />
<c:set var="email" 		    value="${}" scope="page" />
<c:set var="tel" 		    value="${}" scope="page" />
<c:set var="rue" 		    value="${ }" scope="page" />
<c:set var="cp" 		    value="${ }" scope="page" />
<c:set var="ville" 		    value="${ }" scope="page" />


${event.name}<br>

<fmt:parseDate value="${ event.eventBeginning }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
<br>




${event.eventBeginning}<br>
${event.eventEnd}<br>
${event.registrationLimit}<br>
${event.maxRegistration}<br>
${event.description}<br>

${event.place.name}<br>
${event.place.latitude}<br>
${event.place.longitude}<br>
${event.place.street}<br>
${event.place.city.name}<br>
${event.place.city.postalCode}<br>

${event.state.label}<br>
${event.site.name}<br>
${event.organizer}<br>





<div class="container-fluid">
    <br><br>
    <h4 class="text-center">Modifier une sortie</h4>

        <div class="col-sm-11 col-md-12 col-lg-11 mr-auto ml-auto"><!-- sur 11 col centré -->
            <!-- ######### 2 colonnes ######### -->



            <form action="${pageContext.request.contextPath}/event" method="post">

                <div class="form-group row">

                    <!-- Ligne 1 -->
                    <!-- Nom de la sortie -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="eventName" class="col-sm-5 col-form-label">Nom de la sortie : </label>
                        <div class="col-sm-7">
                            <input type="text" name="eventName" class="form-control" id="eventName" placeholder="Nom de la sortie" value="${event.name}" required>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Ville organisatrice -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="site" class="col-sm-5 col-form-label">Ville organisatrice : </label>
                        <div class="col-sm-7">
                            <input type="text" name="site" class="form-control" id="site" placeholder="Ville organisatrice" value="${event.site.name}" required>
                        </div>
                    </div>


                    <!-- Ligne 2-->
                    <!-- Date heure sortie -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="dhEvent" class="col-sm-5  col-form-label">Date et heure de la sortie : </label>
                        <div class="col-sm-7">
                            <input type="datetime-local" name="dhEvent" class="form-control" id="dhEvent" placeholder="prénom" value="${event.eventBeginning}" required>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Email -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="email" class="col-sm-5  col-form-label">Email : </label>
                        <div class="col-sm-7">
                            <input type="email" name="email" class="form-control" id="email" placeholder="exemple@domaine.com" required>
                        </div>
                    </div>


                    <!-- Ligne 3-->
                    <!-- Téléphone -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="tel" class="col-sm-5  col-form-label">Téléphone : </label>
                        <div class="col-sm-7">
                            <input type="tel" name="tel" class="form-control" id="tel" placeholder="N° de téléphone" required>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Rue -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="rue" class="col-sm-5  col-form-label">Rue : </label>
                        <div class="col-sm-7">
                            <input type="text" name="rue" class="form-control" id="rue" placeholder="rue" required>
                        </div>
                    </div>


                    <!-- Ligne 4-->
                    <!-- code Postal -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="code-postal" class="col-sm-5  col-form-label">Code Postal : </label>
                        <div class="col-sm-7">
                            <input type="text" name="code-postal" class="form-control" id="code-postal" placeholder="code postal" required>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Ville -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="ville" class="col-sm-5  col-form-label">Ville : </label>
                        <div class="col-sm-7">
                            <input type="text" name="ville" class="form-control" id="ville" placeholder="ville" required>
                        </div>
                    </div>


                    <!-- Ligne 5-->
                    <!-- Mot de passe -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="pass" class="col-sm-5  col-form-label">Mot de passe : </label>
                        <div class="col-sm-7">
                            <input type="password" name="pass" class="form-control" id="pass" placeholder="mot de passe" required onkeyup='checkpass();'>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Confirmation -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="pass-check" id="confirm" class="col-sm-5  col-form-label">Confirmation : </label>
                        <div class="col-sm-7">
                            <input type="password" name="pass-check" class="form-control" id="pass-check" placeholder="mot de passe" required onkeyup='checkpass();'>
                        </div>
                    </div>


                </div>
                <!-- ######### Fin 2 colonnes ######### -->

                <!-- ######### Boutons ######### -->

                <br>
                <div class="row">

                    <div class="col-xs-5 mr-auto ml-auto">
                        <div class="row center">
                            <div class="col-xs-6 center">
                                <button type="submit" name="create" class="btn btn-outline-dark btn-min">Créer</button>
                            </div>
                            <div class="col-sm-auto d-none d-sm-block">
                                <!-- Spacer pour le responsive -->
                            </div>
                            &nbsp;
                            <div class="col-xs-6 center">
                                <input type="button" value="Annuler" class="btn btn-outline-dark btn-min" onclick="javascript:location.href='${pageContext.request.contextPath}/encheres?sign'"/>
                            </div>
                        </div>
                    </div>


                </div>



            </form>

        </div>



























        </div>


</body>
</html>
