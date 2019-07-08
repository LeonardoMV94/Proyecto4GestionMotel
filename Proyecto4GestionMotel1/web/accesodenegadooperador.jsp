<%-- 
    Document   : accesodenegado
    Created on : Jul 7, 2019, 3:35:25 PM
    Author     : Leonardo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty operador}">
                <div class="row valign-wrapper">
                    <div class="col s6 offset-s3">
                        <div class="white-text">
                            <div class="card-panel center-align transparent">

                                <h1>Acceso Denegado</h1>
                                <br> <img src="img/denied.png">
                                <br> <h5>No eres operador! <br> Seras redireccionado en <span id="countdowntimer">5</span> segundos </h5>

                                <script type="text/javascript">
                                    var timeleft = 5;
                                    var downloadTimer = setInterval(function () {
                                        timeleft--;
                                        document.getElementById("countdowntimer").textContent = timeleft;
                                        if (timeleft <= 0)
                                            clearInterval(downloadTimer);
                                    }, 1000);
                                </script>

                                <meta http-equiv="refresh" content="5;url=salir.jsp">
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>