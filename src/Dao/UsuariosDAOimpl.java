package Dao;

import java.util.ArrayList;
import java.util.List;

import Model.Usuarios;

public class UsuariosDAOimpl implements UsuariosDAO {

    private List<Usuarios> listUseer;

    public UsuariosDAOimpl(){
        listUseer = new ArrayList<>();

        listUseer.add(new Usuarios(0001,"pepito","Estudiante"));
        listUseer.add(new Usuarios(0002,"jaimito","Estudiante"));
        listUseer.add(new Usuarios(0003,"messi","profesor"));
        listUseer.add(new Usuarios(0004,"neymar","empleado"));
        listUseer.add(new Usuarios(0005,"ronaldo","profesor"));
        
    }

    @Override
    public List<Usuarios> usuarios() {
    
        return listUseer;
        
    }
    @Override
    public Usuarios getUsuarios(int id) {
        for(Usuarios usuario : listUseer){
            if(usuario.getId() == id){
                return usuario;
            }
        }

        return null;
    }

    @Override
    public void save(Usuarios usuario) {
        Usuarios otrosUsuarios =  new Usuarios(0,"Nombre","Rol");
        otrosUsuarios.setId(usuario.getId());
        otrosUsuarios.setNombre(usuario.getNombre());
        otrosUsuarios.setRol(usuario.getRol());
        listUseer.add(otrosUsuarios);
    }

    @Override
    public void uptade(Usuarios usuario) {
        for(int i = 0; i < listUseer.size(); i++){
            if(listUseer.get(i).getId() == usuario.getId()){
                listUseer.set(i, usuario);
                break;
            }
        }
    }

    @Override
    public void delete(Usuarios usuario) {
        listUseer.remove(usuario);
    }
    
}
