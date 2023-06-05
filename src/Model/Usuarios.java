
package Model;


public class Usuarios {
    private int id;
    private String nombre;
    private String rol;


    public Usuarios(int id, String nombre, String rol){
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;

    }
        public int getId(){
        return id;
    }
        public void setId(int id){
        this.id = id;
    }
        
        
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }


    public String getRol(){
        return rol;
    }
    public void setRol(String rol){
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre +  ", Rol: " + rol;
    }
}
