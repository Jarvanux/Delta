/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.TipoPago;
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
public class TipoPagoFacade extends AbstractFacade<TipoPago> implements TipoPagoFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPagoFacade() {
        super(TipoPago.class);
    }

    @Override
    public List<TipoPago> tipoPagoTabla() {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from tipo_pago where nombre_pago != 'Otros' and nombre_pago != 'afiliacion'", TipoPago.class);
        } catch (Exception e) {
        }
        return q.getResultList();
    }

    @Override
    public List<TipoPago> tipoPagoCombox() {
         Query q = null;
        try {
            q = em.createNativeQuery("select * from tipo_pago where nombre_pago != 'afiliacion'", TipoPago.class);
        } catch (Exception e) {
        }
        return q.getResultList();
    }
    
    
    
}
