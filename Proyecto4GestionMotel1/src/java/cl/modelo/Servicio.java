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

    //se llama a persistencia con su nombre
    //se le asigna una variable
    @PersistenceContext(unitName = "Proyecto4GestionMotel1PU")
    private EntityManager em;

    /**
     * uso de metodos de entity manager
     * https://www.arquitecturajava.com/jpa-entitymanager-metodos/ presionar
     * control mas clic en enlace para abrir *
     */
    @Override
    public Usuarios iniciarSesion(String rut, String clave) {
        Usuarios user = this.buscarUsuarios(rut);

        return (user != null && user.getClave().equals(clave)) ? user : null;
    }

    @Override
    public Usuarios buscarUsuarios(String rut) {
        return em.find(Usuarios.class, rut);
    }

    @Override
    public void editarUsuarios(String rutUsuario, String nombre, String apellidoPaterno,
            String apellidoMaterno, String correo, String clave, String tipoUsuario) {

        Usuarios user = buscarUsuarios(rutUsuario);
        if (user != null) {

            user.setNombre(nombre);
            user.setApellidoPaterno(apellidoPaterno);
            user.setApellidoMaterno(apellidoMaterno);
            user.setCorreo(correo);
            user.setClave(clave);
            user.setTipoUsuario(tipoUsuario);

            em.merge(user);
            em.flush();
            em.refresh(user);
        } else {
            user = null;
        }

    }

    @Override
    public List<Usuarios> getUsuarios() {

        /**
         * Para las querys en Entity Manager se usa el lenguaje de consulta JPQL
         * es un lenguaje de consulta orientado a objetos independiente de la
         * plataforma definido como parte de la especificación Java Persistence
         * API (JPA) *
         */
        return em.createQuery("select u from Usuarios u").getResultList();
    }

    //CLIENTES
    @Override
    public Cliente buscarCliente(String rut) {
        return em.find(Cliente.class, rut);
    }

    @Override
    public void editarCliente(int codigo, int precio, int stock, int estado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> getClientes() {

        return em.createQuery("select c from Cliente c").getResultList();
    }

    //HABITACION
    @Override
    public Habitacion buscarHabitacion(int idHabitacion) {
        return em.find(Habitacion.class, idHabitacion);
    }

    @Override
    public void editarHabitacion(int idHabitacion, Short estado, TipoHabitacion tHab) {

        Habitacion h = this.buscarHabitacion(idHabitacion);
        //0 es falso = habitacion ocupada
        //1 es verdadero = habitacion disponible
        h.setEstado(estado);
        h.setTipoHabitacionIdTipoHabitacion(tHab);
        em.merge(h);
        em.flush();
        em.refresh(h);

    }

    @Override
    public List<Habitacion> getHabitacion() {
        return em.createQuery("select h from Habitacion h").getResultList();
    }

    //MODULO TIPO DE HABITACION
    @Override
    public void editarTipoHabitacion(int idTipoHabitacion, String descripcion, int precio) {

        TipoHabitacion t = this.buscarTipoHabitacion(idTipoHabitacion);

        t.setDescripcionHabitacion(descripcion);
        t.setPrecio(precio);

        em.merge(t);
        em.flush();
        em.refresh(t);

    }

    @Override
    public TipoHabitacion buscarTipoHabitacion(int idTipoHabitacion) {
        return em.find(TipoHabitacion.class, idTipoHabitacion);
    }

    @Override
    public List<TipoHabitacion> getTipoHabitacion() {
        return em.createQuery("select t from TipoHabitacion t").getResultList();
    }

    @Override
    public Boleta buscarBoleta(int idBoleta) {
        return em.find(Boleta.class, idBoleta);
    }

    @Override
    public void editarBoleta(int idBoleta, int precioConIva, int idMetodoPago, MetodoPago metodoPagoIdMetodoPago) {

        Boleta b = this.buscarBoleta(idBoleta);

        b.setPrecioConIva(precioConIva);
        MetodoPago mp = b.getMetodoPagoIdMetodoPago();
        mp.setIdMetodoPago(idMetodoPago);
        b.setMetodoPagoIdMetodoPago(mp);

        em.merge(b);
        em.flush();
        em.refresh(b);
    }

    @Override
    public List<Boleta> getBoletas() {
        return em.createQuery("select b from Boleta b").getResultList();
    }

    @Override
    public void insertar(Object o) {
        em.persist(o);
    }

    @Override
    public void sincronizar(Object o) {
        em.merge(o);
        em.flush();
    }

    @Override
    public void editarRegistro(int idRegistro, Date horaEntrada, Date horaSalida, Boleta boletaidboleta, Cliente clienterutcliente, Usuarios usuariosrutusuario, Habitacion habitacionidhabitacion) {

    }

    @Override
    public RegistroVentas buscarRegistro(int idRegistro) {
        return em.find(RegistroVentas.class, idRegistro);
    }

    @Override
    public List<RegistroVentas> getRegistro() {
        return em.createQuery("select r from RegistroVentas r").getResultList();
    }

    @Override
    public void eliminarCache() {
        em.getEntityManagerFactory().getCache().evictAll();
    }

    @Override
    public void eliminarUsuario(String rut) {

        Usuarios p = em.find(Usuarios.class, rut);
        em.remove(p);

        em.flush();

    }

    @Override
    public void eliminarHab(int idHab) {

        Habitacion h = em.find(Habitacion.class, idHab);
        em.remove(h);

        em.flush();

    }

    @Override
    public void eliminar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ultimoIdRegistro() {
        RegistroVentas r = new RegistroVentas();
        
        em.persist(r);
        em.flush();
        return r.getIdRegistro();
    }

    @Override
    public List<MetodoPago> getMetodoPago() {
       return em.createQuery("select m from MetodoPago m").getResultList();
    }

    @Override
    public void editarEstHab(int idHabitacion, Short estado) {
       
        Habitacion h = this.buscarHabitacion(idHabitacion);
        //0 es falso = habitacion ocupada
        //1 es verdadero = habitacion disponible
        h.setEstado(estado);
       
        em.merge(h);
        em.flush();
        em.refresh(h);
        
    }

}
