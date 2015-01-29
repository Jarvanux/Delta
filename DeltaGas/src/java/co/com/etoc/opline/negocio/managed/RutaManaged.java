/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.RutaFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Ruta;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author andres
 */
@ManagedBean
@RequestScoped
public class RutaManaged extends ValidaSesion implements Serializable{

    private List<Ruta> listarRuta;
    private List<Ruta> filtrarRuta;

    private Ruta dato;

    @EJB
    public RutaFacadeLocal localRuta;

    public String nombre;
    public String descripcion;

    public RutaManaged() {
    }

    @PostConstruct
    public void init() {
        PrincipalManagedBean pmb = new PrincipalManagedBean();
        pmb.setRutaActual("Rutas\\Gestión rutas");
        listarRuta = localRuta.findAll();
        if(listarRuta.size() != 0){
               dato = listarRuta.get(0);
            };
    }

    public String guardar() {
        String mensaje;
        try {
            // Objeto ruta que almacenara los datos de la ruta
            Ruta ruta = new Ruta();
            // Datos a almacenar
            ruta.setNombreRuta(nombre);
            ruta.setDescripcion(descripcion);

            this.localRuta.create(ruta);
            
            mensaje = "La ruta fue insertada correctamente";
            
            this.limpiar();

        } catch (Exception e) {
            mensaje = "Error al insertar ruta " + e.getMessage();
        }

        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "ruta.xhtml";
    }

    public void limpiar() {
        nombre = "";
        descripcion = "";
        this.init();

    }

    public void prepararEdicion(Ruta ruta) {
        dato = ruta;
    }

    public String editar() {
        String mensaje;
        try {

            localRuta.edit(dato);
            mensaje = "Registro editado satisfactoriamente. ";
            this.limpiar();
        } catch (Exception e) {
            mensaje = "Error al editar ruta " + e.getMessage();
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return  "cliente.xhtml";
    }

    public List<Ruta> getListarRuta() {
        return listarRuta;
    }

    public void setListarRuta(List<Ruta> listarRuta) {
        this.listarRuta = listarRuta;
    }

    public List<Ruta> getFiltarRuta() {
        return filtrarRuta;
    }

    public void setFiltarRuta(List<Ruta> filtrarRuta) {
        this.filtrarRuta = filtrarRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ruta getDato() {
        return dato;
    }

    public void setDato(Ruta dato) {
        this.dato = dato;
    }

}
