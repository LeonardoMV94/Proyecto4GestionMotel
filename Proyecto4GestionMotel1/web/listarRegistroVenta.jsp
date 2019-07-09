<%-- 
    Document   : listarRegistroVenta
    Created on : 09-07-2019, 4:03:47
    Author     : Wilman
--%>

<%@page import="cl.entities.RegistroVentas"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="cl.modelo.ServicioLocal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! ServicioLocal servicio;%>
<%
    InitialContext ctx = new InitialContext();
    servicio = (ServicioLocal) ctx.lookup("java:global/Proyecto4GestionMotel1/Servicio!cl.modelo.ServicioLocal");
    List<RegistroVentas> listar = servicio.getRegistro();

%>


<c:set scope="page" var="listar" value="<%=listar%>"/>
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
<!--
            <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="card-panel z-depth-4">
                        <h3>Listar Resgistro Ventas</h3>
                        <form action="control.do" method="POST">

                            <div class="input-field">
                                <input id="idRegistro" type="interger" name="idRegistro">
                                <label for="rutUsuario">id Registro</label>
                            </div>
                            <div class="input-field">
                                <input id="rutUsuario" type="text" name="rutUsuario" data-lenght="10">
                                <label for="rutUsuario">Rut Usuario</label>
                            </div>
                            <div class="input-field">
                                <input id="rutCliente" type="text" name="rutCliente" data-lenght="10">
                                <label for="rutCliente">Rut Cliente</label>
                            </div>

                            <div class="input-field">
                                <input id="idBoleta" type="text" name="idBoleta">
                                <label for="idBoleta">ID Boleta</label>
                            </div>

                            <div class="input-field">
                                <input id="idHabitacion" type="interger" name="idHabitacion">
                                <label for="idHabitacion">ID Habitacion</label>
                            </div>

                            <div class="input-field">
                                <input id="horaEntrada" type="text" name="horaEntrada">
                                <label for="horaEntrada">Hora Entrada</label>
                            </div>
                            <div class="input-field">
                                <input id="horaSalida" type="text" name="horaSalida">
                                <label for="rutUsuario">Hora Salida</label>
                            </div>


                        </form>
                    </div>
                </div>
            </div>
-->
            <div class="col s10 offset-s1">
                <div class="card-panel z-depth-4">
                    <table class="bordered">
                        <tr>

                            <th>Id Registro</th>
                            <th>Rut Usuario</th>
                            <th>Rut Cliente</th>
                            <th>Id Boleta</th>
                            <th>Id Habitación</th>
                            <th>Hora Entrada</th>
                            <th>Hora Salida</th>     
          
                        </tr>
                        <c:forEach items="${listar}" var="r">
                            <tr>
                                <td>${r.idRegistro}</td>
                                <td> ${r.clienterutcliente.rutCliente}</td>
                                <td>${r.usuariosrutusuario.rutUsuario}</td>
                                <td>${r.boletaidboleta.idBoleta}</td>
                                <td>${r.habitacionidhabitacion.idHabitacion}</td>
                                <td>${r.horaEntrada}</td>
                                <td>${r.horaSalida}</td>
                            </tr>
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
</body>
</html>

