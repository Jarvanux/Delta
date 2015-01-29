/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Vehiculo;
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
public class VehiculoFacade extends AbstractFacade<Vehiculo> implements VehiculoFacadeLocal {

    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }

    @Override
    public List<Vehiculo> vehiculosPorZona(Integer idZona) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from vehiculo where id_zona = ?", Vehiculo.class);
            q.setParameter(1, idZona);
        } catch (Exception e) {
        }
        return q.getResultList();
    }

    @Override
    public Integer capacidadZona(Integer idZona) {
        Query q = null;
        Integer capacidad = 0;
        try {
            q = em.createNativeQuery("select capacidad_vehiculos from zona where id_zona = " + idZona, Integer.class);
            capacidad = (Integer) q.getParameterValue(1);
        } catch (Exception e) {
        }
        return q.getFirstResult();
    }

    @Override
    public List<Vehiculo> ultimoRegistro() {
        Query q = em.createNativeQuery("SELECT * from vehiculo order by id_vehiculo desc limit 1", Vehiculo.class);
        return q.getResultList();
    }

    @Override
    public List<Vehiculo> listarActivos() {
        Query q = em.createNativeQuery("select * from asociado a inner join vehiculo v on a.id_asociado = v.id_vehiculo inner join conductor c on c.nic = v.nic inner join soat s on s.id_vehiculo = v.id_vehiculo inner join tecnomecanica t on t.id_vehiculo = v.id_vehiculo where a.id_estado = 1", Vehiculo.class);
        return q.getResultList();
    }
    
     @Override
    public List<Vehiculo> listarActivos2() {
        Query q = em.createNativeQuery("select * from asociado a inner join vehiculo v on a.id_asociado = v.id_vehiculo inner join conductor c on c.nic = v.nic inner join soat s on s.id_vehiculo = v.id_vehiculo inner join tecnomecanica t on t.id_vehiculo = v.id_vehiculo where a.id_estado = 1", Vehiculo.class);
        return q.getResultList();
    }
//    List<Number> counts = (List<Number>) q.getResultList();
//long count = counts.get(0).longValue();

   @Override
    public List<Vehiculo> listarDeshabilitados() {
        Query q = em.createNativeQuery("select * from asociado a inner join vehiculo v on a.id_asociado = v.id_vehiculo inner join conductor c on c.nic = v.nic inner join soat s on s.id_vehiculo = v.id_vehiculo inner join tecnomecanica t on t.id_vehiculo = v.id_vehiculo where a.id_estado = 2", Vehiculo.class);
        return q.getResultList();
    }

    @Override
    public List<Vehiculo> vehiculosPorAsociado(Integer idAsociado) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from vehiculo where id_asociado = "+idAsociado, Vehiculo.class);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
