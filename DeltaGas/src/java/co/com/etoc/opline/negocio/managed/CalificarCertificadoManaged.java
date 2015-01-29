package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.AsociadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.CertificadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.PagoConvenioFacadeLocal;
import co.com.etoc.opline.persistencia.dao.PagoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.VehiculoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import co.com.etoc.opline.persistencia.entidades.PagoConvenio;
import java.io.Serializable;
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
 * @author jhonjaider1000
 */
@ManagedBean
@ViewScoped
public class CalificarCertificadoManaged extends ValidaSesion implements Serializable{

    public CalificarCertificadoManaged() {
    }
    
    //Listas...
    private List<Certificado> listaSolicitudes;
    private List<Certificado> filtroSolicitudes;
    
    //Atributos...
    private Certificado idCertificado;
    private Asociado idAsociado;
    private Empleado idEmpleado;
    private Integer listarPor;
    private Integer listarPor2;
    
    //Listas para consultar pagos.
    private List<Pago> listaPagos;
    private List<PagoConvenio> listaPagosConvenio;    
    
    @EJB 
    private CertificadoFacadeLocal certificadoFL;
    @EJB
    private AsociadoFacadeLocal asociadoFL;
    @EJB
    private EmpleadoFacadeLocal empleadoFL;
    @EJB
    private PagoFacadeLocal pagoFL;
    @EJB
    private PagoConvenioFacadeLocal pagoConvenioFL;
    @EJB
    private VehiculoFacadeLocal vehiculoFL; 
    
    @PostConstruct
    public void init(){
        this.listarSolicitudes("pendiente");
    }
        
    public void listarSolicitudes(String respuesta){
        this.listaSolicitudes = certificadoFL.listarPor(respuesta);
    }
    
    public void aprobar(Certificado certi){
        try {            
            this.idCertificado = certi;
            this.idCertificado.setRespuesta("aprobado");
            this.idCertificado.setFechaRespuesta(new Date());
            this.certificadoFL.edit(idCertificado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Respuesta enviada!."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","No se pudo enviar la respuesta."));
        }
    }
    
    public void rechazar(Certificado certi){
        try {            
            this.idCertificado = certi;
            this.idCertificado.setRespuesta("rechazado");
            this.idCertificado.setFechaRespuesta(new Date());
            this.certificadoFL.edit(idCertificado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Respuesta enviada!."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"","No se pudo enviar la respuesta."));
        }
    }
        
    public void analizarProgreso(Certificado certificado){
        this.idAsociado = asociadoFL.consultarAsosicado(certificado.getDocumentoSolicitante());
        this.idEmpleado = empleadoFL.consultarEmpleadoPorID(certificado.getIdEmpleado().getIdEmpleado());        
        this.idCertificado = certificado;
    }
    
    
    public void listarPreviamente(){
        this.listarPor = 0;
        this.listarPor();
    }  
    public void listarPor() {
        if (listarPor > 0) {
            this.listaPagos = pagoFL.tipoExclusivo(listarPor,this.idAsociado.getIdAsociado());
        } else {
            this.listaPagos = pagoFL.listarOrdenadamente(this.idAsociado.getIdAsociado());
        }
    }        
    
    //Métodos set y get.
    public List<Certificado> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<Certificado> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public List<Certificado> getFiltroSolicitudes() {
        return filtroSolicitudes;
    }

    public void setFiltroSolicitudes(List<Certificado> filtroSolicitudes) {
        this.filtroSolicitudes = filtroSolicitudes;
    }

    public Certificado getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(Certificado idCertificado) {
        this.idCertificado = idCertificado;
    }        

    public Asociado getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(Asociado idAsociado) {
        this.idAsociado = idAsociado;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public List<Pago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(List<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }

    public List<PagoConvenio> getListaPagosConvenio() {
        return listaPagosConvenio;
    }

    public void setListaPagosConvenio(List<PagoConvenio> listaPagosConvenio) {
        this.listaPagosConvenio = listaPagosConvenio;
    }

    public Integer getListarPor() {
        return listarPor;
    }

    public void setListarPor(Integer listarPor) {
        this.listarPor = listarPor;
    }   
}
