<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: jbruneau2019
  Date: 04/07/2019
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sortir.com</title>
    <jsp:include page="inc/CSS_JS_global.jsp"/>
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
<body>
<header>
    <%-- Inclusion de la NavBar --%>
    <jsp:include page="inc/NavBar_header.jsp"/>
</header>
<c:if test="${!empty errEvent || !empty param.errEvent}">
    <div class="alert alert-danger text-center" role="alert">
        ${ (( empty errEvent? param.errEvent : errEvent)  == 1)? 'Erreur dans le processus d\'annulation' : 'La sortie n\'existe pas' }
    </div>
</c:if>

<fmt:parseDate value="${event.eventBeginning}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="parsedDateTime"  />
<fmt:formatDate value="${ parsedDateTime }" pattern="yyyy-MM-dd" var="dateEvent"  />

<c:if test="${!empty event}">
<div class="container-fluid">
    <br><br>
    <h4 class="text-center">Annuler une sortie</h4>
    <br>

    <div class="col-sm-8 col-md-8 col-lg-8 mr-auto ml-auto">

        <form action="${pageContext.request.contextPath}/cancelEvent" method="post">
            <input type="hidden" name="idEvent" value="${event.idEvent}" required>
            <div class="form-group row">
                <div class="col-sm-3">Nom de la sortie :</div>
                <div class="col-sm-9">
                    <p>${event.name}</p>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3">Date de la sortie :</div>
                <div class="col-sm-9">
                    <p>${dateEvent}</p>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3">Ville organisatrice :</div>
                <div class="col-sm-9">
                    <p>${event.site.name}</p>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-3">Lieu :</div>
                <div class="col-sm-9">
                    <p>${event.place.name} - ${event.place.street} ${event.place.city.postalCode} ${event.place.city.name}</p>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-3">Motif</div>
                <div class="col-sm-9">
                    <textarea class="autoExpand form-control" name="motif" rows="3" data-min-rows='3' placeholder="Motif de l'annulation de la sortie." required></textarea>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-5 mr-auto ml-auto">
                    <div class="row center">
                        <div class="col-xs-6 center">
                            <button type="submit" name="delete" class="btn btn-secondary">Valider</button>
                        </div>
                        <div class="col-sm-auto d-none d-sm-block">
                            <!-- Spacer pour le responsive -->
                        </div>&nbsp;
                        <div class="col-xs-6 center">
                            <input type="button" value="Annuler" class="btn btn-secondary" onclick="javascript:location.href='${pageContext.request.contextPath}/index'"/>
                        </div>
                    </div>
                </div>
            </div>
        </form>

    </div>
</div>
    </c:if>
<br><br>
    <div class="col mx-auto text-center">
        <input type="button" value="Retour à la liste des sorties" class="btn btn-secondary" onclick="javascript:location.href='${pageContext.request.contextPath}/index'"/>
    </div>
    <c:if test="${empty event}">

</c:if>
</body>
</html>
