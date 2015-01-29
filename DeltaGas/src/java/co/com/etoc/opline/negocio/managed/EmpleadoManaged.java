/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.EnvioCorreo;
import co.com.etoc.opline.persistencia.dao.HistorialEstadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.InformacionEmpresaFacadeLocal;
import co.com.etoc.opline.persistencia.dao.OpcionFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.Estado;
import co.com.etoc.opline.persistencia.entidades.Rol;
import co.com.etoc.opline.persistencia.entidades.HistorialEstado;
import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import co.com.etoc.opline.persistencia.entidades.Menu;
import co.com.etoc.opline.persistencia.entidades.Opcion;
import co.com.etoc.opline.persistencia.entidades.Sexos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import co.com.etoc.opline.utilerias.UtilOne;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.FacesException;
import javax.faces.bean.ManagedProperty;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class EmpleadoManaged extends ValidaSesion implements Serializable {

    private RolesManaged lr = new RolesManaged();
    private List<Empleado> listaEmpleado;
    private List<Empleado> filtroEmpleado;
    private List<Sexos> listaSexos;

    @EJB
    private EmpleadoFacadeLocal localEmpleado;
    private HistorialEstadoFacadeLocal historialEstadoFL;

    @EJB
    private InformacionEmpresaFacadeLocal informacionFL;

    //Atributos referentes a la BD. --> ARBD
    Rol idRol;
    Integer rol;
    Integer activo;
    Integer idSelect;
    private String nombre;
    private String apellido;
    private String cedula;
    private String expedida;
    private String celular;
    private String telefono;
    private String correo = "@";
    private String direccion;
    private String clave;
    private String clave2;
    private Empleado datos;
    private Empleado datos2;
    private String listarPor;
    private String foto;
    private String visibilidadBotonEditar;
    private Date fechaNacimiento;
    private Integer sexo;
    private Integer sexoEditar;

    private Integer rolEdit;
    private String iconoAyD;
    private String tituloAyD;
    private String smsAyD;
    private Integer estadoAyD;
    private String justificacion;
    private Integer rolActual = 2;
    private boolean completo;
    private boolean completoEditar;
    private List<Menu> menu;
    private File archivo;
    private boolean seEditaraClave;

    @EJB
    private OpcionFacadeLocal opcionFL;

    //Fin atributos ARBD.
    @PostConstruct
    //Se inicializa la lista que leerá la vista(JSF).
    public void init() {
//        PrincipalManagedBean pmb = new PrincipalManagedBean();
//        pmb.setRutaActual("Empleados\\Ver Registros");           
        this.predeterminarImagen();
        this.completo = true;
        this.completoEditar = true;
        listaEmpleado = localEmpleado.activos();
        if (listaEmpleado.size() != 0) {
            datos = listaEmpleado.get(0);
        } else {
            listaEmpleado = localEmpleado.deshabilitados();
            datos = listaEmpleado.get(0);
        }
        this.listarPor = "Activos";
        this.listarPorPreferencia();
//        }
//        }catch(Exception e){}
    }

    public void predeterminarImagen() {
        this.foto = "imagenesRegistros/fotosEmpleados/perfilPredeterminado.png";
    }

    public String antiguedad() {
        return UtilOne.diferencia(this.datos.getFechaRegistro());
    }

    public String listarPorPreferencia() {
        try {
            if (listarPor.equals("Activos")) {
                listaEmpleado = localEmpleado.activos();
                this.visibilidadBotonEditar = "display";
                this.iconoAyD = "ui-icon-circle-close";
                this.tituloAyD = "Deshabilitar";
                this.smsAyD = "Al deshabilitar el registro quedará invisible "
                        + "ante el sistema para las futuras consultas. "
                        + "¿Está seguro de realizar este cambio?";
                this.estadoAyD = 2;
            } else {
                listaEmpleado = localEmpleado.deshabilitados();
                this.visibilidadBotonEditar = "none";
                this.iconoAyD = "ui-icon-circle-check";
                this.tituloAyD = "Habilitar";
                this.smsAyD = "Al habilitar el registro quedará visible "
                        + "ante el sistema para las futuras consultas. "
                        + "¿Está seguro de realizar este cambio?";
                this.estadoAyD = 1;
            }
        } catch (Exception e) {
            System.out.println("Error listarPorPreferencia " + e.getMessage());
        }
        return "empleado.xhtml";
    }

    //Método para guardar/insertar
    public String guardar() {
        //En esta variable se guardará el mensaje que se imprime como respuesta.        
        try {
            //Se crea un objeto que almacenará los datos recogidos en la vista.
            Empleado empleado = new Empleado();
            //Se asignan los datos de la vista al objeto creado anteriormente.
            empleado.setIdRol(new Rol(rol));
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setCedula(cedula);
            empleado.setExpedida(expedida);
            empleado.setCelular(celular);
            empleado.setTelefono(telefono);
            empleado.setCorreo(correo);
            empleado.setDireccion(direccion);
            empleado.setFoto(foto);
            empleado.setSexo(sexo);
            empleado.setFechaNacimiento(fechaNacimiento);
            empleado.setFechaRegistro(new Date());
            clave = ((Long) System.currentTimeMillis()).toString();
            empleado.setClave(UtilOne.md5(clave));
            if (this.enviarContraseña(this.correo, this.clave, this.cedula)) {
                empleado.setIdEstado(new Estado(1));
            } else {
                empleado.setIdEstado(new Estado(2));
            }
//            empleado.setClave(UtilOne.md5(clave));

            //Se inserta el nuevo registro en la BD.
            this.localEmpleado.create(empleado);
            //Se asigna el mensaje de éxito de la operación en la variable mensaje.            
            //Se alimenta la lista, para que la vista pueda mostrar el nuevo empleado agregado. 
            this.limpiar();
        } catch (Exception e) {
            //Se asigna el mensaje de error de la operación en la variable mensaje.            
        }
        //Se lanza el mensaje.        
        return "empleado.xhtml";
    }

    public void limpiar() {
        rol = -1;
        nombre = "";
        apellido = "";
        cedula = "";
        expedida = "";
        celular = "";
        telefono = "";
        correo = "@";
        direccion = "";
        clave = "";
        this.init();
        new ValidarFormularios().init();
        this.predeterminarImagen();
    }

    public void prepararEdicion(Empleado empleado) {
        rolEdit = empleado.getIdRol().getIdRol();
        sexoEditar = empleado.getSexo();
        foto = empleado.getFoto();
        datos = empleado;
    }

    public String editar() {
        String mensaje;
        try {
            datos.setIdRol(new Rol(rolEdit));
            datos.setSexo(sexoEditar);
            localEmpleado.edit(datos);
            mensaje = "Registro editado satisfactoriamente.";
            this.limpiar();
        } catch (Exception e) {
            System.out.println("Error en EmpleadoManaged al eliminar " + e.getMessage());
            mensaje = "No se pudo deshabilitar el registro.";
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "empleado.xhtml";
    }

    public void cambiarClave() throws Exception {
        this.seEditaraClave = true;
        String claveEnviar = ((Long) System.currentTimeMillis()).toString();
        if (UtilOne.validarConexion()) {
            this.enviarContraseña(datos.getCorreo(), claveEnviar, datos.getCedula());
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sin Conexión para modificar y enviar una contraseña nueva.", ""));
        }
    }

    public void prepareDatos(Empleado empleado) {
        this.listarPorPreferencia();
        this.datos2 = empleado;
    }

    //Metodo deshabilitar.
    public String deshabilitar() {
        String mensaje;
        try {
            datos2.setIdEstado(new Estado(this.estadoAyD));
            localEmpleado.edit(datos2);
            listaEmpleado = localEmpleado.activos();
            if (this.estadoAyD == 2) {
                this.listarPor = "Activos";
                mensaje = "Registro deshabilitado satisfactoriamente.";
            } else {
                this.listarPor = "Deshabilitados";
                mensaje = "Registro habilitado satisfactoriamente.";
            }
        } catch (Exception e) {
            System.out.println("Error en EmpleadoManaged al deshabilitar " + e.getMessage());
            mensaje = "No se pudo deshabilitar el registro.";
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        listaEmpleado = localEmpleado.activos();
        return "empleado.xhtml";
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
                if (datos != null) {
                    datos.setFoto(foto);
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

    //Captura de imagen desde webCam.
    public void capturarImagen(CaptureEvent captureEvent) {
        String nombreImagen = System.currentTimeMillis() + "Captura";
        byte[] data = captureEvent.getData();

        foto = "imagenesRegistros/fotosEmpleados/" + nombreImagen + ".png";

        if (datos != null) {
            datos.setFoto(foto);
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

    public boolean enviarContraseña(String correoReceptor, String claveNueva, String documento) { //true = Creación empleado // false = edición.
        //Consultando informanción empresa.
        InformacionEmpresa informacion = null;
        try {
            informacion = informacionFL.findAll().get(0);
        } catch (Exception e) {
            System.out.println("Error consultando información empresa: " + e.getMessage());
        }

        try {
            if (UtilOne.validarConexion()) {
                EnvioCorreo mail = new EnvioCorreo();

                DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
                String fechaGeneracion = df.format(new Date());
                String mensaje = ""
                        + "<div style=\"font-size: 14px; color: #444444;\">"
                        + "Cordial saludo! se ha creado un rol con sus datos para ingresar en el sistema OPline,"
                        + " este mensaje es proporcionado para dar seguridad inmediata de su cuenta mediante "
                        + "la generación de clave automatica, ingrese al sistema con los siguientes datos; y una vez "
                        + "haya entrado al sistema cambie la clave por una nueva."
                        + "<br/>"
                        + "<br/>"
                        + "<b>Usuario:</b> " + documento + ".<br/>"
                        + "<b>Contraseña nueva:</b> " + claveNueva + ".<br/><br/>"
                        + "<b>Fecha de generación:</b> " + fechaGeneracion + "."
                        + "</div>";
//\"cid:image\"
                String firma = "<br/><br/><br/>"
                        + "<div style=\"float: left; width: 200px; height: 100px; text-align:center; "
                        + "\">"
                        + "<img src=\"cid:image\" style=\"width:200px; height:80px; margin-top:6%\"/>"
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
                String asunto = "Opline - Información de ingreso para su nueva cuenta.";
                String tituloContenido = "<H1>Información de cuenta registrada</H1>";
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String rutaArchivo = servletContext.getRealPath("") + File.separator + "imagenes"
                        + File.separator;
                mail.envioCorreo(tituloContenido, correoReceptor, informacion.getCorreo(), informacion.getClaveCorreo(), asunto, mensaje, firma, rutaArchivo, nombreArchivo);
                try {
                    claveNueva = UtilOne.md5(claveNueva); //Encriptamos la clave.
                    datos.setClave(claveNueva);
                    localEmpleado.edit(datos);
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Clave actualizada y enviada satisfactoriamente!.",
                                    "Clave actualizada y enviada satisfactoriamente!."));
                } catch (Throwable error) {
                    System.out.println("Error enviando clave empleadoManaged: " + error.getMessage());
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "No se pudo actualizar la clave.",
                                    "No se pudo actualizar la clave."));
                }
                if (!seEditaraClave) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado creado satisfactoriamente.",
                                    "Empledo creado satisfactoriamente."));
                }
                return true;
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "SIN CONEXIÓN - El usuario fue creado pero se ha deshabilitado por que no se pudo enviar la clave, para habilitarlo hagalo de manera común y en la opción editar precione el botón Cambiar clave para enviar una nueva.",
                                "SIN CONEXIÓN - El usuario fue creado pero se ha deshabilitado por que no se pudo enviar la contraseña, para habilitarlo hagalo de manera común y en la opción editar precione el botón Cambiar clave."));
                return false;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se produjo un error desconocido por favor contacte el administrador!.",
                            "Se produjo un error desconocido por favor contacte el administrador!."));
            System.out.println("Error enviando mail" + e.getMessage());
            return false;
        }
    }

    public void eliminarArchivo() {
        try {
            archivo.delete();
        } catch (Exception e) {
            System.out.println("No se pudo eliminar el archivo.");
        }
    }

    //Dejará la lista empleado visible para la vista(JSF).
    public List<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    //Filtro.
    public List<Empleado> getFiltroEmpleado() {
        return filtroEmpleado;
    }

    public void setFiltroEmpleado(List<Empleado> filtroEmpleado) {
        this.filtroEmpleado = filtroEmpleado;
    }

    //--------------------------------------------------------------------------
    //Metodos GET & SET para atributos referentes a la BD.
    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getCorreo() {
        return correo;
    }

    public String getVisibilidadBotonEditar() {
        return visibilidadBotonEditar;
    }

    public void setVisibilidadBotonEditar(String visibilidadBotonEditar) {
        this.visibilidadBotonEditar = visibilidadBotonEditar;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getExpedida() {
        return expedida;
    }

    public void setExpedida(String expedida) {
        this.expedida = expedida;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    //Validaciones
    public void validar() {
        if (localEmpleado.comprobarDocumentoRepetido(cedula)) {
            completo = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de documento se encuentra registrado en la Base de datos.", "El número de documento ya se encuentra registrado."));
        } else {
            completo = true;
        }
        if (completo) {
            this.completo = ValidarFormularios.validar(nombre, apellido, cedula, expedida, celular, telefono, correo, direccion, clave, rol, sexo);
        }
        if (completo) {
            this.guardar();
        }
    }

    public void validarEditar() {
        if (completoEditar) {
            this.completoEditar = ValidarFormularios.validar(datos.getNombre(), datos.getApellido(), datos.getCedula(), datos.getExpedida(), datos.getCelular(), datos.getTelefono(), datos.getCorreo(), datos.getDireccion(), datos.getClave(), this.rolEdit, this.sexoEditar);
        }
        if (completoEditar) {
            this.editar();
        }
    }

//    public void validarParaEdicion() {
//        datos.setIdRol(new Rol(rolEdit));
//        this.completoEditar = ValidarFormularios.validar(datos.getNombre(), datos.getApellido(), datos.getCedula(), datos.getExpedida(), datos.getCelular(), datos.getTelefono(), datos.getCorreo(), datos.getDireccion(), datos.getClave(), datos.getIdRol().getIdRol());
//        if (completoEditar) {
//            this.editar();
//        }
//    }
    //Fin metodos SET & GET
    //--------------------------------------------------------------------------
    public Empleado getDatos() {
        return datos;
    }

    public void setDatos(Empleado datos) {
        this.datos = datos;
    }

    public String getListarPor() {
        return listarPor;
    }

    public void setListarPor(String listarPor) {
        this.listarPor = listarPor;
    }

    public String getIconoAyD() {
        return iconoAyD;
    }

    public void setIconoAyD(String iconoAyD) {
        this.iconoAyD = iconoAyD;
    }

    public String getTituloAyD() {
        return tituloAyD;
    }

    public void setTituloAyD(String tituloAyD) {
        this.tituloAyD = tituloAyD;
    }

    public String getSmsAyD() {
        return smsAyD;
    }

    public void setSmsAyD(String smsAyD) {
        this.smsAyD = smsAyD;
    }

    public Integer getEstadoAyD() {
        return estadoAyD;
    }

    public void setEstadoAyD(Integer estadoAyD) {
        this.estadoAyD = estadoAyD;
    }

    public Integer getIdSelect() {
        return idSelect;
    }

    public void setIdSelect(Integer idSelect) {
        this.idSelect = idSelect;
    }

    public Integer getRolEdit() {
        return rolEdit;
    }

    public void setRolEdit(Integer rolEdit) {
        this.rolEdit = rolEdit;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public boolean isCompletoEditar() {
        return completoEditar;
    }

    public void setCompletoEditar(boolean completoEditar) {
        this.completoEditar = completoEditar;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Integer getSexoEditar() {
        return sexoEditar;
    }

    public void setSexoEditar(Integer sexoEditar) {
        this.sexoEditar = sexoEditar;
    }

}
