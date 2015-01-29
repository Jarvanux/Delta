/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.EnvioCorreo;
import co.com.etoc.opline.persistencia.dao.InformacionEmpresaFacadeLocal;
import co.com.etoc.opline.persistencia.dao.ReunionFacadeLocal;
import co.com.etoc.opline.persistencia.dao.VehiculoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import co.com.etoc.opline.persistencia.entidades.Reunion;
import co.com.etoc.opline.persistencia.entidades.Vehiculo;
import co.com.etoc.opline.utilerias.UtilOne;
import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Steven
 */
@ManagedBean
@ViewScoped
public class ReunionManaged extends ValidaSesion implements Serializable {

    private List<Reunion> listaReuniones;
    private List<Reunion> filtroReuniones;

    @EJB

    //Datos de la reunión.
    private ReunionFacadeLocal localReunion;
    private String estadoReunion;
    private String descripcion;
    private Date fechaReunion;
    private String lugar;
    private String nombre;
    private Reunion reunion;
//    private InformacionEmpresa informacion;

    //Atributos para la gestión de asistencia - Conductores.
    private Vehiculo vehiculo;
    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosSeleccionados;
    private List<Vehiculo> vehiculosAgregados;
    private List<Vehiculo> vehiculosFiltrados;
    private Integer numeroRegistros;
    private Integer idZona;
    private Integer correosEnviados;

    //Atributos para l gestión de asistencia - Empleados.
    private List<Empleado> listaEpleados;
    private List<Empleado> filtroEmpleado;
    private List<Empleado> empleadosSeleccionados;
    private List<Empleado> empleadosAgregados;
    private Integer numeroRegistros2;
    private Integer idEstado;
    private String mensaje;
    private boolean enviarParaAsociados;

//    Atributos extras
    private String mensajeEdicion;
    private boolean completo;
    private boolean completoEditar;

    @EJB
    private VehiculoFacadeLocal vehiculoFL;

    @EJB
    private EmpleadoFacadeLocal empleadoFL;

    @EJB
    private InformacionEmpresaFacadeLocal informacionFL;

    @PostConstruct
    public void init() {
        PrincipalManagedBean pmb = new PrincipalManagedBean();
        estadoReunion = "Activa";
        listaReuniones = localReunion.findAll();
        this.completo = true;
        this.completoEditar = true;
        ValidarFormularios v = new ValidarFormularios();
        //Se ejecuta el método init de la calase ValidarFormularios para que todas
        //las rutas de imagenes hagan relación a la imagen null para que no 
        //se vea la imagen error cuando se muestre un formulario nuevo.
        v.init();
        if (listaReuniones.size() > 0) {
            reunion = listaReuniones.get(0);
        }
        this.idZona = 1;
        this.idEstado = 1;
        listarPor();
        listarPorEstado();
        this.vehiculosAgregados = new ArrayList<Vehiculo>();
        this.empleadosAgregados = new ArrayList<Empleado>();
        this.fijarMensaje();
        this.informacionEmpresa();
    }

    public void guardarReunion() {
        try {
            Reunion datos = new Reunion();
            datos.setEstadoReunion(estadoReunion);
            datos.setDescripcion(descripcion);
            datos.setFechaReunion(fechaReunion);
            datos.setLugar(lugar);
            datos.setNombre(nombre);
            localReunion.create(datos);
            this.correosEnviados = 0;
            this.enviarCorreosEmpleados();
            this.enviarCorreosVehiculos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reunión creada y mensajes enviados éxitosamente.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar reunión.", ""));
        }
    }

