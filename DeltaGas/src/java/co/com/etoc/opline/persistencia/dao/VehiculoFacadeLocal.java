/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Vehiculo;
import java.util.List;

/**
 *
 * @author jhonjaider1000
 */
public interface VehiculoFacadeLocal {

    void create(Vehiculo vehiculo);

    void edit(Vehiculo vehiculo);

    void remove(Vehiculo vehiculo);

    Vehiculo find(Object id);

    List<Vehiculo> findAll();

    List<Vehiculo> findRange(int[] range);

    int count();
    
    List<Vehiculo> vehiculosPorZona(Integer idZona);
    
    Integer capacidadZona(Integer idZona);
    
    List<Vehiculo> ultimoRegistro();
    
    List<Vehiculo> listarActivos();
    
    List<Vehiculo> listarActivos2();
    
    List<Vehiculo> listarDeshabilitados();
    
    List<Vehiculo> vehiculosPorAsociado(Integer idAsociado);
}
