/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.etoc.opline.negocio.managed;

import co.com.etoc.opline.persistencia.dao.ClienteFacadeLocal;
import co.com.etoc.opline.persistencia.entidades.Cliente;
import co.com.etoc.opline.persistencia.entidades.Rol;
import co.com.etoc.opline.persistencia.entidades.TipoCliente;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ClienteManaged extends ValidaSesion implements Serializable {

    /**
     * Creates a new instance of EmpleadoManaged
     */
    private RolesManaged lr = new RolesManaged();
    private List<Cliente> listaCliente;
    private List<Cliente> filtroCliente;
    @EJB
    private ClienteFacadeLocal localCliente;
    //Atributos referentes a la BD. --> ARBD
    Rol tipoClientes;
    Integer tipoCliente;
    Integer activo;
    Integer idSelect;
    private String nombre;
    private String apellido;
    private String cedula;
    private String celular;
    private String telefono;
    private String correo = "@";
    private String clave;
    private String clave2;
    private Cliente datos;
    private Cliente datos2;
    private String listarPor;
    private Integer tipoCliente2;

    private String iconoAyD;
    private String tituloAyD;
    private String smsAyD;
    private Integer estadoAyD;
    //Fin atributos ARBD.

    //Atributos extras.
    private boolean completo;
    private boolean completoEditar;

    @PostConstruct
    //Se inicializa la lista que leerá la vista(JSF).
    public void init() {
//        PrincipalManagedBean pmb = new PrincipalManagedBean();
//        pmb.setRutaActual("Clientes\\Gestión de clientes");
        this.completo = true;
        this.completoEditar = true;
        listaCliente = localCliente.findAll();
        if (listaCliente.size() != 0) {
            datos = listaCliente.get(0);
        }
    }

    public ClienteManaged() {
    }

    //Método para guardar/insertar
    public String guardar() {
        //En esta variable se guardará el mensaje que se imprime como respuesta.        
        try {
            //Se crea un objeto que almacenará los datos recogidos en la vista.
            Cliente cliente = new Cliente();
            //Se asignan los datos de la vista al objeto creado anteriormente.                 
            cliente.setIdTipoCliente(new TipoCliente(tipoCliente));
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setCedula(cedula);
            cliente.setCelular(celular);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            //Se inserta el nuevo registro en la BD.
            this.localCliente.create(cliente);
            //Se asigna el mensaje de éxito de la operación en la variable mensaje.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado","Registro guardado."));
            //Se alimenta la lista, para que la vista pueda mostrar el nuevo empleado agregado. 
            this.limpiar();
        } catch (Exception e) {
            //Se asigna el mensaje de error de la operación en la variable mensaje.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registro no guardado.","Registro no guardado."));
        }
        //Se lanza el mensaje.
        this.init();                
        return "cliente.xhtml";
    }

    public void limpiar() {
        tipoCliente = -1;
        nombre = "";
        apellido = "";
        cedula = "";
        celular = "";
        telefono = "";
        correo = "@";
        clave = "";
        this.init();
        new ValidarFormularios().init();
    }

    public void prepararEdicion(Cliente cliente) {
        datos = cliente;
        tipoCliente2 = datos.getIdTipoCliente().getIdTipoCliente();
    }

    public String editar() {
        String mensaje;
        try {
            datos.setIdTipoCliente(new TipoCliente(tipoCliente2));
            localCliente.edit(datos);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro actualizado.","Registro actualizado."));
            this.limpiar();           
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro no actualizado.","Registro no actualizado."));
            System.out.println("Error en EmpleadoManaged al eliminar " + e.getMessage());            
        }
        this.init();        
        return "cliente.xhtml";
    }

    public void validar() {
        if (localCliente.comprobarDocumentoRepetido(cedula)) {
            completo = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El número de documento ya se encuentra registrado.", "El número de documento ya se encuentra registrado."));
        } else {
            completo = true;
        }
        if (completo) {
            this.completo = ValidarFormularios.validar(tipoCliente, nombre, apellido, cedula);
        }
        if (completo) {
            this.guardar();
        }
    }

    public void validarEdicion() {
        if (completoEditar) {
            this.completoEditar = ValidarFormularios.validar(tipoCliente2, datos.getNombre(), datos.getApellido(), datos.getCedula());
        }
        if (completoEditar) {
            this.editar();
        }
    }

    //Métodos set y get.
    //Dejará la lista empleado visible para la vista(JSF).
    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    //Filtro.
    public List<Cliente> getFiltroCliente() {
        return filtroCliente;
    }

    public void setFiltroCliente(List<Cliente> filtroCliente) {
        this.filtroCliente = filtroCliente;
    }

    //--------------------------------------------------------------------------
    //Metodos GET & SET para atributos referentes a la BD.
    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getTipoClientes() {
        return tipoClientes;
    }

    public void setTipoClientes(Rol tipoClientes) {
        this.tipoClientes = tipoClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    //Fin metodos SET & GET
    //--------------------------------------------------------------------------
    public Cliente getDatos() {
        return datos;
    }

    public void setDatos(Cliente datos) {
        this.datos = datos;
    }

    public String getListarPor() {
        return listarPor;
    }

    public void setListarPor(String listarPor) {
        this.listarPor = listarPor;
    }

    public String getIconoAyD() {
        return iconoAyD;
    }

    public void setIconoAyD(String iconoAyD) {
        this.iconoAyD = iconoAyD;
    }

    public String getTituloAyD() {
        return tituloAyD;
    }

    public void setTituloAyD(String tituloAyD) {
        this.tituloAyD = tituloAyD;
    }

    public String getSmsAyD() {
        return smsAyD;
    }

    public void setSmsAyD(String smsAyD) {
        this.smsAyD = smsAyD;
    }

    public Integer getEstadoAyD() {
        return estadoAyD;
    }

    public void setEstadoAyD(Integer estadoAyD) {
        this.estadoAyD = estadoAyD;
    }

    public Integer getIdSelect() {
        return idSelect;
    }

    public void setIdSelect(Integer idSelect) {
        this.idSelect = idSelect;
    }

    public Integer getTipoCliente2() {
        return tipoCliente2;
    }

    public void setTipoCliente2(Integer tipoCliente2) {
        this.tipoCliente2 = tipoCliente2;
    }

    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public boolean isCompletoEditar() {
        return completoEditar;
    }

    public void setCompletoEditar(boolean completoEditar) {
        this.completoEditar = completoEditar;
    }

}
