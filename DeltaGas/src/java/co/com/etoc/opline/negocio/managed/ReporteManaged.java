package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.entidades.Asociado;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
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

@ManagedBean
@ViewScoped
public class ReporteManaged {

    private Asociado asociado;
    private Date fechaInicio;
    private Date fechaFin;

//    @EJB
//    private PagoFacadeLocal pagoFLocal;

    JasperPrint jasperPrint;

    public void init() throws JRException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/opline", "root", "");
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idAsociado_Parameter", asociado.getIdAsociado());
        parametros.put("fechaInicio_Parameter", fechaInicio);
        parametros.put("fechaFin_Parameter", fechaFin);
        //Se obtiene la ruta del reporte.
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                    String rutaArchivo = servletContext.getRealPath("") + File.separator + "reportesOpline" + 
                            File.separator + "report" + File.separator + "pagosReport.jasper";
        jasperPrint = JasperFillManager.fillReport(rutaArchivo, parametros, cnn);
    }

    //Reporte en pdf
    public void PDF(ActionEvent action) throws JRException, ClassNotFoundException, SQLException, IOException {
        init();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        DateFormat formato = DateFormat.getDateInstance();
        response.addHeader("Content-disposition", "attachment; filename="+asociado.getNombre()+asociado.getApellido()+"-"+formato.format(new Date()));
        ServletOutputStream servletOutputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public TimeZone getTimeZone() {
        TimeZone zona = TimeZone.getDefault();
        return zona;
    }

    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
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
