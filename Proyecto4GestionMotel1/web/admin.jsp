<%-- 
    Document   : admin
    Created on : Jul 4, 2019, 12:49:08 AM
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

        <c:if test="${not empty admin}">
            <c:import url="menu.jsp"/>


            <script type="text/javascript">

                $(document).ready(function(){
                $('.sidenav').sidenav();
                        
            </script>
        </c:if>
        <c:if test="${empty admin}">
            <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="card-panel center-align transparent white-text">

                        <h1>Acceso Denegado</h1>
                        <br> <img src="http://www.doingresearchinclusively.org/wp-content/uploads/2012/06/stop-300x300.png" alt="Descripción de la imagen">
                        <br> <h5>No eres administrador! <br> Seras redireccionado en <span id="countdowntimer">5</span> segundos </h5>

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
        </c:if>






        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        
        <script type="text/javascript">

                $(document).ready(function(){
                $('.sidenav').sidenav();
                

            </script>
        
    </body>
</html>
