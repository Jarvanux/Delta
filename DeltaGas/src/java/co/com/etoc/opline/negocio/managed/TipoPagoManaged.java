/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.TipoPagoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.TipoPago;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class TipoPagoManaged extends ValidaSesion implements Serializable{

    /**
     * Creates a new instance of ZonaManaged
     */
    private List<TipoPago> listaTipoPago;
    private List<TipoPago> listaTipoPagoTabla;
    private List<TipoPago> filtroTipoPago;
    private List<TipoPago> listaTipoPagoCombox;

    @EJB
    private TipoPagoFacadeLocal localTipoPago;
    private String nombre;
    private TipoPago tipoPago;    

    public TipoPagoManaged() {
    }

    @PostConstruct
    public void init() {        
        listaTipoPago = localTipoPago.findAll();
        listaTipoPagoTabla = localTipoPago.tipoPagoTabla();
        listaTipoPagoCombox = localTipoPago.tipoPagoCombox();
    }

    //Método para guardar/insertar
    public void guardar() {
        //En esta variable se guardará el mensaje que se imprime como respuesta.
        String mensaje;
        try {
            //Se crea un objeto que almacenará los datos recogidos en la vista.
            TipoPago tipoPago = new TipoPago();
            //Se asignan los datos de la vista al objeto creado anteriormente.                        
            tipoPago.setNombrePago(nombre);
            //Se inserta el nuevo registro en la BD.
            this.localTipoPago.create(tipoPago);
            //Se asigna el mensaje de éxito de la operación en la variable mensaje.
            mensaje = "El registro fue insertado correctamente.";
            //Se alimenta la lista, para que la vista pueda mostrar el nuevo empleado agregado. 
            this.limpiar();
        } catch (Exception e) {
            //Se asigna el mensaje de error de la operación en la variable mensaje.
            mensaje = "Error al Insertar registro." + e.getMessage();
        }
        //Se lanza el mensaje.
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        String mensaje;
        try {
            TipoPago tipoPago = ((TipoPago) event.getObject());
            localTipoPago.edit(tipoPago);
            mensaje = "Registro editado correctamente.";
        } catch (Exception e) {
            mensaje = "Error al editar registro.";
        }
        FacesMessage msg = new FacesMessage("TipoPago ", mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {

    }

    public void prepareEliminar(TipoPago tipoPago){
        this.tipoPago = tipoPago;
    }
    public String eliminar() {
        String mensaje;        
        try {
            localTipoPago.remove(this.tipoPago);
            mensaje = "Registro eliminado satisfactoriamente.";
        } catch (Exception e) {
            System.out.println("Error TipoClienteManaged al eliminar registro." + e.getMessage());
            mensaje = "No se pudo eliminar el registro.";
        }
        FacesMessage msg = new FacesMessage("TipoPago ", mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "tipoPago.xhtml";
    }
    

    public void limpiar() {
        this.nombre = "";
        this.init();
    }

    
    //--------------- METODOS GET & SET -----------------------------------\\
    
    
    public List<TipoPago> getListaTipoPagoCombox() {
        return listaTipoPagoCombox;
    }

    public void setListaTipoPagoCombox(List<TipoPago> listaTipoPagoCombox) {
        this.listaTipoPagoCombox = listaTipoPagoCombox;
    }
    
    
    public List<TipoPago> getListaTipoPago() {
        return listaTipoPago;
    }

    public void setListaTipoCliente(List<TipoPago> listaTipoPago) {
        this.listaTipoPago = listaTipoPago;
    }

    public List<TipoPago> getFiltroTipoPago() {
        return filtroTipoPago;
    }

    public void setFiltroTipoCliente(List<TipoPago> filtroTipoPago) {
        System.out.println(filtroTipoPago + "");
        this.filtroTipoPago = filtroTipoPago;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }   
    
    public String irPagos(){
        return "pago.xhtml";
    }

    public List<TipoPago> getListaTipoPagoTabla() {
        return listaTipoPagoTabla;
    }

    public void setListaTipoPagoTabla(List<TipoPago> listaTipoPagoTabla) {
        this.listaTipoPagoTabla = listaTipoPagoTabla;
    }
    
}
