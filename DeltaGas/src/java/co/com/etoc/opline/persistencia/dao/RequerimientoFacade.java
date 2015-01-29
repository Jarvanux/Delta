/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Requerimiento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class RequerimientoFacade extends AbstractFacade<Requerimiento> implements RequerimientoFacadeLocal {

    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequerimientoFacade() {
        super(Requerimiento.class);
    }

    public Requerimiento consultarPorID(int idReq) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from requerimiento where id_requerimiento = ?", Requerimiento.class);
            q.setParameter(1, idReq);
            return (Requerimiento) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
