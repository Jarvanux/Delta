/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.EmpleadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.InformacionEmpresaFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import co.com.etoc.opline.persistencia.entidades.Menu;
import co.com.etoc.opline.utilerias.UtilOne;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sena
 */
@ManagedBean
@ViewScoped
public class LoginManaged implements Serializable {

    /**
     * Creates a new instance of LoginManaged
     */
    public LoginManaged() {

    }

    @EJB
    private EmpleadoFacadeLocal empleadoFacade;

    @EJB
    private InformacionEmpresaFacadeLocal informacionFL;

    private InformacionEmpresa informacion;

    @PostConstruct
    public void init() {
        informacion = informacionFL.findAll().get(0); //Capturamos la información de la empresa
    }

    private String cedula;
    private String clave;
    private List<Menu> listaMenu;

    public void iniciarSesion() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        try {
            Empleado empleado = empleadoFacade.iniciarSesion(cedula, UtilOne.md5(clave));
            HttpSession sesion = (HttpSession) contexto.getExternalContext().getSession(true);
            sesion.setAttribute("empleado", empleado);
            if (empleado != null) {
                if (empleado.getIdEstado().getIdEstado() == 1) {
                    contexto.getExternalContext().redirect("paginamaestra.xhtml");
                } else if (empleado.getIdEstado().getIdEstado() == 2) {
                    contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Sus datos son válidos pero no tiene permisos para ingresar, contacte el administrador.","Sus datos son válidos pero no tiene permisos para ingresar, contacte el administrador."));
                }
            }else{
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error en el usuario o la clave","Error en el usuario o la clave"));
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginManaged.class.getName()).log(Level.SEVERE, null, ex);
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el usuario o clave", "Error en el usuario o la clave"));
        }
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<Menu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public InformacionEmpresa getInformacion() {
        return informacion;
    }

    public void setInformacion(InformacionEmpresa informacion) {
        this.informacion = informacion;
    }
}
