package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.EJB;
import javax.enterprise.inject.Alternative;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@ApplicationScoped

public class ValidarFormularios implements Serializable {       

    private static String imagenString1;
    private static String imagenString2;
    private static String imagenString3;
    private static String imagenString4;
    private static String imagenString5;
    private static String imagenString6;
    private static String imagenString7;
    private static String imagenString8;
    private static String imagenString9;
    private static String imagenString10;
    private static String imagenDate1;
    private static String imagenDate2;
    private static String imagenDate3;
    private static String imagenDate4;
    //En el caso de los integer los podemos usar para los campos que reciben
    //números claramente, y por su puesto para los combox.
    private static String imagenInteger1;
    private static String imagenInteger2;
    private static String imagenInteger3;
    private static String imagenInteger4;

    private static String imagenObjeto1;
    private static Integer sizeX;
    private static Integer sizeY;
    private static String sizeXFinal;

    @PostConstruct
    public void init() {
        imagenString1 = "null.gif";
        imagenString2 = "null.gif";
        imagenString3 = "null.gif";
        imagenString4 = "null.gif";
        imagenString5 = "null.gif";
        imagenString6 = "null.gif";
        imagenString7 = "null.gif";
        imagenString8 = "null.gif";
        imagenString9 = "null.gif";
        imagenString10 = "null.gif";
        imagenDate1 = "null.gif";
        imagenDate2 = "null.gif";
        imagenDate3 = "null.gif";
        imagenDate4 = "null.gif";
        //En el caso de los integer los podemos usar para los campos que reciben
        //números claramente, y por su puesto para los combox.
        imagenInteger1 = "null.gif";
        imagenInteger2 = "null.gif";
        imagenInteger3 = "null.gif";
        imagenInteger4 = "null.gif";
        imagenObjeto1 = "null.gif";
        sizeX = 20;
        sizeY = 20;
        sizeXFinal = "none";
    }
    //Modelo para validaciones.
    /*
     Validando un String:
    
     if (nombre.length() <= 2) {
     mensaje = (nombre.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
     imagenString1 = "error.png";
     estado = false;
     } else {
     this.imagenString1 = "null.gif";
     }
    
    
     Validando un Integer:
     if (edad <= 0) {
     mensaje = (edad < 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
     this.imagenInteger1 = "error.png";
     estado = false;
     } else {
     this.imagenInteger1 = "null.gif";
     }
    
     Validando un Date:
    
     if (fecha.toString().length() < 10) {
     mensaje = (fecha.toString().length() < 10) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
     this.imagenInteger1 = "error.png";
     estado = false;
     } else {
     this.imagenInteger1 = "null.gif";
     }
     */

