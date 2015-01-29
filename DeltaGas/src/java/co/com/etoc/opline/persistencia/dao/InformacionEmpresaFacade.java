/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class InformacionEmpresaFacade extends AbstractFacade<InformacionEmpresa> implements InformacionEmpresaFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformacionEmpresaFacade() {
        super(InformacionEmpresa.class);
    }      
    
}
