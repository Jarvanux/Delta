/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Proveedores;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface ProveedoresFacadeLocal {

    void create(Proveedores proveedores);

    void edit(Proveedores proveedores);

    void remove(Proveedores proveedores);

    Proveedores find(Object id);

    List<Proveedores> findAll();

    List<Proveedores> findRange(int[] range);

    int count();
    
}
