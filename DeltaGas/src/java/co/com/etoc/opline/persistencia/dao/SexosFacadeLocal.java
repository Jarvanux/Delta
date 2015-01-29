/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Sexos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface SexosFacadeLocal {

    void create(Sexos sexos);

    void edit(Sexos sexos);

    void remove(Sexos sexos);

    Sexos find(Object id);

    List<Sexos> findAll();

    List<Sexos> findRange(int[] range);

    int count();
    
}
