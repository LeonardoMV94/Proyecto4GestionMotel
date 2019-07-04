<%-- 
    Document   : vendedor
    Created on : 22-06-2019, 18:00:26
    Author     : Leonardo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>

    <body class="deep-purple darken-1">

        <c:if test="${not empty operador}">
            <c:import url="menu.jsp"/>






        </c:if>
        <c:if test="${empty operador}">
            <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="white-text">
                        <div class="card-panel center-align transparent">

                            <h1>Acceso Denegado</h1>
                            <br> <img src="http://www.doingresearchinclusively.org/wp-content/uploads/2012/06/stop-300x300.png" alt="Descripción de la imagen">
                            <br> <h5>No eres operador! <br> Seras redireccionado en <span id="countdowntimer">5</span> segundos </h5>

                            <script type="text/javascript">
                                var timeleft = 5;
                                var downloadTimer = setInterval(function () {
                                    timeleft--;
                                    document.getElementById("countdowntimer").textContent = timeleft;
                                    if (timeleft <= 0)
                                        clearInterval(downloadTimer);
                                }, 1000);
                            </script>

                            <meta http-equiv="refresh" content="5;url=salir.jsp">
                        </div>
                    </div>
                </div>
            </div>
        </c:if>


        <ul id="slide-out" class="side-nav">
            <li><div class="user-view">
                    <div class="background">
                        <img src="images/office.jpg">
                    </div>
                    <a href="#!user"><img class="circle" src="images/yuna.jpg"></a>
                    <a href="#!name"><span class="white-text name">John Doe</span></a>
                    <a href="#!email"><span class="white-text email">jdandturk@gmail.com</span></a>
                </div></li>
            <li><a href="#!"><i class="material-icons">cloud</i>First Link With Icon</a></li>
            <li><a href="#!">Second Link</a></li>
            <li><div class="divider"></div></li>
            <li><a class="subheader">Subheader</a></li>
            <li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
        </ul>
        <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>


        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>


        <script type="text/javascript">
                               $(".button-collapse").sideNav();
        </script>
    </body>
</html>
