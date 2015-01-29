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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "reunion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reunion.findAll", query = "SELECT r FROM Reunion r"),
    @NamedQuery(name = "Reunion.findByIdReunion", query = "SELECT r FROM Reunion r WHERE r.idReunion = :idReunion"),
    @NamedQuery(name = "Reunion.findByEstadoReunion", query = "SELECT r FROM Reunion r WHERE r.estadoReunion = :estadoReunion"),
    @NamedQuery(name = "Reunion.findByDescripcion", query = "SELECT r FROM Reunion r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Reunion.findByFechaReunion", query = "SELECT r FROM Reunion r WHERE r.fechaReunion = :fechaReunion"),
    @NamedQuery(name = "Reunion.findByLugar", query = "SELECT r FROM Reunion r WHERE r.lugar = :lugar"),
    @NamedQuery(name = "Reunion.findByNombre", query = "SELECT r FROM Reunion r WHERE r.nombre = :nombre")})
public class Reunion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reunion")
    private Integer idReunion;
    @Size(max = 50)
    @Column(name = "estado_reunion")
    private String estadoReunion;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_reunion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReunion;
    @Size(max = 50)
    @Column(name = "lugar")
    private String lugar;
    @Size(max = 40)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "reunionList", fetch = FetchType.LAZY)
    private List<Empleado> empleadoList;

    public Reunion() {
    }

    public Reunion(Integer idReunion) {
        this.idReunion = idReunion;
    }

    public Integer getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(Integer idReunion) {
        this.idReunion = idReunion;
    }

    public String getEstadoReunion() {
        return estadoReunion;
    }

    public void setEstadoReunion(String estadoReunion) {
        this.estadoReunion = estadoReunion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaReunion() {
        return fechaReunion;
    }

    public void setFechaReunion(Date fechaReunion) {
        this.fechaReunion = fechaReunion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReunion != null ? idReunion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reunion)) {
            return false;
        }
        Reunion other = (Reunion) object;
        if ((this.idReunion == null && other.idReunion != null) || (this.idReunion != null && !this.idReunion.equals(other.idReunion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Reunion[ idReunion=" + idReunion + " ]";
    }
    
}
