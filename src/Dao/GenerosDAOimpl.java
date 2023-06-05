package Dao;

import java.util.ArrayList;
import java.util.List;

import Model.Generos;

public class GenerosDAOimpl implements GenerosDAO {

    public static List<Generos> listGeneros;

    static {
        listGeneros = new ArrayList<>();

        listGeneros.add(new Generos(00001, "Aventura"));
        listGeneros.add(new Generos(00002, "Romance"));
        listGeneros.add(new Generos(00003, "Novela"));
    }

    @Override
    public List<Generos> generos() {
        return listGeneros;
    }

    @Override
    public Generos getGeneros(int id) {
        for (Generos genero : listGeneros) {
            if (genero.getId() == id) {
                return genero;
            }
        }

        return null;
    }

    @Override
    public void save(Generos generos) {
        Generos otrosGeneros = new Generos(0, "Fecha Registro");
        otrosGeneros.setId(generos.getId());
        otrosGeneros.setGenero(generos.getGenero());
        listGeneros.add(otrosGeneros);
    }

    @Override
    public void update(Generos genero) {
        for (int i = 0; i < listGeneros.size(); i++) {
            if (listGeneros.get(i).getId() == genero.getId()) {
                listGeneros.set(i, genero);
                break;
            }
        }
    }

    public void delete(Generos generos) {
        listGeneros.remove(generos);
    }

}
