package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import Dao.*;
import Model.*;
import Vista.*;

public class RecursosCtrl implements ActionListener {

    private VentanaRecursos vRecursos;

    private Recursos Recurss;

    private RecursosDAO Recursosdao;
    private AutoresDAO Autoresdao;
    private GenerosDAO Generosdao;

    public RecursosCtrl(VentanaRecursos vistaRecursos, Recursos Recurss, RecursosDAO Recursosdao, AutoresDAO Autoresdao, GenerosDAO generosdao) {
        this.vRecursos = vistaRecursos;
        this.Recurss = Recurss;
        this.Recursosdao = new RecursosDAOimpl();
        this.Autoresdao = new AutoresDAOimpl();
        this.Generosdao = new GenerosDAOimpl();

        this.vRecursos.btnAgregar.addActionListener(this);
        this.vRecursos.btnBuscar.addActionListener(this);
        this.vRecursos.btnListar.addActionListener(this);
        this.vRecursos.btnEliminar.addActionListener(this);
        this.vRecursos.btnActualizar.addActionListener(this);
        this.vRecursos.btnFinalizar.addActionListener(this);
        this.vRecursos.listVAutores.addActionListener(this);
        this.vRecursos.listVGenero.addActionListener(this);
        List<Autores> autores = Autoresdao.autores(); // Acceder a autores a través de Autoresdao
        List<Generos> generos = Generosdao.generos(); // Acceder a autores a través de Autoresdao

        for (Autores autor : autores) { // Usar la variable 'autores' en el bucle
            vRecursos.listVAutores.addItem(autor.getNombre());
        }
        for (Generos genero : generos) { // Usar la variable 'generos' en el bucle
            vRecursos.listVGenero.addItem(genero.getGenero());
        }

    }

    public void iniciar() {
        vRecursos.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vRecursos.btnAgregar) {
            if (!vRecursos.txtISBN.getText().isEmpty()) {
                String ISBNRecurso = vRecursos.txtISBN.getText();
                Recursos recursoEncontrado = Recursosdao.getRecurso(ISBNRecurso);
                if (recursoEncontrado != null) {
                    JOptionPane.showMessageDialog(null, "¡El recurso ya existe!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Recurss.setISBN(ISBNRecurso);
                    Recurss.setTitulo(vRecursos.txtTitulo.getText());
                    Recurss.setTipo(vRecursos.txtTipo.getText());
                    Recurss.setGenero(vRecursos.listVGenero.getSelectedItem().toString());
                    Recurss.setAutor(vRecursos.listVAutores.getSelectedItem().toString());

                    vRecursos.txtISBN.setText("");
                    vRecursos.txtTitulo.setText("");
                    vRecursos.txtTipo.setText("");
                    //vRecursos.txtGenero.setText("");
                    //String autor = vRecursos.listVAutores.getSelectedItem().toString();

                    Recursosdao.save(Recurss);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite un ISBN válido en el campo ISBN", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo ISBN está vacío", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource() == vRecursos.btnBuscar) {
            if (!vRecursos.txtISBN.getText().isEmpty()) {
                String ISBNRecurso = vRecursos.txtISBN.getText();
                Recursos recursoEncontrado = Recursosdao.getRecurso(ISBNRecurso);
                if (recursoEncontrado == null) {
                    JOptionPane.showMessageDialog(null, "¡El recurso no está registrado!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "¡El recurso está registrado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite el ISBN del recurso", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vRecursos.btnEliminar) {
            String ISBNRecurso = vRecursos.txtISBN.getText();
            Recursos recursoEncontrado = Recursosdao.getRecurso(ISBNRecurso);
            try {
                if (recursoEncontrado != null) {
                    Recursosdao.delete(recursoEncontrado);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite el ISBN del recurso", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vRecursos.btnActualizar) {
            if (!vRecursos.txtISBN.getText().isEmpty() && !vRecursos.txtTitulo.getText().isEmpty() && !vRecursos.txtTipo.getText().isEmpty()) {
                String ISBNRecurso = vRecursos.txtISBN.getText();
                Recursos recursoEncontrado = Recursosdao.getRecurso(ISBNRecurso);
                if (recursoEncontrado != null) {
                    recursoEncontrado.setTitulo(vRecursos.txtTitulo.getText());
                    recursoEncontrado.setTipo(vRecursos.txtTipo.getText());
                    Recursosdao.update(recursoEncontrado);
                    JOptionPane.showMessageDialog(null, "¡El recurso se ha actualizado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

                    vRecursos.txtISBN.setText("");
                    vRecursos.txtTitulo.setText("");
                    vRecursos.txtTipo.setText("");
                    Recurss.setGenero(vRecursos.listVGenero.getSelectedItem().toString());
                    Recurss.setAutor(vRecursos.listVAutores.getSelectedItem().toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el recurso", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los campos para actualizar el recurso", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vRecursos.btnListar) {
            List<Recursos> recursos = Recursosdao.recursos();
            vRecursos.mostrarRecursos(recursos);
        }
        if (e.getSource() == vRecursos.btnFinalizar) {
            VPrincipal vistaMenu = new VPrincipal();
            vistaMenu.setVisible(true);
            MenuCtrl controladorMenu = new MenuCtrl(vistaMenu);
            controladorMenu.iniciar();
            vRecursos.setVisible(false);
        }
    }
}
