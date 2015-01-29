/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.TipoPago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface TipoPagoFacadeLocal {

    void create(TipoPago tipoPago);

    void edit(TipoPago tipoPago);

    void remove(TipoPago tipoPago);

    TipoPago find(Object id);

    List<TipoPago> findAll();

    List<TipoPago> findRange(int[] range);

    int count();
    
    List<TipoPago> tipoPagoTabla();
    
    List<TipoPago> tipoPagoCombox();
}
