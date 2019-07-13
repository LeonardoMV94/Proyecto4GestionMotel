

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


       
        <%
            
            //trabjar los datos de asignar registro aca
            //calcular fecha y hora actual

            //calcular hora_termino sumando cantidadDeHoras

        %>


        <div class="row valign-wrapper">
            <div class="col s6 offset-s3">
                <div class="card-panel z-depth-4">
                    <h3>Registro completo</h3>
                    <form action="control.do" method="POST">



                        <div class="input-field">
                            <input id="rutUsuario" type="text" name="rutUsuario" value="${operador.rutUsuario}" readonly="readonly">
                            <label for="rutUsuario">Rut Usuario</label>
                        </div>        
                        <div class="input-field">
                            <input id="rutCliente" type="text" name="rutCliente" value="${rutcli}" readonly="readonly" >
                            <label for="rutCliente">Rut Cliente</label>
                        </div>
                        <div class="input-field">
                            <input id = "idHabitacion" type="text" name="idHabitacion" readonly="readonly" value="${cod}">
                            <label for="idHabitacon">ID HABITACION</label>
                        </div>

                        <div class="input-field">
                            <input id = "cantidad" type="text" name="cantidad" readonly="readonly" value="${cantidad}">
                            <label for="cantidad">Cantidad de horas</label>
                        </div>



                        <c:if test="${not empty msg}">
                            <div>
                                <div class="chip purple white-text">
                                    <i class="close material-icons">close</i>    
                                    ${msg}
                                </div>
                            </div>
                        </c:if>



                        <div class="card-action right-align">
                            <button class="btn purple white-text" name="bt" value="addReg" type="submit">
                                Agregar Registro
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
