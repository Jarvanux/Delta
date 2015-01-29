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
@Table(name = "historial_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialEstado.findAll", query = "SELECT h FROM HistorialEstado h"),
    @NamedQuery(name = "HistorialEstado.findByIdhistorialEstado", query = "SELECT h FROM HistorialEstado h WHERE h.idhistorialEstado = :idhistorialEstado"),
    @NamedQuery(name = "HistorialEstado.findByTabla", query = "SELECT h FROM HistorialEstado h WHERE h.tabla = :tabla"),
    @NamedQuery(name = "HistorialEstado.findByIdRegistro", query = "SELECT h FROM HistorialEstado h WHERE h.idRegistro = :idRegistro"),
    @NamedQuery(name = "HistorialEstado.findByFecha", query = "SELECT h FROM HistorialEstado h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HistorialEstado.findByIdEstado", query = "SELECT h FROM HistorialEstado h WHERE h.idEstado = :idEstado"),
    @NamedQuery(name = "HistorialEstado.findByJustificacion", query = "SELECT h FROM HistorialEstado h WHERE h.justificacion = :justificacion")})
public class HistorialEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhistorial_estado")
    private Integer idhistorialEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tabla")
    private String tabla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_registro")
    private int idRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado")
    private int idEstado;
    @Size(max = 150)
    @Column(name = "justificacion")
    private String justificacion;

    public HistorialEstado() {
    }

    public HistorialEstado(Integer idhistorialEstado) {
        this.idhistorialEstado = idhistorialEstado;
    }

    public HistorialEstado(Integer idhistorialEstado, String tabla, int idRegistro, Date fecha, int idEstado) {
        this.idhistorialEstado = idhistorialEstado;
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.idEstado = idEstado;
    }

    public Integer getIdhistorialEstado() {
        return idhistorialEstado;
    }

    public void setIdhistorialEstado(Integer idhistorialEstado) {
        this.idhistorialEstado = idhistorialEstado;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhistorialEstado != null ? idhistorialEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialEstado)) {
            return false;
        }
        HistorialEstado other = (HistorialEstado) object;
        if ((this.idhistorialEstado == null && other.idhistorialEstado != null) || (this.idhistorialEstado != null && !this.idhistorialEstado.equals(other.idhistorialEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.HistorialEstado[ idhistorialEstado=" + idhistorialEstado + " ]";
    }
    
}
