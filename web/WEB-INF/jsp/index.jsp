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


                <div class="dropdown dropright">
                    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Site
                    </a>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <c:forEach var="Site" items="${listeSite}">
                            <option class="dropdown-item" value="${Site.getName()}"
                                    name="${Site.getName()}">${Site.getName()}</option>
                        </c:forEach>
                    </div>
                </div>

                Le nom de la sortie contient:
                <div class="input-group mb-3">

                    <input class="form-control" type="text" placeholder="Search" aria-label="Search">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="button-addon1">Rechercher</button>
                    </div>
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


</div>


<jsp:include page="inc/CSS_JS_global.jsp"></jsp:include>
</body>
</html>
