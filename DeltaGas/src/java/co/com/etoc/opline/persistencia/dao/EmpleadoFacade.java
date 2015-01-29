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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> implements EmpleadoFacadeLocal {

    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    @Override
    public List<Empleado> activos() {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from empleado where id_estado = 1 order by apellido", Empleado.class);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return q.getResultList();
    }

    @Override
    public List<Empleado> deshabilitados() {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from empleado where id_estado = 2 order by apellido", Empleado.class);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return q.getResultList();
    }

    @Override
    public Empleado iniciarSesion(String cedula, String clave) throws SQLException {
        Query q = null;
        try {
            q = em.createNativeQuery("Select * from empleado where cedula = ? and clave = ?", Empleado.class);
            q.setParameter(1, cedula);
            q.setParameter(2, clave);
            return (Empleado) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Empleado validarDatos(String cedula, String correo, String celular) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from empleado where id_estado = 1 and cedula = ? and correo = ? and celular= ?", Empleado.class);
            q.setParameter(1, cedula);
            q.setParameter(2, correo);
            q.setParameter(3, celular);
            return (Empleado) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean editarClave(Empleado empleado) {
        Query q = null;
        try {
            q = em.createNativeQuery("update empleado set clave=? where id_empleado = 1");
            q.setParameter(1, empleado.getClave());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean comprobarDocumentoRepetido(String cedula) {
        Query q = null;
        int resultado = 0;
        try {
            q = em.createNativeQuery("select count(*) as documentoUsado from empleado where cedula = ?");
            q.setParameter(1, cedula);
            resultado = Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception e) {
            resultado = -1;
            e.printStackTrace();
        }
        if (resultado > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Empleado consultarEmpleadoPorID(Integer idEmpleado) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from empleado where id_empleado = ? limit 1", Empleado.class);
            q.setParameter(1, idEmpleado);
            return (Empleado) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Empleado> consultarTecnicos() {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from empleado where id_rol = 7", Empleado.class);
            return q.getResultList();
        } catch (Exception e) {
            System.err.println("EmpleadoFacade.consultarTecnicos() : Error >> "+e.getMessage());
            return null;
        }
    }

}
