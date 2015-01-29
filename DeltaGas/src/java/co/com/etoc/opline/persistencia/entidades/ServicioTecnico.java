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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "servicio_tecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioTecnico.findAll", query = "SELECT s FROM ServicioTecnico s"),
    @NamedQuery(name = "ServicioTecnico.findByIdServicio", query = "SELECT s FROM ServicioTecnico s WHERE s.idServicio = :idServicio"),   
    @NamedQuery(name = "ServicioTecnico.findByFechaRegistro", query = "SELECT s FROM ServicioTecnico s WHERE s.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ServicioTecnico.findByFechaRespuesta", query = "SELECT s FROM ServicioTecnico s WHERE s.fechaRespuesta = :fechaRespuesta")})
public class ServicioTecnico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_servicio")
    private Integer idServicio;    
    @Lob
    @Size(max = 32767)
    @Column(name = "descripcion_cliente")
    private String descripcionCliente;    
    @Lob
    @Size(max = 32767)
    @Column(name = "descripcion_tecnico")
    private String descripcionTecnico;
    @Column(name = "codigo_registro")
    private String codigoRegistro;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente idCliente;
    @JoinColumn(name = "id_requerimiento", referencedColumnName = "id_requerimiento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Requerimiento idRequerimiento;
    @JoinColumn(name = "id_estacion", referencedColumnName = "id_estacion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estacion idEtacion;

    public ServicioTecnico() {
    }

    public ServicioTecnico(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcionCliente() {
        return descripcionCliente;
    }

    public void setDescripcionCliente(String descripcionCliente) {
        this.descripcionCliente = descripcionCliente;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    

    public String getDescripcionTecnico() {
        return descripcionTecnico;
    }

    public void setDescripcionTecnico(String descripcionTecnico) {
        this.descripcionTecnico = descripcionTecnico;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Requerimiento getIdRequerimiento() {
        return idRequerimiento;
    }

    public void setIdRequerimiento(Requerimiento idRequerimiento) {
        this.idRequerimiento = idRequerimiento;
    }

    public Estacion getIdEtacion() {
        return idEtacion;
    }

    public void setIdEtacion(Estacion idEtacion) {
        this.idEtacion = idEtacion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioTecnico)) {
            return false;
        }
        ServicioTecnico other = (ServicioTecnico) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.ServicioTecnico[ idServicio=" + idServicio + " ]";
    }
    
}
