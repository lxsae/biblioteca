package Dao;

import java.util.List;

import Model.Generos;

public interface GenerosDAO {
    List<Generos> generos();
    Generos getGeneros(int id);
    void save(Generos generos);
    void update(Generos generos);
    void delete(Generos generos);
}
