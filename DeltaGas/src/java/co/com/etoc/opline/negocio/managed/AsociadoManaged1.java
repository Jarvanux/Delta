/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.AsociadoFacadeLocal;
import co.com.etoc.opline.persistencia.dao.ConductorFacadeLocal;
import co.com.etoc.opline.persistencia.dao.SoatFacadeLocal;
import co.com.etoc.opline.persistencia.dao.TecnomecanicaFacadeLocal;
import co.com.etoc.opline.persistencia.dao.VehiculoFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Asociado;
import co.com.etoc.opline.persistencia.entidades.Conductor;
import co.com.etoc.opline.persistencia.entidades.Estado;
import co.com.etoc.opline.persistencia.entidades.Soat;
import co.com.etoc.opline.persistencia.entidades.Tecnomecanica;
import co.com.etoc.opline.persistencia.entidades.Vehiculo;
import co.com.etoc.opline.persistencia.entidades.Zona;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Juan Camilo
 */
@ManagedBean
@ViewScoped
public class AsociadoManaged1 implements Serializable {

    //Datos de la tabla asociado
    private int idAsociado;
    private String nombreAsociado;
    private String apellidoAsociado;
    private Date fechaNacimientoAsociado;
    private String cedulaAsociado;
    private String expedidaCedulaAsociado;
    private Date fechaAfiliacion;
    private String antiguedadEnCorabastos;
    private String celularAsociado;
    private String correoAsociado;

    //Datos de la tabla conductor
    private int nit;
    private String nombreConductor;
    private String apellidoConductor;
    private Date fechaNacimientoConductor;
    private String nivelEscolaridad;
    private String direccion;
    private String correoConductor;
    private String celularConductor;
    private String telefono;
    private String barrio;
    private String arp;
    private String eps;
    private String genero;
    private String estadoCivil;
    private int numHijos;
    private String grupoSangineo;
    private String cedulaConductor;
    private String expedidaCedulaConductor;
    private boolean propietario;

    //Datos de la tabla Vehículo
    private int idZona;
    private String placa;
    private String numMotor;
    private String marca;
    private int modelo;
    private String numChasis;
    private String tipoServicio;
    private String tipoVehiculo;
    private String listaPor;

    //Datos de la tabla soat
    private String aseguradora;
    private Date fechaInicioSoat;
    private Calendar fechaFinSoat;

    //Datos de la tabla tecnomecanica
    private Date fechaInicioTecno;
    private Calendar fechaFinTecno;

    //Conductor autocompletar
    Conductor conductorAutocompletar;

    //Lista para tener los asociados, conductores y vehículos
    private List<Vehiculo> listaVehiculos;
    private List<Vehiculo> filtroVehiculos;


    //Objetos de las tablas
    Asociado asociado;
    Conductor conductor;
    Vehiculo vehiculo;
    Tecnomecanica tecnomecanica;
    Soat soat;

    //Objeto para editar y ver detalles
    Vehiculo vehiculoSeleccionado;
    Vehiculo vehiculoSeleccionado2;

    @EJB
    AsociadoFacadeLocal asociadoFLocal;

    @EJB
    ConductorFacadeLocal conductorFLocal;

    @EJB
    VehiculoFacadeLocal vehiculoFLocal;

    @EJB
    SoatFacadeLocal soatFLocal;

    @EJB
    TecnomecanicaFacadeLocal tecnoFLocal;

    @PostConstruct
    public void init() {
        listaVehiculos = vehiculoFLocal.listarActivos();
        if (listaVehiculos.isEmpty()) {
            listaVehiculos = vehiculoFLocal.listarDeshabilitados();

        }
        listaPor = "Activos";
        listarPorPreferencia();
    }

    
    public String listarPorPreferencia() {
        try {
            if (listaPor.equals("Activos")) {
                listaVehiculos = vehiculoFLocal.listarActivos();
            } else {
                listaVehiculos = vehiculoFLocal.listarDeshabilitados();
            }
        } catch (Exception e) {
            System.out.println("Error listar por preferencia " + e.getMessage());
        }
        return "asociado.xhtml";
    }
   
