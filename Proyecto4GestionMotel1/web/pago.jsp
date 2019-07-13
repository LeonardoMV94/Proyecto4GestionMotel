<%-- 
    Document   : pago
    Created on : 09-07-2019, 15:04:37
    Author     : home
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div id="imp1">
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
                    <div class="col s3 offset-s4 disabled">

                        <div class="card disabled">
                            <div class="card-content black-text disabled">
                                
                                <table class="bordered">

                                  
                                    <tr>
                                        <th scope="col">Folio Boleta</th>
                                        <td>${folio}</td>
                                    </tr>
                                    <tr>
                                        <th>Fecha</th>
                                        <td>${fecha}</td>
                                        
                                    </tr>
                                    <tr>
                                        <th>Tipo Habitación</th>
                                        <td>${tipohab}</td>
                                        
                                    </tr>
                                    <tr>
                                        <th>TOTAL</th>
                                        <td>${total}</td>
                                    </tr>


                                </table>


                            </div>
                        </div>
                    </div>

                </div>
        </div>
        <div class="card-action center-align">
            <button class="btn purple white-text pulse" type="button" onclick="javascript:imprim1(imp1);">Imprimir</button>

        </div>


















    </c:if>


    <c:import url="accesodenegadooperador.jsp"/> 


    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>


    <script type="text/javascript">
                $('.button-collapse').sideNav();

    </script>

    <script>
        function imprim1(imp1) {
            var printContents = document.getElementById('imp1').innerHTML;
            w = window.open();
            w.document.write(printContents);
            w.document.close(); // necessary for IE >= 10
            w.focus(); // necessary for IE >= 10
            w.print();
            w.close();
            return true;
        }
    </script>

</body>
</html>
