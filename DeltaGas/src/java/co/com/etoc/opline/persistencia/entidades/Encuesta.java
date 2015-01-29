/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "encuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e"),
    @NamedQuery(name = "Encuesta.findByIdEncuesta", query = "SELECT e FROM Encuesta e WHERE e.idEncuesta = :idEncuesta"),
    @NamedQuery(name = "Encuesta.findByTitulo", query = "SELECT e FROM Encuesta e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "Encuesta.findByFechaCreacion", query = "SELECT e FROM Encuesta e WHERE e.fechaCreacion = :fechaCreacion")})
public class Encuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encuesta")
    private Integer idEncuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinTable(name = "destalles_encuesta", joinColumns = {
        @JoinColumn(name = "id_encuesta", referencedColumnName = "id_encuesta")}, inverseJoinColumns = {
        @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pregunta> preguntaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncuesta", fetch = FetchType.LAZY)
    private List<EncuestaAplicada> encuestaAplicadaList;

    public Encuesta() {
    }

    public Encuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Encuesta(Integer idEncuesta, String titulo, Date fechaCreacion) {
        this.idEncuesta = idEncuesta;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @XmlTransient
    public List<EncuestaAplicada> getEncuestaAplicadaList() {
        return encuestaAplicadaList;
    }

    public void setEncuestaAplicadaList(List<EncuestaAplicada> encuestaAplicadaList) {
        this.encuestaAplicadaList = encuestaAplicadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuesta != null ? idEncuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.idEncuesta == null && other.idEncuesta != null) || (this.idEncuesta != null && !this.idEncuesta.equals(other.idEncuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Encuesta[ idEncuesta=" + idEncuesta + " ]";
    }
    
}
