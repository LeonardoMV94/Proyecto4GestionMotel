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

            <div class="card-panel transparent z-depth-3 white-text">
                <h3>ASIGNAR HABITACIÓN</h3>
                <form action="control.do" method="POST">

                    <div class="input-field col s12">
                        <input id = "idHabitacion" type="text" name="idHabitacion" data-length="100">
                        <label for="idHabitacon">ID HABITACION</label>
                    </div>
            </div>
            <div class="input-field col s12">
                <input id="rutCliente" type="text" name="rutCliente" data-length="12">
                <label for="rutCliente">RUT</label>
            </div>

            <div class="input-field col s12">
                <select>
                    <option value="" disabled selected>Cantidad Horas</option>
                    <option value="1">3 HORAS</option>
                    <option value="2">6 HORAS</option>
                    <option value="3">9 HORAS</option>
                    <option value="1">12 HORAS</option>


                </select>
                <label>SELECCIONE CANTIDAD DE HORAS</label>
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




    </body>
</html>

