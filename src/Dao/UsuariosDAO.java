package Dao;

import java.util.List;

import Model.Usuarios;

public interface UsuariosDAO {
    List<Usuarios> usuarios();
    Usuarios getUsuarios(int id);
    void save(Usuarios usuario);
    void uptade(Usuarios usuario);
    void delete(Usuarios usuario);
}
