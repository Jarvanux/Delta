/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Soat;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface SoatFacadeLocal {

    void create(Soat soat);

    void edit(Soat soat);

    void remove(Soat soat);

    Soat find(Object id);

    List<Soat> findAll();

    List<Soat> findRange(int[] range);

    int count();
    
}
