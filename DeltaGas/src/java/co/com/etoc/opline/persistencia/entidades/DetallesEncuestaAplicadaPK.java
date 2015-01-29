/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jhonjaider1000
 */
@Embeddable
public class DetallesEncuestaAplicadaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pregunta")
    private int idPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_encuesta_aplicada")
    private int idEncuestaAplicada;

    public DetallesEncuestaAplicadaPK() {
    }

    public DetallesEncuestaAplicadaPK(int idPregunta, int idEncuestaAplicada) {
        this.idPregunta = idPregunta;
        this.idEncuestaAplicada = idEncuestaAplicada;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdEncuestaAplicada() {
        return idEncuestaAplicada;
    }

    public void setIdEncuestaAplicada(int idEncuestaAplicada) {
        this.idEncuestaAplicada = idEncuestaAplicada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPregunta;
        hash += (int) idEncuestaAplicada;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesEncuestaAplicadaPK)) {
            return false;
        }
        DetallesEncuestaAplicadaPK other = (DetallesEncuestaAplicadaPK) object;
        if (this.idPregunta != other.idPregunta) {
            return false;
        }
        if (this.idEncuestaAplicada != other.idEncuestaAplicada) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.DetallesEncuestaAplicadaPK[ idPregunta=" + idPregunta + ", idEncuestaAplicada=" + idEncuestaAplicada + " ]";
    }
    
}
