<%-- 
    Document   : AsisgnarHabitacion
    Created on : 09-07-2019, 0:35:56
    Author     : Wilman
--%>
<%@page import="cl.entities.MetodoPago"%>
<%@page import="cl.entities.Boleta"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="cl.modelo.ServicioLocal"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! ServicioLocal servicio;%>
<%
    InitialContext ctx = new InitialContext();
    servicio = (ServicioLocal) ctx.lookup("java:global/Proyecto4GestionMotel1/Servicio!cl.modelo.ServicioLocal");
    List<MetodoPago> listam = servicio.getMetodoPago();


%>
<c:set scope="page" var="listam" value="<%=listam%>"/>
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

            <%
                //info
                //https://www.todoexpertos.com/categorias/tecnologia-e-internet/programacion/java/respuestas/115920/url-de-procedencia
                String urlAntes = request.getHeader("Referer");
                if (urlAntes.equals("http://localhost:8085/Proyecto4GestionMotel1/disponibilidad.jsp")) {
                    String idH = request.getParameter("codigoHab");
            %>
            <c:set scope="page" var="codigoHab" value="<%=idH%>"/>
            <%
                }

            %>




            <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="card-panel  z-depth-3">
                        <h3>Asignar Habitación</h3>
                        <br>

                        <form action="control.do" method="POST">

                            <div class="input-field">
                                <input id = "idHabitacion" type="text" name="idHabitacion" readonly="readonly" value="${cod}${codigoHab}">
                                <label for="idHabitacon">ID HABITACION</label>
                            </div>



                            <div class="col s10">  
                                <div class="input-field">
                                    <input id="rutCliente" type="text" name="rutCliente" data-length="10"  value="${rutCliente}" placeholder="${rc}">
                                    <label for="rutCliente">RUT</label>
                                </div>
                            </div>


                            <div class="card-action">

                                <button class="modal1 btn-floating purple white-text" name="bt" value="asignarhab" type="submit">
                                    <i class="material-icons">search</i>
                                </button>

                            </div>
                            <c:if test="${not empty msg}">

                                <div class="chip">
                                    ${msg}
                                    <i class="close material-icons">close</i>
                                </div>

                            </c:if>


                            <br>    

                            <div class="input-field">
                                <select name="cantidad">
                                    <option value="3">3 HORAS</option>
                                    <option value="6">6 HORAS</option>
                                    <option value="9">9 HORAS</option>
                                    <option value="12">12 HORAS</option>


                                </select>
                                <label>Cantidad de Horas</label>
                            </div>

                            <div class="input-field">
                                <select name="metodopago">
                                    <c:forEach items="${listam}" var="m">
                                        <option value="${m.idMetodoPago}">${m.descripcionMetodoPago}</option>
                                    </c:forEach>
                                </select>
                                <label>Metodo de pago</label>
                            </div>


                            <div class="card-action center-align">
                                <c:if test="${not empty msgmodal}">

                                    <button data-target="modal1" class="btn purple modal-trigger">
                                        Ingresar Cliente
                                    </button>
                                </c:if>    


                                <!-- 
                                <button class="btn purple">
                                    <a href="registroCompleto.jsp?codigoHab=${h.idHabitacion}&rutCliente=${rutCliente}&cantidad=${cantidad}" class="white-text">Asignar Habitacion</a>

                                </button>
                                -->

                                <button class="btn purple white-text pulse" name="bt" value="registrar" type="submit">
                                    Ir a registro
                                </button>


                            </div>



                        </form>

                    </div>
                </div>
            </div>


            <div id="modal1" class="modal">
                <div class="modal-content">

                    <h3>Agregar Cliente al sistema</h3>
                    <form action="control.do" method="POST">

                        <div class="input-field">
                            <input id="clirutCliente" type="text" name="clirutCliente" data-length="10">
                            <label for="clirutCliente">Rut</label>
                        </div>

                        <div class="input-field">
                            <input id="clinombre" type="text" name="clinombre">
                            <label for="clinombre">Nombre</label>
                        </div>
                        <div class="input-field">
                            <input id="cliapellidoPaterno" type="text" name="cliapellidoPaterno">
                            <label for="cliapellidoPaterno">Apellido Paterno</label>
                        </div>
                        <div class="input-field">
                            <input id="cliapellidoMaterno" type="text" name="cliapellidoMaterno">
                            <label for="cliapellidoMaterno">Apellido Materno</label>
                        </div>              
                        <div class="input-field ">
                            <input id="clifechaNacimiento" type="text" class="datepicker" name="clifechaNacimiento">
                            <label for="clifechaNacimiento">Fecha de nacimiento</label>
                        </div>
                        <div class="card-action right-align">
                            <button class="btn purple white-text" name="bt" value="addcli" type="submit">
                                Agregar Cliente
                            </button>
                        </div>
                    </form>

                </div>

            </div>                    



        </c:if>


        <c:import url="accesodenegadooperador.jsp"/>
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

        <script type="text/javascript">
            $('.datepicker').pickadate({
                selectMonths: true, // Creates a dropdown to control month
                selectYears: 68, // Creates a dropdown of 15 years to control year,
                clear: 'Limpiar',
                close: 'Ok',
                closeOnSelect: false, // Close upon selecting a date,
                format: 'yyyy-mm-dd'
            });
        </script>
    </body>
</html>

