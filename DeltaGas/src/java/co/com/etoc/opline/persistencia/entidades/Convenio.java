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
@Table(name = "convenio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convenio.findAll", query = "SELECT c FROM Convenio c"),
    @NamedQuery(name = "Convenio.findByIdConvenio", query = "SELECT c FROM Convenio c WHERE c.idConvenio = :idConvenio"),
    @NamedQuery(name = "Convenio.findByNombreConvenio", query = "SELECT c FROM Convenio c WHERE c.nombreConvenio = :nombreConvenio"),
    @NamedQuery(name = "Convenio.findByDescripcion", query = "SELECT c FROM Convenio c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Convenio.findByFechaInicio", query = "SELECT c FROM Convenio c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Convenio.findByFechaFin", query = "SELECT c FROM Convenio c WHERE c.fechaFin = :fechaFin")})
public class Convenio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_convenio")
    private Integer idConvenio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_convenio")
    private String nombreConvenio;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConvenio", fetch = FetchType.LAZY)
    private List<PagoConvenio> pagoConvenioList;

    public Convenio() {
    }

    public Convenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Convenio(Integer idConvenio, String nombreConvenio, Date fechaInicio) {
        this.idConvenio = idConvenio;
        this.nombreConvenio = nombreConvenio;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<PagoConvenio> getPagoConvenioList() {
        return pagoConvenioList;
    }

    public void setPagoConvenioList(List<PagoConvenio> pagoConvenioList) {
        this.pagoConvenioList = pagoConvenioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConvenio != null ? idConvenio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Convenio)) {
            return false;
        }
        Convenio other = (Convenio) object;
        if ((this.idConvenio == null && other.idConvenio != null) || (this.idConvenio != null && !this.idConvenio.equals(other.idConvenio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Convenio[ idConvenio=" + idConvenio + " ]";
    }
    
}
