/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface InformacionEmpresaFacadeLocal {

    void create(InformacionEmpresa informacionEmpresa);

    void edit(InformacionEmpresa informacionEmpresa);

    void remove(InformacionEmpresa informacionEmpresa);

    InformacionEmpresa find(Object id);

    List<InformacionEmpresa> findAll();

    List<InformacionEmpresa> findRange(int[] range);

    int count();        
    
}
