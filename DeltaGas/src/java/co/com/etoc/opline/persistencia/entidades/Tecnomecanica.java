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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "tecnomecanica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tecnomecanica.findAll", query = "SELECT t FROM Tecnomecanica t"),
    @NamedQuery(name = "Tecnomecanica.findByIdVehiculo", query = "SELECT t FROM Tecnomecanica t WHERE t.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Tecnomecanica.findByFechaDeInicio", query = "SELECT t FROM Tecnomecanica t WHERE t.fechaDeInicio = :fechaDeInicio"),
    @NamedQuery(name = "Tecnomecanica.findByFechaDeVencimiento", query = "SELECT t FROM Tecnomecanica t WHERE t.fechaDeVencimiento = :fechaDeVencimiento")})
public class Tecnomecanica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_de_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_de_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeVencimiento;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Vehiculo vehiculo;

    public Tecnomecanica() {
    }

    public Tecnomecanica(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Tecnomecanica(Integer idVehiculo, Date fechaDeInicio, Date fechaDeVencimiento) {
        this.idVehiculo = idVehiculo;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Date getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(Date fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public Date getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
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
        if (!(object instanceof Tecnomecanica)) {
            return false;
        }
        Tecnomecanica other = (Tecnomecanica) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Tecnomecanica[ idVehiculo=" + idVehiculo + " ]";
    }
    
}
