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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "certificado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificado.findAll", query = "SELECT c FROM Certificado c"),
    @NamedQuery(name = "Certificado.findByIdCertificado", query = "SELECT c FROM Certificado c WHERE c.idCertificado = :idCertificado"),
    @NamedQuery(name = "Certificado.findByTablaReferencia", query = "SELECT c FROM Certificado c WHERE c.tablaReferencia = :tablaReferencia"),
    @NamedQuery(name = "Certificado.findByDocumentoSolicitante", query = "SELECT c FROM Certificado c WHERE c.documentoSolicitante = :documentoSolicitante"),
    @NamedQuery(name = "Certificado.findByFechaSolicitud", query = "SELECT c FROM Certificado c WHERE c.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Certificado.findByFechaRespuesta", query = "SELECT c FROM Certificado c WHERE c.fechaRespuesta = :fechaRespuesta"),
    @NamedQuery(name = "Certificado.findByRespuesta", query = "SELECT c FROM Certificado c WHERE c.respuesta = :respuesta"),
    @NamedQuery(name = "Certificado.findByFechaGenerado", query = "SELECT c FROM Certificado c WHERE c.fechaGenerado = :fechaGenerado")})
public class Certificado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_certificado")
    private Integer idCertificado;
    @Size(max = 1)
    @Column(name = "tabla_referencia")
    private String tablaReferencia;
    @Size(max = 20)
    @Column(name = "documento_solicitante")
    private String documentoSolicitante;
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "fecha_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;
    @Size(max = 10)
    @Column(name = "respuesta")
    private String respuesta;
    @Column(name = "id_pago")
    private Integer idPago;
    @Column(name = "fecha_generado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGenerado;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado idEmpleado;

    public Certificado() {
    }

    public Certificado(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }

    public Integer getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(Integer idCertificado) {
        this.idCertificado = idCertificado;
    }

    public String getTablaReferencia() {
        return tablaReferencia;
    }

    public void setTablaReferencia(String tablaReferencia) {
        this.tablaReferencia = tablaReferencia;
    }

    public String getDocumentoSolicitante() {
        return documentoSolicitante;
    }

    public void setDocumentoSolicitante(String documentoSolicitante) {
        this.documentoSolicitante = documentoSolicitante;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }    

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(Date fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCertificado != null ? idCertificado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificado)) {
            return false;
        }
        Certificado other = (Certificado) object;
        if ((this.idCertificado == null && other.idCertificado != null) || (this.idCertificado != null && !this.idCertificado.equals(other.idCertificado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Certificado[ idCertificado=" + idCertificado + " ]";
    }
    
}
