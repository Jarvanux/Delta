/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Estacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface EstacionFacadeLocal {

    void create(Estacion estacion);

    void edit(Estacion estacion);

    void remove(Estacion estacion);

    Estacion find(Object id);

    List<Estacion> findAll();

    List<Estacion> findRange(int[] range);

    int count();

    public Estacion consultarEstacionPorId(int idEstacion);
    
}
