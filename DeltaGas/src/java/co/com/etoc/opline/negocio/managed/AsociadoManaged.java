package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.AsociadoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class AsociadoManaged extends ValidaSesion implements Serializable {

    private List<Asociado> listaAsociado;
    private int asociado;    
    private String fijarPagina;

    @EJB
    private AsociadoFacadeLocal asociadoFL;
    private Asociado objectAsociado;

    @PostConstruct
    public void init() {
        listaAsociado = asociadoFL.findAll();
        this.fijarPagina = "cliente.xhtml";
    }
    
    public void cargarEmpleados(){
         this.fijarPagina = "empleado.xhtml";
    }
     
     public String cargarPagina(){
         return this.fijarPagina;
     }

    public List<Asociado> getListaAsociado() {
        return listaAsociado;
    }

    public int getAsociado() {
        return asociado;
    }

    public Asociado getObjectAsociado() {
        return objectAsociado;
    }

    public void setObjectAsociado(Asociado objectAsociado) {
        this.objectAsociado = objectAsociado;
    }

    public String getFijarPagina() {
        return fijarPagina;
    }

    public void setFijarPagina(String fijarPagina) {
        this.fijarPagina = fijarPagina;
    }
    
    
   
}
