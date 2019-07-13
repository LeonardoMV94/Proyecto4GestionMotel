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
    List<TipoHabitacion> listat = servicio.getTipoHabitacion();
// se llama a todas las habitaciones existentes y se guardan en una lista
// se muestra en la vista usando variables dentro de jstl 

%>

<c:set scope="page" var="listah" value="<%=listah%>"/>
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


            <div class="row">
                <div class="col s6 offset-s3">
                    <div class="card-panel z-depth-4">
                        <h3>Crear Habitación </h3>
                        <form action="control.do" method="POST">



                            <div class="input-field">
                                <select name="tipoHabitacion">

                                    <c:forEach items="${listat}" var="t">
                                        <option value="${t.idTipoHabitacion}">${t.descripcionHabitacion}</option>
                                    </c:forEach>
                                </select>
                                <label>Tipo de Habitación</label>
                            </div>

                            <div class="input-field">
                                <select name="estado">
                                    <option value="1">Disponible</option>

                                    <option value="0">No disponible</option>

                                </select>
                                <label>Estado de habitacion</label>
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
            <br>

            <c:if test="${not empty msg2}">
                <div>
                    <div class="chip purple white-text">
                        ${msg2}
                    </div>
                </div>
            </c:if>


            <div class="col s10 offset-s1">
                <div class="card-panel z-depth-4">
                    <table class="bordered">
                        <tr>
                            <th>Id</th>
                            <th>Estado</th>
                            <th>Tipo De Habitacion</th>
                            <th>Precio</th>
                            <th>Editar</th>
                            <th>Eliminar</th>


                        </tr>

                        <c:forEach items="${listah}" var="h">

                            <tr>
                                <td>${h.idHabitacion}</td>
                                <c:choose>
                                    <c:when test="${h.estado eq 0}">

                                        <td>No disponible</td>

                                    </c:when>
                                    <c:when test="${h.estado eq 1}">
                                        <td>Disponible</td>

                                    </c:when>
                                </c:choose>

                                <td>${h.tipoHabitacionIdTipoHabitacion.descripcionHabitacion}</td>
                                <td>$ ${h.tipoHabitacionIdTipoHabitacion.precio} x 3 HRS</td>
                                <td>
                                    <!--cambiar por modal-->
                                    <div class="card-action left-align"> 
                                        <button data-target="modal${h.idHabitacion}" class="btn-floating blue modal-trigger">
                                            <i class="material-icons">edit</i>
                                        </button>

                                    </div>
                                </td>

                                </td>
                                <td>

                                    <a href="control.do?bt=deletehab&idhab=${h.idHabitacion}"
                                       class="btn-floating red">
                                        <i class="material-icons">delete</i>
                                    </a>

                                </td>

                            </tr>

                            <div id="modal${h.idHabitacion}" class="modal">
                                <div class="modal-content">
                                    <h3>Editar Tipo a la habitacion N° ${h.idHabitacion}</h3>
                                    <form action="control.do" method="POST">

                                        <div class="input-field">
                                            <input id="midTipoHabitacion" type="text" name="midTipoHabitacion" value="${h.idHabitacion}" readonly="readonly">
                                            <label for="midTipoHabitacion">ID Habitacion</label>
                                        </div>

                                        <div class="input-field">
                                            <select name="mtipoHabitacion">

                                                <c:forEach items="${listat}" var="t">
                                                    <option value="${t.idTipoHabitacion}">${t.descripcionHabitacion}</option>
                                                </c:forEach>
                                            </select>
                                            <label>Tipo de Habitación</label>
                                        </div>



                                        <div class="card-action right-align">
                                            <button class="btn" name="bt" value="editHabitacion" type="submit">
                                                editar
                                            </button>
                                        </div>
                                    </form>

                                </div>

                            </div>  

                        </c:forEach>
                    </table>
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

        <script type="text/javascript">
            $(document).ready(function () {
                $('select').material_select();
            });

        </script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('.modal').modal();
            });
        </script>


    </body>
</html>