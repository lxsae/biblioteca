package Dao;

import java.util.List;

import Model.Recursos;

public interface RecursosDAO {
    List<Recursos> recursos();
    Recursos getRecurso(String ISBN);
    void save(Recursos recurso);
    void update(Recursos recurso);
    void delete(Recursos recurso);
}

