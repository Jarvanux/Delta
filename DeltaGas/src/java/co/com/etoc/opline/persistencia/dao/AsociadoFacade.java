/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Asociado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan Camilo
 */
@Stateless
public class AsociadoFacade extends AbstractFacade<Asociado> implements AsociadoFacadeLocal {

    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsociadoFacade() {
        super(Asociado.class);
    }

    @Override
    public void deshabilitar(int IdAsociado) {
        try {
            //Query q = em.createNativeQuery("UPDATE asociado SET id_estado = 2 WHERE id_asociado = ?", Asociado.class);
            //q.setParameter(1, IdAsociado);
            Query x = em.createQuery("UPDATE asociado SET id_estado = 2 WHERE id_asociado = :?");
            x.setParameter(1, IdAsociado);
            x.executeUpdate();
            //System.out.println("Consulta parametro1: "+q.getParameterValue(1));
            System.out.println("Consulta single result: " + x.getSingleResult());
            System.out.println("Consulta toString() " + x.toString());
        } catch (Exception e) {
            System.out.println("Error deshabilitar AsociadoFacade: " + e.getMessage());
        }
    }

    @Override
    public long consultarPagos(String documento) {
        Query q = null;
        long resultado = 0;
        try {
            q = em.createNativeQuery("SELECT id_asociado FROM asociado WHERE cedla = ? LIMIT 1");
            q.setParameter(1, documento);

        } catch (Exception e) {
            System.out.println("No consulto el id" + e.getMessage());
        }
        return q.getFirstResult();
    }
    
    @Override
    public Asociado consultarAsosicado(String cedula){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from asociado where cedula = ? limit 1",Asociado.class);
            q.setParameter(1, cedula);
            return (Asociado)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
