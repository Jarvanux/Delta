/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByApellido", query = "SELECT c FROM Cliente c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Cliente.findByCedula", query = "SELECT c FROM Cliente c WHERE c.cedula = :cedula"),
    @NamedQuery(name = "Cliente.findByCelular", query = "SELECT c FROM Cliente c WHERE c.celular = :celular"),
    @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cliente.findByCorreo", query = "SELECT c FROM Cliente c WHERE c.correo = :correo")})
public class Cliente implements Serializable {
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<ServicioTecnico> servicioTecnicoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Basic(optional = false)
    @NotNull    
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull    
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 30)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 30)
    @Column(name = "celular")
    private String celular;
    @Size(max = 30)
    @Column(name = "telefono")
    private String telefono;    
    @Column(name = "correo")
    private String correo;
    @ManyToMany(mappedBy = "clienteList", fetch = FetchType.LAZY)
    private List<Viaje> viajeList;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<EncuestaAplicada> encuestaAplicadaList;
    @JoinColumn(name = "id_tipo_cliente", referencedColumnName = "id_tipo_cliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoCliente idTipoCliente;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String nombre, String apellido) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @XmlTransient
    public List<Viaje> getViajeList() {
        return viajeList;
    }

    public void setViajeList(List<Viaje> viajeList) {
        this.viajeList = viajeList;
    }

    @XmlTransient
    public List<EncuestaAplicada> getEncuestaAplicadaList() {
        return encuestaAplicadaList;
    }

    public void setEncuestaAplicadaList(List<EncuestaAplicada> encuestaAplicadaList) {
        this.encuestaAplicadaList = encuestaAplicadaList;
    }

    public TipoCliente getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(TipoCliente idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Cliente[ idCliente=" + idCliente + " ]";
    }

    @XmlTransient
    public List<ServicioTecnico> getServicioTecnicoList() {
        return servicioTecnicoList;
    }

    public void setServicioTecnicoList(List<ServicioTecnico> servicioTecnicoList) {
        this.servicioTecnicoList = servicioTecnicoList;
    }
    
}
