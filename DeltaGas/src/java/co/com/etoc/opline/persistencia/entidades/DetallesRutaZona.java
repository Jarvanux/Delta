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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "detalles_ruta_zona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesRutaZona.findAll", query = "SELECT d FROM DetallesRutaZona d"),
    @NamedQuery(name = "DetallesRutaZona.findByIdDetalleRuta", query = "SELECT d FROM DetallesRutaZona d WHERE d.idDetalleRuta = :idDetalleRuta")})
public class DetallesRutaZona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_ruta")
    private Integer idDetalleRuta;
    @JoinColumn(name = "id_zona", referencedColumnName = "id_zona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Zona idZona;
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ruta idRuta;

    public DetallesRutaZona() {
    }

    public DetallesRutaZona(Integer idDetalleRuta) {
        this.idDetalleRuta = idDetalleRuta;
    }

    public Integer getIdDetalleRuta() {
        return idDetalleRuta;
    }

    public void setIdDetalleRuta(Integer idDetalleRuta) {
        this.idDetalleRuta = idDetalleRuta;
    }

    public Zona getIdZona() {
        return idZona;
    }

    public void setIdZona(Zona idZona) {
        this.idZona = idZona;
    }

    public Ruta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Ruta idRuta) {
        this.idRuta = idRuta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleRuta != null ? idDetalleRuta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesRutaZona)) {
            return false;
        }
        DetallesRutaZona other = (DetallesRutaZona) object;
        if ((this.idDetalleRuta == null && other.idDetalleRuta != null) || (this.idDetalleRuta != null && !this.idDetalleRuta.equals(other.idDetalleRuta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.etoc.opline.persistencia.entidades.DetallesRutaZona[ idDetalleRuta=" + idDetalleRuta + " ]";
    }
    
}
