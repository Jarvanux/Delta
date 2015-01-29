/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.ServicioTecnico;
import co.com.etoc.opline.persistencia.entidades.Sexos;
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
public class ServicioTecnicoFacade extends AbstractFacade<ServicioTecnico> implements ServicioTecnicoFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioTecnicoFacade() {
        super(ServicioTecnico.class);
    }
    
    /**
     * Lista los servicios pedidos, solicitados y/o realizados por un cliente.
     * @param idCliente
     * @return Lista de objetos ServicioTecnico, de todos los registros consultados de un cliente.
     */
    @Override
    public List<ServicioTecnico> listarServiciosRealizados(Integer idCliente){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from servicio_tecnico where id_cliente = ? order by fecha_registro desc",ServicioTecnico.class);
            q.setParameter(1, idCliente);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
