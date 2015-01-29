package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.VehiculoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.ZonaFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Vehiculo;
import co.com.etoc.opline.persistencia.entidades.Zona;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class ViajesManaged extends ValidaSesion implements Serializable{

    private List<Vehiculo> listaVehiculos;
    private List<Vehiculo> filtroVehiculos;

    //Atributos de lista externas.
    private Integer idZona;
    private Integer numeroVehiculos;
    private Integer capacidadZona;
    private Integer espacioDisponible;

    @EJB
    private VehiculoFacadeLocal vehiculosFL;

    @EJB
    private VehiculoFacadeLocal vehiculoFL;

    @EJB
    private ZonaFacadeLocal zonaFL;

    @PostConstruct
    public void init() {
        this.idZona = 1;
        this.listarPor();
    }

    public void listarPor() {
        //Se obtine la lista de vehiculos de esa zona.
        listaVehiculos = vehiculoFL.vehiculosPorZona(idZona);
        //Se consulta el número de vhiculos que hay actualmente en esa zoa.
        numeroVehiculos = listaVehiculos.size();
        //Se conoce la capacidad de zona(Número de vehiculos permitidos).
        capacidadZona = zonaFL.capacidadZona(idZona);
        //Espacio disponible(Espacio para nuevos vehiculos).
        espacioDisponible = this.capacidadZona - numeroVehiculos;
    }

    //Métodos get y set.
    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public List<Vehiculo> getFiltroVehiculos() {
        return filtroVehiculos;
    }

    public void setFiltroVehiculos(List<Vehiculo> filtroVehiculos) {
        this.filtroVehiculos = filtroVehiculos;
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public Integer getNumeroVehiculos() {
        return numeroVehiculos;
    }

    public void setNumeroVehiculos(Integer numeroVehiculos) {
        this.numeroVehiculos = numeroVehiculos;
    }

    public Integer getCapacidadZona() {
        return capacidadZona;
    }

    public void setCapacidadZona(Integer capacidadZona) {
        this.capacidadZona = capacidadZona;
    }

    public Integer getEspacioDisponible() {
        return espacioDisponible;
    }

    public void setEspacioDisponible(Integer espacioDisponible) {
        this.espacioDisponible = espacioDisponible;
    }
    
    

}
