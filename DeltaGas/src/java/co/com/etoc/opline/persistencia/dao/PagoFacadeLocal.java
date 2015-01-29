/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Pago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface PagoFacadeLocal {

    void create(Pago pago);

    void edit(Pago pago);

    void remove(Pago pago);

    Pago find(Object id);

    List<Pago> findAll();

    List<Pago> findRange(int[] range);

    int count();
    
    List<Pago> tipoExclusivo(Integer listarPor);
    
    List<Pago> listarOrdenadamente();
 
    Pago ultimoPago(Integer idVehiculo);        
    
    Pago ultimoPago(Integer idAsociado, Integer idTipoPago);

    public boolean comprobarCodigoRepetido(Integer codigoRecibo);

    public List<Pago> consultarPagosAsociado(Integer id);

    public Pago consultarPorId(Integer idPago);

    public List<Pago> tipoExclusivo(Integer listarPor, Integer idAsociado);

    public List<Pago> listarOrdenadamente(Integer idAsociado);
}
