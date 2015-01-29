/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import javax.faces.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author andres
 */
@ManagedBean
@ViewScoped
public class CertificadoManaged {

    /* que parametros resive ??
     mi idea era que recibiera :p el id del asociado pero por 
     lo que veo se lista es lo de la tabla certificado
     así qeu la cedula, deme un momentico ahora
    
    
     */
    private String cedula;

    public void obtnerCedula(String cedula) {
        this.cedula = cedula;
    }
    private JasperPrint jasperPrint;

    public void init() throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/opline", "opline", "opline");
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("cedula", cedula);
        //Se obtiene la ruta del reporte.
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String rutaArchivo = servletContext.getRealPath("") + File.separator + "reportesOpline"
                + File.separator + "report" + File.separator + "reportCertificado.jasper";
        jasperPrint = JasperFillManager.fillReport(rutaArchivo, parametros, cnn);

    }

    //Reporte en pdf
    public void generarReporte(ActionEvent action) throws JRException, ClassNotFoundException, SQLException, IOException {
        init();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
    }

    public CertificadoManaged() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

}
