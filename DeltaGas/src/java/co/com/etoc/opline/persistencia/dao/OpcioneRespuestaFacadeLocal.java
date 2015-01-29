/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.OpcioneRespuesta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface OpcioneRespuestaFacadeLocal {

    void create(OpcioneRespuesta opcioneRespuesta);

    void edit(OpcioneRespuesta opcioneRespuesta);

    void remove(OpcioneRespuesta opcioneRespuesta);

    OpcioneRespuesta find(Object id);

    List<OpcioneRespuesta> findAll();

    List<OpcioneRespuesta> findRange(int[] range);

    int count();
    
}
