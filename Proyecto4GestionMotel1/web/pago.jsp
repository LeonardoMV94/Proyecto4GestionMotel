<%-- 
    Document   : pago
    Created on : 09-07-2019, 15:04:37
    Author     : home
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
                    <div class="card White">
                        <div class="card-content black-text">
                            <span class="card-title center-align"><b>Servicios Ricardo Milos SPA</b></span>
                            <table class="striped">
                                <tbody>
                                     <tr>
                                         <td>Folio Boleta   </td>
                                     <tr>
                                     <tr>
                                         <td>Fecha  </td>
                                     <tr>
                                     <tr>
                                         <td>N° Habitación  </td>
                                     </tr>
                                     <tr>
                                         <td>Tipo Habitación  </td>
                                     </tr>
                                     <tr>
                                         <td>N° Habitación  </td>
                                     </tr>
                                     <tr>
                                         <td>Tarifa asociada  </td>
                                     </tr>
                                      <tr>
                                         <td>TOTAL </td>
                                     </tr>
                                     
                                </tbody>
                            </table>
                        </div>
                        <div class="card-action center-align">
                            <button class="btn purple white-text pulse" name="bt" value="imprimirBoleta" type="submit">
                                Imprimir Boleta
                            </button>
                            <button class="btn purple white-text pulse" name="bt" value="generarNuevamente" type="submit">
                                Generar Nuevamente
                            </button>
                        </div>
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
    </body>
</html>
