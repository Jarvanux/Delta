/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "soat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Soat.findAll", query = "SELECT s FROM Soat s"),
    @NamedQuery(name = "Soat.findByIdVehiculo", query = "SELECT s FROM Soat s WHERE s.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Soat.findByFechaVencimiento", query = "SELECT s FROM Soat s WHERE s.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Soat.findByFechaDeInicio", query = "SELECT s FROM Soat s WHERE s.fechaDeInicio = :fechaDeInicio"),
    @NamedQuery(name = "Soat.findByAseguradora", query = "SELECT s FROM Soat s WHERE s.aseguradora = :aseguradora")})
public class Soat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_de_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeInicio;
    @Size(max = 30)
    @Column(name = "aseguradora")
    private String aseguradora;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Vehiculo vehiculo;

    public Soat() {
    }

    public Soat(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Soat(Integer idVehiculo, Date fechaVencimiento, Date fechaDeInicio) {
        this.idVehiculo = idVehiculo;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaDeInicio = fechaDeInicio;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(Date fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soat)) {
            return false;
        }
        Soat other = (Soat) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Soat[ idVehiculo=" + idVehiculo + " ]";
    }
    
}
