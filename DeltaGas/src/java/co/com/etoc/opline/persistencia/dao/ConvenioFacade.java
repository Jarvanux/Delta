/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Convenio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class ConvenioFacade extends AbstractFacade<Convenio> implements ConvenioFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConvenioFacade() {
        super(Convenio.class);
    }
    
    @Override
    public Integer ConsultarNumeroVehiculos(Integer idConvenio){
        Query q = null;
        try {
            q = em.createNativeQuery("select distinct count(*) as numero_vehiculos from pago_convenio where id_convenio = "+idConvenio);
            return Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception e) {
            return -1;
        }        
    }
}
