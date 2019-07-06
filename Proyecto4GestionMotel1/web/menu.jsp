
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="deep-purple darken-1">

    <div class="nav-wrapper">
        <a href="#" class="brand-logo" data-activates="mobile-demo">Motel Eclipse</a>
        <a href="#" data-activates="slide-out" class="button-collapse show-on-large"><i class="material-icons">menu</i></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">

            <c:if test ="${not empty admin}">
                <li> ${admin.tipoUsuario}</li>
                
                <li><a href="salir.jsp"><i class="small material-icons">exit_to_app</i></a></li>
                </c:if>

            <c:if test ="${not empty operador}">
                
                <li>${operador.tipoUsuario}</li>
                <li><a href="salir.jsp"><i class="small material-icons">exit_to_app</i></a></li>

            </c:if>

        </ul>

       
    </div>
</nav>



