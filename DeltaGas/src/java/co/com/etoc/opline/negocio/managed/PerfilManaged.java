/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.utilerias.UtilOne;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class PerfilManaged extends ValidaSesion implements Serializable {

    private File archivo;
    private String foto;
    private Empleado datosEmpleado;
    private String clave;
    private String claveNueva;
    private String claveConfirmada;

    //Atributos para validaciones.
    private boolean completoEditar;

    @EJB
    private EmpleadoFacadeLocal empleadoFL;

    @PostConstruct
    public void init() {
        PrincipalManagedBean pmb = new PrincipalManagedBean();
        pmb.setRutaActual("Configuración\\Mi perfil");
        foto = "imagenesSistema/perfilPredeterminado.png";
        completoEditar = true;
        datosEmpleado = empleado;
    }

    //Subir foto...
    public void subirImagen(FileUploadEvent event) {
        try {
            if (event.getFile() != null) {
                String nombreArchivo = System.currentTimeMillis() + event.getFile().getFileName();
                int tamanio = (int) event.getFile().getSize();
                byte[] data = new byte[tamanio];
                InputStream in = event.getFile().getInputstream();
                while ((in.read(data, 0, tamanio)) != -1) {
                }

                //Se obtiene la ruta real del archivo.
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String rutaArchivo = servletContext.getRealPath("") + File.separator + "imagenesRegistros" + File.separator + "fotosEmpleados"
                        + File.separator + nombreArchivo;
                //Se parametriza el objeto archivo con la ruta antes consultada
                //para una futura eliminación exitosa.
                archivo = new File(rutaArchivo);

                nombreArchivo = imagenTemporal(data, nombreArchivo);
                foto = nombreArchivo;
                //Si datos es diferente de null se le pasará la nueva ruta de la imagen
                //que previamente se ha subido para cuando se edite el registro tome
                //la imagen que se desea.                
                if (datosEmpleado != null) {
                    datosEmpleado.setFoto(foto);
                }
                FacesMessage message = new FacesMessage("Exito", event.getFile().getFileName() + " Fue subido.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error de conexión.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String imagenTemporal(byte[] bytes, String nombreArchivo) {
        String imagen = "";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("imagenesRegistros/fotosEmpleados") + "/" + nombreArchivo;
        File f = null;
        InputStream in = null;
        try {
            f = new File(path);
            in = new ByteArrayInputStream(bytes);
            FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
            int c = 0;
            while ((c = in.read()) >= 0) {
                out.write(c);
            }
            out.flush();
            out.close();
            imagen = "imagenesRegistros/fotosEmpleados/" + nombreArchivo;
        } catch (Exception e) {
            System.out.println("No se puede cargar la imagen");
        }
        return imagen;
    }

    /**
     * En este método se edita la clave del usuario, aquí hacemos las respectivas
     * validaciones y demás cosas necesarias.
     */
    public void editarClave() {
        try {
            if (this.datosEmpleado.getClave().equals(UtilOne.md5(this.clave))) {
                if (this.claveNueva.equals(this.claveConfirmada)) {
                    if (this.claveNueva.length() > 0) {
                        this.datosEmpleado.setClave(UtilOne.md5(this.claveNueva)); //Se fija la clave en el objeto que vamos a enviar.
                        this.empleadoFL.edit(this.datosEmpleado); //Se edita el registro.
                        this.init();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Clave actualizada correctamente!.", "Clave actualizada correctamente!."));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No ingresó una clave!"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las claves no coinciden.", "Las claves no coinciden."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Clave ingresada no válida!.", "Clave ingresada no válida!."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo editar la clave.", "No se pudo editar la clave."));
        }
    }

    //Captura de imagen desde webCam.
    public void capturarImagen(CaptureEvent captureEvent) {
        String nombreImagen = System.currentTimeMillis() + "Captura";
        byte[] data = captureEvent.getData();

        foto = "imagenesRegistros/fotosEmpleados/" + nombreImagen + ".png";

        if (datosEmpleado != null) {
            datosEmpleado.setFoto(foto);
        }
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String rutaArchivo = servletContext.getRealPath("") + File.separator + "imagenesRegistros"
                + File.separator + "fotosEmpleados" + File.separator + nombreImagen + ".png";

        FileImageOutputStream imageOutput;
        try {
            archivo = new File(rutaArchivo);
            imageOutput = new FileImageOutputStream(archivo);
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (IOException e) {
            throw new FacesException("Error creando imagen capturada.", e);
        }
    }

    public void eliminarArchivo() {
        try {
            archivo.delete();
        } catch (Exception e) {
            System.out.println("No se pudo eliminar el archivo.");
        }
    }

    //Editamos la información que ha ingresado el usuario.
    public void editar() {
        try {
            this.empleadoFL.edit(datosEmpleado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Datos actualizados correctamente."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se pudo editar su información."));
            System.out.println("Error PerfilManaged.editar() - al editar registro.");
        }
    }

    //Validamos que el formulario no tenga datos nulos.
    public void validarEditar() {
        this.completoEditar = ValidarFormularios.validar(datosEmpleado.getNombre(), datosEmpleado.getApellido(), datosEmpleado.getCedula(), datosEmpleado.getExpedida(), datosEmpleado.getCelular(), datosEmpleado.getTelefono(), datosEmpleado.getCorreo(), datosEmpleado.getDireccion(), datosEmpleado.getClave(), datosEmpleado.getIdRol().getIdRol(),datosEmpleado.getSexo());
        if (completoEditar) {
            this.editar();
        }
    }

    public void limpiar() {
        new ValidarFormularios().init();
        this.clave = "";
        this.claveNueva = "";
        this.claveConfirmada = "";
    }

    //Métodos set y get.    
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Empleado getDatosEmpleado() {
        return datosEmpleado;
    }

    public void setDatosEmpleado(Empleado datosEmpleado) {
        this.datosEmpleado = datosEmpleado;
    }

    public boolean isCompletoEditar() {
        return completoEditar;
    }

    public void setCompletoEditar(boolean completoEditar) {
        this.completoEditar = completoEditar;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveConfirmada() {
        return claveConfirmada;
    }

    public void setClaveConfirmada(String claveConfirmada) {
        this.claveConfirmada = claveConfirmada;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

}
