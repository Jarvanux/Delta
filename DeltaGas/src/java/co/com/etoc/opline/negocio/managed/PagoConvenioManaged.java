
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.PagoConvenioFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.PagoConvenio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jhonjaider1000
 */
//Extencion de que es un managedBean
@ManagedBean
//Forma en la que se guarda
@ViewScoped
//Clase EntidadManaged
public class PagoConvenioManaged extends ValidaSesion implements Serializable{    
    //Creamos las listas que usaremos para devolver datos.
    private List<PagoConvenio> listaPagoConvenio;
    private List<PagoConvenio> filtroPagoConvenio;
    //EJB
    @EJB
    //Creamos un objeto de la clase DAO
    private PagoConvenioFacadeLocal pagoConvenioFL;
    //Atributos de la tabla en la bd que recibiremos por la vista.
    private Integer numeroConsignacion;
    private Integer idConvenio;
    private Integer idVehiculo;
    private Date fechaConsignacion;
    private Double valor;
    private PagoConvenio datos;   
    
    private Integer listaPor; 
    private Asociado asociado;
    
    //Constructor POST, que incializará el método init()
    @PostConstruct
    //Inicializamos la lista de los pagos
    public void init(){
        listaPagoConvenio = pagoConvenioFL.findAll();
    }
    
    public void guardar(){
        PagoConvenio pagoConvenio = new PagoConvenio();
        //Mensaje
        String mensaje = "";
        //Paramentrizando el objeto.
        pagoConvenio.setNumeroConsig(numeroConsignacion);
        pagoConvenio.setFechaConsignacion(fechaConsignacion);
        pagoConvenio.setValorConsignacion(valor);        
        try{
        pagoConvenioFL.create(pagoConvenio);
        }catch(Exception e){
            //Creo un mensaje de la clase FacesMessage
            FacesMessage msg = new FacesMessage(mensaje);
            //Consigo la instancia de la vista en donde publicaré el mensaje.
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void prepareDatos(PagoConvenio pagoConvenio){        
        datos = pagoConvenio;        
    }
    
    public void listarPor(){
        if(listaPor > 0){
        this.listaPagoConvenio = pagoConvenioFL.listarPor(listaPor);
        }else{
            this.listaPagoConvenio = pagoConvenioFL.findAll();
        }
    }
    
    
    //Métodos SET Y GET
    
    public Integer getListaPor() {
        return listaPor;
    }

    public void setListarPor(Integer listarPor) {
        this.listaPor = listarPor;
    }
    
    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
    }
    
    public PagoConvenio getDatos() {
        return datos;
    }

    public void setDatos(PagoConvenio datos) {
        this.datos = datos;
    }
    
    public List<PagoConvenio> getListaPagoConvenio() {
        return listaPagoConvenio;
    }

    public void setListaPagoConvenio(List<PagoConvenio> listaPagoConvenio) {
        this.listaPagoConvenio = listaPagoConvenio;
    }

    public List<PagoConvenio> getFiltroPagoConvenio() {
        return filtroPagoConvenio;
    }

    public void setFiltroPagoConvenio(List<PagoConvenio> filtroPagoConvenio) {
        this.filtroPagoConvenio = filtroPagoConvenio;
    }

    public Integer getNumeroConsignacion() {
        return numeroConsignacion;
    }

    public void setNumeroConsignacion(Integer numeroConsignacion) {
        this.numeroConsignacion = numeroConsignacion;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
