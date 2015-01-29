/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Empleado;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Local
public interface EmpleadoFacadeLocal {

    void create(Empleado empleado);

    void edit(Empleado empleado);

    void remove(Empleado empleado);

    Empleado find(Object id);

    List<Empleado> findAll();

    List<Empleado> findRange(int[] range);

    int count();
    
    List<Empleado> activos();
    
    List<Empleado> deshabilitados(); 
    
    public Empleado iniciarSesion(String cedula, String clave) throws SQLException;
    
    public Empleado validarDatos(String cedula, String correo, String celular);
    
    public boolean editarClave(Empleado empleado);    

    public boolean comprobarDocumentoRepetido(String cedula);

    public Empleado consultarEmpleadoPorID(Integer idEmpleado);

    public List<Empleado> consultarTecnicos();
}
