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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "imagenesprueba")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imagenesprueba.findAll", query = "SELECT i FROM Imagenesprueba i"),
    @NamedQuery(name = "Imagenesprueba.findByIdImagen", query = "SELECT i FROM Imagenesprueba i WHERE i.idImagen = :idImagen")})
public class Imagenesprueba implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idImagen")
    private Integer idImagen;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    public Imagenesprueba() {
    }

    public Imagenesprueba(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagenesprueba)) {
            return false;
        }
        Imagenesprueba other = (Imagenesprueba) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.Imagenesprueba[ idImagen=" + idImagen + " ]";
    }
    
}
