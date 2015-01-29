package co.com.etoc.opline.utilerias.autocompletar.conductor;
 
/**
 *
 * @author jhonjaider1000
 */
import co.com.etoc.opline.persistencia.entidades.Conductor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.SelectEvent;
 
@ManagedBean
public class AutocompleteConductor {
     

//    private Conductor conductor;

    @ManagedProperty("#{listaStringConductor}")
    private ListaString service;
     
    public List<String> completaText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
     
    public List<Conductor> completeConductor(String query) {
        List<Conductor> conductores = service.getConductores();
        List<Conductor> filtroConductor = new ArrayList<Conductor>();
         
        for (int i = 0; i < conductores.size(); i++) {
            Conductor conductor = conductores.get(i);
            if(conductor.getNic().toString().toLowerCase().startsWith(query)) {
                filtroConductor.add(conductor);
            }
        }
         
        return filtroConductor;
    }
     
    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }    

    public ListaString getService() {
        return service;
    }

    public void setService(ListaString service) {
        this.service = service;
    }        
    
}