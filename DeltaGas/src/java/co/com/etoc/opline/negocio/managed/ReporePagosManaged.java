/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.entidades.Asociado;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class ReporePagosManaged extends ValidaSesion implements Serializable{

    
    private Date fechaInicio;
    private Date fechaFin;
    private int idAsociado;
    private Asociado listarAsociado; 
    
    
    public ReporePagosManaged() {
    }

    public void generarReporte(){
    
     
        
    }
    
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(int idAsociado) {
        this.idAsociado = idAsociado;
    }

    public Asociado getListarAsociado() {
        return listarAsociado;
    }

    public void setListarAsociado(Asociado listarAsociado) {
        this.listarAsociado = listarAsociado;
    }
    
    
    
}
