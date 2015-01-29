/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.CertificadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.PagoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import co.com.etoc.opline.persistencia.entidades.Conductor;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class SolicitarCertificadoManaged extends ValidaSesion implements Serializable {

    private String para;
    private Empleado empleadoM;
    private Asociado asociado;
    private List<Certificado> listaCertificados;
    private List<Certificado> filtroCertificados;
    private List<Empleado> empleadosSeleccionados;
    private List<Asociado> asociadosSeleccionados;
    private List<Conductor> conducotresSeleccionados;
    private String respuesta;
    private Conductor conductor;

    @EJB
    CertificadoFacadeLocal certificadoFL;

    @PostConstruct
    public void init() {
        PrincipalManagedBean pmb = new PrincipalManagedBean();
        pmb.setRutaActual("Certificado\\SolicitarCertificado");
//        listaCertificados = certificadoFL.consultarPorEmpleado(this.empleado.getIdEmpleado());
    }

    public void agregarEmpleadosALista() {
        if (this.empleadosSeleccionados.size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registros Agregados."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Ningun registro seleccionado!."));
        }
    }

    public void agregarConductoresALista() {
        if (this.empleadosSeleccionados.size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registros Agregados."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Ningun registro seleccionado!."));
        }
    }

    public void agregarAsociadosALista() {
        if (this.empleadosSeleccionados.size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registros Agregados."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Ningun registro seleccionado!."));
        }
    }

    public void solicitudEmpleado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Solicitud enviada!"));
    }

    public void solicitudAsociado() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Solicitud enviada!"));
    }

    public void solicitudConductor() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Solicitud enviada!"));
    }

    public void validarRespuesta(Certificado certificado) {
        try {
            if (certificado.getRespuesta().length() <= 0) {
                this.respuesta = "La solicitud aún no ha sido respondida.";
            } else {
                this.respuesta = "La solicitud ha sido respondida, precione el botón más para ver más detalles.";
            }
        } catch (Exception e) {
        }
    }

    public String solicitarPara() {
        return this.para;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public Empleado getEmpleadoM() {
        return empleadoM;
    }

    public void setEmpleadoM(Empleado empleadoM) {
        this.empleadoM = empleadoM;
    }

    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
    }

    public List<Certificado> getListaCertificados() {
        return listaCertificados;
    }

    public void setListaCertificados(List<Certificado> listaCertificados) {
        this.listaCertificados = listaCertificados;
    }

    public List<Certificado> getFiltroCertificados() {
        return filtroCertificados;
    }

    public void setFiltroCertificados(List<Certificado> filtroCertificados) {
        this.filtroCertificados = filtroCertificados;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public List<Empleado> getEmpleadosSeleccionados() {
        return empleadosSeleccionados;
    }

    public void setEmpleadosSeleccionados(List<Empleado> empleadosSeleccionados) {
        this.empleadosSeleccionados = empleadosSeleccionados;
    }

    public List<Asociado> getAsociadosSeleccionados() {
        return asociadosSeleccionados;
    }

    public void setAsociadosSeleccionados(List<Asociado> asociadosSeleccionados) {
        this.asociadosSeleccionados = asociadosSeleccionados;
    }

    public List<Conductor> getConducotresSeleccionados() {
        return conducotresSeleccionados;
    }

    public void setConducotresSeleccionados(List<Conductor> conducotresSeleccionados) {
        this.conducotresSeleccionados = conducotresSeleccionados;
    }

}
