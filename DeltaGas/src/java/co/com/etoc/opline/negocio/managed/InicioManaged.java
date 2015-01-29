package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.utilerias.UtilOne;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@RequestScoped
public class InicioManaged extends ValidaSesion implements Serializable {

    public InicioManaged() {
    }

    private String mensaje = "";

    @PostConstruct
    public void init() {
        //Evaluar cumpleaños.     
        if (empleado.getFechaNacimiento() != null) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            if (df.format(empleado.getFechaNacimiento()).equals(df.format(new Date()))) {

                if (empleado.getSexo() == 1) {
                    mensaje = "Bienvenido " + empleado.getNombre() + " !Feliz Cumpleaños!, que tengas un excelente día!.";
                } else if (empleado.getSexo() == 2) {
                    mensaje = "Bienvenida " + empleado.getNombre() + " !Feliz Cumpleaños!, que tengas un excelente día!.";
                }
            } else {
                mensaje = "Seleccione una opción en el menú superior aquí se cargará el contenido.";
            }
        } else {
            mensaje = "Seleccione una opción en el menú superior, aquí se cargará el contenido.";
        }
    }

    //Métodos set y get.
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
