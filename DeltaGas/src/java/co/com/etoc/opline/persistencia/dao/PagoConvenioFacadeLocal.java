/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.PagoConvenio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface PagoConvenioFacadeLocal {

    void create(PagoConvenio pagoConvenio);

    void edit(PagoConvenio pagoConvenio);

    void remove(PagoConvenio pagoConvenio);

    PagoConvenio find(Object id);

    List<PagoConvenio> findAll();

    List<PagoConvenio> findRange(int[] range);

    int count();
    
    List<PagoConvenio> listarPor(Integer idConvenio);
 
    public List<PagoConvenio> listarOrdenadamente();
    
    PagoConvenio ultimoPago(Integer idAsociado);
    
    PagoConvenio ultimoPago(Integer idVehiculo, Integer idConvenio);

    public boolean comprobarCodigoRepetido(Integer numeroRecibo);

    public List<PagoConvenio> tipoExclusivo(Integer listarPor, Integer idVehiculo);

    public List<PagoConvenio> listarOrdenadamente(Integer idVehiculo);
}
