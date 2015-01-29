/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.AsociadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.PagoConvenioFacadeLocal;
import co.com.etoc.opline.persistencia.dao.VehiculoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Convenio;
import co.com.etoc.opline.persistencia.entidades.PagoConvenio;
import co.com.etoc.opline.persistencia.entidades.Vehiculo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class PagoManaged1 extends ValidaSesion implements Serializable {

    private List<PagoConvenio> listaPagoConvenios;
    private List<PagoConvenio> filtroPagoConvenios;
    private List<Vehiculo> listaVehiculos;

    @EJB
    private PagoConvenioFacadeLocal localPagoConvenio;

    @EJB
    private VehiculoFacadeLocal vehiculoFL;

    private Integer numeroConsig;
    private Integer idConvenio;
    private Integer idConvenioEditar;
    private Integer idVehiculo;
    private Integer idVehiculoEditar;
    private Date fechaConsignacion;
    private double valorConsignacion;
    private String observaciones;

    private PagoConvenio pagoConvenio;
    private Vehiculo vehiculo;
    private Integer listarPor;
    private Asociado asociado;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaUltimoPago;
    private boolean completo;

    //Controles de visibilidad de elementos de la vista.    
    private String tituloBotonActivarSubFiltros;
    private String iconoBotonSubFiltros;
    private boolean subFiltrosActivados;
    private String visibilidaSubFiltros;

    @PostConstruct
    public void init() {
        PrincipalManagedBean pmb = new PrincipalManagedBean();
        pmb.setRutaActual("Pagos\\Convenio");
        listaPagoConvenios = localPagoConvenio.listarOrdenadamente();
        completo = true;
        this.subFiltrosActivados = false;
        this.controlarSubFiltros();
    }

    public void verifiqueUltimoPago() {
        try {
            this.fechaUltimoPago = localPagoConvenio.ultimoPago(idVehiculo, idConvenio).getFechaConsignacion();            
        } catch (Exception e) {
            this.fechaUltimoPago = new Date();
        }
    }

    //Método para guardar/insertar
    public void guardar() {
        //En esta variable se guardará el mensaje que se imprime como respuesta.
        String mensaje;
        try {
            //Se crea un objeto que almacenará los datos recogidos en la vista.          
            PagoConvenio datos = new PagoConvenio();
            //Se asignan los datos de la vista al objeto creado anteriormente.
            //empleado.setRolIdRol(new Rol(rol));          
            datos.setNumeroConsig(numeroConsig);
            datos.setIdVehiculo(new Vehiculo(idVehiculo));
            datos.setIdConvenio(new Convenio(idConvenio));
            datos.setFechaConsignacion(new Date());
            datos.setValorConsignacion(valorConsignacion);
            datos.setFechaInicio(new Date());
            datos.setFechaFin(new Date());
            datos.setObservacion(observaciones);
            //Se inserta el nuevo registro en la BD.
            this.localPagoConvenio.create(datos);
            //Se asigna el mensaje de éxito de la operación en la variable mensaje.
            mensaje = "El PagoConvenio fue insertado correctamente.";
            //Se alimenta la lista, para que la vista pueda mostrar el nuevo empleado agregado. 
            this.limpiar();
        } catch (Exception e) {
            //Se asigna el mensaje de error de la operación en la variable mensaje.
            mensaje = "Error al Insertar PagoConvenio." + e.getMessage();
        }
        //Se lanza el mensaje.
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void editarPago() {
        FacesMessage mensaje;
        try {
            localPagoConvenio.edit(pagoConvenio);
            mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Pago editado satisfactoriamente.");
        } catch (Exception e) {
            mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No se pudo Editar el pago.");
        }
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }

    public void limpiar() {
        this.pagoConvenio = null;
        this.fechaConsignacion = null;
        this.idConvenio = -1;
        this.idVehiculo = -1;
        this.numeroConsig = 0;
        this.valorConsignacion = 0;
        this.asociado = null;
        this.init();
        new ValidarFormularios().init();
    }

    public void listarPor() {
        if (listarPor > 0) {
            this.listaPagoConvenios = localPagoConvenio.listarPor(listarPor);
        } else {
            this.listaPagoConvenios = localPagoConvenio.listarOrdenadamente();
        }
    }

    public void prepararEdicion(PagoConvenio PagoConvenio) {
        this.pagoConvenio = PagoConvenio;
    }

    public void vehiculosPorAsociado() {
        if (asociado != null) {
            listaVehiculos = vehiculoFL.vehiculosPorAsociado(asociado.getIdAsociado());
        }
    }

    //public boolean validar(, Double valorConsignacion) {
    public void validar() {
        if (localPagoConvenio.comprobarCodigoRepetido(numeroConsig)) {
            completo = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de recibo se encuentra registrado en la Base de datos.", "El número de recibo ya se encuentra registrado."));
        } else {
            completo = true;
        }
        if (completo) {
            this.completo = ValidarFormularios.validar(numeroConsig, asociado,
                    idVehiculo, idConvenio, fechaUltimoPago, fechaInicio, fechaFin, valorConsignacion);
        }
        if (completo) {
            this.guardar();
        }
    }

    public void controlarSubFiltros() {
        if (!this.subFiltrosActivados) {
            this.tituloBotonActivarSubFiltros = "Activar SubFiltros";
            this.iconoBotonSubFiltros = "ui-icon-zoomin";
            this.visibilidaSubFiltros = "none";
            this.subFiltrosActivados = true;
        } else {
            this.tituloBotonActivarSubFiltros = "Desactivar SubFiltros";
            this.iconoBotonSubFiltros = "ui-icon-zoomout";
            this.visibilidaSubFiltros = "display";
            this.subFiltrosActivados = false;
        }
    }

    public List<PagoConvenio> getListaPagoConvenios() {
        return listaPagoConvenios;
    }

    public void setListaPagoConvenios(List<PagoConvenio> listaPagoConvenios) {
        this.listaPagoConvenios = listaPagoConvenios;
    }

    public List<PagoConvenio> getFiltroPagoConvenios() {
        return filtroPagoConvenios;
    }

    public void setFiltroPagoConvenios(List<PagoConvenio> filtroPagoConvenios) {
        System.out.println(filtroPagoConvenios + "");
        this.filtroPagoConvenios = filtroPagoConvenios;
    }

    public Integer getListarPor() {
        return listarPor;
    }

    public void setListarPor(Integer listarPor) {
        this.listarPor = listarPor;
    }

    public Integer getNumeroConsig() {
        return numeroConsig;
    }

    public void setNumeroConsig(Integer numeroConsig) {
        this.numeroConsig = numeroConsig;
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Date getFechaConsignacion() {
        return fechaConsignacion;
    }

    public void setFechaConsignacion(Date fechaConsignacion) {
        this.fechaConsignacion = fechaConsignacion;
    }

    public double getValorConsignacion() {
        return valorConsignacion;
    }

    public void setValorConsignacion(double valorConsignacion) {
        this.valorConsignacion = valorConsignacion;
    }

    public PagoConvenio getPagoConvenio() {
        return pagoConvenio;
    }

    public void setPagoConvenio(PagoConvenio PagoConvenio) {
        this.pagoConvenio = PagoConvenio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
    }

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdConvenioEditar() {
        return idConvenioEditar;
    }

    public void setIdConvenioEditar(Integer idConvenioEditar) {
        this.idConvenioEditar = idConvenioEditar;
    }

    public Integer getIdVehiculoEditar() {
        return idVehiculoEditar;
    }

    public void setIdVehiculoEditar(Integer idVehiculoEditar) {
        this.idVehiculoEditar = idVehiculoEditar;
    }

    public String getTituloBotonActivarSubFiltros() {
        return tituloBotonActivarSubFiltros;
    }

    public void setTituloBotonActivarSubFiltros(String tituloBotonActivarSubFiltros) {
        this.tituloBotonActivarSubFiltros = tituloBotonActivarSubFiltros;
    }

    public String getIconoBotonSubFiltros() {
        return iconoBotonSubFiltros;
    }

    public void setIconoBotonSubFiltros(String iconoBotonSubFiltros) {
        this.iconoBotonSubFiltros = iconoBotonSubFiltros;
    }

    public String getVisibilidaSubFiltros() {
        return visibilidaSubFiltros;
    }

    public void setVisibilidaSubFiltros(String visibilidaSubFiltros) {
        this.visibilidaSubFiltros = visibilidaSubFiltros;
    }

}
