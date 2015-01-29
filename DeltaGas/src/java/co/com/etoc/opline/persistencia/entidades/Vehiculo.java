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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByIdVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa"),
    @NamedQuery(name = "Vehiculo.findByNumeroMotor", query = "SELECT v FROM Vehiculo v WHERE v.numeroMotor = :numeroMotor"),
    @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca"),
    @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "Vehiculo.findByNumeroChasis", query = "SELECT v FROM Vehiculo v WHERE v.numeroChasis = :numeroChasis"),
    @NamedQuery(name = "Vehiculo.findByTipoServicio", query = "SELECT v FROM Vehiculo v WHERE v.tipoServicio = :tipoServicio"),
    @NamedQuery(name = "Vehiculo.findByTipoVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.tipoVehiculo = :tipoVehiculo")})
public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "placa")
    private String placa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_motor")
    private String numeroMotor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modelo")
    private int modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_chasis")
    private String numeroChasis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "tipo_servicio")
    private String tipoServicio;
    @Size(max = 25)
    @Column(name = "tipo_vehiculo")
    private String tipoVehiculo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private Tecnomecanica tecnomecanica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVehiculo", fetch = FetchType.LAZY)
    private List<Viaje> viajeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVehiculo", fetch = FetchType.LAZY)
    private List<PagoConvenio> pagoConvenioList;
    @JoinColumn(name = "id_asociado", referencedColumnName = "id_asociado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Asociado idAsociado;
    @JoinColumn(name = "nic", referencedColumnName = "nic")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conductor nic;
    @JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Zona idZona;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vehiculo", fetch = FetchType.LAZY)
    private Soat soat;

    public Vehiculo() {
    }

    public Vehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Vehiculo(Integer idVehiculo, String placa, String numeroMotor, String marca, int modelo, String numeroChasis, String tipoServicio) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.numeroMotor = numeroMotor;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroChasis = numeroChasis;
        this.tipoServicio = tipoServicio;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Tecnomecanica getTecnomecanica() {
        return tecnomecanica;
    }

    public void setTecnomecanica(Tecnomecanica tecnomecanica) {
        this.tecnomecanica = tecnomecanica;
    }

    @XmlTransient
    public List<Viaje> getViajeList() {
        return viajeList;
    }

    public void setViajeList(List<Viaje> viajeList) {
        this.viajeList = viajeList;
    }

    @XmlTransient
    public List<PagoConvenio> getPagoConvenioList() {
        return pagoConvenioList;
    }

    public void setPagoConvenioList(List<PagoConvenio> pagoConvenioList) {
        this.pagoConvenioList = pagoConvenioList;
    }

    public Asociado getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(Asociado idAsociado) {
        this.idAsociado = idAsociado;
    }

    public Conductor getNic() {
        return nic;
    }

    public void setNic(Conductor nic) {
        this.nic = nic;
    }

    public Zona getIdZona() {
        return idZona;
    }

    public void setIdZona(Zona idZona) {
        this.idZona = idZona;
    }

    public Soat getSoat() {
        return soat;
    }

    public void setSoat(Soat soat) {
        this.soat = soat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Vehiculo[ idVehiculo=" + idVehiculo + " ]";
    }
    
}
