package Dao;

import java.util.List;

import Model.Autores;

public interface AutoresDAO {
    List<Autores> autores();
    Autores getAutores(int id);
    void save(Autores autores);
    void update(Autores autores);
    void delete(Autores autores);
}
