<%-- 
    Document   : admin
    Created on : Jul 4, 2019, 12:49:08 AM
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

    <body class="black">

        <c:if test="${not empty admin}">
           <c:import url="menulateral.jsp"/>
            <c:import url="menu.jsp"/>


            
        </c:if>
       
        <c:import url="accesodenegadoadmin.jsp"/>

        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>

        <script type="text/javascript">
             $('.button-collapse').sideNav();

        </script>
    </body>
</html>
