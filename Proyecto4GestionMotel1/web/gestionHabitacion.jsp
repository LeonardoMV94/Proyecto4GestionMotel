<%-- 
    Document   : gestionHotel
    Created on : 09-07-2019, 1:30:24
    Author     : home
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

        <c:if test="${not empty admin}">
           <c:import url="menulateral.jsp"/>
            <c:import url="menu.jsp"/>
        <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="card-panel z-depth-4">
                        <h3>Crear Nueva </h3>
                        <form action="control.do" method="POST">

                            <div class="input-field">
                                <input id="rutUsuario" type="text" name="rutUsuario">
                                <label for="rutUsuario">N°de habitación</label>
                            </div>
                            <div class="input-field">
                                <input id="nombre" type="text" name="nombre">
                                <label for="nombre">Tipo de Habitación</label>
                            </div>

                            <div class="input-field">
                                <input id="apellidoPaterno" type="text" name="apellidoPaterno">
                                <label for="apellidoPaterno">Precio/Horas</label>
                            </div>

                            <div class="input-field">
                                <input id="apellidoMaterno" type="text" name="apellidoMaterno">
                                <label for="apellidoMaterno">Apellido Materno</label>
                            </div>
                            

                            

                            <div class="card-action right-align">
                                <button class="btn" name="bt" value="addhab" type="submit">
                                    Añadir Habitación
                                </button>
                            </div>

                        </form>
                        <c:if test="${not empty msg}">
                            <div>
                                <div class="chip purple white-text">
                                    ${msg}
                                </div>
                            </div>
                        </c:if>


                    </div>
                </div>
                </div>
 
            
        </c:if>
       
        <c:import url="accesodenegadoadmin.jsp"/>

        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>

        <script type="text/javascript">
             $('.button-collapse').sideNav();

        </script>
          <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('select').material_select();
            });

        </script>
        <script type="text/javascript">
            $('.button-collapse').sideNav();

        </script>
    </body>
</html>