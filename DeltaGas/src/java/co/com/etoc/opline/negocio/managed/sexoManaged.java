
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.SexosFacade;
import co.com.etoc.opline.persistencia.dao.SexosFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Sexos;
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
public class sexoManaged {

    private List<Sexos> listaSexos;
    
    @EJB
    private SexosFacadeLocal sexoFL;
    public sexoManaged() {
    }
        
    @PostConstruct
    public void init(){
        this.listaSexos = sexoFL.findAll();
    }
    
    //Métodos set y get.
    public List<Sexos> getListaSexos() {
        return listaSexos;
    }

    public void setListaSexos(List<Sexos> listaSexos) {
        this.listaSexos = listaSexos;
    }
    
}
