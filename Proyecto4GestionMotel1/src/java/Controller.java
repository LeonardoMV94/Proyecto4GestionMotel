
import cl.entities.*;

import cl.modelo.ServicioLocal;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "Controller", urlPatterns = {"/control.do"})
public class Controller extends HttpServlet {

    @EJB
    private ServicioLocal servicio;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bt = request.getParameter("bt");
        switch (bt) {

            case "deletcache":
                this.eliminarCache(request, response);
                break;

            case "adduser":
                this.adduser(request, response);
                break;

            case "iniciar":
                this.iniciarSesion(request, response);
                break;
            case "seleccionarhab":
                this.addCliente(request, response);
                break;
            //BOTON CAMBIO DE CLAVE

            //BOTON ASIGNAR HABITACION A CLIENTE
            case "asignarHab":

                break;
            //editar usuario    
            case "edituser":
                this.edituser(request, response);
                break;

            //eliminar usuario    
            case "deleteuser":
                this.deletuser(request, response);
                break;

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void eliminarCache(HttpServletRequest request, HttpServletResponse response) {

        servicio.eliminarCache();
    }

    private void cambiarClave(HttpServletRequest request, HttpServletResponse response) {

    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rut = request.getParameter("rut");
        String clave = request.getParameter("clave");

        Usuarios user = servicio.iniciarSesion(rut, Hash.md5(clave));

        if (user != null) {

            if (user.getTipoUsuario().equals("admin")) {

                request.getSession().setAttribute("admin", user);
                response.sendRedirect("admin.jsp");
            } else {
                if (user.getTipoUsuario().equals("operador")) {
                    request.getSession().setAttribute("operador", user);
                    response.sendRedirect("operador.jsp");
                }

            }
        } else {
            request.setAttribute("msg", "usuario o contraseña incorrecta");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    private void adduser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rutUsuario = request.getParameter("rutUsuario");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String clave2 = request.getParameter("claveR");
        String tipoUsuario = request.getParameter("tipoUsuario");

        if (clave.equals(clave2)) {

            if (servicio.buscarUsuarios(rutUsuario) == null) {
                Usuarios newUser = new Usuarios();
                newUser.setRutUsuario(rutUsuario);
                newUser.setNombre(nombre);
                newUser.setApellidoPaterno(apellidoPaterno);
                newUser.setApellidoMaterno(apellidoMaterno);
                newUser.setCorreo(correo);
                newUser.setClave(Hash.md5(clave));
                newUser.setTipoUsuario(tipoUsuario);
                servicio.insertar(newUser);
                response.sendRedirect("listarUsuario.jsp");

            } else {
                request.setAttribute("msg", "Usuario ya registrado");
                request.getRequestDispatcher("disponibilidad.jsp").forward(request, response);

            }
        }
    }

    private void edituser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rutUsuario = request.getParameter("rutUsuario");
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String claveR = request.getParameter("claveR");
        String tipoUsuario = request.getParameter("tipoUsuario");
        if (clave.equals(claveR) && tipoUsuario != null) {
            servicio.editarUsuarios(rutUsuario, nombre, apellidoPaterno, apellidoMaterno, correo, Hash.md5(clave), tipoUsuario);
            request.setAttribute("msg", "Usuario actualizado exitosamente");
            response.sendRedirect("gestionUsuario.jsp");

        } else {
            request.setAttribute("msg", "las contraseñas no coinciden o no se ingreso tipo de usuario");
            response.sendRedirect("gestionUsuario.jsp");

        }

    }

    private void deletuser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EJBException {

        String rutUsuario = request.getParameter("delrutUsuario");

        Usuarios usr = servicio.buscarUsuarios(rutUsuario);
        try {
            if (usr != null) {
                servicio.eliminarUsuario(usr);
            }
        } catch (Exception e) {

            request.setAttribute("msg", "no se pydo eliminar usuario: " + e);

            request.getRequestDispatcher("gestionUsuariojsp").forward(request, response);
        }

    }

    private void addCliente(HttpServletRequest request, HttpServletResponse response) {

        String rutCliente = request.getParameter("rutCliente");
        String nombre = request.getParameter("nombre");
        String apPaterno = request.getParameter("apellidoPaterno");
        String apMaterno = request.getParameter("apellidoMaterno");
        String fechaNac = request.getParameter("fechaNacimiento");

    }

    private void verificarCliente(HttpServletRequest request, HttpServletResponse response) {

        String rutCliente = request.getParameter("rutCliente");

        Cliente cli = servicio.buscarCliente(rutCliente);

    }

    private void addHabitacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idHabitacion = Integer.parseInt(request.getParameter("idHabitacion"));
        Short estado = Short.parseShort(request.getParameter("estado"));

        Habitacion addHAB = servicio.buscarHabitacion(idHabitacion);

        if (servicio.buscarHabitacion(idHabitacion) == null) {
            Habitacion newHab = new Habitacion();
            newHab.setIdHabitacion(idHabitacion);
            newHab.setEstado(estado);
            servicio.insertar(newHab);
            response.sendRedirect("gestionHotel.jsp");

        } else {
            request.setAttribute("msg", "Habitacion ya registrada");
            request.getRequestDispatcher("gestionHotel.jsp").forward(request, response);

        }
    }
    
    private void addpre(HttpServletRequest request, HttpServletResponse response) 
         {
        
        String idhabitacion = request.getParameter("idTipoHabitacion");
        String descr = request.getParameter("descripcionHabitacion");
        String precio = request.getParameter("precio");
       
    
        
    }

    private void editpre(HttpServletRequest request, HttpServletResponse response)
        {
        
        String idhabitacion = request.getParameter("idTipoHabitacion");
        String descr = request.getParameter("descripcionHabitacion");
        String precio = request.getParameter("precio");
        
       // servicio.editarTipoHabitacion(idTipoHabitacion, descr, precio);
        
        
    }
    
    
    
    
}
