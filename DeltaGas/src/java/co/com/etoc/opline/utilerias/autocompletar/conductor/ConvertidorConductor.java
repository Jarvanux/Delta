package co.com.etoc.opline.utilerias.autocompletar.conductor;
/**
 *
 * @author jhonjaider1000
 */ 
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Conductor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

 
@FacesConverter("convertidorConductor")
public class ConvertidorConductor implements Converter {
 
    //El siguiente método nos permite obtener el objeto que viene de la lista
    //String como un objeto de la clase que hemos asociado en el Array String de
    //la clase ListaString.
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            ListaString service = (ListaString) fc.getExternalContext().getApplicationMap().get("listaStringConductor");
            return service.getConductores().get(Integer.parseInt(value));
        }
        else {
            return null;
        }
    }
 
    //A continuación el metodo getAsString servirá para convertir el objeto en
    //String.
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Conductor) object).getNic());
        }
        else {
            return null;
        }
    }   
}     