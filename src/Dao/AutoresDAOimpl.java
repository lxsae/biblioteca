package Dao;

import java.util.ArrayList;
import java.util.List;

import Model.Autores;

public class AutoresDAOimpl implements AutoresDAO {

    public static List<Autores> listAutorr;

    static {
        listAutorr = new ArrayList<>();

        listAutorr.add(new Autores(0001,"pepito","perez",123456789,"calle 100 "));
        listAutorr.add(new Autores(0002,"jaimito","perez",987654321,"calle 3"));
        listAutorr.add(new Autores(003, "Ivan", "Noriega", 123123, "calle2"));
    }

    @Override
    public List<Autores> autores() {
        return listAutorr;
    }


    @Override
    public Autores getAutores(int id) {
        for(Autores usuario : listAutorr){
            if(usuario.getId() == id){
                return usuario;
            }
        }

        return null;
    }

    @Override
    public void save(Autores autores) {
        Autores otrosAutores =  new Autores(0,"Nombre","Apelledido",0,"Direccion" );
        otrosAutores.setId(autores.getId());
        otrosAutores.setNombre(autores.getNombre());
        otrosAutores.setApellido(autores.getApellido());        
        otrosAutores.setTelefono(autores.getTelefono());
        otrosAutores.setDireccion(autores.getDireccion()); 
        listAutorr.add(otrosAutores);
    }

    @Override
    public void update(Autores autores) {
        for(int i = 0; i < listAutorr.size(); i++){
            if(listAutorr.get(i).getId() == autores.getId()){
                listAutorr.set(i, autores);
                break;
            }
        }
    }

    @Override
    public void delete(Autores autores) {
        listAutorr.remove(autores);
    }
    
}
