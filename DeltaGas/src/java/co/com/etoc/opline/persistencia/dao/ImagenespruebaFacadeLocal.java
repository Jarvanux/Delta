/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Imagenesprueba;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ImagenespruebaFacadeLocal {

    void create(Imagenesprueba imagenesprueba);

    void edit(Imagenesprueba imagenesprueba);

    void remove(Imagenesprueba imagenesprueba);

    Imagenesprueba find(Object id);

    List<Imagenesprueba> findAll();

    List<Imagenesprueba> findRange(int[] range);

    int count();
    
}
