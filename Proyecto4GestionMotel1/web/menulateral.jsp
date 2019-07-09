<%-- 
    Document   : menulateral
    Created on : Jul 6, 2019, 3:24:14 AM
    Author     : Leonardo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul id="slide-out" class="side-nav transparent">

    <c:if test ="${not empty admin}">

        <li><div class="user-view">
                <div class="background deep-purple darken-1">

                </div>
                <a href="#!user"><img class="circle" src="img/milos.gif"></a>
                <a href="#!name"><span class="white-text name">${admin.nombre} ${admin.apellidoPaterno} ${admin.apellidoMaterno}</span></a>
                <a href="#!email"><span class="white-text email">${admin.correo}</span></a>
            </div></li>
        <li><div class="divider"></div></li>
        <li><a href="gestionUsuario.jsp" class="white-text">Gestion Usuarios</a></li>
        <li><div class="divider"></div></li>
        <li><a href="gestionHabitacion.jsp" class="white-text">Gestion Habitaciones</a></li>
        <li><div class="divider"></div></li>
        <li><a href="listarRegistroVenta.jsp" class="white-text">Listar Registros</a></li>
        <li><div class="divider"></div></li>
        <li><a href="gestionPrecios.jsp" class="white-text">Gestion Precios</a></li>
        <li><div class="divider"></div></li>
        </c:if>

    <c:if test ="${not empty operador}">

        <li><div class="user-view">
                <div class="background deep-purple darken-1">

                </div>
                <a href="#!user"><img class="circle" src="img/milos.gif"></a>
                <a href="#!name"><span class="white-text name">${operador.nombre} ${operador.apellidoPaterno} ${operador.apellidoMaterno}</span></a>
                <a href="#!email"><span class="white-text email">${operador.correo}</span></a>
            </div></li>

        <li><div class="divider"></div></li>
        <li><a href="disponibilidad.jsp" class="white-text">Habitaciones</a></li>
        <li><div class="divider"></div></li>
        <li><a href="ingresarCliente.jsp" class="white-text">Clientes</a></li>
        <li><div class="divider"></div></li>
        <li><a href="asignarHabitacion.jsp" class="white-text">Asignar Habitacion</a></li>
        <li><div class="divider"></div></li>
        <li><a href="#" class="white-text">Boleta</a></li>
        <li><div class="divider"></div></li>
        


    </c:if>

</ul>
