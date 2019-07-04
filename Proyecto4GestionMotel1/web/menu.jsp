
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="transparent">
    <div class="nav-wrapper">
       
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