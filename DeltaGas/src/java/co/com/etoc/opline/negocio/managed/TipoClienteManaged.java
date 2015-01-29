/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.TipoClienteFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.TipoCliente;
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
public class TipoClienteManaged extends ValidaSesion implements Serializable{

    /**
     * Creates a new instance of ZonaManaged
     */
    private List<TipoCliente> listaTipoCliente;
    private List<TipoCliente> filtroTipoCliente;

    @EJB
    private TipoClienteFacadeLocal localTipoCliente;
    private String nombre;
    private TipoCliente tipoCliente;    

    public TipoClienteManaged() {
    }

    @PostConstruct
    public void init() {     
        listaTipoCliente = localTipoCliente.findAll();
    }

    //Método para guardar/insertar
    public void guardar() {
        //En esta variable se guardará el mensaje que se imprime como respuesta.
        String mensaje;
        try {
            //Se crea un objeto que almacenará los datos recogidos en la vista.
            TipoCliente tipoCliente = new TipoCliente();
            //Se asignan los datos de la vista al objeto creado anteriormente.                        
            tipoCliente.setDescripcion(nombre);
            //Se inserta el nuevo registro en la BD.
            this.localTipoCliente.create(tipoCliente);
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
            TipoCliente tipoCliente = ((TipoCliente) event.getObject());
            localTipoCliente.edit(tipoCliente);
            mensaje = "Registro actualizado correctamente.";
        } catch (Exception e) {
            mensaje = "Error al actualizar registro.";
        }
        FacesMessage msg = new FacesMessage("TipoCliente ", mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {

    }

    public void prepareEliminar(TipoCliente tipoCliente){
        this.tipoCliente = tipoCliente;
    }
    public String eliminar() {
        String mensaje;
        System.out.println("Eliminar: "+this.tipoCliente.getDescripcion());
        try {
            localTipoCliente.remove(this.tipoCliente);
            mensaje = "Registro eliminado satisfactoriamente.";
        } catch (Exception e) {
            System.out.println("Error TipoClienteManaged al eliminar registro." + e.getMessage());
            mensaje = "No se pudo eliminar el registro.";
        }
        FacesMessage msg = new FacesMessage("TipoCliente ", mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        this.init();
        return "tipoCliente.xhtml";        
    }

    public List<TipoCliente> getListaTipoCliente() {
        return listaTipoCliente;
    }

    public void setListaTipoCliente(List<TipoCliente> listaTipoCliente) {
        this.listaTipoCliente = listaTipoCliente;
    }

    public List<TipoCliente> getFiltroTipoCliente() {
        return filtroTipoCliente;
    }

    public void setFiltroTipoCliente(List<TipoCliente> filtroTipoCliente) {
        System.out.println(filtroTipoCliente + "");
        this.filtroTipoCliente = filtroTipoCliente;
    }

    public void limpiar() {
        this.nombre = "";
        this.init();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String irClientes(){
        return "cliente.xhtml";
    }
}
