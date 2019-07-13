
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
            throws ServletException, IOException, EJBException {

        String bt = request.getParameter("bt");
        switch (bt) {

            case "deletcache":
                this.eliminarCache(request, response);
                break;

            //INICIO DE SESION    
            case "iniciar":
                this.iniciarSesion(request, response);
                break;

            //HABITACION    
            case "asignarhab":
                this.asignarHab(request, response);
                break;
            case "editarHabitacion":
                this.editHab(request, response);
                break;

            case "addhab":
                this.addHabitacion(request, response);
                break;
            case "deletehab":
                this.deletHab(request, response);
                break;
            //USUARIOS
            case "edituser":
                this.edituser(request, response);
                break;

            case "deleteuser":
                this.deletuser(request, response);
                break;

            case "adduser":
                this.adduser(request, response);
                break;

            //PRECIOS    
            case "editpre":
                this.editpre(request, response);
                break;
            case "addpre":
                this.addpre(request, response);
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
                request.setAttribute("msg2", "Usuario añadido exitosamente");
                request.getRequestDispatcher("gestionUsuario.jsp").forward(request, response);

            } else {
                request.setAttribute("msg2", "Usuario ya registrado");
                request.getRequestDispatcher("gestionUsuario.jsp").forward(request, response);

            }
        }
    }

    private void edituser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EJBException {

        String rutUsuario = request.getParameter("mrutUsuario");
        String nombre = request.getParameter("mnombre");
        String apellidoPaterno = request.getParameter("mapellidoPaterno");
        String apellidoMaterno = request.getParameter("mapellidoMaterno");
        String correo = request.getParameter("mcorreo");
        String clave = request.getParameter("mclave");
        String claveR = request.getParameter("mclaveR");
        String tipoUsuario = request.getParameter("mtipoUsuario");

        try {
            if (clave != claveR) {
                request.setAttribute("msgm", "las contraseñas no coinciden o no se ingreso tipo de usuario");

                request.getRequestDispatcher("gestionUsuario.jsp").forward(request, response);

            } else {

                servicio.editarUsuarios(rutUsuario, nombre, apellidoPaterno,
                        apellidoMaterno, correo, Hash.md5(clave), tipoUsuario);

                request.setAttribute("msgm", "Usuario actualizado exitosamente");

                request.getRequestDispatcher("gestionUsuario.jsp").forward(request, response);

            }
        } catch (IOException | ServletException e) {
            request.setAttribute("msgm", "ERROR: " + e.getMessage());

            request.getRequestDispatcher("gestionUsuario.jsp").forward(request, response);
        }

    }

    private void deletuser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EJBException {

        String rutUsuario = request.getParameter("delrutUsuario");

        Usuarios usr = servicio.buscarUsuarios(rutUsuario);
        try {
            if (usr != null) {
                servicio.eliminarUsuario(usr.getRutUsuario());
                request.setAttribute("msg2", "Usuario eliminado exitosamente");
                request.getRequestDispatcher("gestionUsuario.jsp").forward(request, response);
            }
        } catch (IOException | ServletException e) {

            request.setAttribute("msg2", "no se pudo eliminar usuario: " + e);

            request.getRequestDispatcher("gestionUsuario.jsp").forward(request, response);
        }

    }

    private void asignarHab(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, ServletException, IOException {

        String idHab = request.getParameter("idHabitacion").trim();
        String rut = request.getParameter("rutCliente").trim();
        String cant = request.getParameter("cantidad").trim();

        if (idHab == null || rut == null || cant == null) {

            request.setAttribute("msg", "ingrese datos en todos los campos");
            request.setAttribute("cod", idHab);
            request.setAttribute("rutCliente", "ingrese rut");
            request.setAttribute("cantidad", 3);
            request.getRequestDispatcher("asignarHabitacion.jsp").forward(request, response);

        }

        try {
            if (servicio.buscarCliente(rut) == null) {

                request.setAttribute("msgmodal", 1);
                request.setAttribute("msg", "Cliente NO esta registrado");
                request.setAttribute("cod", idHab);
                request.setAttribute("rutCliente", "presione boton de ingresar cliente");
                request.setAttribute("cantidad", 3);
                request.getRequestDispatcher("asignarHabitacion.jsp").forward(request, response);

            }

        } catch (IOException | ServletException | NullPointerException e) {

            request.setAttribute("msgmodal", "ERROR: " + e);

            response.sendRedirect("asignarHabitacion.jsp");

        }

    }

    private void verificarCliente(HttpServletRequest request, HttpServletResponse response) {

        String rutCliente = request.getParameter("rutCliente");

        Cliente cli = servicio.buscarCliente(rutCliente);

    }

    private void addHabitacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        Short estado = Short.parseShort(request.getParameter("estado").trim());
        Habitacion newHab = new Habitacion();
        TipoHabitacion th = new TipoHabitacion();
        String thId = request.getParameter("tipoHabitacion").trim();
        
        if (thId.equals("")) {
            request.setAttribute("msg", "error al ingresar idTipoHabitacion");
            request.getRequestDispatcher("gestionHabitacion.jsp").forward(request, response);
        }else{
           int t = Integer.parseInt(thId);
             th.setIdTipoHabitacion(t);
        }
       
        
        
        
        if (newHab != null || th.getIdTipoHabitacion()!= null) {
            
            newHab.setEstado(estado);
            newHab.setTipoHabitacionIdTipoHabitacion(th);
            servicio.insertar(newHab);
            request.setAttribute("msg2", "Habitacion añadida exitosamente");
            request.getRequestDispatcher("gestionHabitacion.jsp").forward(request, response);

        } else {
            request.setAttribute("msg2", "Debe ingresar datos");
            request.getRequestDispatcher("gestionHabitacion.jsp").forward(request, response);

        }
    }

    private void editHab(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EJBException {

        String rutUsuario = request.getParameter("idHabitacion");
        String nombre = request.getParameter("tipoHabitacionIdTipoHabitacion");
        String apellidoPaterno = request.getParameter("estado");

        try {

            //verificar si hay datos
            if (nombre.equals("")) {

                request.setAttribute("msg2", "Debe ingresar datos");

                request.getRequestDispatcher("gestionHabitacion.jsp").forward(request, response);

            } else {

                //servicio.editarhabitacion
                request.setAttribute("msg2", "Habitacion actualizada exitosamente");

                request.getRequestDispatcher("gestionHabitacion.jsp").forward(request, response);

            }
        } catch (IOException | ServletException e) {
            request.setAttribute("msg2", "ERROR: " + e.getMessage());

            request.getRequestDispatcher("gestionHabitacion.jsp").forward(request, response);
        }

    }

    private void deletHab(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, EJBException {

        int idH = Integer.parseInt(request.getParameter("idhab"));

        Habitacion hb = servicio.buscarHabitacion(idH);
        try {
            if (hb != null) {
                servicio.eliminarHab(idH);
                request.setAttribute("msg2", "Habitacion eliminada");
                request.getRequestDispatcher("gestionHabitacion.jsp").forward(request, response);
            }
        } catch (IOException | ServletException e) {

            request.setAttribute("msg2", "no se pudo eliminar usuario: " + e);

            request.getRequestDispatcher("gestionHabitacion.jsp").forward(request, response);
        }

    }

    private void deletedpre(HttpServletRequest request, HttpServletResponse response) {

        String idhabitacion = request.getParameter("idTipoHabitacion");
        String descr = request.getParameter("descripcionHabitacion");
        String precio = request.getParameter("precio");

    }

    private void editpre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, EJBException {

        int idTipoHabitacion = Integer.parseInt(request.getParameter("midTipoHabitacion"));
        String descr = request.getParameter("mdescripcionHabitacion");
        int precio = Integer.parseInt(request.getParameter("mprecio"));
        try {
            servicio.editarTipoHabitacion(idTipoHabitacion, descr, precio);
            request.setAttribute("msg", "Precio actualizado exitosamente");

            request.getRequestDispatcher("gestionPrecios.jsp").forward(request, response);
        } catch (IOException | ServletException | EJBException e) {
            request.setAttribute("msg", "ERROR: " + e);

            request.getRequestDispatcher("gestionPrecios.jsp").forward(request, response);

        }

    }

    private void addpre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String des = request.getParameter("descripcionHabitacion").trim();
        String precio = request.getParameter("precio").trim();

        if (des.equals("") || precio.equals("")) {
            request.setAttribute("msg", "Debe ingresar datos");
            request.getRequestDispatcher("gestionPrecios.jsp").forward(request, response);

        } else {
            int pre = Integer.parseInt(precio);
            if (pre > 0) {
                TipoHabitacion t = new TipoHabitacion();
                t.setDescripcionHabitacion(des);
                t.setPrecio(pre);
                servicio.insertar(t);
                request.setAttribute("msg", "Dato de precio y descripcion ingresado exitosamente");
                response.sendRedirect("gestionPrecios.jsp");
            } else {
                request.setAttribute("msg", "Debe ingresar precio mayor a 0");
                request.getRequestDispatcher("gestionPrecios.jsp").forward(request, response);
            }

        }
    }

}
