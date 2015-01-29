/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EstacionFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Estacion;
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
public class EstacionManaged {

    /**
     * Creates a new instance of EstacionManaged
     */
    public EstacionManaged() {
    }
    
    @EJB
    EstacionFacadeLocal estacionFL;
    
    //Atributos
    List<Estacion> lista;    
    
    @PostConstruct
    public void init(){        
        lista = estacionFL.findAll();
    }

    //Métodos get y set.
    public List<Estacion> getLista() {
        return lista;
    }

    public void setLista(List<Estacion> lista) {
        this.lista = lista;
    }        
}
