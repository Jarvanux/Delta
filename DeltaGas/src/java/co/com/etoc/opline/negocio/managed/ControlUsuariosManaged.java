/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class ControlUsuariosManaged implements Serializable{

    public ControlUsuariosManaged() {
    }
    
    @EJB
    EmpleadoFacadeLocal empleadoFL;
    
    //Atributos
    List<Empleado> lista;
    List<Empleado> seleccionados;
    List<Empleado> filtro;
    int idEstado;
    
    @PostConstruct
    public void init(){
        lista = empleadoFL.findAll();
    }
    
    public void guardarCambios(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("#"+this.seleccionados));
    }
        
    //Métodos set y get.
    public List<Empleado> getLista() {
        return lista;
    }

    public void setLista(List<Empleado> lista) {
        this.lista = lista;
    }

    public List<Empleado> getSeleccionados() {
        return seleccionados;
    }

     public void listarPorEstado() {
        if (this.idEstado == 1) {
            this.lista = empleadoFL.activos();            
        } else {
            this.lista = empleadoFL.deshabilitados();            
        }
    }
    public void setSeleccionados(List<Empleado> seleccionados) {
        this.seleccionados = seleccionados;
    }   

    public List<Empleado> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<Empleado> filtro) {
        this.filtro = filtro;
    }
    
}