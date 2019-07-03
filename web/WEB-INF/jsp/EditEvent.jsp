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
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editEvent.css">--%>
    <script>
        // Applied globally on all textareas with the "autoExpand" class
        $(document)
            .one('focus.autoExpand', 'textarea.autoExpand', function(){
                var savedValue = this.value;
                this.value = '';
                this.baseScrollHeight = this.scrollHeight;
                this.value = savedValue;
            })
            .on('input.autoExpand', 'textarea.autoExpand', function(){
                var minRows = this.getAttribute('data-min-rows')|0, rows;
                this.rows = minRows;
                rows = Math.ceil((this.scrollHeight - this.baseScrollHeight) / 16);
                this.rows = minRows + rows;
            });
    </script>
</head>
<header>
    <%-- Inclusion de la NavBar --%>
    <jsp:include page="inc/NavBar_header.jsp"/>
</header>
<body>

<c:if test="${!empty errEvent || !empty param.errEvent}">
    <div class="alert alert-danger text-center" role="alert">
        Echec dans l'édition de la sortie.
    </div>
</c:if>

${event.registrationLimit}.<br>
<c:set var="eventName" 	    value="${event.name}" scope="page" />
<%--<c:set var="dateTimeEvent"  value="${event.eventBeginning}" scope="page" />--%>

<fmt:parseDate value="${event.eventBeginning}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="parsedDateTime"  />
<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="dateTimeEvent" />

<fmt:parseDate value="${event.registrationLimit}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="parsedDateTime"  />
<fmt:formatDate value="${ parsedDateTime }" pattern="yyyy-MM-dd" var="dateLimit"  />

<%--<c:set var="dateLimit" 	    value="${event.registrationLimit}" scope="page" />--%>
<c:set var="nbParticipant"  value="${event.maxRegistration}" scope="page" />
<%--<c:set var="durationMinute" value="${event.eventEnd - event.eventBeginning}" scope="page" />--%>
<c:set var="description" 	value="${event.description}" scope="page" />
<c:set var="siteIdEvent" 	value="${event.site.idSite}" scope="page" /><%--Retourne cette valeur sous site lors du post--%>
<c:set var="placeName" 		value="${event.place.name}" scope="page" />
<c:set var="street" 		value="${event.place.street}" scope="page" />
<c:set var="cityName" 		value="${event.place.city.name}" scope="page" />
<c:set var="postalCode" 	value="${event.place.city.postalCode}" scope="page" />
<c:set var="latitude" 		value="${event.place.latitude}" scope="page" />
<c:set var="longitude" 		value="${event.place.longitude}" scope="page" />


<%--<fmt:parseDate value="${ event.eventBeginning }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />--%>
<%--<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />--%>
<%--<br>--%>



<div class="container-fluid">
    <br><br>
    <h4 class="text-center">${(empty event)? 'Créer une sortie': 'Modifier une sortie'}</h4>
    <br>
<%--        <div class="col-sm-11 col-md-12 col-lg-11 mr-auto ml-auto"><!-- sur 11 col centré -->--%>
            <div class="col-sm-12 col-md-12 col-lg-12 mr-auto ml-auto">
            <!-- ######### 2 colonnes ######### -->



            <form action="${pageContext.request.contextPath}/editEvent" method="post">

                <input type="hidden" name="idEvent" value="${event.idEvent}" required>

                <div class="form-group row">

                    <!-- #################### Ligne 1 #################### -->
                    <!-- Nom de la sortie -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="eventName" class="col-sm-5 col-form-label">Nom de la sortie : </label>
                        <div class="col-sm-7">
                            <input type="text" name="eventName" class="form-control" id="eventName" placeholder="Nom de la sortie" value="${eventName}" required>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Ville organisatrice --> <!-- TODO A revoir pour liste déroulante -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="site" class="col-sm-5 col-form-label">Ville organisatrice : </label>

                        <div class="col-sm-7">
                            <select class="custom-select" name="site" id="site">
                                <c:forEach var="site" items="${sites}">
                                    <option value="${site.idSite}"
                                            name="site"
                                            ${ (siteIdEvent == site.idSite)? 'selected' : '' }>${site.name}</option>
                                </c:forEach>
                            </select>

