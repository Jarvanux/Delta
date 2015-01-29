/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "asistencia_reunion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsistenciaReunion.findAll", query = "SELECT a FROM AsistenciaReunion a"),
    @NamedQuery(name = "AsistenciaReunion.findByIdAsistencia", query = "SELECT a FROM AsistenciaReunion a WHERE a.idAsistencia = :idAsistencia"),
    @NamedQuery(name = "AsistenciaReunion.findByTablaReferencia", query = "SELECT a FROM AsistenciaReunion a WHERE a.tablaReferencia = :tablaReferencia"),
    @NamedQuery(name = "AsistenciaReunion.findByIdTablaReferencia", query = "SELECT a FROM AsistenciaReunion a WHERE a.idTablaReferencia = :idTablaReferencia"),
    @NamedQuery(name = "AsistenciaReunion.findByAsistencia", query = "SELECT a FROM AsistenciaReunion a WHERE a.asistencia = :asistencia")})
public class AsistenciaReunion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asistencia")
    private Integer idAsistencia;
    @Size(max = 14)
    @Column(name = "tabla_referencia")
    private String tablaReferencia;
    @Column(name = "id_tabla_referencia")
    private Integer idTablaReferencia;
    @Column(name = "asistencia")
    private Boolean asistencia;

    public AsistenciaReunion() {
    }

    public AsistenciaReunion(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public String getTablaReferencia() {
        return tablaReferencia;
    }

    public void setTablaReferencia(String tablaReferencia) {
        this.tablaReferencia = tablaReferencia;
    }

    public Integer getIdTablaReferencia() {
        return idTablaReferencia;
    }

    public void setIdTablaReferencia(Integer idTablaReferencia) {
        this.idTablaReferencia = idTablaReferencia;
    }

    public Boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaReunion)) {
            return false;
        }
        AsistenciaReunion other = (AsistenciaReunion) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.AsistenciaReunion[ idAsistencia=" + idAsistencia + " ]";
    }
    
}
