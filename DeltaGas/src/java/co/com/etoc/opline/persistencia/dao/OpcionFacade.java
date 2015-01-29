/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Opcion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class OpcionFacade extends AbstractFacade<Opcion> implements OpcionFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OpcionFacade() {
        super(Opcion.class);
    }

    @Override
    public Opcion consultePagina(String url) {
        Query q = null;
        try {
            q = em.createNamedQuery("select * from opcion where url = ?", Opcion.class);                  
            return (Opcion)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
}