    public void guardarCambios() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("#" + empleadosSeleccionados));
    }

    public void prepararEdicion(Reunion reunion) {
        this.reunion = reunion;
    }

    public String editar() {
        try {
            localReunion.edit(reunion);
            this.enviarCorreosEmpleados();
            this.enviarCorreosVehiculos();
        } catch (Exception e) {
            System.out.println("Error ReunionManaged: " + e.getMessage());
        }
        return "reunion.xhtml";
    }

    public void limpiar() {
        nombre = "";
        estadoReunion = "";
        descripcion = "";
        fechaReunion = null;
        lugar = "";
        this.init();
    }

    public void fijarMensaje() {
        this.mensaje = "Cordial saludo! la gerencia ETOC ha creado una reunión "
                + "a la cual usted ha sido invitado y es de carácter obligatorio "
                + "asistir, por favor presentarse la fecha y el lugar fijados en "
                + "la parte inferior y final de este mensaje.\n"
                + "\n"
                + "Gracias.";
        this.mensajeEdicion = this.mensaje;
    }

    /**
     * listarPor será el método controlado por el ajax en el componente
     * p:selectOneMenu que parametrizará los datos de la lista mostrada en la
     * tabla checkVehiculos cuando se seleccione una zona en el mismo
     * combobox(p:selectOneMenu).
     */
    public void listarPor() {
        vehiculos = vehiculoFL.vehiculosPorZona(idZona);
        if (vehiculos.size() > 0) {
            this.numeroRegistros = vehiculos.size();
        } else {
            this.numeroRegistros = 0;
        }
    }

    public void listarPorEstado() {
        if (this.idEstado == 1) {
            this.listaEpleados = empleadoFL.activos();
            this.numeroRegistros2 = listaEpleados.size();
        } else {
            this.listaEpleados = empleadoFL.deshabilitados();
            this.numeroRegistros2 = listaEpleados.size();
        }
    }

    /**
     * agregarALista() - Este método nos permitirá guardar todos los conductores
     * agregados, en este caso se agregarán objetos de la clase vehículo, debido
     * a que este Bean es una copia con algunas alteraciones del
     * DeshabilitarZonaManaged.
     */
    public void agregarAListaVehiculos() {
        if (vehiculosSeleccionados.size() > 0) {
            for (int i = 0; i < this.vehiculosSeleccionados.size(); i++) {
                // Se evalua que no se haya seleccionado dos veces el mismo vehículo, pues
                //al cambiar de zona los vehículos seleccionados no permanecerán seleccionados
                //por lo tanto es posible que el usuario los repita.
                if (!vehiculosAgregados.contains(vehiculosSeleccionados.get(i))) {
                    vehiculosAgregados.add(vehiculosSeleccionados.get(i));
                }//Fin primera condición                                   
            }//Fin for 1 primario.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registros Agregados"));
            System.out.println(vehiculosAgregados);
        }//Fin condición global.
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Ningun registro seleccionado!."));
        }
    }

    public void agregarAListaEmpleados() {
        if (this.empleadosSeleccionados.size() > 0) {
            for (int i = 0; i < this.empleadosSeleccionados.size(); i++) {
                if (!this.empleadosAgregados.contains(this.empleadosSeleccionados.get(i))) {
                    this.empleadosAgregados.add(this.empleadosSeleccionados.get(i));
                }//Fin segunda condición interna.
            }//Fin for.
        }//Fin primeara condición.
        System.out.println(this.empleadosAgregados);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registros Agregados"));
    }//Fin método agregarAListaEmpleados().

    public void enviarCorreosVehiculos() {
        if (UtilOne.validarConexion()) {
            if (this.vehiculosAgregados.size() > 0) {
                for (int i = 0; i < vehiculosAgregados.size(); i++) {
                    if (this.enviarParaAsociados) {
                        this.enviarCorreos(this.vehiculosAgregados.get(i).getIdAsociado().getCorreo());
                    }
                    if (this.enviarCorreos(this.vehiculosAgregados.get(i).getNic().getCorreo())) {
                        this.correosEnviados++;
                    }
                }
            }
        }
    }//Fin método.

    public void enviarCorreosEmpleados() {
        if (UtilOne.validarConexion()) {
            if (this.empleadosAgregados.size() > 0) {
                for (int i = 0; i < empleadosAgregados.size(); i++) {
                    try {
                        if (this.enviarCorreos(this.empleadosAgregados.get(i).getCorreo())) {
                            this.correosEnviados++;
                        }
                    } catch (Throwable e) {
                        System.out.println("Error enviando correos Empleados: " + e.getMessage());
                    }
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "SIN CONEXIÓN - La operación fue un exito pero no se enviaron notificaciones para ningún usuario, por problemas de conexión.",
                            "SIN CONEXIÓN - La reunión fue creada pero no se enviaron notificaciones para ningún usuario, por problemas de conexión."));
        }
    }

    public void limpiarListas() {
        this.vehiculosSeleccionados = null;
        this.vehiculosAgregados = null;
        this.empleadosSeleccionados = null;
        this.empleadosAgregados = null;
    }

    public void todosLosEmpleados() {
        this.empleadosAgregados = this.listaEpleados;
        System.out.println(this.empleadosAgregados);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registros agregados"));
    }

    public void todosLosConductores() {
        this.vehiculosAgregados = this.vehiculoFL.findAll();
        System.out.println(this.vehiculosAgregados);
    }

    public void informacionEmpresa() {
        try {
//            informacion = informacionFL.findAll().get(0);
        } catch (Exception e) {
            System.out.println("Error consultando información empresa: " + e.getMessage());
        }
    }

    public void enviarUnCorreo() {
        this.todosLosEmpleados();
        for (int i = 0; i < this.empleadosAgregados.size(); i++) {
            this.enviarCorreos(empleadosAgregados.get(i).getCorreo());
        }
    }

    public boolean enviarCorreos(String correo) {
        InformacionEmpresa informacion = null;
        try {
            informacion = informacionFL.findAll().get(0);
        } catch (Exception e) {
            System.out.println("Error consultando información empresa: " + e.getMessage());
        }

        try {
            if (UtilOne.validarConexion()) {
                if (correo.length() > 5) {
                    this.informacionEmpresa(); //Consultando informanción empresa.
                    EnvioCorreo mail = new EnvioCorreo();
                    DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
                    String fechaGeneracion = df.format(new Date());
                    String sitioWeb;
                    if (informacion.getSitioWeb().length() > 0) {
                        sitioWeb = "Sitio web: " + informacion.getSitioWeb() + "<br/>";
                    } else {
                        sitioWeb = "";
                    }
                    this.mensaje += ""
                            + "<div style=\"font-size: 14px; color: #444444;\">"
                            + "<br/>"
                            + "<br/>"
                            + "<b>Fecha de la reunión: </b>" + df.format(this.fechaReunion) + ".<br/>"
                            + "<b>Lugar de la reunión: </b>" + this.lugar + ".<br/><br/>"
                            + "<b>Fecha de generación de este mensaje:</b> " + fechaGeneracion + "."
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
                    String asunto = "ETOC - Notificación de reunión ETOC.";
                    String tituloContenido = "<H1>Notificación de Reunión ETOC</H1>";
                    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                    String rutaArchivo = servletContext.getRealPath("") + File.separator + "imagenes"
                            + File.separator;

                    mail.envioCorreo(tituloContenido, correo,
                            informacion.getCorreo(), informacion.getClaveCorreo(),
                            asunto, mensaje,
                            firma, rutaArchivo, nombreArchivo);
                }
                return true;
            } else {
                System.out.println("Error de conexión.");
                return false;
            }
        } catch (Throwable e) {
            return false;
        }
    }

    public void actualizarMensaje() {
        this.mensaje = mensajeEdicion;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensaje Actualizado"));
    }

    public void validar() {
        this.completo = ValidarFormularios.validar(nombre, fechaReunion, lugar, estadoReunion);
        if (completo) {
            this.guardarReunion();
        }
    }

    public void validarEditar() {
        this.completoEditar = ValidarFormularios.validar(reunion.getNombre(), reunion.getFechaReunion(), reunion.getLugar(), reunion.getEstadoReunion());
        if (completoEditar) {
            this.editar();
        }
    }

