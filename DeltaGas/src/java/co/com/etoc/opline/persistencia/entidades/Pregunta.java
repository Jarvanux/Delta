/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "pregunta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta"),
    @NamedQuery(name = "Pregunta.findByTipo", query = "SELECT p FROM Pregunta p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Pregunta.findByTextoPregunta", query = "SELECT p FROM Pregunta p WHERE p.textoPregunta = :textoPregunta")})
public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private Integer idPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "texto_pregunta")
    private String textoPregunta;
    @ManyToMany(mappedBy = "preguntaList", fetch = FetchType.LAZY)
    private List<Encuesta> encuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta", fetch = FetchType.LAZY)
    private List<DetallesEncuestaAplicada> detallesEncuestaAplicadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta", fetch = FetchType.LAZY)
    private List<OpcioneRespuesta> opcioneRespuestaList;

    public Pregunta() {
    }

    public Pregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Pregunta(Integer idPregunta, String tipo, String textoPregunta) {
        this.idPregunta = idPregunta;
        this.tipo = tipo;
        this.textoPregunta = textoPregunta;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    @XmlTransient
    public List<Encuesta> getEncuestaList() {
        return encuestaList;
    }

    public void setEncuestaList(List<Encuesta> encuestaList) {
        this.encuestaList = encuestaList;
    }

    @XmlTransient
    public List<DetallesEncuestaAplicada> getDetallesEncuestaAplicadaList() {
        return detallesEncuestaAplicadaList;
    }

    public void setDetallesEncuestaAplicadaList(List<DetallesEncuestaAplicada> detallesEncuestaAplicadaList) {
        this.detallesEncuestaAplicadaList = detallesEncuestaAplicadaList;
    }

    @XmlTransient
    public List<OpcioneRespuesta> getOpcioneRespuestaList() {
        return opcioneRespuestaList;
    }

    public void setOpcioneRespuestaList(List<OpcioneRespuesta> opcioneRespuestaList) {
        this.opcioneRespuestaList = opcioneRespuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Pregunta[ idPregunta=" + idPregunta + " ]";
    }
    
}
