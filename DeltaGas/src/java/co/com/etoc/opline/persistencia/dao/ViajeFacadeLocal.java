/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Viaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ViajeFacadeLocal {

    void create(Viaje viaje);

    void edit(Viaje viaje);

    void remove(Viaje viaje);

    Viaje find(Object id);

    List<Viaje> findAll();

    List<Viaje> findRange(int[] range);

    int count();
    
}
