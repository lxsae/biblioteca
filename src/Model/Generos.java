/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Generos {

    private int id;
    private String Genero;

    public Generos(int id, String Genero) {

        this.id = id;
        this.Genero = Genero;

    }

    public int getId() {
        return id;
    }
    
    public String getGenero() {
        return Genero;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Genero: " + Genero;
    }
}
