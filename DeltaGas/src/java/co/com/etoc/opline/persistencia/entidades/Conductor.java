/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "conductor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conductor.findAll", query = "SELECT c FROM Conductor c"),
    @NamedQuery(name = "Conductor.findByNic", query = "SELECT c FROM Conductor c WHERE c.nic = :nic"),
    @NamedQuery(name = "Conductor.findByNombre", query = "SELECT c FROM Conductor c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Conductor.findByApellido", query = "SELECT c FROM Conductor c WHERE c.apellido = :apellido"),
    @NamedQuery(name = "Conductor.findByFechaNacimiento", query = "SELECT c FROM Conductor c WHERE c.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Conductor.findByNivelEscolaridad", query = "SELECT c FROM Conductor c WHERE c.nivelEscolaridad = :nivelEscolaridad"),
    @NamedQuery(name = "Conductor.findByDireccion", query = "SELECT c FROM Conductor c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Conductor.findByCorreo", query = "SELECT c FROM Conductor c WHERE c.correo = :correo"),
    @NamedQuery(name = "Conductor.findByCelular", query = "SELECT c FROM Conductor c WHERE c.celular = :celular"),
    @NamedQuery(name = "Conductor.findByTelefono", query = "SELECT c FROM Conductor c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Conductor.findByBarrio", query = "SELECT c FROM Conductor c WHERE c.barrio = :barrio"),
    @NamedQuery(name = "Conductor.findByArp", query = "SELECT c FROM Conductor c WHERE c.arp = :arp"),
    @NamedQuery(name = "Conductor.findByEps", query = "SELECT c FROM Conductor c WHERE c.eps = :eps"),
    @NamedQuery(name = "Conductor.findByGenero", query = "SELECT c FROM Conductor c WHERE c.genero = :genero"),
    @NamedQuery(name = "Conductor.findByEstadoCivil", query = "SELECT c FROM Conductor c WHERE c.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Conductor.findByNumHijos", query = "SELECT c FROM Conductor c WHERE c.numHijos = :numHijos"),
    @NamedQuery(name = "Conductor.findByGrupoSangineo", query = "SELECT c FROM Conductor c WHERE c.grupoSangineo = :grupoSangineo"),
    @NamedQuery(name = "Conductor.findByCedula", query = "SELECT c FROM Conductor c WHERE c.cedula = :cedula"),
    @NamedQuery(name = "Conductor.findByExpedida", query = "SELECT c FROM Conductor c WHERE c.expedida = :expedida")})
public class Conductor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "nic")
    private Integer nic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Size(max = 25)
    @Column(name = "nivel_escolaridad")
    private String nivelEscolaridad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 30)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 30)
    @Column(name = "barrio")
    private String barrio;
    @Size(max = 25)
    @Column(name = "arp")
    private String arp;
    @Size(max = 25)
    @Column(name = "eps")
    private String eps;
    @Size(max = 10)
    @Column(name = "genero")
    private String genero;
    @Size(max = 40)
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Column(name = "num_hijos")
    private Integer numHijos;
    @Size(max = 5)
    @Column(name = "grupo_sangineo")
    private String grupoSangineo;
    @Size(max = 50)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 50)
    @Column(name = "expedida")
    private String expedida;
    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public Conductor() {
    }

    public Conductor(Integer nic) {
        this.nic = nic;
    }

    public Conductor(Integer nic, String nombre, String apellido, Date fechaNacimiento, String direccion, String celular, String telefono) {
        this.nic = nic;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.celular = celular;
        this.telefono = telefono;
    }

    public Integer getNic() {
        return nic;
    }

    public void setNic(Integer nic) {
        this.nic = nic;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNivelEscolaridad() {
        return nivelEscolaridad;
    }

    public void setNivelEscolaridad(String nivelEscolaridad) {
        this.nivelEscolaridad = nivelEscolaridad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getArp() {
        return arp;
    }

    public void setArp(String arp) {
        this.arp = arp;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Integer getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(Integer numHijos) {
        this.numHijos = numHijos;
    }

    public String getGrupoSangineo() {
        return grupoSangineo;
    }

    public void setGrupoSangineo(String grupoSangineo) {
        this.grupoSangineo = grupoSangineo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getExpedida() {
        return expedida;
    }

    public void setExpedida(String expedida) {
        this.expedida = expedida;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nic != null ? nic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.nic == null && other.nic != null) || (this.nic != null && !this.nic.equals(other.nic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Conductor[ nic=" + nic + " ]";
    }
    
}
