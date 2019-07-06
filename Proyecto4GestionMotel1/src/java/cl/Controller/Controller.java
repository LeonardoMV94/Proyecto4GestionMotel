/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cl.entities.*;

import cl.modelo.ServicioLocal;
import directorio.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
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

            case "adduser":
                this.adduser(request, response);
                break;

            case "iniciar":
                this.iniciarSesion(request, response);
                break;

            //BOTON CAMBIO DE CLAVE
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

    private void adduser(HttpServletRequest request, HttpServletResponse response) {


    }

}
