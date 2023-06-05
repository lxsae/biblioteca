package Dao;

import java.util.ArrayList;
import java.util.List;

import Model.Recursos;

public class RecursosDAOimpl implements RecursosDAO {

    public static List<Recursos> listRecursos;

    static {
        listRecursos = new ArrayList<>();

        //Adiministradores
        listRecursos.add(new Recursos("1984", "9780451524935", "Libro", "Distopía", ""));
        listRecursos.add(new Recursos("Cien años de soledad", "9780307474728", "Libro", "Realismo magico", ""));
        listRecursos.add(new Recursos("Vogue", "NO APLICA", "Revista", "Moda y estilo de vida", ""));
      /*  listRecursos.add(new Recursos("", "", " ", " ", "Abierto"));
        listRecursos.add(new Recursos("", "", " ", " ", "Cerrado"));
        listRecursos.add(new Recursos("", "", " ", " ", "PA.Abierto"));
        listRecursos.add(new Recursos("", "", " ", " ", "PA.Cerrado")); */
    }

    @Override
    public List<Recursos> recursos() {

        return listRecursos;

    }

    @Override
    public Recursos getRecurso(String ISBN) {
        for (Recursos recursos : listRecursos) {
            if (recursos.getISBN().equals(ISBN)) {
                System.out.println(recursos);
                return recursos;
            }
        }

        return null;
    }

    @Override
    public void save(Recursos recursos) {
        Recursos otrosRecurso = new Recursos("Titulo", "ISBN", "Tipo", "Genero", "");
        otrosRecurso.setTitulo(recursos.getTitulo());
        otrosRecurso.setISBN(recursos.getISBN());
        otrosRecurso.setTipo(recursos.getTipo());
        otrosRecurso.setGenero(recursos.getGenero());
        otrosRecurso.setAutor(recursos.getAutor());
        listRecursos.add(otrosRecurso);
    }

    @Override
    public void update(Recursos recursos) {
        for (int i = 0; i < listRecursos.size(); i++) {
            if (listRecursos.get(i).getISBN().equals(recursos.getISBN())) {
                listRecursos.set(i, recursos);
                break;
            }
        }
    }

    @Override
    public void delete(Recursos recursos) {
        listRecursos.remove(recursos);
    }

}
