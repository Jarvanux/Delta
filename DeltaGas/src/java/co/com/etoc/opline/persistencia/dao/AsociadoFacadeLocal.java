/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Asociado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Camilo
 */
@Local
public interface AsociadoFacadeLocal {

    void create(Asociado asociado);

    void edit(Asociado asociado);

    void remove(Asociado asociado);
    
    void deshabilitar(int IdAsociado);

    Asociado find(Object id);
    
    List<Asociado> findAll();
  
    List<Asociado> findRange(int[] range);

    int count();

    public long consultarPagos(String documento);

    public Asociado consultarAsosicado(String cedula);
    
}
