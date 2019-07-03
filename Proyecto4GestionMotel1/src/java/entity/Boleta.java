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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "boleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleta.findAll", query = "SELECT b FROM Boleta b")
    , @NamedQuery(name = "Boleta.findByIdBoleta", query = "SELECT b FROM Boleta b WHERE b.idBoleta = :idBoleta")
    , @NamedQuery(name = "Boleta.findByPrecioConIva", query = "SELECT b FROM Boleta b WHERE b.precioConIva = :precioConIva")})
public class Boleta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_boleta")
    private Integer idBoleta;
    @Column(name = "precio_con_iva")
    private Integer precioConIva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boletaidboleta")
    private Collection<RegistrosVentas> registrosVentasCollection;
    @JoinColumn(name = "metodo_pago_id_metodo_pago", referencedColumnName = "id_metodo_pago")
    @ManyToOne(optional = false)
    private MetodoPago metodoPagoIdMetodoPago;

    public Boleta() {
    }

    public Boleta(Integer idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Integer getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(Integer idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Integer getPrecioConIva() {
        return precioConIva;
    }

    public void setPrecioConIva(Integer precioConIva) {
        this.precioConIva = precioConIva;
    }

    @XmlTransient
    public Collection<RegistrosVentas> getRegistrosVentasCollection() {
        return registrosVentasCollection;
    }

    public void setRegistrosVentasCollection(Collection<RegistrosVentas> registrosVentasCollection) {
        this.registrosVentasCollection = registrosVentasCollection;
    }

    public MetodoPago getMetodoPagoIdMetodoPago() {
        return metodoPagoIdMetodoPago;
    }

    public void setMetodoPagoIdMetodoPago(MetodoPago metodoPagoIdMetodoPago) {
        this.metodoPagoIdMetodoPago = metodoPagoIdMetodoPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBoleta != null ? idBoleta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleta)) {
            return false;
        }
        Boleta other = (Boleta) object;
        if ((this.idBoleta == null && other.idBoleta != null) || (this.idBoleta != null && !this.idBoleta.equals(other.idBoleta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Boleta[ idBoleta=" + idBoleta + " ]";
    }
    
}
