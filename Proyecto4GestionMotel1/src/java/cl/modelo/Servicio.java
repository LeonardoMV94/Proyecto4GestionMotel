/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;

import java.util.*;
import cl.entities.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leonardo
 */
@Stateless
public class Servicio implements ServicioLocal {

    @PersistenceContext(unitName = "Proyecto4GestionMotel1PU")
    private EntityManager em;
    
    /** uso de metodos de entity manager
    https://www.arquitecturajava.com/jpa-entitymanager-metodos/
     presionar control mas clic en enlace para abrir **/

    @Override
    public Usuarios iniciarSesion(String rut, String clave) {
        Usuarios user = buscarUsuarios(rut);

        return (user != null && user.getClave().equals(clave)) ? user : null;
    }

    @Override
    public Usuarios buscarUsuarios(String rut) {
        return em.find(Usuarios.class, rut);
    }

    @Override
    public void editarUsuarios(String rut, String clave) {
        Usuarios user = buscarUsuarios(rut);
        user.setClave(clave);
        em.merge(user);
        em.flush();
        em.refresh(user);
    }

    @Override
    public List<Usuarios> getUsuarios() {
        
        /** Para las querys en Entity Manager se usa el lenguaje de consulta JPQL
        es un lenguaje de consulta orientado a objetos independiente de 
        la plataforma definido como parte de la especificación Java Persistence API (JPA) **/
        
        return em.createQuery("select u from Usuario u").getResultList();
    }

    
    //CLIENTES
    
    @Override
    public Cliente buscarCliente(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editarCliente(int codigo, int precio, int stock, int estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> getClientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    //HABITACION
    
    @Override
    public Habitacion buscarHabitacion(int idHabitacion) {
        return em.find(Habitacion.class, idHabitacion);
    }

    @Override
    public void editarHabitacion(int idHabitacion, Short estado) {
        
        Habitacion h = this.buscarHabitacion(idHabitacion);
        //0 es falso = habitacion ocupada
        //1 es verdadero = habitacion disponible
        h.setEstado(estado);
        
        em.merge(h);
        em.flush();
        em.refresh(h);
        
        
    }

    @Override
    public List<Habitacion> getHabitacion() {
        return em.createQuery("select h from Habitacion h").getResultList();
    }

}
