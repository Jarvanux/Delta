/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Tecnomecanica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface TecnomecanicaFacadeLocal {

    void create(Tecnomecanica tecnomecanica);

    void edit(Tecnomecanica tecnomecanica);

    void remove(Tecnomecanica tecnomecanica);

    Tecnomecanica find(Object id);

    List<Tecnomecanica> findAll();

    List<Tecnomecanica> findRange(int[] range);

    int count();
    
}
