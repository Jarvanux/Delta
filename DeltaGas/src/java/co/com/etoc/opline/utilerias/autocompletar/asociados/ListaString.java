package co.com.etoc.opline.utilerias.autocompletar.asociados;

/**
 *
 * @author jhonjaider1000
 */
import co.com.etoc.opline.persistencia.dao.AsociadoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "listaString", eager = true)
@ApplicationScoped
public class ListaString {

    private List<Asociado> asociados;
    private List<Asociado> listaAsociado;
    @EJB
    private AsociadoFacadeLocal asociadoFL;

    @PostConstruct
    public void init() {

        listaAsociado = asociadoFL.findAll();
        asociados = new ArrayList<Asociado>();
        //Generalmente lo que estoy haciendo es agregando a la lista un dato que no existe en la BD.
        //Debido a que necesitamos tener ocupada la posición 0 del array.
        asociados.add(new Asociado(0, "", "","", "", null));
        if (listaAsociado != null) {
            Iterator iterador = listaAsociado.listIterator();
            while (iterador.hasNext()) {
                Asociado asociado = (Asociado) iterador.next();                
                asociados.add(new Asociado(asociado.getIdAsociado(), asociado.getNombre(), asociado.getApellido(), asociado.getCedula(), asociado.getExpedida(), asociado.getFechaAfiliacion()));
            }
        }
    }
     
    
    public List<Asociado> getAsociados() {
        return asociados;
    }
}
