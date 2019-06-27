<%--
  Created by IntelliJ IDEA.
  User: jbruneau2019
  Date: 26/06/2019
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="inc/CSS_JS_global.jsp"></jsp:include>
</head>
<header>
    <%-- Inclusion de la NavBar --%>
    <jsp:include page="inc/NavBar_header.jsp"></jsp:include>
    <script>
        function forgottenPass(){
            var emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            var emailInput = document.forms["loginForm"]["email"];

            if(!emailInput.value.match(emailPattern)){
                //console.log("mail pas ok");
                alert("Veuillez entrer votre email.");
                emailInput.focus()
            }else{
                //console.log("mail ok");
                alert("Un message a bien été envoyé, vous recevrez une réponse par email.");
            }
            return false;
        }
    </script>
</header>
<body>

    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card my-5">
                    <div class="card-body">
                        <h5 class="card-title text-center text-uppercase">Connexion</h5>
                        <br>
                        <%-- Formulaire de connexion --%>
                        <form name="loginForm" action="${pageContext.request.contextPath}/login" method="post">
                            <div class="form-group">
                                <label for="inputEmail">Email :</label>
                                <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Addresse mail" value="${requestScope.email}" required autofocus>
                            </div>

                            <div class="form-group">
                                <label for="inputPassword">Mot de passe</label>
                                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Mot de passe" required>
                            </div>

                            <div class="float-right custom-control custom-checkbox mb-3">
                                <input type="checkbox" name="remembreMe" class="custom-control-input" id="remembreMe" ${(requestScope.remembreMe == 'on')? 'checked' : ''}>
                                <label class="custom-control-label" for="remembreMe">Se rappeler de moi</label>
                            </div>
                            <br><br>
                            <button class="btn btn-lg btn-secondary btn-block" type="submit">Se connecter</button>

                            <hr class="my-4">
                            <div class="text-center"><a href="#" onclick="return forgottenPass();">Mot de passe oublié</a></div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>


</body>
</html>
