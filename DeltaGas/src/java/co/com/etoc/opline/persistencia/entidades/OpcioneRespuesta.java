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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "opcione_respuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcioneRespuesta.findAll", query = "SELECT o FROM OpcioneRespuesta o"),
    @NamedQuery(name = "OpcioneRespuesta.findByIdOpcionesRespuesta", query = "SELECT o FROM OpcioneRespuesta o WHERE o.idOpcionesRespuesta = :idOpcionesRespuesta"),
    @NamedQuery(name = "OpcioneRespuesta.findByDescripcion", query = "SELECT o FROM OpcioneRespuesta o WHERE o.descripcion = :descripcion")})
public class OpcioneRespuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_opciones_respuesta")
    private Integer idOpcionesRespuesta;
    @Size(max = 30)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pregunta idPregunta;

    public OpcioneRespuesta() {
    }

    public OpcioneRespuesta(Integer idOpcionesRespuesta) {
        this.idOpcionesRespuesta = idOpcionesRespuesta;
    }

    public Integer getIdOpcionesRespuesta() {
        return idOpcionesRespuesta;
    }

    public void setIdOpcionesRespuesta(Integer idOpcionesRespuesta) {
        this.idOpcionesRespuesta = idOpcionesRespuesta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcionesRespuesta != null ? idOpcionesRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcioneRespuesta)) {
            return false;
        }
        OpcioneRespuesta other = (OpcioneRespuesta) object;
        if ((this.idOpcionesRespuesta == null && other.idOpcionesRespuesta != null) || (this.idOpcionesRespuesta != null && !this.idOpcionesRespuesta.equals(other.idOpcionesRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.OpcioneRespuesta[ idOpcionesRespuesta=" + idOpcionesRespuesta + " ]";
    }
    
}
