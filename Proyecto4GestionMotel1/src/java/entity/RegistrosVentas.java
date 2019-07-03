/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "registros_ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistrosVentas.findAll", query = "SELECT r FROM RegistrosVentas r")
    , @NamedQuery(name = "RegistrosVentas.findByIdRegistro", query = "SELECT r FROM RegistrosVentas r WHERE r.idRegistro = :idRegistro")
    , @NamedQuery(name = "RegistrosVentas.findByHoraEntrada", query = "SELECT r FROM RegistrosVentas r WHERE r.horaEntrada = :horaEntrada")})
public class RegistrosVentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "hora_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEntrada;
    @JoinColumn(name = "Boleta_id_boleta", referencedColumnName = "id_boleta")
    @ManyToOne(optional = false)
    private Boleta boletaidboleta;
    @JoinColumn(name = "Cliente_rut_cliente", referencedColumnName = "rut_cliente")
    @ManyToOne(optional = false)
    private Cliente clienterutcliente;
    @JoinColumn(name = "Habitacion_id_habitacion", referencedColumnName = "id_habitacion")
    @ManyToOne(optional = false)
    private Habitacion habitacionidhabitacion;
    @JoinColumn(name = "Usuarios_rut_usuario", referencedColumnName = "rut_usuario")
    @ManyToOne(optional = false)
    private Usuarios usuariosrutusuario;

    public RegistrosVentas() {
    }

    public RegistrosVentas(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Boleta getBoletaidboleta() {
        return boletaidboleta;
    }

    public void setBoletaidboleta(Boleta boletaidboleta) {
        this.boletaidboleta = boletaidboleta;
    }

    public Cliente getClienterutcliente() {
        return clienterutcliente;
    }

    public void setClienterutcliente(Cliente clienterutcliente) {
        this.clienterutcliente = clienterutcliente;
    }

    public Habitacion getHabitacionidhabitacion() {
        return habitacionidhabitacion;
    }

    public void setHabitacionidhabitacion(Habitacion habitacionidhabitacion) {
        this.habitacionidhabitacion = habitacionidhabitacion;
    }

    public Usuarios getUsuariosrutusuario() {
        return usuariosrutusuario;
    }

    public void setUsuariosrutusuario(Usuarios usuariosrutusuario) {
        this.usuariosrutusuario = usuariosrutusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistrosVentas)) {
            return false;
        }
        RegistrosVentas other = (RegistrosVentas) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RegistrosVentas[ idRegistro=" + idRegistro + " ]";
    }
    
}
