<%-- 
    Document   : menulateral
    Created on : Jul 6, 2019, 3:24:14 AM
    Author     : Leonardo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul id="slide-out" class="side-nav transparent">

    <c:if test ="${not empty admin}">
        <li><a href="#" class="white-text">Crear Usuarios</a></li>
        <li><div class="divider"></div></li>
        </c:if>

    <c:if test ="${not empty operador}">
        <li><a href="disponibilidad.jsp" class="white-text">Habitaciones</a></li>
        <li><div class="divider"></div></li>

    </c:if>

</ul>
