package co.com.etoc.opline.persistencia.entidades;

/**
 *
 * @author jhonjaider1000
 */
public class EstadoCertificado {
    private String idEstado;
    private String estado;

    public EstadoCertificado() {
    }
    
    public EstadoCertificado(String idEstado) {
        this.idEstado = idEstado;
    }
    
    public EstadoCertificado(String idEstado, String estado) {
        this.idEstado = idEstado;
        this.estado = estado;
    }  
    
    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    
    
}
