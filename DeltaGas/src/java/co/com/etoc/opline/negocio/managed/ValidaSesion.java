/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.entidades.Empleado;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sena
 */
public class ValidaSesion implements Serializable {

    protected Empleado empleado;

    @PostConstruct
    public void validarSesion() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            Object objEmpleado = getSesion().getAttribute("empleado");
            if (objEmpleado == null) {
                redireccionar("login.xhtml");
                FacesContext.getCurrentInstance().addMessage("Error", null);
            } else if (!request.getRequestURL().toString().contains("paginamaestra.xhtml") && request.getMethod().equalsIgnoreCase("GET")) {
                redireccionar("paginamaestra.xhtml");
                FacesContext.getCurrentInstance().addMessage("Error", null);
            } else {
                empleado = (Empleado) objEmpleado;
            }
        } catch (Exception e) {
            redireccionar("login.xhtml");
        }
    }

    public void validarPermisos() {
        Object objEmpleado = getSesion().getAttribute("empleado");
        if (objEmpleado == null) {
            redireccionar("login.xhtml");
        } else {
            empleado = (Empleado) objEmpleado;
        }
    }

    public void redireccionar(String pagina) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
        } catch (IOException ex) {
            Logger.getLogger(ValidaSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarSesion() {
        getSesion().invalidate();
        redireccionar("login.xhtml");
    }

    public HttpSession getSesion() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) contexto.getExternalContext().getSession(false);
        return sesion;
    }

}
