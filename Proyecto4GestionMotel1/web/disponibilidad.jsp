<%-- 
    Document   : disponibilidad
    Created on : Jul 6, 2019, 12:59:48 AM
    Author     : Leonardo
--%>

<%@page import="cl.entities.TipoHabitacion"%>
<%@page import="cl.entities.Habitacion"%>
<%@page import="java.util.List"%>
<%@page import="cl.modelo.ServicioLocal"%>
<%@page import="javax.naming.InitialContext"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! ServicioLocal servicio;%>
<%
    InitialContext ctx = new InitialContext();
    servicio = (ServicioLocal) ctx.lookup("java:global/Proyecto4GestionMotel1/Servicio!cl.modelo.ServicioLocal");
    List<Habitacion> listah = servicio.getHabitacion();

// se llama a todas las habitaciones existentes y se guardan en una lista
// se muestra en la vista usando variables dentro de jstl 

%>
<c:set scope="page" var="listah" value="<%=listah%>"/>


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

    <body class="black">

        <c:if test="${not empty operador}">
            <c:import url="menulateral.jsp"/>
            <c:import url="menu.jsp"/>


            <h2 class="center-align white-text">Habitaciones</h2>
            <div class="row">
                <c:forEach items="${listah}" var = "h">

                    <div class="col s3">

                        <c:choose>
                            <c:when test="${h.estado eq 0}">

                                <div class="card z-depth-4 red">

                                </c:when>
                                <c:when test="${h.estado eq 1}">
                                    <div class="card z-depth-4 green">

                                    </c:when>
                                </c:choose>





                                <form action="control.do" method="post">

                                    <div class="card-content white-text">
                                        <span class="card-title">Nº ${h.idHabitacion}</span>
                                        <i class="material-icons prefix medium white-text">
                                            hotel
                                        </i>

                                        <!-- para obtener datos de las clases se debe 
                                        observar el nombre de la variable en las entity de cada clase -->
                                        <p>Tipo de Habitacion: ${h.tipoHabitacionIdTipoHabitacion.descripcionHabitacion}</p>
                                        <p>$ ${h.tipoHabitacionIdTipoHabitacion.precio} x 3 HRS </p>
                                        <p>Aqui: COUNTDOWN</p>

                                        <br>
                                        <!--
                                        <c:choose>
                                            <c:when test="${h.estado eq 0}">

                                                <a class="btn-floating waves-effect waves-light red pulse"><i class="material-icons">not_interested</i></a>

                                            </c:when>
                                            <c:when test="${h.estado eq 1}">
                                                <a class="btn-floating waves-effect waves-light green pulse"><i class="material-icons">done_outline</i></a>    

                                            </c:when>
                                        </c:choose>
                                        -->        
                                                
                                        <div class="card-action">
                                            <a href="control.do?bt=asignarHab&codigoHab=${h.idHabitacion}" class="white-text">Seleccionar</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </c:forEach>
                </div>

            </div>






        </c:if>
        <c:if test="${empty operador}">
            <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="white-text">
                        <div class="card-panel center-align transparent">

                            <h1>Acceso Denegado</h1>
                            <br> <img src="img/denied.png">
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




        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>


        <script type="text/javascript">
                                $(".button-collapse").sideNav();
        </script>
    </body>
</html>
