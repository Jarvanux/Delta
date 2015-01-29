package co.com.etoc.opline.utilerias.autocompletar.asociados;

/**
 *
 * @author jhonjaider1000
 */

import co.com.etoc.opline.persistencia.entidades.Asociado;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean
public class Autocomplete {

    private Asociado asociado;

    @ManagedProperty("#{listaString}")
    private ListaString service;

    public List<String> completaText(String query) {
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }

    public List<Asociado> completeAsociado(String query) {
        List<Asociado> asociados = service.getAsociados();
        List<Asociado> filtroAsociados = new ArrayList<Asociado>();

        for (int i = 0; i < asociados.size(); i++) {
            Asociado asociado = asociados.get(i);
            if (asociado.getCedula().toLowerCase().startsWith(query)) {
                filtroAsociados.add(asociado);
            }
        }

        return filtroAsociados;
    }

    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }

    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
    }

    public ListaString getService() {
        return service;
    }

    public void setService(ListaString service) {
        this.service = service;
    }

}
