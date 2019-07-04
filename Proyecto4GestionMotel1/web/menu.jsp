
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="blue darken-3">
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">
            <c:if test ="${not empty admin}">
                Hola ${admin.nombre} ${admin.apellido}
            </c:if>
            <c:if test ="${not empty operador}">
                Hola ${vendedor.nombre} ${vendedor.apellido}
            </c:if>
            

        </a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <c:if test ="${not empty admin}">
                <li><a href="producto.jsp">Gestion</a></li>
                              
                <li><a href="salir.jsp"><i class="small material-icons">exit_to_app</i></a></li>
            </c:if>
            <c:if test ="${not empty operador}">
                <li><a href="listarventa.jsp">ingresar clientes</a></li>
                <li><a href="salir.jsp"><i class="small material-icons">exit_to_app</i></a></li>
                
            </c:if>
            
        </ul>
    </div>
</nav>