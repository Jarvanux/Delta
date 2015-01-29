/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.HistorialEstado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface HistorialEstadoFacadeLocal {

    void create(HistorialEstado historialEstado);

    void edit(HistorialEstado historialEstado);

    void remove(HistorialEstado historialEstado);

    HistorialEstado find(Object id);

    List<HistorialEstado> findAll();

    List<HistorialEstado> findRange(int[] range);

    int count();
    
}