    //Despliegen este método que ven a continuación y observen el modelo. :3
//Métodos para validar, la idea es que creen los métodos validar que necesiten.
    public static Boolean validar(String nombre, Date fecha, String lugar, String estadoReunion) {
        //Validando...        
        boolean estado = true;
        String mensaje = "";
        //Validaciones de que los campos no estén vacios.

        if (nombre.length() <= 2) {
            mensaje = (nombre.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString1 = "error.png";
            System.out.println("Error.");
            estado = false;
        } else {
            imagenString1 = "null.gif";
        }

        if (lugar.length() <= 2) {
            mensaje = (lugar.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString2 = "error.png";
            System.out.println("Error.");
            estado = false;
        } else {
            imagenString2 = "null.gif";
        }
        if (estadoReunion.length() <= 2) {
            mensaje = (estadoReunion.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString3 = "error.png";
            estado = false;
        } else {
            imagenString3 = "null.gif";
        }

        if (fecha == null) {
            mensaje = "Se encontraron datos nulos por favor corríjalos.";
            imagenDate1 = "error.png";
            estado = false;
        } else {
            imagenDate1 = "null.gif";
        }

        if (estado) {
            sizeXFinal = "0px";
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
            sizeXFinal = "block";
            return false;
        }
    }

    //Validando formularios cliente
    public static boolean validar(Integer tipo, String nombre, String apellidos, String cedula) {
        //Validando...        
        boolean estado = true;
        String mensaje = "";
        //Validaciones de que los campos no estén vacios.

        if (tipo < 0) {
            mensaje = "Debe seleccionar un tipo.";
            imagenInteger1 = "error.png";
        } else {
            imagenInteger1 = "null.gif";
        }
        if (nombre.length() <= 2) {
            mensaje = (nombre.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString1 = "error.png";
            System.out.println("Error.");
            estado = false;
        } else {
            imagenString1 = "null.gif";
        }

        if (apellidos.length() <= 2) {
            mensaje = (apellidos.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString2 = "error.png";
            System.out.println("Error.");
            estado = false;
        } else {
            imagenString2 = "null.gif";
        }
        if (cedula.length() <= 2) {
            mensaje = (cedula.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString3 = "error.png";
            estado = false;
        } else {
            imagenString3 = "null.gif";
        }

        if (estado) {
            sizeXFinal = "0px";
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
            sizeXFinal = "block";
            return false;
        }
    }

    public static boolean validar(String nombre, String apellido, String cedula, String expedida, String celular, String telefono, String correo, String direccion, String clave, Integer rol, Integer sexo) {
        //Validando...                        
        boolean estado = true;
        String mensaje = "";
        //Validaciones de que los campos no estén vacios.

        if (rol <= 0) {
            mensaje = "Debe seleccionar un rol";
            imagenInteger1 = "error.png";
            estado = false;
        } else {
            imagenInteger1 = "null.gif";
        }
        
        if (nombre.length() <= 2) {
            mensaje = (nombre.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString1 = "error.png";
            estado = false;
        } else {
            imagenString1 = "null.gif";
        }

        if (apellido.length() <= 2) {
            mensaje = (apellido.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString2 = "error.png";
            estado = false;
        } else {
            imagenString2 = "null.gif";
        }

         if (sexo <= 0) {
            mensaje = "Debe seleccionar un sexo.";
            imagenInteger2 = "error.png";
            estado = false;
        } else {
            imagenInteger2 = "null.gif";
        }
         
        if (cedula.length() <= 2) {
            mensaje = (cedula.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString3 = "error.png";
            estado = false;
        } else {
            imagenString3 = "null.gif";
        }

        if (celular.length() <= 2) {
            mensaje = (celular.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString5 = "error.png";
            estado = false;
        } else {
            imagenString5 = "null.gif";
        }

        if (correo.length() <= 2) {
            mensaje = (correo.length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString7 = "error.png";
            estado = false;
        } else {
            imagenString7 = "null.gif";
        }              
        
        if (estado) {
            sizeXFinal = "0px";
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
            sizeXFinal = "block";
            return false;
        }
    }

    public static boolean validar(Integer numeroRecibo, Asociado asociado, Integer idTipoPago, Date fechaUltimoPago, Date fechaPagoFinal, Integer valorPago) {
        boolean estado = true;
        String mensaje = "";
        //Validaciones de que los campos no estén vacios.

        if (numeroRecibo.toString().length() <= 2) {
            mensaje = (numeroRecibo.toString().length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenInteger1 = "error.png";
            estado = false;
        } else {
            imagenInteger1 = "null.gif";
        }

        if (asociado == null) {
            mensaje = (asociado == null) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenObjeto1 = "error.png";
            estado = false;
        } else {
            imagenObjeto1 = "null.gif";
        }

        if (idTipoPago <= 0) {
            mensaje = (idTipoPago <= 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenInteger2 = "error.png";
            estado = false;
        } else {
            imagenInteger2 = "null.gif";
        }
        if (fechaPagoFinal == null) {
            mensaje = (fechaPagoFinal == null) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenDate1 = "error.png";
            estado = false;
        } else {
            imagenDate1 = "null.gif";
            if (fechaPagoFinal.before(fechaUltimoPago)) {
                mensaje = "Fecha final es menor que la fecha del último pago";
                imagenDate4 = "error.png";
                estado = false;
            }
        }

        if (valorPago.toString().length() <= 2) {
            mensaje = (valorPago.toString().length() <= 2) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenInteger3 = "error.png";
            estado = false;
        } else {
            imagenInteger3 = "null.gif";
        }
        if (estado) {
            //Ejecutamos el método Guardar, Editar y/o lo que necesitemos.            
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
            sizeXFinal = "block";
            return false;
        }
    }

    public static boolean validar(Integer numeroConsig, Asociado asociado, Integer idConvenio, Integer idVehiculo, Date fechaUltimoPago, Date fechaInicio, Date fechaFin, Double valorConsignacion) {

        String mensaje = "hsd";
        boolean estado = true;
        if (numeroConsig.toString().length() <= 2) {
            mensaje = (numeroConsig.toString().length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenInteger1 = "error.png";
            estado = false;
        } else {
            imagenInteger1 = "null.gif";
        }

        if (asociado == null) {
            mensaje = "Debe filtrar y seleccionar un asociado.";
            imagenObjeto1 = "error.png";
            estado = false;
        } else {
            imagenObjeto1 = "null.gif";
        }

        if (idConvenio < 0) {
            mensaje = "Debe seleccionar un convenio";
            imagenInteger2 = "error.png";
            estado = false;
        } else {
            imagenInteger2 = "null.gif";
        }

        if (idVehiculo <= 0) {
            mensaje = "Debe seleccionar un vehículo";
            imagenInteger3 = "error.png";
            estado = false;
        } else {
            imagenInteger3 = "null.gif";
        }

        if (fechaFin == null) {
            mensaje = "Debe seleccionar una fecha.";
            imagenDate1 = "error.png";
            estado = false;
        } else {
            imagenDate1 = "null.gif";
            if (fechaFin.before(fechaUltimoPago)) {
                mensaje = "Fecha final es menor que la fecha del último pago";
                imagenDate4 = "error.png";
                imagenDate1 = "error.png";
                estado = false;
            }
        }
        if (valorConsignacion.toString().length() <= 2) {
            mensaje = (valorConsignacion.toString().length() == 2) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString1 = "error.png";
            estado = false;
        } else {
            imagenString1 = "null.gif";
        }

        if (estado) {
            //Ejecutamos el método Guardar, Editar y/o lo que necesitemos.            
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
            sizeXFinal = "block";
            return false;
        }
    }

    public static boolean validar(String nombre, String descripcion, Date fechaInicio, Date fechaFin) {
        String mensaje = "hsd";
        boolean estado = true;
        if (nombre.toString().length() <= 2) {
            mensaje = (nombre.toString().length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString1 = "error.png";
            estado = false;
        } else {
            imagenString1 = "null.gif";
        }
        if (descripcion.toString().length() <= 2) {
            mensaje = (descripcion.toString().length() == 0) ? "Se encontraron datos nulos, por favor corríjalos." : "Ha ingresado datos muy cortos, por favor corríjalos!.";
            imagenString2 = "error.png";
            estado = false;
        } else {
            imagenString2 = "null.gif";
        }
        if (fechaInicio == null) {
            mensaje = "Se encontraron datos nulos por favor corríjalos.";
            imagenDate1 = "error.png";
            estado = false;
        } else {
            imagenDate1 = "null.gif";
        }
        if (fechaFin == null) {
            mensaje = "Se encontraron datos nulos por favor corríjalos.";
            imagenDate2 = "error.png";
            estado = false;
        } else {
            imagenDate2 = "null.gif";
        }
        if (estado) {
            //Ejecutamos el método Guardar, Editar y/o lo que necesitemos.            
            return true;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
            sizeXFinal = "block";
            return false;
        }
    }

    //Métodos set y get.    
    public String getSizeXFinal() {
        return sizeXFinal;
    }

    public void setSizeXFinal(String sizeXFinal) {
        ValidarFormularios.sizeXFinal = sizeXFinal;
    }

    public Integer getSizeX() {
        return sizeX;
    }

    public void setSizeX(Integer sizeX) {
        ValidarFormularios.sizeX = sizeX;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public void setSizeY(Integer sizeY) {
        ValidarFormularios.sizeY = sizeY;
    }

    public String getImagenString1() {
        return imagenString1;
    }

    public void setImagenString1(String imagenString1) {
        this.imagenString1 = imagenString1;
    }

    public String getImagenString2() {
        return imagenString2;
    }

    public void setImagenString2(String imagenString2) {
        this.imagenString2 = imagenString2;
    }

    public String getImagenString3() {
        return imagenString3;
    }

    public void setImagenString3(String imagenString3) {
        this.imagenString3 = imagenString3;
    }

    public String getImagenString4() {
        return imagenString4;
    }

    public void setImagenString4(String imagenString4) {
        this.imagenString4 = imagenString4;
    }

    public String getImagenString5() {
        return imagenString5;
    }

    public void setImagenString5(String imagenString5) {
        this.imagenString5 = imagenString5;
    }

    public String getImagenString6() {
        return imagenString6;
    }

    public void setImagenString6(String imagenString6) {
        this.imagenString6 = imagenString6;
    }

    public String getImagenString7() {
        return imagenString7;
    }

    public void setImagenString7(String imagenString7) {
        this.imagenString7 = imagenString7;
    }

    public String getImagenString8() {
        return imagenString8;
    }

    public void setImagenString8(String imagenString8) {
        this.imagenString8 = imagenString8;
    }

    public String getImagenString9() {
        return imagenString9;
    }

    public void setImagenString9(String imagenString9) {
        this.imagenString9 = imagenString9;
    }

    public String getImagenString10() {
        return imagenString10;
    }

    public void setImagenString10(String imagenString10) {
        this.imagenString10 = imagenString10;
    }

    public String getImagenDate1() {
        return imagenDate1;
    }

    public void setImagenDate1(String imagenDate1) {
        this.imagenDate1 = imagenDate1;
    }

    public String getImagenDate2() {
        return imagenDate2;
    }

    public void setImagenDate2(String imagenDate2) {
        this.imagenDate2 = imagenDate2;
    }

    public String getImagenDate3() {
        return imagenDate3;
    }

    public void setImagenDate3(String imagenDate3) {
        this.imagenDate3 = imagenDate3;
    }

    public String getImagenDate4() {
        return imagenDate4;
    }

    public void setImagenDate4(String imagenDate4) {
        this.imagenDate4 = imagenDate4;
    }

    public String getImagenInteger1() {
        return imagenInteger1;
    }

    public void setImagenInteger1(String imagenInteger1) {
        this.imagenInteger1 = imagenInteger1;
    }

    public String getImagenInteger2() {
        return imagenInteger2;
    }

    public void setImagenInteger2(String imagenInteger2) {
        this.imagenInteger2 = imagenInteger2;
    }

    public String getImagenInteger3() {
        return imagenInteger3;
    }

    public void setImagenInteger3(String imagenInteger3) {
        this.imagenInteger3 = imagenInteger3;
    }

    public String getImagenInteger4() {
        return imagenInteger4;
    }

    public void setImagenInteger4(String imagenInteger4) {
        this.imagenInteger4 = imagenInteger4;
    }

    public String getImagenObjeto1() {
        return imagenObjeto1;
    }

    public void setImagenObjeto1(String imagenObjeto1) {
        this.imagenObjeto1 = imagenObjeto1;
    }
}
