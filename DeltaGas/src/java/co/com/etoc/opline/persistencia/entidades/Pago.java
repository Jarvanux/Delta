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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
    @NamedQuery(name = "Pago.findByNumeroRecibo", query = "SELECT p FROM Pago p WHERE p.numeroRecibo = :numeroRecibo"),
    @NamedQuery(name = "Pago.findByFechaPago", query = "SELECT p FROM Pago p WHERE p.fechaPago = :fechaPago"),
    @NamedQuery(name = "Pago.findByFechaInicio", query = "SELECT p FROM Pago p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Pago.findByFechaFin", query = "SELECT p FROM Pago p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "Pago.findByValorPago", query = "SELECT p FROM Pago p WHERE p.valorPago = :valorPago"),
    @NamedQuery(name = "Pago.findByObservacion", query = "SELECT p FROM Pago p WHERE p.observacion = :observacion")})
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_recibo")
    private Integer numeroRecibo;
    @Basic(optional = false)    
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pago")
    private double valorPago;
    @Size(max = 200)
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado idEmpleado;    
    @JoinColumn(name = "id_asociado", referencedColumnName = "id_asociado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Asociado idAsociado;
    @JoinColumn(name = "id_tipo_pago", referencedColumnName = "id_tipo_pago")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPago idTipoPago;

    public Pago() {
    }

    public Pago(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public Pago(Integer numeroRecibo, Date fechaPago, double valorPago) {
        this.numeroRecibo = numeroRecibo;
        this.fechaPago = fechaPago;
        this.valorPago = valorPago;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
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

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Asociado getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(Asociado idAsociado) {
        this.idAsociado = idAsociado;
    }

    public TipoPago getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(TipoPago idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroRecibo != null ? numeroRecibo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.numeroRecibo == null && other.numeroRecibo != null) || (this.numeroRecibo != null && !this.numeroRecibo.equals(other.numeroRecibo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.negocio.managed.Pago[ numeroRecibo=" + numeroRecibo + " ]";
    }
    
}
