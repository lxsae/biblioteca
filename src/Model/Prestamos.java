package Model;

/**
 * UNIVERSIDAD DEL VALLLE
 *
 * @author
 * @author Ivan Alexis Noriega Cuadros 2126012-3743
 * @author
 * @Profesor
 */
public class Prestamos {

    private int id;
    private String fechaRegistro;
    private String fechaDevolucion;
    private String estado;

    public Prestamos(int id, String fechaRegistro, String fechaDevolucion, String estado) {

        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.fechaDevolucion = fechaDevolucion;

    }

    public int getId() {
        return id;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public String getEstado(){
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Fecha Devolucion: " + fechaRegistro + ", Fecha Registro: " + fechaDevolucion + ", Estado: " + estado;
    }
}
