package co.com.etoc.opline.utilerias.autocompletar.conductor;
/**
 *
 * @author jhonjaider1000
 */
import co.com.etoc.opline.persistencia.dao.ConductorFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Conductor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "listaStringConductor", eager = true)
@ApplicationScoped
public class ListaString {

    private List<Conductor> conductores;
    private List<Conductor> listaConductor;
    @EJB
    private ConductorFacadeLocal conductorFL;

    @PostConstruct
    public void init() {

        listaConductor= conductorFL.findAll();
        conductores = new ArrayList<Conductor>();
        //Generalmente lo que estoy haciendo es agregando a la lista un dato que no existe en la BD.
        //Debido a que necesitamos tener ocupada la posición 0 del array.
        conductores.add(new Conductor(0));
        if (listaConductor != null) {
            Iterator iterador = listaConductor.listIterator();
            while (iterador.hasNext()) {
                Conductor conductor = (Conductor) iterador.next();                
                conductores.add(conductor);
            }
        }
    }
     
    
    public List<Conductor> getConductores() {
        return conductores;
    }
}
