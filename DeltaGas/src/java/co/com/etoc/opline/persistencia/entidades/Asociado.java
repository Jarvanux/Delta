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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author sena
 */
@Entity
@Table(name = "asociado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asociado.findAll", query = "SELECT a FROM Asociado a"),
    @NamedQuery(name = "Asociado.findByIdAsociado", query = "SELECT a FROM Asociado a WHERE a.idAsociado = :idAsociado"),
    @NamedQuery(name = "Asociado.findByNombre", query = "SELECT a FROM Asociado a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Asociado.findByApellido", query = "SELECT a FROM Asociado a WHERE a.apellido = :apellido"),
    @NamedQuery(name = "Asociado.findByFechaNacimiento", query = "SELECT a FROM Asociado a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Asociado.findByCedula", query = "SELECT a FROM Asociado a WHERE a.cedula = :cedula"),
    @NamedQuery(name = "Asociado.findByExpedida", query = "SELECT a FROM Asociado a WHERE a.expedida = :expedida"),
    @NamedQuery(name = "Asociado.findByFechaAfiliacion", query = "SELECT a FROM Asociado a WHERE a.fechaAfiliacion = :fechaAfiliacion"),
    @NamedQuery(name = "Asociado.findByAntiguedadCorabastos", query = "SELECT a FROM Asociado a WHERE a.antiguedadCorabastos = :antiguedadCorabastos"),
    @NamedQuery(name = "Asociado.findByCelular", query = "SELECT a FROM Asociado a WHERE a.celular = :celular"),
    @NamedQuery(name = "Asociado.findByCorreo", query = "SELECT a FROM Asociado a WHERE a.correo = :correo")})
public class Asociado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asociado")
    private Integer idAsociado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "expedida")
    private String expedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_afiliacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAfiliacion;
    @Size(max = 20)
    @Column(name = "antiguedad_corabastos")
    private String antiguedadCorabastos;
    @Size(max = 20)
    @Column(name = "celular")
    private String celular;
    @Size(max = 30)
    @Column(name = "correo")
    private String correo;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado idEstado;

    public Asociado() {
    }

    public Asociado(Integer idAsociado) {
        this.idAsociado = idAsociado;
    }

    public Asociado(Integer idAsociado, String nombre, String apellido, String cedula, String expedida, Date fechaAfiliacion) {
        this.idAsociado = idAsociado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.expedida = expedida;
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public Integer getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(Integer idAsociado) {
        this.idAsociado = idAsociado;
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

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public String getAntiguedadCorabastos() {
        return antiguedadCorabastos;
    }

    public void setAntiguedadCorabastos(String antiguedadCorabastos) {
        this.antiguedadCorabastos = antiguedadCorabastos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
        hash += (idAsociado != null ? idAsociado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asociado)) {
            return false;
        }
        Asociado other = (Asociado) object;
        if ((this.idAsociado == null && other.idAsociado != null) || (this.idAsociado != null && !this.idAsociado.equals(other.idAsociado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Asociado[ idAsociado=" + idAsociado + " ]";
    }
    
}
