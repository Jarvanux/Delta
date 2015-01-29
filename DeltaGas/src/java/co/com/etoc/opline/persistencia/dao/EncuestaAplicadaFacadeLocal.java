/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.EncuestaAplicada;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface EncuestaAplicadaFacadeLocal {

    void create(EncuestaAplicada encuestaAplicada);

    void edit(EncuestaAplicada encuestaAplicada);

    void remove(EncuestaAplicada encuestaAplicada);

    EncuestaAplicada find(Object id);

    List<EncuestaAplicada> findAll();

    List<EncuestaAplicada> findRange(int[] range);

    int count();
    
}
