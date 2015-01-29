/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.TipoCliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface TipoClienteFacadeLocal {

    void create(TipoCliente tipoCliente);

    void edit(TipoCliente tipoCliente);

    void remove(TipoCliente tipoCliente);

    TipoCliente find(Object id);

    List<TipoCliente> findAll();

    List<TipoCliente> findRange(int[] range);

    int count();
    
}
