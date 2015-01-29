package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.InformacionEmpresaFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jhonjaider1000
 */
@ManagedBean
@RequestScoped
public class InformacionEmpresaManaged extends ValidaSesion implements Serializable{
    public InformacionEmpresaManaged() {
    }
    
    private InformacionEmpresa informacionEmpresa;
    private String visibilidadBoton;
    private String visibilidadTextBox;
            
    @EJB
    private InformacionEmpresaFacadeLocal informacionFL;
    
    @PostConstruct
    public void init(){
        this.informacionEmpresa = informacionFL.findAll().get(0);
        this.visibilidadBoton = "block";
        this.visibilidadTextBox = "none";
    }
    
    public void editar(){
        try{
        informacionFL.edit(informacionEmpresa);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Información editada satisfactoriamente!."));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Error al editar información"));
        }
    }
    
    public void mostrarTextbox(){
        this.visibilidadBoton = "none";
        this.visibilidadTextBox = "block";
    }
    
    //Métodos set y get
    public InformacionEmpresa getInformacionEmpresa() {
        return informacionEmpresa;
    }

    public void setInformacionEmpresa(InformacionEmpresa informacionEmpresa) {
        this.informacionEmpresa = informacionEmpresa;
    }

    public String getVisibilidadBoton() {
        return visibilidadBoton;
    }

    public void setVisibilidadBoton(String visibilidadBoton) {
        this.visibilidadBoton = visibilidadBoton;
    }

    public String getVisibilidadTextBox() {
        return visibilidadTextBox;
    }

    public void setVisibilidadTextBox(String visibilidadTextBox) {
        this.visibilidadTextBox = visibilidadTextBox;
    }   
}
