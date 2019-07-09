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

    <body>

        <c:if test="${not empty admin}">
             <c:import url="menulateral.jsp"/>
            <c:import url="menu.jsp"/>



            <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="card-panel">
                        <h4>Cambiar Clave</h4>

                        <br>
                        <form action="control.do" method="POST">
                            <div class="input-field">
                                <input id="claveanterior" type="password" name="claveanterior">
                                <label for="claveanterior">Contraseña anterior</label>
                           </div>     
                                <div class="input-field">
                                    <input id="clavenueva" type="password" name="clavenueva">
                                    <label for="clavenueva">Nueva contraseña</label>
                                </div>
                                <div class="input-field">
                                    <input id="clavenueva2" type="password" name="clavenueva2">
                                    <label for="clavenueva2">Reingrese nueva contraseña</label>
                                </div>

                                <button class="btn" name="bt" value="cambiarclave" type="submit">
                                    Actualizar
                                </button>
                        </form>

                        <br>
                        ${msg}
                    </div>
                </div>
            </div>



        </c:if>


        <c:import url="accesodenegadoadmin.jsp"/>



        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
