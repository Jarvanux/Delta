/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    @Override
    public boolean comprobarDocumentoRepetido(String cedula) {
        Query q = null;
        int resultado = 0;
        try {
            q = em.createNativeQuery("select count(*) as documentoUsado from empleado where cedula = ? limit 1");
            q.setParameter(1, cedula);
            resultado = Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception e) {
            resultado = -1;
            e.printStackTrace();
        }                
        if(resultado > 0){
            return true;
        }else{
            return false;
        }
    }
    
}
