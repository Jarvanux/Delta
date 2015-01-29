/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.ServicioTecnico;
import co.com.etoc.opline.persistencia.entidades.Sexos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ServicioTecnicoFacadeLocal {

    void create(ServicioTecnico servicio);

    void edit(ServicioTecnico servicio);

    void remove(ServicioTecnico servicio);

    ServicioTecnico find(Object id);   

    int count();

    public List<ServicioTecnico> listarServiciosRealizados(Integer idCliente);
    
}
