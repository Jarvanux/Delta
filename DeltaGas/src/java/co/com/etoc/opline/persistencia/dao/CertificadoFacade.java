/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Certificado;
import co.com.etoc.opline.persistencia.entidades.Pago;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class CertificadoFacade extends AbstractFacade<Certificado> implements CertificadoFacadeLocal {
    @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CertificadoFacade() {
        super(Certificado.class);
    }

    @Override
    public List<Certificado> consultarPorEmpleado(Integer idEmpleado) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from certificado where id_empleado = "+idEmpleado, Certificado.class);
        } catch (Exception e) {
        }
        return q.getResultList();
    }

    /**
     * Registra la petición.
     * @param idEmpleado
     * @param tablaReferencia
     * @param documentoSolicitante
     * @param fecha
     * @param respuesta 
     */
    @Override
    public void peticion(Integer idEmpleado,String tablaReferencia, String documentoSolicitante,Date fecha,String respuesta) {
        Query q = null;
        System.out.println("Id empleado: "+idEmpleado);
        try {                     
           
            DateFormat d = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
          
            q = em.createNativeQuery("INSERT INTO certificado (id_empleado,tabla_referencia,documento_solicitante,fecha_solicitud,respuesta,id_pago) VALUES (?,?,?,?,?,?)");
            q.setParameter(1, idEmpleado);
            q.setParameter(2, tablaReferencia);
            q.setParameter(3, documentoSolicitante);
            q.setParameter(4, fecha);
            q.setParameter(5, respuesta);
            q.setParameter(6, 0);
            q.executeUpdate();
            System.out.println("Inserto");
        } catch (Exception e) {
            System.out.println("Se presento un problema realizando la petición de un certificado"+e.getMessage());
        }                
    }    

    //Métodos creados por John Vanegas.
    @Override
    public List<Certificado> listarPor(String respuesta){
        Query q = null;
        try {
            q = em.createNativeQuery("Select * from certificado where respuesta = ?", Certificado.class);
            q.setParameter(1, respuesta);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
