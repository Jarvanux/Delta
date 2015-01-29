/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Pago;
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
public class PagoFacade extends AbstractFacade<Pago> implements PagoFacadeLocal {

    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoFacade() {
        super(Pago.class);
    }

    @Override
    public List<Pago> tipoExclusivo(Integer listarPor) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_tipo_pago = ? order by numero_recibo desc", Pago.class);
            q.setParameter(1, listarPor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return q.getResultList();
    }
    
    @Override
    public List<Pago> tipoExclusivo(Integer listarPor,Integer idAsociado) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_tipo_pago = ? and id_asociado = ? order by numero_recibo desc", Pago.class);
            q.setParameter(1, listarPor);
            q.setParameter(2, idAsociado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return q.getResultList();
    }

    @Override
    public List<Pago> listarOrdenadamente() {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago order by numero_recibo desc", Pago.class);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("Se ha producido un error en el Facade al tratar de listar Ordenadmente.");
            return null;
        }

    }
    @Override
    public List<Pago> listarOrdenadamente(Integer idAsociado) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_asociado = ? order by numero_recibo desc", Pago.class);
            q.setParameter(1, idAsociado);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("Se ha producido un error en el Facade al tratar de listar Ordenadmente.");
            return null;
        }

    }
    @Override
    public Pago ultimoPago(Integer idAsociado) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_asociado = " + idAsociado + " order by fecha_pago desc limit 1 ", Pago.class);
        } catch (Exception e) {
        }
        return (Pago) q.getSingleResult();
    }

    @Override
    public Pago ultimoPago(Integer idAsociado, Integer idTipoPago) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_asociado = " + idAsociado + " and id_tipo_pago = " + idTipoPago + " order by fecha_pago desc limit 1 ", Pago.class);
            return (Pago) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public boolean comprobarCodigoRepetido(Integer numeroRecibo) {
        Query q = null;
        int resultado = 0;
        try {
            q = em.createNativeQuery("select count(*) as codigoUsado from pago where numero_recibo = ?");
            q.setParameter(1, numeroRecibo);
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
    public List<Pago> consultarPagosAsociado(Integer id){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where id_asociado = ?", Pago.class);
            q.setParameter(1, id);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Pago consultarPorId(Integer idPago){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from pago where numero_recibo = ? limit 1", Pago.class);
            q.setParameter(1, idPago);
            return (Pago)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }        
    }
}
