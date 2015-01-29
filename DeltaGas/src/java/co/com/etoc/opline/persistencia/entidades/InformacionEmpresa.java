/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "informacion_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionEmpresa.findAll", query = "SELECT i FROM InformacionEmpresa i"),
    @NamedQuery(name = "InformacionEmpresa.findByIdEmpresa", query = "SELECT i FROM InformacionEmpresa i WHERE i.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "InformacionEmpresa.findByNombre", query = "SELECT i FROM InformacionEmpresa i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "InformacionEmpresa.findByGerente", query = "SELECT i FROM InformacionEmpresa i WHERE i.gerente = :gerente"),
    @NamedQuery(name = "InformacionEmpresa.findByCelular", query = "SELECT i FROM InformacionEmpresa i WHERE i.celular = :celular"),
    @NamedQuery(name = "InformacionEmpresa.findByTelefono", query = "SELECT i FROM InformacionEmpresa i WHERE i.telefono = :telefono"),
    @NamedQuery(name = "InformacionEmpresa.findByDireccion", query = "SELECT i FROM InformacionEmpresa i WHERE i.direccion = :direccion"),
    @NamedQuery(name = "InformacionEmpresa.findByCiudad", query = "SELECT i FROM InformacionEmpresa i WHERE i.ciudad = :ciudad"),
    @NamedQuery(name = "InformacionEmpresa.findByPais", query = "SELECT i FROM InformacionEmpresa i WHERE i.pais = :pais")})
public class InformacionEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private Integer idEmpresa;
    @Size(max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 20)
    @Column(name = "gerente")
    private String gerente;
    @Size(max = 20)
    @Column(name = "celular")
    private String celular;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 20)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 20)
    @Column(name = "pais")
    private String pais;
    @Column(name = "correo")
    private String correo;
    @Column(name = "clave_correo")
    private String claveCorreo;
    @Column(name = "pagina_facebook")
    private String facebook;
    @Column(name = "pagina_twitter")
    private String twitter;
    @Column(name = "pagina_google")
    private String google;
    @Column(name = "pagina_in")
    private String in;
    @Column(name = "sitio_web")
    private String sitioWeb;
    
    public InformacionEmpresa() {
    }

    public InformacionEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }    

    public String getClaveCorreo() {
        return claveCorreo;
    }

    public void setClaveCorreo(String claveCorreo) {
        this.claveCorreo = claveCorreo;
    }   

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionEmpresa)) {
            return false;
        }
        InformacionEmpresa other = (InformacionEmpresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.InformacionEmpresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
