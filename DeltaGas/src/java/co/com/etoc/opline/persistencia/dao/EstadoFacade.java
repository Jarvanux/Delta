/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Estado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class EstadoFacade extends AbstractFacade<Estado> implements EstadoFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFacade() {
        super(Estado.class);
    }
    
}
