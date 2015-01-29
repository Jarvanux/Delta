/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Estacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class EstacionFacade extends AbstractFacade<Estacion> implements EstacionFacadeLocal {

    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstacionFacade() {
        super(Estacion.class);
    }

    @Override
    public Estacion consultarEstacionPorId(int idEstacion) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from estacion where id_estacion = ?", Estacion.class);
            q.setParameter(1, idEstacion);
            return (Estacion) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("EstacionFacade.consultarEstacionPorID(int idEstacion) : Error >> "+e.getMessage());
            return null;
        }
    }
}
