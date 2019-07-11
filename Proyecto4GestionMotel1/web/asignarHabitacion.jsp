<%-- 
    Document   : AsisgnarHabitacion
    Created on : 09-07-2019, 0:35:56
    Author     : Wilman
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

    <body class="black">

        <c:if test="${not empty operador}">


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
                                    <input id="rutCliente" type="text" name="rutCliente" data-length="10" value="${rutCliente}">
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


                            <
                            <br>    

                            <div class="input-field">
                                <select name="cantidad">
                                    <option value="${cantidad}" disabled selected>Cantidad Horas</option>
                                    <option value="3">3 HORAS</option>
                                    <option value="6">6 HORAS</option>
                                    <option value="9">9 HORAS</option>
                                    <option value="12">12 HORAS</option>


                                </select>
                                <label>Cantidad de Horas</label>
                            </div>
                            
                                <c:if test="${not empty msgmodal}">
                                <div class="card-action left-align"> 
                                    <button data-target="modal1" class="btn purple modal-trigger">Ingresar Cliente</button>

                                </div>
                            </c:if>    
                                    
                                    
                                    
                                    <div class="card-action right-align"> 
                                <button class="btn purple" name = "bt" value="registro" type="submit">

                                    Registrar
                                </button>

                            </div>


                            
                        </form>

                    </div>
                </div>
            </div>


            <div id="modal1" class="modal">
                <div class="modal-content">

                    <h3>Buscar Cliente</h3>
                    <form action="control.do" method="POST">

                        <div class="input-field">
                            <input id="rutCliente" type="text" name="rutCliente" data-length="10">
                            <label for="rutCliente">Rut</label>
                        </div>

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



    </body>
</html>

