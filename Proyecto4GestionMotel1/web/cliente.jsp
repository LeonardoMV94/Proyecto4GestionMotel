

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="cl.entities.Cliente"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="cl.modelo.ServicioLocal"%>
<%! ServicioLocal servicio;%>
<%
    InitialContext ctx = new InitialContext();
    servicio = (ServicioLocal) ctx.lookup("java:global/Proyecto4GestionMotel1/Servicio!cl.modelo.ServicioLocal");
    List<Cliente> lista = servicio.getClientes();
%>

<c:set scope="page" var="lista" value="<%=lista%>"/>



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

    <body class="black">


        <c:import url="menulateral.jsp"/>
        <c:import url="menu.jsp"/>


        <div class="row">
            <div class="col s6 offset-s3">
                <div class="card-panel z-depth-4">
                    <h3>Buscar Cliente</h3>
                    <form action="control.do" method="POST">

                        <div class="input-field col s10">
                            <input id="rutCliente" type="text" name="rutCliente" data-length="12">
                            <label for="rutCliente">Rut</label>
                        </div>
                        <div class="card-action col s2">
                            
                            <button class="btn-floating purple white-text" name="bt" value="addcli" type="submit">
                                <i class="material-icons">search</i>
                            </button>
                            
                        </div>
                        <c:if test="${not empty msg}">
                            <div>
                                <div class="chip purple white-text">
                                    <i class="close material-icons">close</i>    
                                    ${msg}
                                </div>
                            </div>
                        </c:if>

                        <div class="input-field">
                            <input id="nombre" type="text" name="nombre">
                            <label for="nombre">Nombre</label>
                        </div>
                        <div class="input-field">
                            <input id="apellidoPaterno" type="text" name="apellidoPaterno">
                            <label for="apellidoPaterno">Apellido Paterno</label>
                        </div>
                        <div class="input-field">
                            <input id="apellidoMaterno" type="text" name="apellidoMaterno">
                            <label for="apellidoMaterno">Apellido Materno</label>
                        </div>              
                        <div class="input-field ">
                            <input id="fechaNacimiento" type="text" class="datepicker" name="fechaNacimiento">
                            <label for="fechaNacimiento">Fecha de nacimiento</label>
                        </div>
                        <div class="card-action right-align">
                            <button class="btn purple white-text" name="bt" value="addcli" type="submit">
                                Agregar
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <br><br>



        <c:import url="accesodenegadooperador.jsp"/> 







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
