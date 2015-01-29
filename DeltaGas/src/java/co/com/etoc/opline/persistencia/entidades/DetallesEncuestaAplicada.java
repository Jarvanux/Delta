/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "detalles_encuesta_aplicada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesEncuestaAplicada.findAll", query = "SELECT d FROM DetallesEncuestaAplicada d"),
    @NamedQuery(name = "DetallesEncuestaAplicada.findByIdPregunta", query = "SELECT d FROM DetallesEncuestaAplicada d WHERE d.detallesEncuestaAplicadaPK.idPregunta = :idPregunta"),
    @NamedQuery(name = "DetallesEncuestaAplicada.findByIdEncuestaAplicada", query = "SELECT d FROM DetallesEncuestaAplicada d WHERE d.detallesEncuestaAplicadaPK.idEncuestaAplicada = :idEncuestaAplicada"),
    @NamedQuery(name = "DetallesEncuestaAplicada.findByResultado", query = "SELECT d FROM DetallesEncuestaAplicada d WHERE d.resultado = :resultado")})
public class DetallesEncuestaAplicada implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallesEncuestaAplicadaPK detallesEncuestaAplicadaPK;
    @Column(name = "resultado")
    private Integer resultado;
    @JoinColumn(name = "id_encuesta_aplicada", referencedColumnName = "id_encuesta_aplicada", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EncuestaAplicada encuestaAplicada;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pregunta pregunta;

    public DetallesEncuestaAplicada() {
    }

    public DetallesEncuestaAplicada(DetallesEncuestaAplicadaPK detallesEncuestaAplicadaPK) {
        this.detallesEncuestaAplicadaPK = detallesEncuestaAplicadaPK;
    }

    public DetallesEncuestaAplicada(int idPregunta, int idEncuestaAplicada) {
        this.detallesEncuestaAplicadaPK = new DetallesEncuestaAplicadaPK(idPregunta, idEncuestaAplicada);
    }

    public DetallesEncuestaAplicadaPK getDetallesEncuestaAplicadaPK() {
        return detallesEncuestaAplicadaPK;
    }

    public void setDetallesEncuestaAplicadaPK(DetallesEncuestaAplicadaPK detallesEncuestaAplicadaPK) {
        this.detallesEncuestaAplicadaPK = detallesEncuestaAplicadaPK;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public EncuestaAplicada getEncuestaAplicada() {
        return encuestaAplicada;
    }

    public void setEncuestaAplicada(EncuestaAplicada encuestaAplicada) {
        this.encuestaAplicada = encuestaAplicada;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallesEncuestaAplicadaPK != null ? detallesEncuestaAplicadaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesEncuestaAplicada)) {
            return false;
        }
        DetallesEncuestaAplicada other = (DetallesEncuestaAplicada) object;
        if ((this.detallesEncuestaAplicadaPK == null && other.detallesEncuestaAplicadaPK != null) || (this.detallesEncuestaAplicadaPK != null && !this.detallesEncuestaAplicadaPK.equals(other.detallesEncuestaAplicadaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.DetallesEncuestaAplicada[ detallesEncuestaAplicadaPK=" + detallesEncuestaAplicadaPK + " ]";
    }
    
}
