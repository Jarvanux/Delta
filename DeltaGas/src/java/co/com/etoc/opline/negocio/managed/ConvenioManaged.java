/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.entidades.Convenio;
import co.com.etoc.opline.persistencia.dao.ConvenioFacadeLocal;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
public class ConvenioManaged extends ValidaSesion implements Serializable {

    private List<Convenio> listaConvenio;
    private List<Convenio> filtroConvenio;

    @EJB
    private ConvenioFacadeLocal convenioFL;
    private Integer idConvenio;
    private String nombreConvenio;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Convenio convenio;

    //Atributo para validar.
    private boolean completo;

    @PostConstruct
    public void init() {
        PrincipalManagedBean pmb = new PrincipalManagedBean();
        pmb.setRutaActual("Convenio\\Gestión Convenios");
        this.completo = true;
        listaConvenio = convenioFL.findAll();
        if (listaConvenio.size() > 0) {
            convenio = listaConvenio.get(0);
        }
    }

    public String guardar() {
        String mensaje;
        try {
            Convenio datos = new Convenio();
            datos.setNombreConvenio(nombreConvenio);
            datos.setDescripcion(descripcion);
            datos.setFechaInicio(fechaInicio);
            datos.setFechaFin(null);
            convenioFL.create(datos);
            mensaje = "Registro creado satisfactoriamente.";
            this.limpiar();
        } catch (Exception e) {
            System.out.println("Error ConvenioManaged: " + e.getMessage());
            mensaje = "No se pudo crear el registro.";
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "convenio.xhtml";
    }

    public void prepararEdicion(Convenio convenio) {
        this.convenio = convenio;
    }

    public String editar() {
        String mensaje;
        try {
            convenioFL.edit(convenio);
            mensaje = "Se ha editado el registro satisfactoriamente.";
        } catch (Exception e) {
            System.out.println("Error ConvenioManaged: " + e.getMessage());
            mensaje = "No se pudo editar el registro.";
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "convenio.xhtml";
    }

    public void limpiar() {
        this.nombreConvenio = "";
        this.descripcion = "";
        this.fechaInicio = null;
        this.fechaFin = null;
        this.init();
        new ValidarFormularios().init();
    }

    public String consultarNumeroVehiculos() {
        return convenioFL.ConsultarNumeroVehiculos(convenio.getIdConvenio()).toString();
    }

    public void validar() {
        this.completo = ValidarFormularios.validar(nombreConvenio, descripcion, fechaInicio, fechaFin);
        if (completo) {
            this.guardar();
        }
    }

    //METODOS GET AND SET.        
    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public List<Convenio> getListaConvenio() {
        return listaConvenio;
    }

    public void setListaConvenio(List<Convenio> listaConvenio) {
        this.listaConvenio = listaConvenio;
    }

    public List<Convenio> getFiltroConvenio() {
        return filtroConvenio;
    }

    public void setFiltroConvenio(List<Convenio> filtroConvenio) {
        this.filtroConvenio = filtroConvenio;
    }

    public boolean getCompleto(){
        return this.completo;
    }
    
    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

}
