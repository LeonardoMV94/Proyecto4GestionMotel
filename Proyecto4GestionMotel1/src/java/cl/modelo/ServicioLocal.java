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
    
    
    //metodo habitacion

    
    
    //metodo registro de ventas
    
    
}