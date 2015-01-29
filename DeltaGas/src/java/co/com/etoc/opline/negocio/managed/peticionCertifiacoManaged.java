/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.CertificadoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import co.com.etoc.opline.persistencia.entidades.Conductor;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author andres
 */
@ManagedBean
@ViewScoped
public class peticionCertifiacoManaged extends ValidaSesion implements Serializable {

    private String tablaReferencia;
    private String documentoSolicitante;
    private String respuesta;
    private Conductor conductor;
    private Asociado asociado;
    private Certificado dato;

    @EJB
    private CertificadoFacadeLocal localCertificado;

    public peticionCertifiacoManaged() {
    }

    public String guardar() {
        final Calendar fechaDeHoy = Calendar.getInstance();
        Empleado em = this.empleado;        
        final Date date = new Date();
        try {
            tablaReferencia = "A";
            //A = tabla asociado en BD.
            //C = tabla conductor en BD.
            //E = tabla empleado en BD.
            //C y E no se implementaron debido a que el módulo no fue totalmente
            //finalizado.
            respuesta = "pendiente";
            localCertificado.peticion(em.getIdEmpleado(), tablaReferencia, asociado.getCedula(), fechaDeHoy.getTime(), respuesta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Petición enviada!.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo enviar la petición", ""));
        }
        return "peticionCertificado.xhtml";
    }

    public String getTablaReferencia() {
        return tablaReferencia;
    }

    public void setTablaReferencia(String tablaReferencia) {
        this.tablaReferencia = tablaReferencia;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getDocumentoSolicitante() {
        return documentoSolicitante;
    }

    public void setDocumentoSolicitante(String documentoSolicitante) {
        this.documentoSolicitante = documentoSolicitante;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
    }

}
