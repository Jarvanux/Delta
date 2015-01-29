/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.DetallesEncuestaAplicada;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface DetallesEncuestaAplicadaFacadeLocal {

    void create(DetallesEncuestaAplicada detallesEncuestaAplicada);

    void edit(DetallesEncuestaAplicada detallesEncuestaAplicada);

    void remove(DetallesEncuestaAplicada detallesEncuestaAplicada);

    DetallesEncuestaAplicada find(Object id);

    List<DetallesEncuestaAplicada> findAll();

    List<DetallesEncuestaAplicada> findRange(int[] range);

    int count();
    
}
