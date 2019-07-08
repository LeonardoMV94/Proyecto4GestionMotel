<%@page import="cl.entities.Usuarios"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="cl.entities.Cliente"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="cl.modelo.ServicioLocal"%>
<%! ServicioLocal servicio;%>
<%
    InitialContext ctx = new InitialContext();
    servicio = (ServicioLocal) ctx.lookup("java:global/Proyecto4GestionMotel1/Servicio!cl.modelo.ServicioLocal");
    List<Usuarios> lista = servicio.getUsuarios();
%>

<c:set scope="page" var="lista" value="<%=lista%>"/>

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


            <div class="row valign-wrapper">
                <div class="col s6 offset-s3">
                    <div class="card-panel z-depth-4">
                        <h3>Usuarios</h3>
                        <form action="control.do" method="POST">

                            <div class="input-field">
                                <input id="rutUsuario" type="text" name="rutUsuario">
                                <label for="rutUsuario">Rut</label>
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
                            <div class="input-field">
                                <input id="correo" type="email" name="correo">
                                <label for="correo">Correo</label>
                            </div>

                            <div class="input-field">
                                <input id="clave" type="password" name="clave">
                                <label for="clave">Clave</label>
                            </div>

                            <div class="input-field">
                                <select name="tipoUsuario">
                                    <option>operador</option>
                                    <option value="admin">admin</option>
                                </select>
                                <label>Tipo de Usuario</label>
                            </div>

                            <div class="card-action right-align">
                                <button class="btn" name="bt" value="adduser" type="submit">
                                    Añadir
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
                <br><br>

                <div class="col s10 offset-s1">
                    <div class="card-panel z-depth-4">
                        <table class="bordered">
                            <tr>
                                <th>RUT</th>
                                <th>NOMBRE</th>
                                <th>APELLIDO PATERNO</th>
                                <th>APELLIDO MATERNO</th>
                                <th>CORREO</th>
                                <th>CLAVE</th>
                                <th>TIPO</th>

                            </tr>

                            <c:forEach items="${lista}" var="u">
                                <tr>
                                    <td>${u.rutUsuario}</td>
                                    <td>${u.nombre}</td>
                                    <td>${u.apellidoPaterno}</td>
                                    <td>${u.apellidoMaterno}</td>
                                    <td>${u.correo}</td>
                                    <td>${u.clave}</td>
                                    <td>${u.tipoUsuario}</td>

                                </tr>
                            </c:forEach>
                        </table>
                        <br>
                    </div>
                </div>

            


        </c:if>

        <c:import url="accesodenegadoadmin.jsp"/> 


        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('select').material_select();
            });

        </script>


    </body>
</html>