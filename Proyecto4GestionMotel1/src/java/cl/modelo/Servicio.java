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
        return em.createQuery("select u from Usuario u").getResultList();
    }

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

}
