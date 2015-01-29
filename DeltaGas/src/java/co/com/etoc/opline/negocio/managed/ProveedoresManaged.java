/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.ProveedoresFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Proveedores;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class ProveedoresManaged implements Serializable {

    public ProveedoresManaged() {
    }

    @EJB
    ProveedoresFacadeLocal proveedoresFL;
    //Atributos
    List<Proveedores> lista;
    List<Proveedores> listaFiltro;
    Proveedores proveedor;
    String nombres;
    String apellidos;
    String correo;
    String telefono;
    String celular;
    String direccion;
    String documento;

    @PostConstruct
    public void init() {
        this.lista = proveedoresFL.findAll();
    }

    public void guardar() {
        try {
            Proveedores p = new Proveedores();
            p.setNombres(nombres);
            p.setApellidos(apellidos);
            p.setCorreo(correo);
            p.setTelefono(telefono);
            p.setCelular(celular);
            p.setDireccion(direccion);
            p.setDocumento(documento);
            p.setFechaRegistro(new Date());
            proveedoresFL.create(p);
            this.init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proveedor registrado.", "Proveedor registrado."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo registrar el proveedor", "No se pudo registrar el proveedor."));
        }
    }        

    public void limpiarFormulario() {
        this.nombres = "";
        this.apellidos = "";
        this.correo = "";
        this.telefono = "";
        this.celular = "";
        this.direccion = "";
        this.documento = "";
    }

    public void detalles(Proveedores p){
        this.proveedor = p;
    }
    //Métodos set y get.
    public List<Proveedores> getLista() {
        return lista;
    }

    public void setLista(List<Proveedores> lista) {
        this.lista = lista;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedores> getListaFiltro() {
        return listaFiltro;
    }

    public void setListaFiltro(List<Proveedores> listaFiltro) {
        this.listaFiltro = listaFiltro;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
