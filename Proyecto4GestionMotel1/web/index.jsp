<%-- 
    Document   : index
    Created on : Jun 30, 2019, 1:24:21 PM
    Author     : Leonardo
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

    <body>

        <br>
        <br>
        <br>


        <!-- deep-purple darken-4 -->

        <div class="row valign-wrapper">
            <div class="col s6 offset-s3">
                <div class="card-panel transparent z-depth-3 white-text">

                    <h4>Iniciar Sesión</h4>
                    <br><br>
                    <form action="control.do" method="POST">
                        <div class="input-field">
                            <i class="material-icons prefix purple-text">account_circle</i>
                            <input id="rut" type="text" name="rut" data-length="12">
                            <label for="rut">Rut</label>
                        </div>

                        <div class="input-field">
                            <i class="material-icons prefix purple-text">lock_open</i>
                            <input id="clave" type="password" name="clave">
                            <label for="clave">Clave</label>
                        </div>
                        <div class="card-action right-align">
                            <button class="btn purple white-text pulse" name="bt" value="iniciar" type="submit">
                                Ingresar
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





        <style>
            body{
                background-image: url(img/motel.png);
                background-size: cover;
            }

        </style>           

        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var elems = document.querySelectorAll('.parallax');
                var instances = M.Parallax.init(elems);
            });


        </script>


    </body>
</html>
