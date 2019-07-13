
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
                        <h3>Ingresar nuevo usuario</h3>
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
                                <input id="claveR" type="password" name="claveR">
                                <label for="claveR">Repetir Clave</label>
                            </div>

                            <div class="input-field">
                                <select name="tipoUsuario">
                                    <option value="operador">Operador</option>
                                    <option value="admin">Administrador</option>
                                </select>
                                <label>Tipo de Usuario</label>
                            </div>

                            <div class="card-action right-align">
                                <button class="btn" name="bt" value="adduser" type="submit">
                                    Añadir
                                </button>
                            </div>


                        </form>


                    </div>
                </div>
            </div>
            <br><br> 
            <c:if test="${not empty msg2}">
                <div>
                    <div class="chip purple white-text">
                        ${msg2}
                    </div>
                </div>

            </c:if>



            <div class="col s10 offset-s1">
                <div class="card-panel z-depth-4">
                    <table class="bordered">
                        <tr>
                            <th>Rut</th>
                            <th>Nombre</th>
                            <th>Apellido Paterno</th>
                            <th>Apellido Materno</th>
                            <th>Correo</th>
                            <th>Clave</th>
                            <th>Tipo</th>
                            <th>Editar</th>
                            <th>Eliminar</th>

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
                                <td><a href="#modal1-${u.rutUsuario}" class="btn-floating blue modal-trigger">
                                        <i class="material-icons">edit</i>
                                    </a> 
                                </td>
                                <td>
                                    <a href="control.do?bt=deleteuser&delrutUsuario=${u.rutUsuario}" class="btn-floating red"><i class="material-icons">delete</i></a>
                                </td>
                            </tr>

                            <!-- Modal Structure -->
                            <div id="modal1-${u.rutUsuario}" class="modal">
                                <div class="modal-content">

                                    <h3>Editar Usuario</h3>
                                    <form action="control.do" method="POST">

                                        <div class="input-field ">
                                            <input id="mrutUsuario" type="text" name="mrutUsuario" disabled="" value="${u.rutUsuario}">
                                            <label for="mrutUsuario">Rut</label>
                                        </div>
                                        <div class="input-field">
                                            <input id="mnombre" type="text" name="mnombre" value="${u.nombre}">
                                            <label for="mnombre">Nombre</label>
                                        </div>

                                        <div class="input-field">
                                            <input id="mapellidoPaterno" type="text" name="mapellidoPaterno" value="${u.apellidoPaterno}">
                                            <label for="mapellidoPaterno">Apellido Paterno</label>
                                        </div>

                                        <div class="input-field">
                                            <input id="mapellidoMaterno" type="text" name="mapellidoMaterno" value="${u.apellidoMaterno}">
                                            <label for="mapellidoMaterno">Apellido Materno</label>
                                        </div>
                                        <div class="input-field">
                                            <input id="mcorreo" type="email" name="mcorreo" value="${u.correo}">
                                            <label for="mcorreo">Correo</label>
                                        </div>

                                        <div class="input-field">
                                            <input id="mclave" type="password" name="mclave">
                                            <label for="mclave">Clave</label>
                                        </div>

                                        <div class="input-field">
                                            <input id="mclaveR" type="password" name="mclaveR">
                                            <label for="mclaveR">Repetir Clave</label>
                                        </div>

                                        <div class="input-field">
                                            <select name="tipoUsuario">
                                                <option value="operador">operador</option>
                                                <option value="admin">admin</option>
                                            </select>
                                            <label>Tipo de Usuario</label>
                                        </div>

                                        <div class="card-action right-align">
                                            <button class="btn purple" name="bt" value="edituser" type="submit">
                                                Editar
                                            </button>
                                        </div>



                                    </form>
                                    <c:if test="${not empty msgm}">
                                        <div>
                                            <div class="chip purple white-text">
                                                ${msgm}
                                            </div>
                                        </div>

                                    </c:if>
                                </div>

                            </div>

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
        <script type="text/javascript">
            $('.button-collapse').sideNav();

        </script>
        <script type="text/javascript">

            $(document).ready(function () {
                // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
                $('.modal').modal();
            });

        </script>

    </body>
</html>