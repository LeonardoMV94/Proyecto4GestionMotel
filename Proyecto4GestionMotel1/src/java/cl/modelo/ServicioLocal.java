/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.modelo;
import java.util.*;
import cl.entities.*;
import javax.ejb.Local;

/**
 *
 * @author Leonardo
 */
@Local
public interface ServicioLocal {
    
    void insertar(Object o);
    void sincronizar(Object o);
    
    //modulo usuario
    Usuarios iniciarSesion(String rut, String clave);
    
    Usuarios buscarUsuarios(String rut);
    void editarUsuarios(String rut,String clave);
    List<Usuarios> getUsuarios();
        
    //modulo cliente
    Cliente buscarCliente(String rut);
    void editarCliente(int codigo,int precio,int stock, int estado);
    List<Cliente> getClientes();
    
    
    // modulo boleta
    Boleta buscarBoleta(int idBoleta);
    void editarBoleta(int idBoleta, int precioConIva,int idMetodoPago, MetodoPago metodoPagoIdMetodoPago);
    List<Boleta> getBoletas();
    
  
     
    
    //metodo habitacion
    void editarHabitacion(int idHabitacion, Short estado);
    Habitacion buscarHabitacion(int idHabitacion);
    List<Habitacion> getHabitacion();
    //metodos tipo habitacion
    
    void editarTipoHabitacion(int idTipoHabitacion, String descripcion, int precio);
    TipoHabitacion buscarTipoHabitacion(int idTipoHabitacion);
    List<TipoHabitacion> getTipoHabitacion();
    
    
    //metodo registro de ventas
    
    
}
