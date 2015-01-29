/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.ProductosFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Productos;
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
public class AlmacenManaged implements Serializable {

    public AlmacenManaged() {
    }

    @EJB
    ProductosFacadeLocal productFL;

    //Atributos
    List<Productos> lista;
    List<Productos> listaFiltro;
    Proveedores proveedor;
    int idProveedor;
    String codigo;
    String nombre;
    String descripcion;
    double cantidad;
    double valorInterno;
    double valorCliente;
    Date fecha;
    Productos producto;

    @PostConstruct
    public void init() {
        fecha = new Date();
        lista = productFL.findAll();
    }

    //Funciones
    public Date consultarFecha() {
        fecha = new Date();
        return fecha;
    }

    public String codigoProducto() {
        this.codigo = ("" + System.currentTimeMillis()).toString().substring(4);
        return codigo;
    }

    public void guardarProducto() {
        try {
            Productos p = new Productos();
            p.setCodigo(codigo);
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setIdProveedor(new Proveedores(idProveedor));
            p.setCantidad("" + cantidad);
            p.setValorCliente(valorCliente);
            p.setValorInterno(valorInterno);
            p.setFechaAgregado(fecha);
            productFL.create(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto registrado.", "Producto registrado."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto no registrado.", "Producto no registrado."));
            System.err.println("AlmacenManaged.guardarProducto() : Error >> " + e.getMessage());
        }
    }

    public void editarProducto() {
        Productos p = this.producto;
        if (p != null) {
            try {
                productFL.edit(producto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro editado!.", "Registro editado!."));
            } catch (Exception e) {
                System.err.println("AlmacenManaged.editarProducto() : Error >> " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo editar el registro", "No se pudo editar el registro."));
            }
        }
    }

    //Métodos set y get.
    public List<Productos> getLista() {
        return lista;
    }

    public String eliminar(Productos p) {
        try {
            this.producto = p;
            return "";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el registro!.", "No se pudo eliminar el registro!."));
            return "";
        }
    }

    public void eliminar() {
        productFL.remove(producto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el registro!.", "Se ha eliminado el registro"));
        this.init();
    }

    public void detalles(Productos p) {
        this.producto = p;
    }

    public void setLista(List<Productos> lista) {
        this.lista = lista;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorInterno() {
        return valorInterno;
    }

    public void setValorInterno(double valorInterno) {
        this.valorInterno = valorInterno;
    }

    public double getValorCliente() {
        return valorCliente;
    }

    public void setValorCliente(double valorCliente) {
        this.valorCliente = valorCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Productos> getListaFiltro() {
        return listaFiltro;
    }

    public void setListaFiltro(List<Productos> listaFiltro) {
        this.listaFiltro = listaFiltro;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}
