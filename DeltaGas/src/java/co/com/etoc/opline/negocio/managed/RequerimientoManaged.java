/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.RequerimientoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Requerimiento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@RequestScoped
public class RequerimientoManaged {

    public RequerimientoManaged() {
    }
    
    @EJB
    private RequerimientoFacadeLocal rqFL;

    //Variables
    List<Requerimiento> listaRequerimientos = null;
    
    @PostConstruct
    public void init(){
        listaRequerimientos = rqFL.findAll();
    }
  
    public List<Requerimiento> getListaRequerimientos() {
        return listaRequerimientos;
    }

    public void setListaRequerimientos(List<Requerimiento> listaRequerimientos) {
        this.listaRequerimientos = listaRequerimientos;
    }
    
    
    
}