<%--                            <input type="text" name="site" class="form-control" id="site" value="${site}" required>--%>
                        </div>

                    </div>


                    <!-- #################### Ligne 2 #################### -->
                    <!-- Date heure sortie -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="dateTimeEvent" class="col-sm-5  col-form-label">Date et heure de la sortie : </label>
                        <div class="col-sm-7">
                            <input type="datetime-local" name="dateTimeEvent" class="form-control" id="dateTimeEvent" value="${dateTimeEvent}" required>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Lieu --> <!-- TODO A revoir pour liste déroulante -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="placeName" class="col-sm-5  col-form-label">Lieu : </label>
                        <div class="col-sm-7">
                            <input type="text" name="placeName" class="form-control" id="placeName" placeholder="Nom du lieu" value="${placeName}" required>
                        </div>
                    </div>


                    <!-- #################### Ligne 3 #################### -->
                    <!-- Date limite inscription -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="dateLimit" class="col-sm-5  col-form-label">Date limite inscription : </label>
                        <div class="col-sm-7">
                            <input type="date" name="dateLimit" class="form-control" id="dateLimit" value="${dateLimit}" required>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Ville --> <!-- TODO A revoir passer en texte fixe et affichage selon Liste déroulante Lieu -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="cityName" class="col-sm-5  col-form-label">Ville : </label>
                        <div class="col-sm-7">
                            <input type="text" name="cityName" class="form-control" id="cityName" placeholder="Nom de la ville" value="${cityName}" required>
                        </div>
                    </div>


                    <!-- #################### Ligne 4 #################### -->
                    <!-- Participant max -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="nbParticipant" class="col-sm-5  col-form-label">Nombre de participants : </label>
                        <div class="col-sm-7">
                            <input type="number" name="nbParticipant" class="form-control" id="nbParticipant" value="${nbParticipant}" required>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Rue --> <!-- TODO A revoir passer en texte fixe et affichage selon Liste déroulante Lieu -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="street" class="col-sm-5  col-form-label">Rue : </label>
                        <div class="col-sm-7">
                            <input type="text" name="street" class="form-control" id="street" placeholder="rue" value="${street}" required>
                        </div>
                    </div>


                    <!-- #################### Ligne 5 #################### -->
                    <!-- Durée -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="durationMinute" class="col-sm-5  col-form-label">Durée : </label>
                        <div class="col-sm-7">
                            <div><input type="number" name="durationMinute" class="form-control w-50 d-inline-block" id="durationMinute" value="${requestScope.durationMinute}" required> &nbsp; minute(s)</div>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Code postal --> <!-- TODO A revoir passer en texte fixe et affichage selon Liste déroulante Lieu -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="postalCode" class="col-sm-5  col-form-label">Code postal : </label>
                        <div class="col-sm-7">
                            <input type="text" name="postalCode" class="form-control" id="postalCode" placeholder="Code postal" value="${postalCode}" required>
                        </div>
                    </div>


                    <!-- #################### Ligne 6 #################### -->
                    <!-- Description -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="description" class="col-sm-5  col-form-label">Description : </label>
                        <div class="col-sm-7">
                            <textarea class="autoExpand form-control" id="description" name="description" rows="3" data-min-rows='3' placeholder="Description de la sortie." required>${description}</textarea>
                        </div>
                    </div>
                    <div class="col-lg-auto d-none d-lg-block">
                        <!-- Spacer pour le responsive -->
                    </div>
                    <!-- Latitude --> <!-- TODO A revoir passer en texte fixe et affichage selon Liste déroulante Lieu -->
                    <div class="col-md-6 form-group row col-lg-5 mr-auto ml-auto">
                        <label for="latitude" class="col-sm-5  col-form-label">Latitude : </label>
                        <div class="col-sm-7">
                            <input type="text" name="latitude" class="form-control" id="latitude" placeholder="xx.xxx" value="${latitude}" required>
                        </div>
                        <br><br>
                        <!-- Longitude --> <!-- TODO A revoir passer en texte fixe et affichage selon Liste déroulante Lieu -->
                        <label for="longitude" class="col-sm-5  col-form-label">Longitude : </label>
                        <div class="col-sm-7">
                            <input type="text" name="longitude" class="form-control" id="longitude" placeholder="xx.xxx" value="${longitude}" required>
                        </div>
                    </div>


                </div>
                <!--  #################### Fin 2 colonnes #################### -->

                <!-- ######### Boutons ######### -->

                <br>

<%--                <div class="row">--%>
<%--                    --%>
<%--                        <div class="col-xs-3 mx-auto text-center">--%>
<%--                            <button type="submit" name="save" class="btn-block btn btn-secondary">Enregistrer</button>--%>
<%--                        </div>--%>
<%--                        <div class="col-xs-3 mx-auto text-center">--%>
<%--                            <button type="submit" name="create" class="btn-block btn btn-secondary">Publier</button>--%>
<%--                        </div>--%>
<%--                        <div class="col-xs-3 mx-auto text-center">--%>
<%--                            <button type="submit" name="create" class="btn-block btn btn-secondary">Supprimer</button>--%>
<%--                        </div>--%>
<%--                        <div class="col-xs-3 mx-auto text-center">--%>
<%--                            <input type="button" value="Annuler" class="btn-block btn btn-secondary" onclick="javascript:location.href='${pageContext.request.contextPath}/encheres?sign'"/>--%>
<%--                        </div>--%>
<%--                    --%>
<%--                </div>--%>


                <div class="row">

                    <div class="col-xs-5 mr-auto ml-auto">
                        <div class="row center">

                            <div class="col-xs-6 center">
                                <button type="submit" name="save" class="btn btn-secondary">Enregistrer</button>
                            </div>
                            <div class="col-sm-auto d-none d-sm-block">
                                <!-- Spacer pour le responsive -->
                            </div>
                            &nbsp;
                            <div class="col-xs-6 center">
                                <button type="submit" name="publish" class="btn btn-secondary">Publier</button>
                            </div>
                            <div class="col-sm-auto d-none d-sm-block">
                                <!-- Spacer pour le responsive -->
                            </div>
                            <c:if test="${!empty event}">
                            <div class="col-xs-6 center">
                                <button type="submit" name="delete" class="btn btn-secondary">Supprimer</button>
                            </div>
                            <div class="col-sm-auto d-none d-sm-block">
                                <!-- Spacer pour le responsive -->
                            </div>
                            </c:if>
                            <div class="col-xs-6 center">
                                <input type="button" value="cancel" class="btn btn-secondary" onclick="javascript:location.href='${pageContext.request.contextPath}/index'"/>
                            </div>
                        </div>
                    </div>
                </div>

            </form>

        </div>
        </div>
</body>
</html>