    public String guardar() {
        String mensaje;
        try {
            asociado = new Asociado();
            asociado.setIdEstado(new Estado(1));
            asociado.setNombre(nombreAsociado);
            asociado.setApellido(apellidoAsociado);
            asociado.setFechaNacimiento(fechaNacimientoAsociado);
            asociado.setCedula(cedulaAsociado);
            asociado.setExpedida(expedidaCedulaAsociado);
            asociado.setFechaAfiliacion(fechaAfiliacion);
            asociado.setAntiguedadCorabastos(antiguedadEnCorabastos);
            asociado.setCelular(celularAsociado);
            asociado.setCorreo(correoAsociado);
            asociadoFLocal.create(asociado);

            conductor = new Conductor();
            conductor.setNic(nit);
            conductor.setNombre(nombreConductor);
            conductor.setApellido(apellidoConductor);
            conductor.setFechaNacimiento(fechaNacimientoConductor);
            conductor.setNivelEscolaridad(nivelEscolaridad);
            conductor.setDireccion(direccion);
            conductor.setCorreo(correoConductor);
            conductor.setCelular(celularConductor);
            conductor.setTelefono(telefono);
            conductor.setBarrio(barrio);
            conductor.setArp(arp);
            conductor.setEps(eps);
            conductor.setGenero(genero);
            conductor.setEstadoCivil(estadoCivil);
            conductor.setNumHijos(numHijos);
            conductor.setGrupoSangineo(grupoSangineo);
            conductor.setCedula(cedulaConductor);
            conductor.setExpedida(expedidaCedulaConductor);
            conductor.setFoto(null);
            conductorFLocal.create(conductor);

            vehiculo = new Vehiculo();
            vehiculo.setIdZona(new Zona(idZona));
            asociado.getIdAsociado();
            vehiculo.setIdAsociado(asociado);
            vehiculo.setNic(new Conductor(nit));
            vehiculo.setPlaca(placa);
            vehiculo.setNumeroMotor(numMotor);
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            vehiculo.setNumeroChasis(numChasis);
            vehiculo.setTipoServicio(tipoServicio);
            vehiculo.setTipoVehiculo(tipoVehiculo);
            vehiculoFLocal.create(vehiculo);

            soat = new Soat();
            soat.setIdVehiculo(vehiculo.getIdVehiculo());
            soat.setAseguradora(aseguradora);
            soat.setFechaDeInicio(fechaInicioSoat);
            soat.setFechaVencimiento(fechaVencimientoSoat());
            soatFLocal.create(soat);

            tecnomecanica = new Tecnomecanica();
            tecnomecanica.setIdVehiculo(vehiculo.getIdVehiculo());
            tecnomecanica.setFechaDeInicio(fechaInicioTecno);
            tecnomecanica.setFechaDeVencimiento(fechaVencimientoTecto());
            tecnoFLocal.create(tecnomecanica);

            mensaje = "Nuevo asociado registrado con éxito";
        } catch (Exception e) {
            mensaje = "Se encontraron errores en el formulario de registro, por favor verifique que los campos estén diligenciados correctamente o que el NIC que intenta asignar ya fue asignado a otro conductor";
            System.out.println("AsociadoManaged error guardar " + e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
        return "asociado.xhtml";
    }

    public void preapararDatosEditar(Vehiculo vehiculo) {
        vehiculoSeleccionado = vehiculo;
    }

    public String actualizar() {
        String mensaje;
        try {
            asociadoFLocal.edit(vehiculoSeleccionado.getIdAsociado());
            vehiculoFLocal.edit(vehiculoSeleccionado);
            soatFLocal.edit(vehiculoSeleccionado.getSoat());
            tecnoFLocal.edit(vehiculoSeleccionado.getTecnomecanica());
            mensaje = "Registro actualizado con éxito";
        } catch (Exception e) {
            mensaje = "Se presentó un error al actualizar el asociado, \n"
                    + " por favor revise los campos del formulario y asegurese "
                    + " que todos estén diligenciados por completo y correctamente";
            System.out.println("AsociadoManaged error guardar " + e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
        return "asociado.xhtml";
    }

    public void prepareDatos(Vehiculo vehiculo) {
        vehiculoSeleccionado2 = vehiculo;
    }

    public String deshabilitar() {
        String mensaje;
        try {
            vehiculoSeleccionado2.getIdAsociado().setIdEstado(new Estado(2));
            asociadoFLocal.edit(vehiculoSeleccionado2.getIdAsociado());
            mensaje = "Asociado deshabilitado con éxito";
        } catch (Exception e) {
            mensaje = "Se presentó un error al deshabilitar el asociado";
            System.out.println("Error deshabilitar AsociadoManaged: " + e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
        return "asociado.xhtml";
    }

    public String habilitar() {
        String mensaje;
        try {
            vehiculoSeleccionado2.getIdAsociado().setIdEstado(new Estado(1));
            asociadoFLocal.edit(vehiculoSeleccionado2.getIdAsociado());
            mensaje = "Asociado habilitado con éxito";
        } catch (Exception e) {
            mensaje = "Se presentó un error al habilitar el asociado";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensaje));
        listaPor = "Deshabilitados";
        return "asociado.xhtml";
    }


    public void propietario() {
        if (propietario) {
            nombreConductor = nombreAsociado;
            apellidoConductor = apellidoAsociado;
            fechaNacimientoConductor = fechaNacimientoAsociado;
            cedulaConductor = cedulaAsociado;
            expedidaCedulaConductor = expedidaCedulaAsociado;
            celularConductor = celularAsociado;
            correoConductor = correoAsociado;
        } else {
            nombreConductor = null;
            apellidoConductor = null;
            fechaNacimientoConductor = null;
            cedulaConductor = null;
            expedidaCedulaConductor = null;
            celularConductor = null;
            correoConductor = null;
        }
    }

    public void onTabChange(TabChangeEvent event) {
        if (propietario) {
            nombreConductor = nombreAsociado;
            apellidoConductor = apellidoAsociado;
            fechaNacimientoConductor = fechaNacimientoAsociado;
            cedulaConductor = cedulaAsociado;
            expedidaCedulaConductor = expedidaCedulaAsociado;
            celularConductor = celularAsociado;
            correoConductor = correoAsociado;
        }
    }

    private Date fechaVencimientoSoat() {
        fechaFinSoat = Calendar.getInstance();
        fechaFinSoat.add(fechaFinSoat.YEAR, 1);
        return fechaFinSoat.getTime();
    }

    private Date fechaVencimientoTecto() {
        fechaFinTecno = Calendar.getInstance();
        fechaFinTecno.add(fechaFinTecno.YEAR, 1);
        return fechaFinTecno.getTime();
    }

    private static Date getFechaHoy() {
        Date fecha;
        return fecha = new Date();
    }

    //Métodos get y set ASOCIADO
    public int getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(int idAsociado) {
        this.idAsociado = idAsociado;
    }

    public String getNombreAsociado() {
        return nombreAsociado;
    }

    public void setNombreAsociado(String nombreAsociado) {
        this.nombreAsociado = nombreAsociado;
    }

    public String getApellidoAsociado() {
        return apellidoAsociado;
    }

    public void setApellidoAsociado(String apellidoAsociado) {
        this.apellidoAsociado = apellidoAsociado;
    }

    public Date getFechaNacimientoAsociado() {
        return fechaNacimientoAsociado;
    }

    public void setFechaNacimientoAsociado(Date fechaNacimientoAsociado) {
        this.fechaNacimientoAsociado = fechaNacimientoAsociado;
    }

    public String getCedulaAsociado() {
        return cedulaAsociado;
    }

    public void setCedulaAsociado(String cedulaAsociado) {
        this.cedulaAsociado = cedulaAsociado;
    }

    public String getExpedidaCedulaAsociado() {
        return expedidaCedulaAsociado;
    }

    public void setExpedidaCedulaAsociado(String expedidaCedulaAsociado) {
        this.expedidaCedulaAsociado = expedidaCedulaAsociado;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public String getAntiguedadEnCorabastos() {
        return antiguedadEnCorabastos;
    }

    public void setAntiguedadEnCorabastos(String antiguedadEnCorabastos) {
        this.antiguedadEnCorabastos = antiguedadEnCorabastos;
    }

    public String getCelularAsociado() {
        return celularAsociado;
    }

    public void setCelularAsociado(String celularAsociado) {
        this.celularAsociado = celularAsociado;
    }

    public String getCorreoAsociado() {
        return correoAsociado;
    }

    public void setCorreoAsociado(String correoAsociado) {
        this.correoAsociado = correoAsociado;
    }

    //Métodos get y set CONDUCTOR
    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getApellidoConductor() {
        return apellidoConductor;
    }

    public void setApellidoConductor(String apellidoConductor) {
        this.apellidoConductor = apellidoConductor;
    }

    public Date getFechaNacimientoConductor() {
        return fechaNacimientoConductor;
    }

    public void setFechaNacimientoConductor(Date fechaNacimientoConductor) {
        this.fechaNacimientoConductor = fechaNacimientoConductor;
    }

    public String getNivelEscolaridad() {
        return nivelEscolaridad;
    }

    public void setNivelEscolaridad(String nivelEscolaridad) {
        this.nivelEscolaridad = nivelEscolaridad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoConductor() {
        return correoConductor;
    }

    public void setCorreoConductor(String correoConductor) {
        this.correoConductor = correoConductor;
    }

    public String getCelularConductor() {
        return celularConductor;
    }

    public void setCelularConductor(String celularConductor) {
        this.celularConductor = celularConductor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getArp() {
        return arp;
    }

    public void setArp(String arp) {
        this.arp = arp;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public String getGrupoSangineo() {
        return grupoSangineo;
    }

    public void setGrupoSangineo(String grupoSangineo) {
        this.grupoSangineo = grupoSangineo;
    }

    public String getCedulaConductor() {
        return cedulaConductor;
    }

    public void setCedulaConductor(String cedulaConductor) {
        this.cedulaConductor = cedulaConductor;
    }

    public String getExpedidaCedulaConductor() {
        return expedidaCedulaConductor;
    }

    public void setExpedidaCedulaConductor(String expedidaCedulaConductor) {
        this.expedidaCedulaConductor = expedidaCedulaConductor;
    }

    public boolean isPropietario() {
        return propietario;
    }

    public void setPropietario(boolean propietario) {
        this.propietario = propietario;
    }

    //Métodos get y set VEHICULO
    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNumMotor() {
        return numMotor;
    }

    public void setNumMotor(String numMotor) {
        this.numMotor = numMotor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getNumChasis() {
        return numChasis;
    }

    public void setNumChasis(String numChasis) {
        this.numChasis = numChasis;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getListaPor() {
        return listaPor;
    }

    public void setListaPor(String listaPor) {
        this.listaPor = listaPor;
    }

    //Métodos get y set SOAT
    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public Date getFechaInicioSoat() {
        return fechaInicioSoat;
    }

    public void setFechaInicioSoat(Date fechaInicioSoat) {
        this.fechaInicioSoat = fechaInicioSoat;
    }

    public void setFechaFinSoat(Calendar fechaFinSoat) {
        this.fechaFinSoat = fechaFinSoat;
    }

    //Métodos get y set Tecnomecanica
    public Date getFechaInicioTecno() {
        return fechaInicioTecno;
    }

    public void setFechaInicioTecno(Date fechaInicioTecno) {
        this.fechaInicioTecno = fechaInicioTecno;
    }

    public void setFechaFinTecno(Calendar fechaFinTecno) {
        this.fechaFinTecno = fechaFinTecno;
    }

    //Métodos get y set para las listas de las consultas
    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public List<Vehiculo> getFiltroVehiculos() {
        return filtroVehiculos;
    }

    public void setFiltroVehiculos(List<Vehiculo> filtroVehiculos) {
        this.filtroVehiculos = filtroVehiculos;
    }

    //Mapeo get y set
    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Soat getSoat() {
        return soat;
    }

    public void setSoat(Soat soat) {
        this.soat = soat;
    }

    public TecnomecanicaFacadeLocal getTecnoFLocal() {
        return tecnoFLocal;
    }

    public void setTecnoFLocal(TecnomecanicaFacadeLocal tecnoFLocal) {
        this.tecnoFLocal = tecnoFLocal;
    }

    //Método get y set del objeto seleccionador
    public Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    public void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    public Vehiculo getVehiculoSeleccionado2() {
        return vehiculoSeleccionado2;
    }

    public void setVehiculoSeleccionado2(Vehiculo vehiculoSeleccionado2) {
        this.vehiculoSeleccionado2 = vehiculoSeleccionado2;
    }

    //Métodos get y set para las variables del formulario
   
    public Conductor getConductorAutocompletar() {
        return conductorAutocompletar;
    }

    public void setConductorAutocompletar(Conductor conductorAutocompletar) {
        this.conductorAutocompletar = conductorAutocompletar;
    }

}
