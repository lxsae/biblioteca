
package Dao;

import java.util.ArrayList;
import java.util.List;

import Model.Prestamos;

public class PrestamosDAOimpl implements PrestamosDAO{
     public static List<Prestamos> listPrestam;

    static {
        listPrestam = new ArrayList<>();

        listPrestam.add(new Prestamos(00001,"Fecha Registro","Fecha Devolucion", "Estado"));
        listPrestam.add(new Prestamos(00002,"Fecha Registro","Fecha Devolucion", "Estado"));
        listPrestam.add(new Prestamos(00003,"Fecha Registro","Fecha Devolucion", "Estado"));
    }

    @Override
    public List<Prestamos> prestamos() {
        return listPrestam;
    }


    @Override
    public Prestamos getPrestamos(int id) {
        for(Prestamos usuario : listPrestam){
            if(usuario.getId() == id){
                return usuario;
            }
        }

        return null;
    }

    @Override
    public void save(Prestamos prestamos) {
        Prestamos otrosAutores =  new Prestamos(0,"Fecha Registro","Fecha Devolucion", "Estado");
        otrosAutores.setId(prestamos.getId());
        otrosAutores.setFechaDevolucion(prestamos.getFechaRegistro());
        otrosAutores.setFechaRegistro(prestamos.getFechaDevolucion());     
        otrosAutores.setEstado(prestamos.getEstado());
        listPrestam.add(otrosAutores);
    }

    @Override
    public void update(Prestamos prestamos) {
        for(int i = 0; i < listPrestam.size(); i++){
            if(listPrestam.get(i).getId() == prestamos.getId()){
                listPrestam.set(i, prestamos);
                break;
            }
        }
    }

    @Override
    public void delete(Prestamos prestamos) {
        listPrestam.remove(prestamos);
    }
    
}
