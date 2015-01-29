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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "zona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zona.findAll", query = "SELECT z FROM Zona z"),
    @NamedQuery(name = "Zona.findByIdZona", query = "SELECT z FROM Zona z WHERE z.idZona = :idZona"),
    @NamedQuery(name = "Zona.findByCapacidadVehiculos", query = "SELECT z FROM Zona z WHERE z.capacidadVehiculos = :capacidadVehiculos"),
    @NamedQuery(name = "Zona.findByNombre", query = "SELECT z FROM Zona z WHERE z.nombre = :nombre"),
    @NamedQuery(name = "Zona.findByUbicacion", query = "SELECT z FROM Zona z WHERE z.ubicacion = :ubicacion"),
    @NamedQuery(name = "Zona.findByCarga", query = "SELECT z FROM Zona z WHERE z.carga = :carga")})
public class Zona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_zona")
    private Integer idZona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad_vehiculos")
    private int capacidadVehiculos;
    @Basic(optional = false)
    @NotNull   
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull   
    @Column(name = "ubicacion")
    private String ubicacion;
    @Size(max = 100)
    @Column(name = "carga")
    private String carga;
    @ManyToMany(mappedBy = "zonaList", fetch = FetchType.LAZY)
    private List<Empleado> empleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idZona", fetch = FetchType.LAZY)
    private List<Vehiculo> vehiculoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idZona", fetch = FetchType.LAZY)
    private List<DetallesRutaZona> detallesRutaZonaList;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado idEstado;

    public Zona() {
    }

    public Zona(Integer idZona) {
        this.idZona = idZona;
    }

    public Zona(Integer idZona, int capacidadVehiculos, String nombre, String ubicacion) {
        this.idZona = idZona;
        this.capacidadVehiculos = capacidadVehiculos;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public int getCapacidadVehiculos() {
        return capacidadVehiculos;
    }

    public void setCapacidadVehiculos(int capacidadVehiculos) {
        this.capacidadVehiculos = capacidadVehiculos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @XmlTransient
    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }

    @XmlTransient
    public List<DetallesRutaZona> getDetallesRutaZonaList() {
        return detallesRutaZonaList;
    }

    public void setDetallesRutaZonaList(List<DetallesRutaZona> detallesRutaZonaList) {
        this.detallesRutaZonaList = detallesRutaZonaList;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZona != null ? idZona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.idZona == null && other.idZona != null) || (this.idZona != null && !this.idZona.equals(other.idZona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Zona[ idZona=" + idZona + " ]";
    }
    
}
