/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.ConductorFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Conductor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Juan Camilo
 */
@ManagedBean
@ViewScoped
public class ConductoresManaged implements Serializable {

    //Trae la lista de los conductores con el primer List, el segundo sirve para el filtro
    private List<Conductor> listaConductores;
    private List<Conductor> filtroConductores;

    //Objeto conductor para utilizar en el método editar
    private Conductor conductorSelecionado;
    private Conductor conductorSelecionado2;


    @EJB
    ConductorFacadeLocal conductorFLocal;

    @PostConstruct
    public void init() {
        listaConductores = conductorFLocal.findAll();
    }

    public void prepararEditar(Conductor conductor) {
        conductorSelecionado2 = conductor;
    }

    public void editar() {
        String mensaje;

        try {
            conductorFLocal.edit(conductorSelecionado2);
            mensaje = "Conductor actualizado con éxito";
        } catch (Exception e) {
            mensaje = "Se encontraron errores en el formulario, por favor corrigalos";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null ,mensaje));   
    }

    public List<Conductor> getListaConductores() {
        return listaConductores;
    }

    public void setListaConductores(List<Conductor> listaConductores) {
        this.listaConductores = listaConductores;
    }

    public List<Conductor> getFiltroConductores() {
        return filtroConductores;
    }

    public void setFiltroConductores(List<Conductor> filtroConductores) {
        this.filtroConductores = filtroConductores;
    }

    public Conductor getConductorSelecionado() {
        return conductorSelecionado;
    }

    public void setConductorSelecionado(Conductor conductorSelecionado) {
        this.conductorSelecionado = conductorSelecionado;
    }

    public Conductor getConductorSelecionado2() {
        return conductorSelecionado2;
    }

    public void setConductorSelccionado2(Conductor conductorSelecionado2) {
        this.conductorSelecionado2 = conductorSelecionado2;
    }
}
