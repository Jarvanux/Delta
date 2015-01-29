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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "encuesta_aplicada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncuestaAplicada.findAll", query = "SELECT e FROM EncuestaAplicada e"),
    @NamedQuery(name = "EncuestaAplicada.findByIdEncuestaAplicada", query = "SELECT e FROM EncuestaAplicada e WHERE e.idEncuestaAplicada = :idEncuestaAplicada"),
    @NamedQuery(name = "EncuestaAplicada.findByFechaAplicada", query = "SELECT e FROM EncuestaAplicada e WHERE e.fechaAplicada = :fechaAplicada"),
    @NamedQuery(name = "EncuestaAplicada.findByCantidadAplicada", query = "SELECT e FROM EncuestaAplicada e WHERE e.cantidadAplicada = :cantidadAplicada")})
public class EncuestaAplicada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encuesta_aplicada")
    private Integer idEncuestaAplicada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_aplicada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_aplicada")
    private int cantidadAplicada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encuestaAplicada", fetch = FetchType.LAZY)
    private List<DetallesEncuestaAplicada> detallesEncuestaAplicadaList;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente idCliente;
    @JoinColumn(name = "nit", referencedColumnName = "nit")
    @ManyToOne(fetch = FetchType.LAZY)
    private Conductor nit;
    @JoinColumn(name = "id_encuesta", referencedColumnName = "id_encuesta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Encuesta idEncuesta;

    public EncuestaAplicada() {
    }

    public EncuestaAplicada(Integer idEncuestaAplicada) {
        this.idEncuestaAplicada = idEncuestaAplicada;
    }

    public EncuestaAplicada(Integer idEncuestaAplicada, Date fechaAplicada, int cantidadAplicada) {
        this.idEncuestaAplicada = idEncuestaAplicada;
        this.fechaAplicada = fechaAplicada;
        this.cantidadAplicada = cantidadAplicada;
    }

    public Integer getIdEncuestaAplicada() {
        return idEncuestaAplicada;
    }

    public void setIdEncuestaAplicada(Integer idEncuestaAplicada) {
        this.idEncuestaAplicada = idEncuestaAplicada;
    }

    public Date getFechaAplicada() {
        return fechaAplicada;
    }

    public void setFechaAplicada(Date fechaAplicada) {
        this.fechaAplicada = fechaAplicada;
    }

    public int getCantidadAplicada() {
        return cantidadAplicada;
    }

    public void setCantidadAplicada(int cantidadAplicada) {
        this.cantidadAplicada = cantidadAplicada;
    }

    @XmlTransient
    public List<DetallesEncuestaAplicada> getDetallesEncuestaAplicadaList() {
        return detallesEncuestaAplicadaList;
    }

    public void setDetallesEncuestaAplicadaList(List<DetallesEncuestaAplicada> detallesEncuestaAplicadaList) {
        this.detallesEncuestaAplicadaList = detallesEncuestaAplicadaList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Conductor getNit() {
        return nit;
    }

    public void setNit(Conductor nit) {
        this.nit = nit;
    }

    public Encuesta getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Encuesta idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuestaAplicada != null ? idEncuestaAplicada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaAplicada)) {
            return false;
        }
        EncuestaAplicada other = (EncuestaAplicada) object;
        if ((this.idEncuestaAplicada == null && other.idEncuestaAplicada != null) || (this.idEncuestaAplicada != null && !this.idEncuestaAplicada.equals(other.idEncuestaAplicada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.EncuestaAplicada[ idEncuestaAplicada=" + idEncuestaAplicada + " ]";
    }
    
}