//------------------------------------------------------------------------------
    //Métodos set y get.
    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public String getEstadoReunion() {
        return estadoReunion;
    }

    public void setEstadoReunion(String estadoReunion) {
        this.estadoReunion = estadoReunion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaReunion() {
        return fechaReunion;
    }

    public void setFechaReunion(Date fechaReunion) {
        this.fechaReunion = fechaReunion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    public List<Reunion> getListaReuniones() {
        return listaReuniones;
    }

    public void setListaReuniones(List<Reunion> listaReuniones) {
        this.listaReuniones = listaReuniones;
    }

    public List<Reunion> getFiltroReuniones() {
        return filtroReuniones;
    }

    public void setFiltroReuniones(List<Reunion> filtroReuniones) {
        this.filtroReuniones = filtroReuniones;
    }

    public ReunionFacadeLocal getLocalReunion() {
        return localReunion;
    }

    public void setLocalReunion(ReunionFacadeLocal localReunion) {
        this.localReunion = localReunion;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Vehiculo> getVehiculosSeleccionados() {
        return vehiculosSeleccionados;
    }

    public void setVehiculosSeleccionados(List<Vehiculo> vehiculosSeleccionados) {
        this.vehiculosSeleccionados = vehiculosSeleccionados;
    }

    public List<Vehiculo> getVehiculosAgregados() {
        return vehiculosAgregados;
    }

    public void setVehiculosAgregados(List<Vehiculo> vehiculosAgregados) {
        this.vehiculosAgregados = vehiculosAgregados;
    }

    public List<Vehiculo> getVehiculosFiltrados() {
        return vehiculosFiltrados;
    }

    public void setVehiculosFiltrados(List<Vehiculo> vehiculosFiltrados) {
        this.vehiculosFiltrados = vehiculosFiltrados;
    }

    public Integer getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(Integer numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public List<Empleado> getListaEpleados() {
        return listaEpleados;
    }

    public void setListaEpleados(List<Empleado> listaEpleados) {
        this.listaEpleados = listaEpleados;
    }

    public List<Empleado> getFiltroEmpleado() {
        return filtroEmpleado;
    }

    public void setFiltroEmpleado(List<Empleado> filtroEmpleado) {
        this.filtroEmpleado = filtroEmpleado;
    }

    public List<Empleado> getEmpleadosSeleccionados() {
        return empleadosSeleccionados;
    }

    public void setEmpleadosSeleccionados(List<Empleado> empleadosSeleccionados) {
        this.empleadosSeleccionados = empleadosSeleccionados;
    }

    public Integer getNumeroRegistros2() {
        return numeroRegistros2;
    }

    public void setNumeroRegistros2(Integer numeroRegistros2) {
        this.numeroRegistros2 = numeroRegistros2;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEnviarParaAsociados() {
        return enviarParaAsociados;
    }

    public void setEnviarParaAsociados(boolean enviarParaAsociados) {
        this.enviarParaAsociados = enviarParaAsociados;
    }

    public String getMensajeEdicion() {
        return mensajeEdicion;
    }

    public void setMensajeEdicion(String mensajeEdicion) {
        this.mensajeEdicion = mensajeEdicion;
    }

    public boolean isCompletoEditar() {
        return completoEditar;
    }

    public void setCompletoEditar(boolean completoEditar) {
        this.completoEditar = completoEditar;
    }
}
