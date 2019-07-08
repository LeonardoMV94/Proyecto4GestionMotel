<%-- 
    Document   : listarclientes
    Created on : Jul 7, 2019, 7:48:40 PM
    Author     : Leonardo
--%>
<%@page import="cl.entities.Cliente"%>
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
    List<Cliente> lista = servicio.getClientes();
%>

<c:set scope="page" var="lista" value="<%=lista%>"/>
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
    <body>
        <div class="card z-depth-4">
            <table class="bordered">
                <tr>
                    <td>RUT CLIENTE</td>
                    <td>NOMBRE</td>
                    <td>APELLIDO PARTERNO</td>
                    <td>APELLIDO MATERNO</td>
                    <td>FECHA NACIMIENTO</td>
                </tr>

                <c:forEach items="${lista}" var="c">
                    <tr>
                        <td>${c.rutCliente}</td>
                        <td>${c.nombre}</td>
                        <td>${c.apellidoPaterno}</td>
                        <td>${c.apellidoMaterno}</td>
                        <td>${c.fechaNacimiento}</td> 
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>



<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript">
    $('.button-collapse').sideNav();

</script>

<script type="text/javascript">
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 18, // Creates a dropdown of 15 years to control year,
        today: 'Hoy',
        clear: 'Limpiar',
        close: 'Ok',
        closeOnSelect: false // Close upon selecting a date,

    });
</script>
</body>
</html>
