/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.AsistenciaReunion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface AsistenciaReunionFacadeLocal {

    void create(AsistenciaReunion asistenciaReunion);

    void edit(AsistenciaReunion asistenciaReunion);

    void remove(AsistenciaReunion asistenciaReunion);

    AsistenciaReunion find(Object id);

    List<AsistenciaReunion> findAll();

    List<AsistenciaReunion> findRange(int[] range);

    int count();
    
}
