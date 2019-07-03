/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "tipo_habitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoHabitacion.findAll", query = "SELECT t FROM TipoHabitacion t")
    , @NamedQuery(name = "TipoHabitacion.findByIdTipoHabitacion", query = "SELECT t FROM TipoHabitacion t WHERE t.idTipoHabitacion = :idTipoHabitacion")
    , @NamedQuery(name = "TipoHabitacion.findByDescripcionHabitacion", query = "SELECT t FROM TipoHabitacion t WHERE t.descripcionHabitacion = :descripcionHabitacion")
    , @NamedQuery(name = "TipoHabitacion.findByPrecio", query = "SELECT t FROM TipoHabitacion t WHERE t.precio = :precio")})
public class TipoHabitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_habitacion")
    private Integer idTipoHabitacion;
    @Size(max = 45)
    @Column(name = "descripcion_habitacion")
    private String descripcionHabitacion;
    @Column(name = "precio")
    private Integer precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoHabitacionIdTipoHabitacion")
    private Collection<Habitacion> habitacionCollection;

    public TipoHabitacion() {
    }

    public TipoHabitacion(Integer idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public Integer getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(Integer idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public String getDescripcionHabitacion() {
        return descripcionHabitacion;
    }

    public void setDescripcionHabitacion(String descripcionHabitacion) {
        this.descripcionHabitacion = descripcionHabitacion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<Habitacion> getHabitacionCollection() {
        return habitacionCollection;
    }

    public void setHabitacionCollection(Collection<Habitacion> habitacionCollection) {
        this.habitacionCollection = habitacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoHabitacion != null ? idTipoHabitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoHabitacion)) {
            return false;
        }
        TipoHabitacion other = (TipoHabitacion) object;
        if ((this.idTipoHabitacion == null && other.idTipoHabitacion != null) || (this.idTipoHabitacion != null && !this.idTipoHabitacion.equals(other.idTipoHabitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TipoHabitacion[ idTipoHabitacion=" + idTipoHabitacion + " ]";
    }
    
}
