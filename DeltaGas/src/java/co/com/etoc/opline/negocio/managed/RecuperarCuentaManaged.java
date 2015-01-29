/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.EnvioCorreo;
import co.com.etoc.opline.persistencia.dao.InformacionEmpresaFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import co.com.etoc.opline.utilerias.UtilOne;
import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped

public class RecuperarCuentaManaged implements Serializable {
    //No extiende de la clase ValidarSesion debido a que si lo hacemos
    //cuando redireccionemos a la página nos renornará el login y 
    //el usuario nunca podría acceder a recuperar la cuenta.

    //String cedula, String correo, String celular
    private String cedula;
    private String correo;
    private String celullar;

    @EJB
    private EmpleadoFacadeLocal empleadoFL;

    @EJB
    private InformacionEmpresaFacadeLocal informacionFL;

    @EJB
    private EmpleadoFacadeLocal empleado2FL;

    public void recuperarCuenta() {
        //Consultando informanción empresa.
        InformacionEmpresa informacion = null;
        try {
            informacion = informacionFL.findAll().get(0);
        } catch (Exception e) {
            System.out.println("Error consultando información empresa: " + e.getMessage());
        }
        Empleado empleado = empleadoFL.validarDatos(cedula, correo, celullar);

        try {
            if (UtilOne.validarConexion()) {
                if (empleado != null) {
                    EnvioCorreo mail = new EnvioCorreo();
                    String claveNueva = "" + System.currentTimeMillis();
                    DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
                    String fechaGeneracion = df.format(new Date());
                    String mensaje = ""
                            + "<div style=\"font-size: 14px; color: #444444;\">"
                            + "Cordial saludo! para la recuperación de su cuenta, el "
                            + "sistema Opline ha generado una contraseña automática con"
                            + " la cual podrá ingresar a partir de estos momentos, tenga"
                            + " en cuenta que por seguridad es importante el cambio de "
                            + "su contraseña después de ingresar al sistema con la clave"
                            + " que se ha generado mediante esta solicitud.."
                            + "<br/>"
                            + "<br/>"
                            + "<b>Contraseña nueva</b>: " + claveNueva + ".<br/>"
                            + "<b>Fecha de generación:</b> " + fechaGeneracion + "."
                            + "</div>";
//\"cid:image\"
                    String firma = "<br/><br/><br/>"
                            + "<div style=\"float: left; width: 200px; height: 100px; text-align:center; "
                            + "\">"
                            + "<img src=\"cid:image\" style=\"width:auto; height:80px; margin-top:6%\"/>"
                            + "</div>"
                            + "<div style=\"float: left; width: 300px; height: 100px; border-left: 2px "
                            + "solid #0097CC; padding-left: 20px; padding-top:10px;\">"
                            + "<font size=\"2\" >"
                            + "<b>Opline </b>"
                            + "Sistema de información."
                            + "<br/>"
                            + "<b>" + informacion.getNombre() + ".</b>"
                            + "<br/>"
                            + informacion.getGerente() + " - Gerente"
                            + "<br/>"
                            + "Móvil: " + informacion.getCelular()
                            + "<br/>"
                            + "Teléfono: " + informacion.getTelefono()
                            + "<br/>"
                            + informacion.getDireccion() + ", " + informacion.getCiudad() + ", " + informacion.getPais()
                            + "</font>"
                            + "<div>";

                    //Se obtiene la ruta real del archivo.
                    String nombreArchivo = "logoOpline.png";                    
                    String asunto = "Opline - Generación de contraseña automatica para recuperación de cuenta.";
                    String tituloContenido = "<H1>Recuperación de contraseña</H1>";
                    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                    String rutaArchivo = servletContext.getRealPath("") + File.separator + "imagenes"
                            + File.separator;
                    mail.envioCorreo(tituloContenido, correo, informacion.getCorreo(), informacion.getClaveCorreo(), asunto, mensaje, firma, rutaArchivo, nombreArchivo);

                    try {
                        claveNueva = UtilOne.md5(claveNueva); //Encriptamos la contraseña
                        empleado.setClave(claveNueva);
                        empleado2FL.edit(empleado);
                    } catch (Exception e) {
                        System.out.println("Error al editar clave en la BD: " + e.getMessage());
                    }
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha enviado una contraseña nueva a tu correo electronico!.",
                                    "Se ha enviado una contraseña nueva a tu correo electronico!."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los datos ingresados son invalidos!.",
                                    "Los datos ingresados son invalidos!."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "SIN CONEXIÓN - Compruebe su conexión a internet!.",
                                "SIN CONEXIÓN - Compruebe su conexión a internet!."));
            }
        } catch (Exception e) {
            System.out.println("Error enviando mail");
        }
    }

    public String volverAtras() {
        return "login.xhtml";
    }

    public void limpiar() {
        this.correo = "";
        this.cedula = "";
        this.celullar = "";
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelullar() {
        return celullar;
    }

    public void setCelullar(String celullar) {
        this.celullar = celullar;
    }
}
