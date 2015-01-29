/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.AsociadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.PagoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.TipoPagoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import co.com.etoc.opline.persistencia.entidades.TipoPago;
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
public class PagoManaged extends ValidaSesion implements Serializable {

    private List<Pago> listaPagos;
    private List<Pago> filtroPagos;

    @EJB
    private PagoFacadeLocal localPago;
    @EJB
    private AsociadoFacadeLocal localAsociado;
    
    private Integer numeroRecibo;
    private Integer idAsociado;
    private Integer idTipoPago;
    private Integer idTipoPago2;
    private Date fechaPago;
    private Integer valorPago;
    private String observaciones;
    private Asociado asociado;

    private Pago pago;
    private Pago pago2;
    private Integer listarPor;
    private Date fechaUltimoPago;
    private Date fechaPagoFinal;

    private boolean completo;

    public PagoManaged() {
    }

    @PostConstruct
    public void init() {
        listaPagos = localPago.findAll();
        this.completo = true;
    }

    public void verifiqueUltimoPago() {
        try {
            this.fechaUltimoPago = localPago.ultimoPago(asociado.getIdAsociado(), idTipoPago).getFechaPago();
        } catch (Exception e) {
            this.fechaUltimoPago = new Date();
        }
    }
    
    public void consultarPagosAsociado(String documento){
        this.asociado = localAsociado.consultarAsosicado(documento);        
        this.listaPagos = localPago.consultarPagosAsociado(asociado.getIdAsociado());
    }

    //Método para guardar/insertar
    public void guardar() {
        //En esta variable se guardará el mensaje que se imprime como respuesta.
        String mensaje;
        try {
            //Se crea un objeto que almacenará los datos recogidos en la vista.
            Pago pagoRegistrar = new Pago();
            //Se asignan los datos de la vista al objeto creado anteriormente.
            //empleado.setRolIdRol(new Rol(rol));
            pagoRegistrar.setNumeroRecibo(numeroRecibo);
            pagoRegistrar.setIdTipoPago(new TipoPago(2));
            pagoRegistrar.setIdAsociado(asociado);
            pagoRegistrar.setFechaPago(new Date());
            pagoRegistrar.setValorPago(valorPago);
            pagoRegistrar.setObservacion(observaciones);
            pagoRegistrar.setFechaInicio(fechaUltimoPago);
            pagoRegistrar.setFechaFin(fechaPago);
            //Se inserta el nuevo registro en la BD.
            this.localPago.create(pagoRegistrar);
            //Se asigna el mensaje de éxito de la operación en la variable mensaje.
            mensaje = "El Pago fue insertado correctamente.";
            //Se alimenta la lista, para que la vista pueda mostrar el nuevo empleado agregado. 
            this.limpiar();
        } catch (Exception e) {
            //Se asigna el mensaje de error de la operación en la variable mensaje.
            mensaje = "Error al Insertar Pago." + e.getMessage();
        }
        //Se lanza el mensaje.
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String limpiar() {
        this.numeroRecibo = 0;
        this.idAsociado = 0;
        this.idTipoPago = 0;
        this.fechaPago = null;
        this.valorPago = 0;
        this.observaciones = "";
        this.asociado = null;
        this.fechaPagoFinal = null;
        this.fechaUltimoPago = null;
        this.init();
        new ValidarFormularios().init();
        return "pago.xhtml";
    }

    public void listarPor() {
        if (listarPor > 0) {
            this.listaPagos = localPago.tipoExclusivo(listarPor);
        } else {
            this.listaPagos = localPago.listarOrdenadamente();
        }
    }

    public void prepararEdicion(Pago pago) {
        this.idTipoPago2 = pago.getIdTipoPago().getIdTipoPago();
        this.pago = pago;
    }

    public void prepararEdicion2(Pago pago) {
        this.idTipoPago2 = pago.getIdTipoPago().getIdTipoPago();
        this.pago2 = pago;
    }

    public void onRowCancel(RowEditEvent event) {

    }

    public List<Pago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(List<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }

    public List<Pago> getFiltroPagos() {
        return filtroPagos;
    }

    public void setFiltroPagos(List<Pago> filtroPagos) {
        System.out.println(filtroPagos + "");
        this.filtroPagos = filtroPagos;
    }

    public Integer getListarPor() {
        return listarPor;
    }

    public void setListarPor(Integer listarPor) {
        this.listarPor = listarPor;
    }

    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
    }

    public String goToTipoPagos() {
        return "tipoPago.xhtml";
    }

//--------------------------------------------------------------------------
// METODOS GET Y SET
//--------------------------------------------------------------------------
    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public Integer getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(Integer idAsociado) {
        this.idAsociado = idAsociado;
    }

    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getValorPago() {
        return valorPago;
    }

    public void setValorPago(Integer valorPago) {
        this.valorPago = valorPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Pago getPago2() {
        return pago2;
    }

    public void setPago2(Pago pago2) {
        this.pago2 = pago2;
    }

    public Integer getIdTipoPago2() {
        return idTipoPago2;
    }

    public void setIdTipoPago2(Integer idTipoPago2) {
        this.idTipoPago2 = idTipoPago2;
    }

    //Validaciones
    public void validar() {
        if (this.localPago.comprobarCodigoRepetido(numeroRecibo)) {
            completo = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de recibo se encuentra registrado en la Base de datos.", "El número de recibo ya se encuentra registrado."));
        } else {
            completo = true;
        }
        if (completo) {
            this.completo = ValidarFormularios.validar(numeroRecibo, asociado,
                    idTipoPago, fechaUltimoPago, fechaPagoFinal, valorPago);
        }
        if (completo) {
            this.guardar();
        }
    }

    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public Date getFechaPagoFinal() {
        return fechaPagoFinal;
    }

    public void setFechaPagoFinal(Date fechaPagoFinal) {
        this.fechaPagoFinal = fechaPagoFinal;
    }
}
