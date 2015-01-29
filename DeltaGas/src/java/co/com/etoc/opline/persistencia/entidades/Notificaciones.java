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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "notificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificaciones.findAll", query = "SELECT n FROM Notificaciones n"),
    @NamedQuery(name = "Notificaciones.findByIdNotificacion", query = "SELECT n FROM Notificaciones n WHERE n.idNotificacion = :idNotificacion"),
    @NamedQuery(name = "Notificaciones.findByTipoNotificacion", query = "SELECT n FROM Notificaciones n WHERE n.tipoNotificacion = :tipoNotificacion"),
    @NamedQuery(name = "Notificaciones.findByFechaNotificacion", query = "SELECT n FROM Notificaciones n WHERE n.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "Notificaciones.findByRolDestino", query = "SELECT n FROM Notificaciones n WHERE n.rolDestino = :rolDestino"),
    @NamedQuery(name = "Notificaciones.findByDescripcion", query = "SELECT n FROM Notificaciones n WHERE n.descripcion = :descripcion"),
    @NamedQuery(name = "Notificaciones.findByEstado", query = "SELECT n FROM Notificaciones n WHERE n.estado = :estado")})
public class Notificaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipo_notificacion")
    private String tipoNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_notificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rol_destino")
    private int rolDestino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @Column(name = "estado")
    private String estado;

    public Notificaciones() {
    }

    public Notificaciones(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Notificaciones(Integer idNotificacion, String tipoNotificacion, Date fechaNotificacion, int rolDestino, String descripcion, String estado) {
        this.idNotificacion = idNotificacion;
        this.tipoNotificacion = tipoNotificacion;
        this.fechaNotificacion = fechaNotificacion;
        this.rolDestino = rolDestino;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public int getRolDestino() {
        return rolDestino;
    }

    public void setRolDestino(int rolDestino) {
        this.rolDestino = rolDestino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificaciones)) {
            return false;
        }
        Notificaciones other = (Notificaciones) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Notificaciones[ idNotificacion=" + idNotificacion + " ]";
    }
    
}
