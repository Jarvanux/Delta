package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.VehiculoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.ZonaFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Estado;
import co.com.etoc.opline.persistencia.entidades.Vehiculo;
import co.com.etoc.opline.persistencia.entidades.Zona;
import java.io.Serializable;
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
@ManagedBean
@ViewScoped
public class VehiculosManaged extends ValidaSesion implements Serializable {

    private Vehiculo vehiculo;
    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosSeleccionados;
    private List<Vehiculo> vehiculosFiltrados;
    private List<Vehiculo> vehiculosZonaDestino;
    private Integer idVehiculo;
    private Integer numeroRegistros;
    private Integer numeroRegistrosDestino;
    private Integer espacioDisponible;
    private Integer idZona;

    private Integer idZona2;

    private List<Zona> listaZonas;
    private List<Zona> filtroZonas;
    private Integer capacidadZona;
    private Zona zonaSelecionada;

    

    @EJB
    private VehiculoFacadeLocal vehiculoFL;

    @EJB
    private ZonaFacadeLocal zonaFL;

    @PostConstruct
    public void init() {
        PrincipalManagedBean pmb = new PrincipalManagedBean();
        pmb.setRutaActual("Zona\\Deshabilitar zona");       
        idZona2=(Integer) getSesion().getAttribute("idZona");
        System.out.println("Ejecuto " + idZona2);
        listarPor();
        listarPor2();
        numeroRegistrosDestino = vehiculosZonaDestino.size();
    }

    public String redireccionarDeshabilitar() {
        return "deshabilitarZona.xhtml";
    }

    public void listarPor() {
        vehiculosZonaDestino = vehiculoFL.vehiculosPorZona(idZona);
        numeroRegistrosDestino = vehiculosZonaDestino.size();
        Vehiculo zona = new Vehiculo();
        if (numeroRegistrosDestino > 0) {
            zona = vehiculosZonaDestino.get(0);
            try {
                capacidadZona = zonaFL.capacidadZona(idZona);
            } catch (Exception e) {

            }
            espacioDisponible = this.capacidadZona - this.numeroRegistrosDestino;
        } else {
            capacidadZona = zonaFL.capacidadZona(idZona);
            espacioDisponible = this.capacidadZona;
        }
    }

    public void listarPor2() {
        vehiculos = vehiculoFL.vehiculosPorZona(idZona2);
        numeroRegistrosDestino = vehiculosZonaDestino.size();
        if (vehiculosZonaDestino.size() > 0) {
            try {
//            capacidadZona = zonaFL.capacidadZona(idZona2);
            } catch (Exception e) {

            }
//            espacioDisponible = this.capacidadZona - this.numeroRegistrosDestino;
        } else {
//            capacidadZona = zonaFL.capacidadZona(idZona2);
//            espacioDisponible = this.capacidadZona;
        }
    }

    public void desabilitarZona(){
        try {            
            zonaSelecionada = zonaFL.consultarZonaPorID(idZona2);
            zonaSelecionada.setIdEstado(new Estado(2));
            
            if(vehiculos.size() == 0){
                zonaFL.edit(zonaSelecionada);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Zona deshabilitada."));
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"","No es posible desabilitar la zona si contiene vehículos."));
            }            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Error al deshabilitar zona."));
        }
    }
    public String pasarVehiculos() {
        try {
            if (this.capacidadZona == 0) {
                this.capacidadZona = 20;
            }
            if (this.espacioDisponible >= vehiculosSeleccionados.size()) {
                Vehiculo registro;
                for (int i = 0; i < vehiculosSeleccionados.size(); i++) {
                    registro = vehiculosSeleccionados.get(i);
                    registro.setIdZona(new Zona(idZona));
                    vehiculoFL.edit(registro);
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se han editado los registros satisfactoriamente!."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "No hay suficiente espacio para pasar todos los vehiculos seleccionados."));
            }
        } catch (Exception e) {
            System.out.println("Error al pasar vehiculos en el managed.");
        }
        return "deshabilitarZona.xhtml";
    }

    //Métodos GET y SET
    public Integer getIdZona2() {
        return idZona2;
    }

    public void setIdZona2(Integer idZona2) {
        this.idZona2 = idZona2;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
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

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public List<Vehiculo> getVehiculosFiltrados() {
        return vehiculosFiltrados;
    }

    public void setVehiculosFiltrados(List<Vehiculo> vehiculosFiltrados) {
        this.vehiculosFiltrados = vehiculosFiltrados;
    }

    public List<Vehiculo> getVehiculosZonaDestino() {
        return vehiculosZonaDestino;
    }

    public void setVehiculosZonaDestino(List<Vehiculo> vehiculosZonaDestino) {
        this.vehiculosZonaDestino = vehiculosZonaDestino;
    }

    public Integer getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(Integer numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    public Integer getNumeroRegistrosDestino() {
        return numeroRegistrosDestino;
    }

    public void setNumeroRegistrosDestino(Integer numeroRegistrosDestino) {
        this.numeroRegistrosDestino = numeroRegistrosDestino;
    }

    public Integer getEspacioDisponible() {
        return espacioDisponible;
    }

    public void setEspacioDisponible(Integer espacioDisponible) {
        this.espacioDisponible = espacioDisponible;
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public Integer getCapacidadZona() {
        return capacidadZona;
    }

    public void setCapacidadZona(Integer capacidadZona) {
        this.capacidadZona = capacidadZona;
    }

}