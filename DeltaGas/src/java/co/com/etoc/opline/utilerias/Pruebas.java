/**
 *
 * @author jhonjaider1000
 */
package co.com.etoc.opline.utilerias;

import co.com.etoc.opline.persistencia.entidades.Empleado;
import co.com.etoc.opline.persistencia.entidades.InformacionEmpresa;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

class Pruebas {

    public static void main(String[] args) throws Exception {
        //Espacio para crear pruebas. :D
//        System.out.println("Antiguedad: "+UtilOne.diferencia(new Date()));
//        Scanner lea = new Scanner(System.in);
//        System.out.println("Clave a encriptar:");
//        String clave = lea.nextLine();
//        System.out.println("Clave encriptada: "+UtilOne.md5(clave));
//        
        Empleado empleado = new Empleado();
        empleado.setNombre("Juan");
        empleado.setApellido("Vargas");
        Date fechaSolicitud = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        String estacionName = "Estación 24";
        String reqString = "Fallo inesperado";
        String descripcion = "Ha pasado algo";
        String fechaGeneracion = df.format(fechaSolicitud);
        String mensaje = ""
                + "<div style=\"font-size: 14px; color: #444444;\">"
                + "Cordial saludo! el usuario " + empleado.getNombre() + " " + empleado.getApellido() + " con número de identidad: " + empleado.getCedula()
                + ", ha enviado una nueva solicitud con los siguientes parámetros: <br/>"
                + "<br>"
                + "Fecha Solicitud: " + df.format(fechaSolicitud)
                + "<br>"
                + "Estación de gas: " + estacionName
                + "<br/>"
                + "Requerimiento: " + reqString
                + "<br/>"
                + "Descripción: <textarea disabled='disabled' style='width:200px; height: 100px;'"
                + "value='" + descripcion
                + "'></textarea>"
                + "<br/>"
                + "<b>Fecha del mensaje:</b> " + fechaGeneracion + "."
                + "</div>";
//\"cid:image\"
        InformacionEmpresa informacion = new InformacionEmpresa();
        informacion.setNombre("Empresa x");
        informacion.setGerente("Alguien");
        informacion.setCelular("314124314");
        informacion.setTelefono("34134");
        informacion.setDireccion("Carrera 2341 12341 4s");
        informacion.setCiudad("Bogotá");
        informacion.setPais("Colombia");
        String firma = "<br/><br/><br/>"
                + "<div style=\"float: left; width: 200px; height: 100px; text-align:center; "
                + "\">"
                + "<img src=\"cid:image\" style=\"width:200px; height:80px; margin-top:6%\"/>"
                + "</div>"
                + "<div style=\"float: left; width: 300px; height: 100px; border-left: 2px "
                + "solid #0097CC; padding-left: 20px; padding-top:10px;\">"
                + "<font size=\"2\" >"
                + "<b>Opline </b>"
                + "Sistema de información."
                + "<br/>"
                + "<b>" + informacion.getNombre() + ".</b>"
                + "<br/>"
                + informacion.getGerente() + " - Gerente"
                + "<br/>"
                + "Móvil: " + informacion.getCelular()
                + "<br/>"
                + "Teléfono: " + informacion.getTelefono()
                + "<br/>"
                + informacion.getDireccion() + ", " + informacion.getCiudad() + ", " + informacion.getPais()
                + "</font>"
                + "<div>";
        
        
        System.out.println(mensaje+firma);
    }
}
