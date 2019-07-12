<%-- 
    Document   : gestionPrecios
    Created on : Jul 9, 2019, 12:26:48 PM
    Author     : Johnny Casanova
--%>

<%@page import="cl.entities.TipoHabitacion"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="cl.modelo.ServicioLocal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! ServicioLocal servicio;%>
<%
    InitialContext ctx = new InitialContext();
    servicio = (ServicioLocal) ctx.lookup("java:global/Proyecto4GestionMotel1/Servicio!cl.modelo.ServicioLocal");
    List<TipoHabitacion> listat = servicio.getTipoHabitacion();

%>
<c:set scope="page" var="listat" value="<%=listat%>"/>


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
                        <h3>Lista de precios</h3>
                        <form action="control.do" method="POST">

                            <div class="input-field">
                                <input id="idTipoHabitacion" type="text" name="idTipoHabitacion">
                                <label for="idTipoHabitacion">ID Habitacion</label>
                            </div>
                            <div class="input-field">
                                <select name="descripcionHabitacion">
                                    <option>Jacuzzi</option>
                                    <option>Sin jacuzzi</option>
                                    <option>Cama de agua</option>     
                                </select>
                                <label>Tipo de habitacion</label>
                            </div>

                            <div class="input-field">
                                <input id="precio" type="text" name="precio">
                                <label for="precio">Precio </label>
                            </div>



                            <div class="card-action right-align">
                                <button class="btn" name="bt" value="addpre" type="submit">
                                    Añadir
                                </button>
                            </div>

                    </div>
                </div>
            </div>
            <br><br>

            <div class="col s10 offset-s1">
                <div class="card-panel z-depth-4">
                    <table class="bordered">
                        <tr>
                            <th>ID habitacion</th>
                            <th>Tipo habitacion</th>
                            <th>Precio</th>
                            <th>Editar</th>
                            
                        </tr>

                        <c:forEach items="${listat}" var="t">
                            <tr>
                                <td>${t.idTipoHabitacion}</td>
                                <td>${t.descripcionHabitacion}</td>
                                <td>${t.precio}</td> 
                                <td>
                                    <a href="control.do?bt=editpre&idTipoHabitacion=${c.idTipoHabitacion}&descripcionHabitacion=${c.descripcionHabitacion}&precio=${c.precio}"
                                       class="btn-floating blue"><i class="material-icons">edit</i>
                                    </a> 
                                   
                                </td>
                                

                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                </div>
            </div>




        </c:if>

        <c:import url="accesodenegadoadmin.jsp"/> 


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
