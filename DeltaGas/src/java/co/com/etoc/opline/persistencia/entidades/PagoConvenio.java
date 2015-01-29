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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "pago_convenio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoConvenio.findAll", query = "SELECT p FROM PagoConvenio p"),
    @NamedQuery(name = "PagoConvenio.findByNumeroConsig", query = "SELECT p FROM PagoConvenio p WHERE p.numeroConsig = :numeroConsig"),
    @NamedQuery(name = "PagoConvenio.findByFechaConsignacion", query = "SELECT p FROM PagoConvenio p WHERE p.fechaConsignacion = :fechaConsignacion"),
    @NamedQuery(name = "PagoConvenio.findByFechaInicio", query = "SELECT p FROM PagoConvenio p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PagoConvenio.findByFechaFin", query = "SELECT p FROM PagoConvenio p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "PagoConvenio.findByValorConsignacion", query = "SELECT p FROM PagoConvenio p WHERE p.valorConsignacion = :valorConsignacion")})
public class PagoConvenio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id    
    @Basic(optional = false)
    @Column(name = "numero_consig")
    private Integer numeroConsig;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado idEmpleado;    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_consignacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConsignacion;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_consignacion")
    private double valorConsignacion;
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vehiculo idVehiculo;    
    @JoinColumn(name = "id_convenio", referencedColumnName = "id_convenio")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Convenio idConvenio;

    public PagoConvenio() {
    }

    public PagoConvenio(Integer numeroConsig) {
        this.numeroConsig = numeroConsig;
    }

    public PagoConvenio(Integer numeroConsig, Date fechaConsignacion, double valorConsignacion) {
        this.numeroConsig = numeroConsig;
        this.fechaConsignacion = fechaConsignacion;
        this.valorConsignacion = valorConsignacion;
    }

    public Integer getNumeroConsig() {
        return numeroConsig;
    }

    public void setNumeroConsig(Integer numeroConsig) {
        this.numeroConsig = numeroConsig;
    }

    public Date getFechaConsignacion() {
        return fechaConsignacion;
    }

    public void setFechaConsignacion(Date fechaConsignacion) {
        this.fechaConsignacion = fechaConsignacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getValorConsignacion() {
        return valorConsignacion;
    }

    public void setValorConsignacion(double valorConsignacion) {
        this.valorConsignacion = valorConsignacion;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }    

    public Vehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Vehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Convenio getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Convenio idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroConsig != null ? numeroConsig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoConvenio)) {
            return false;
        }
        PagoConvenio other = (PagoConvenio) object;
        if ((this.numeroConsig == null && other.numeroConsig != null) || (this.numeroConsig != null && !this.numeroConsig.equals(other.numeroConsig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.PagoConvenio[ numeroConsig=" + numeroConsig + " ]";
    }
    
}
