/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.persistencia.dao;

import java.util.Date;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jhonjaider1000
 */
@Stateless
public class ReporteFacade implements ReporteFacadeLocal {

  @PersistenceContext(unitName = "OplinePU")
    private EntityManager em;
  
  public byte[] generarReorte(int idAsociado,Date fechaInicio, Date fechaFin){
      try {
          em.persist(this);
          HashMap<String,Object> parametros = new HashMap<String,Object>();
          
      } catch (Exception e) {
          e.getMessage();
      }finally{
          em.flush();
      }
      return null;
  
  }
}
