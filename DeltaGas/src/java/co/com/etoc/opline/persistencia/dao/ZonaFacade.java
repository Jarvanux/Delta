/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Zona;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Steven
 */
@Stateless
public class ZonaFacade extends AbstractFacade<Zona> implements ZonaFacadeLocal {

    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZonaFacade() {
        super(Zona.class);
    }

    @Override
    public List<Zona> activos() {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from zona  where id_estado = 1 order by nombre", Zona.class);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return q.getResultList();
    }

    @Override
    public List<Zona> deshabilidatos() {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from zona  where id_estado = 2 order by nombre", Zona.class);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return q.getResultList();
    }

    @Override
    public void deshabilitar(Zona zona, Integer idZona, String tabla, Date fecha, String justificacion) {

        Query q = null;

        try {

        } catch (Exception e) {

        }

    }

    public Integer capacidadZona(Integer idZona) {
        Query q = null;

        try {
            q = em.createNativeQuery("select capacidad_vehiculos from zona where id_zona = "+idZona);
            return Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception e) {
            e.printStackTrace();

        }
        return -1;
    }
    
    @Override
    public Zona consultarZonaPorID(Integer idzona){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from zona where id_zona = ?",Zona.class);
            q.setParameter(1, idzona);
            return (Zona)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
