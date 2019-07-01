<%--
  Created by IntelliJ IDEA.
  User: jbruneau2019
  Date: 28/06/2019
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div class="container- container-fluid">
    <br>

    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            ${event.name}
        </div>
    </div>
</div>

</body>
</html>
