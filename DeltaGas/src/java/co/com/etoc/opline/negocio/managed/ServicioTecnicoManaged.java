/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.EnvioCorreo;
import co.com.etoc.opline.persistencia.dao.EstacionFacadeLocal;
import co.com.etoc.opline.persistencia.dao.InformacionEmpresaFacadeLocal;
import co.com.etoc.opline.persistencia.dao.RequerimientoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.ServicioTecnicoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Cliente;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.Estacion;
import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import co.com.etoc.opline.persistencia.entidades.Requerimiento;
import co.com.etoc.opline.persistencia.entidades.ServicioTecnico;
import co.com.etoc.opline.utilerias.UtilOne;
import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class ServicioTecnicoManaged extends ValidaSesion  implements Serializable{

    public ServicioTecnicoManaged() {
    }
    @EJB //Se crea una instancia al dao de conexión.
    private ServicioTecnicoFacadeLocal stFL;

    @EJB
    private InformacionEmpresaFacadeLocal informacionFL;

    @EJB
    private EstacionFacadeLocal estacionFL;

    @EJB
    private RequerimientoFacadeLocal reqFL;

    @EJB
    EmpleadoFacadeLocal empleadoFL;

    //Atributos
    List<Empleado> listaUsuarios;
    List<Empleado> seleccionados;
    List<Empleado> filtro;
    private List<Empleado> listaEpleados;
    private List<Empleado> empleadosSeleccionados;
    private List<Empleado> filtroEmpleado;
    int idEstado;

    //Atributos de la clase.
    List<ServicioTecnico> lista;
    List<ServicioTecnico> listaFiltro;
    Date fechaSolicitud;
    int idRequerimiento;
    String estacion;
    int idEstacion;
    String descripcion;
    String codigoRegistro;
    ServicioTecnico servicioTecnico;

    @PostConstruct
    public void init() {
//        listaUsuarios = empleadoFL.consultarTecnicos();
        this.listaEpleados = empleadoFL.activos();
        lista = stFL.listarServiciosRealizados(this.empleado.getIdEmpleado());
    }

    public void guardarCambios() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("#" + empleadosSeleccionados));
    }

    public void enviarSolicitud() {
        try {
            if (UtilOne.validarConexion()) {
                ServicioTecnico object = new ServicioTecnico();
                //Instanciamos el objeto.      
                object.setCodigoRegistro(codigoRegistro);
                object.setIdCliente(new Cliente(empleado.getIdEmpleado()));
                object.setFechaRegistro(fechaSolicitud);
                object.setDescripcionCliente(descripcion);
                object.setIdEtacion(new Estacion(idEstacion));
                object.setIdRequerimiento(new Requerimiento(idRequerimiento));
                object.setEstado("No");
                stFL.create(object); //Creamos el registro.
                enviarInforme();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "SIN CONEXIÓN - Imposible conectar con el sistema.",
                                "SIN CONEXIÓN - Imposible conectar con el sistema"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "No se pudo enviar la solicitud.", "No se pudo enviar la solicitud."));
        }
    }

    public void limpiarFormulario() {
        fechaSolicitud = null;
        idRequerimiento = -1;
        idEstacion = -1;
        estacion = "";
        descripcion = "";
    }

    public boolean enviarInforme() { //true = Creación empleado // false = edición.
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
                Estacion obj = estacionFL.consultarEstacionPorId(idEstacion);
                Requerimiento reqObj = reqFL.consultarPorID(idRequerimiento);
                String correoReceptor = informacion.getCorreo();
                String estacionName = (obj == null) ? "Se produjo un error y ha quedado sin definir," : obj.getNombre();
                String reqString = (reqObj == null) ? "Se producjo un error y ha quedado sin definir." : reqObj.getDescripcion();
                String mensaje = ""
                        + "<div style=\"font-size: 14px; color: #444444;\">"
                        + "Cordial saludo! el usuario <b>" + empleado.getNombre() + " " + empleado.getApellido() + "</b> con número de identidad: <b>" + empleado.getCedula()
                        + "</b>, ha enviado una nueva solicitud con los siguientes parámetros: <br/>"
                        + "<br>"
                        + "<b>Fecha Solicitud:</b> " + df.format(fechaSolicitud)
                        + "<br>"
                        + "<b>Código de solicitud:</b> " + codigoRegistro
                        + "<br/>"
                        + "<b>Estación de gas:</b> " + estacionName
                        + "<br/>"
                        + "<b>Requerimiento: </b>" + reqString
                        + "<br/>"
                        + "<table border='0'>"
                        + "<tr><td style='vertical-align: top;'><b>Descripción:</b></td> <td><textarea disabled='disabled' style='width:500px; height: 100px;'>"
                        + descripcion
                        + "</textarea></td></tr></table>"
                        + "<br/>"
                        + "<b>Fecha del mensaje:</b> " + fechaGeneracion + "."
                        + "</div>";
//\"cid:image\"
                String firma = "<br/><br/><br/>"
                        + "<div style=\"float: left; width: 300px; height: 100px; text-align:center; "
                        + "\">"
                        + "<img src=\"cid:image\" style=\"width:100%; margin-top:6%\"/>"
                        + "</div>"
                        + "<div style=\"float: left; width: 300px; height: 100px; border-left: 2px "
                        + "solid #0097CC; padding-left: 20px; padding-top:10px;\">"
                        + "<font size=\"2\" >"
                        + "<b>DeltaGas </b>"
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
                String asunto = "Sistema DeltaGas - notificación de solicitud nueva. ";
                String tituloContenido = "<H1>Solicitud de un servicio nuevo</H1>";
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String rutaArchivo = servletContext.getRealPath("") + File.separator + "imagenes"
                        + File.separator;
                mail.envioCorreo(tituloContenido, correoReceptor, informacion.getCorreo(), informacion.getClaveCorreo(), asunto, mensaje, firma, rutaArchivo, nombreArchivo);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Se ha enviado la solicitud!.", "Se ha enviado la solicigud!."));
                return true;
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "SIN CONEXIÓN - Imposible conectar con el sistema.",
                                "SIN CONEXIÓN - Imposible conectar con el sistema"));
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

    //Métodos set y get.
    public List<ServicioTecnico> getLista() {
        return lista;
    }

    public void setLista(List<ServicioTecnico> lista) {
        this.lista = lista;
    }

    public Date obtenerHoraDelSistema() {
        this.fechaSolicitud = new Date();
        return this.fechaSolicitud;
    }

    public String obtenerIdSolicitud() {
        this.codigoRegistro = ("" + System.currentTimeMillis()).toString().substring(4);
        return this.codigoRegistro;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getIdRequerimiento() {
        return idRequerimiento;
    }

    public void setIdRequerimiento(int idRequerimiento) {
        this.idRequerimiento = idRequerimiento;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public List<ServicioTecnico> getListaFiltro() {
        return listaFiltro;
    }

    public void setListaFiltro(List<ServicioTecnico> listaFiltro) {
        this.listaFiltro = listaFiltro;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public ServicioTecnico getServicioTecnico() {
        return servicioTecnico;
    }

    public void setServicioTecnico(ServicioTecnico servicioTecnico) {
        this.servicioTecnico = servicioTecnico;
    }

    public List<Empleado> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Empleado> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Empleado> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(List<Empleado> seleccionados) {
        this.seleccionados = seleccionados;
    }

    public List<Empleado> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<Empleado> filtro) {
        this.filtro = filtro;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public List<Empleado> getListaEpleados() {
        return listaEpleados;
    }

    public void setListaEpleados(List<Empleado> listaEpleados) {
        this.listaEpleados = listaEpleados;
    }

    public List<Empleado> getEmpleadosSeleccionados() {
        return empleadosSeleccionados;
    }

    public void setEmpleadosSeleccionados(List<Empleado> empleadosSeleccionados) {
        this.empleadosSeleccionados = empleadosSeleccionados;
    }

    public List<Empleado> getFiltroEmpleado() {
        return filtroEmpleado;
    }

    public void setFiltroEmpleado(List<Empleado> filtroEmpleado) {
        this.filtroEmpleado = filtroEmpleado;
    }

}
