/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.VehiculoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Vehiculo;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author yhomii
 */
@ManagedBean
@ViewScoped
public class ReporteConvenioManaged {

    
    public ReporteConvenioManaged() {
    }
    
    private int idVehiculo;
    private Date fechaInicio;
    private Date fechaFin;
    
    @EJB
    VehiculoFacadeLocal vehiculoLocal;
    
    
    JasperPrint jasperPrint;
    
    public void init() throws JRException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/opline", "root", "");
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("id_vehiculo", idVehiculo);
        parametros.put("fecha_inicio", fechaInicio);
        parametros.put("fecha_fin", fechaFin);
        //Se obtiene la ruta del reporte.
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                    String rutaArchivo = servletContext.getRealPath("") + File.separator + "reportesOpline" + 
                            File.separator + "report" + File.separator + "reportes.jasper";
        jasperPrint = JasperFillManager.fillReport(rutaArchivo, parametros, cnn);
    }

    //Reporte en pdf
    public void PDF(ActionEvent action) throws JRException, ClassNotFoundException, SQLException, IOException {
        init();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        DateFormat formato = DateFormat.getDateInstance();
        response.addHeader("Content-disposition", "attachment; filename="+encontrarVehiculo().getPlaca()+"-"+formato.format(new Date()));
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public final Vehiculo encontrarVehiculo() {
     Vehiculo vehiculo = null;
     return vehiculo = vehiculoLocal.find(idVehiculo);
    }
    
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
