
package Model;

/**
 *
 * @author leand
 */
public class Recursos {
    private String titulo;
    private String ISBN;
    private String tipo;
    private String genero;
    private String autor;

    public Recursos(String titulo, String ISBN, String tipo, String genero, String autor){
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.tipo = tipo;
        this.genero = genero;
        this.autor = autor;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getISBN(){
        return ISBN;
    }

    public String getTipo(){
        return tipo;
    }

    public String getGenero(){
        return genero;
    }
    
    public String getAutor(){
        return autor;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", ISBN: " + ISBN + ", Tipo: " + tipo + ", Género: " + genero + ", Autor: " + autor;
    }
}

