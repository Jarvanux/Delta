package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.RolFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Rol;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class RolesManaged extends ValidaSesion implements Serializable {

    private List<Rol> listaRol;
    private int roles;    

    @EJB
    private RolFacadeLocal rolFL;

    @PostConstruct
    public void init() {
        listaRol = rolFL.findAll();
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public int getRoles() {
        return roles;
    }
   
}
