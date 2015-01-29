/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.Menu;
import co.com.etoc.opline.persistencia.entidades.Zona;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author sena
 */
@ManagedBean
@ViewScoped
public class PrincipalManagedBean extends ValidaSesion implements Serializable {

    private String nombreEmpleado;
    private List<Menu> listaMenu;
    private Empleado datosPrincipal;
    public static Integer idZona;
    public static Zona zona;
    public static String rutaActual = "";

    /**
     * Creates a new instance of PrincipalManagedBean
     */
    @PostConstruct
    public void init() {
        rutaActual = "Inicio";
        this.datosPrincipal = empleado;
    }

    public Date obtenerHoraDelSistema(){
        return new Date();
    }
    
    public Empleado getDatosPrincipal() {
        return datosPrincipal;
    }

    public void setDatosPrincipal(Empleado datosPrincipal) {
        this.datosPrincipal = datosPrincipal;
    }

    public String getNombreEmpleado() {
        System.out.println(" Empleado: " + empleado);
        nombreEmpleado = empleado.getNombre();
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public List<Menu> getListaMenu() {
        listaMenu = empleado.getIdRol().getMenuList();
        return listaMenu;
    }

    public void setListaMenu(List<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public String recuperacionCuenta() {
        return "recuperarContraseña.xhtml";
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        PrincipalManagedBean.idZona = idZona;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        zona = zona;
    }

    public String getRutaActual() {
        return rutaActual;
    }

    public void setRutaActual(String rutaActual) {
        this.rutaActual = rutaActual;
    }


    public void seleccionarZona(Zona zonaSeleccionada) {
        getSesion().setAttribute("idZona", zonaSeleccionada.getIdZona());
    }

}
