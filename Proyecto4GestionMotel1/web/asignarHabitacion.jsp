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
            <c:import url="menulateral.jsp"/>
            <c:import url="menu.jsp"/>

            <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="card-panel  z-depth-3">
                        <h3>Asignar Habitación</h3>
                        <form action="control.do" method="POST">

                            <div class="input-field">
                                <input id = "idHabitacion" type="text" name="idHabitacion" data-length="100">
                                <label for="idHabitacon">ID HABITACION</label>
                            </div>

                            <div class="input-field">
                                <input id="rutCliente" type="text" name="rutCliente" data-length="12">
                                <label for="rutCliente">RUT</label>
                            </div>

                            <div class="input-field">
                                <select>
                                    <option value="" disabled selected>Cantidad Horas</option>
                                    <option value="1">3 HORAS</option>
                                    <option value="2">6 HORAS</option>
                                    <option value="3">9 HORAS</option>
                                    <option value="1">12 HORAS</option>


                                </select>
                                <label>Seleccione Cantidad De Horas</label>
                            </div>
                            <div class="card-action right-align"> 
                                <button class="btn purple" name = "bt" value="asignarhab" type="submit">

                                    Asignar
                                </button>
                            </div>
                        </form>

                    </div>
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




    </body>
</html>

