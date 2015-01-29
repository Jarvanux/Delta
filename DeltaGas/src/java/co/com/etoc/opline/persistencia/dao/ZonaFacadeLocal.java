/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Zona;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Steven
 */
@Local
public interface ZonaFacadeLocal {

    void create(Zona zona);

    void edit(Zona zona);

    void remove(Zona zona);

    Zona find(Object id);

    List<Zona> findAll();

    List<Zona> findRange(int[] range);

    int count();
     
    List<Zona>activos();
    
    List<Zona>deshabilidatos();
    
    void deshabilitar(Zona zona,Integer idZona, String tabla, Date fecha,String justificacion);
    
    Integer capacidadZona(Integer idZona);

    public Zona consultarZonaPorID(Integer idzona);
}
