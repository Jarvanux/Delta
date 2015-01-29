/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.HistorialEstadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.ZonaFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Convenio;
import co.com.etoc.opline.persistencia.entidades.Estado;
import co.com.etoc.opline.persistencia.entidades.HistorialEstado;
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
 * @author sena
 */
@ManagedBean
@ViewScoped
public class ZonaManaged extends ValidaSesion implements Serializable{

    /**
     * Creates a new instance of ZonaManaged
     */
    private List<Zona> listaZonas;
    private List<Zona> filtroZonas;

    @EJB
    private ZonaFacadeLocal localZona;
    private HistorialEstadoFacadeLocal historialEstadoFL;
    //Atributos referenciados a la base de datos
    private int capacidadVehiculos;
    private String nombreZona;
    private String ubicacion;
    private String carga;
    private Zona zona;
    private Zona zonaEditar;
    private Zona datos2;   
    private String listarPor;
    public Integer idZona;
    private Zona zonaEnviar;

    private String iconoAyD;
    private String tituloAyD;
    private String smsAyD;
    private Integer estadoAyD;
    private String justificacion;
    private String estado;

    private boolean completo = false;
    //Fin de los atributos

    
    @PostConstruct
    public void init() {
        PrincipalManagedBean pmb = new PrincipalManagedBean();
        pmb.setRutaActual("Zonas\\Gestión de zona");
        listaZonas = localZona.activos();
        if (listaZonas.size() != 0) {
            zonaEditar = listaZonas.get(0);
        } else {
            listaZonas = localZona.deshabilidatos();
            zonaEditar = listaZonas.get(0);
        }
        this.listarPor = "Activos";
        this.listarPreferencia();
    }
    
    public String dehabilitarZona(){
        PrincipalManagedBean.idZona = zonaEnviar.getIdZona();
        return "deshabilitarZona.xhtml";
    }

    public String listarPreferencia() {
        try {
            if (listarPor.equals("Activos")) {
                listaZonas = localZona.activos();
                this.iconoAyD = "ui-icon-circle-close";
                this.tituloAyD = "Deshabilitar";
                this.smsAyD = "Al deshabilitar el registro quedará invisible "
                        + "ante el sistema para las futuras consultas. "
                        + "¿Está seguro de realizar este cambio?";
                this.estadoAyD = 2;
            } else {
                listaZonas = localZona.deshabilidatos();
                this.iconoAyD = "ui-icon-circle-check";
                this.tituloAyD = "Habilitar";
                this.smsAyD = "Al habilitar el registro quedará visible "
                        + "ante el sistema para las futuras consultas. "
                        + "¿Está seguro de realizar este cambio?";
                this.estadoAyD = 1;
            }
        } catch (Exception e) {
            System.out.println("Error listarPorPreferencia " + e.getMessage());
        }
        return "zona.xhtml";
    }
    
    //Método para insertar una zona
    public String insertar() {

        String mensaje;
        try {

            Zona insertar = new Zona();
            insertar.setIdEstado(new Estado(1));
            insertar.setNombre(nombreZona);
            insertar.setCapacidadVehiculos(capacidadVehiculos);
            insertar.setNombre(nombreZona);
            insertar.setUbicacion(ubicacion);
            insertar.setCarga(carga);
            this.localZona.create(insertar);
            mensaje = "Registro insertado correctamente.";
            this.limpiar();
        } catch (Exception e) {
            mensaje = "No se pudo registrar el registro." + e.getMessage();
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "zona.xhtml";
    }
    public void prepararEdicion(Zona zona) {
        this.zona = zona;
    }

    public void limpiar() {

        //capacidadVehiculos = "";
        nombreZona = "";
        ubicacion = "";
        carga = "";
        capacidadVehiculos = 0;        
    }

    public void fijarZona(Integer idZona){
        this.idZona = idZona;
        FacesMessage msg = new FacesMessage("Id zona = "+idZona);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public String editar() {
        String mensaje;
        try {
            localZona.edit(zonaEditar);
            mensaje = "Registro editado satisfactoriamente.";
            this.limpiar();
        } catch (Exception e) {
            System.out.println("Error en ZonaManaged al eliminar " + e.getMessage());
            mensaje = "No se pudo deshabilitar el registro.";
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "zona.xhtml";
    }

    public void prepareDatos(Zona zona) {
        this.datos2 = zona;
    }

    //Metodo deshabilitar.
   
    
    
    //Metodos get y set
    
    

    public String getEstado() {
        return estado;
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    public List<Zona> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(List<Zona> listaZonas) {
        this.listaZonas = listaZonas;
    }

    public List<Zona> getFiltroZonas() {
        return filtroZonas;
    }

    public void setFiltroZonas(List<Zona> filtroZonas) {
        this.filtroZonas = filtroZonas;
    }

    public ZonaFacadeLocal getLocalZona() {
        return localZona;
    }

    public void setLocalZona(ZonaFacadeLocal localZona) {
        this.localZona = localZona;
    }

    public HistorialEstadoFacadeLocal getHistorialEstadoFL() {
        return historialEstadoFL;
    }

    public void setHistorialEstadoFL(HistorialEstadoFacadeLocal historialEstadoFL) {
        this.historialEstadoFL = historialEstadoFL;
    }

    public int getCapacidadVehiculos() {
        return capacidadVehiculos;
    }

    public void setCapacidadVehiculos(int capacidadVehiculos) {
        this.capacidadVehiculos = capacidadVehiculos;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Zona getZonaEditar() {
        return zonaEditar;
    }

    public void setZonaEditar(Zona datos) {
        this.zonaEditar = datos;
    }

    public Zona getDatos2() {
        return datos2;
    }

    public void setDatos2(Zona datos2) {
        this.datos2 = datos2;
    }

    public String getListarPor() {
        return listarPor;
    }

    public void setListarPor(String listarPor) {
        this.listarPor = listarPor;
    }

    public String getIconoAyD() {
        return iconoAyD;
    }

    public void setIconoAyD(String iconoAyD) {
        this.iconoAyD = iconoAyD;
    }

    public String getTituloAyD() {
        return tituloAyD;
    }

    public void setTituloAyD(String tituloAyD) {
        this.tituloAyD = tituloAyD;
    }

    public String getSmsAyD() {
        return smsAyD;
    }

    public void setSmsAyD(String smsAyD) {
        this.smsAyD = smsAyD;
    }

    public Integer getEstadoAyD() {
        return estadoAyD;
    }

    public void setEstadoAyD(Integer estadoAyD) {
        this.estadoAyD = estadoAyD;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public Zona getZonaEnviar() {
        return zonaEnviar;
    }

    public void setZonaEnviar(Zona zonaEnviar) {
        this.zonaEnviar = zonaEnviar;
    }
    
}
