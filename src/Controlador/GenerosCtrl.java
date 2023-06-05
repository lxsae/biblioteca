package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import Dao.*;
import Model.Generos;
import Vista.*;

public class GenerosCtrl implements ActionListener {

    private VentanaGeneros vGeneros;
    private Generos Genero;
    private GenerosDAO Generosdao;

    public GenerosCtrl(VentanaGeneros vGeneros, Generos Genero, GenerosDAO Generosdao) {
        this.vGeneros = vGeneros;
        this.Genero = Genero;
        this.Generosdao = new GenerosDAOimpl();

        this.vGeneros.btnAgregar.addActionListener(this);
        this.vGeneros.btnBuscar.addActionListener(this);
        this.vGeneros.btnListar.addActionListener(this);
        this.vGeneros.btnEliminar.addActionListener(this);
        this.vGeneros.btnActualizar.addActionListener(this);
        this.vGeneros.btnFinalizar.addActionListener(this);
    }

    public void iniciar() {
        vGeneros.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vGeneros.btnAgregar) {
            if (!vGeneros.txtId.getText().isEmpty()) {
                int idGeneros = Integer.parseInt(vGeneros.txtId.getText());
                Generos generoEncontrado = Generosdao.getGeneros(idGeneros);
                if (generoEncontrado != null) {
                    JOptionPane.showMessageDialog(null, "¡El Genero ya existe!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Genero.setId(idGeneros);
                    Genero.setGenero(vGeneros.txtGenero.getText());
                 
        

                    vGeneros.txtId.setText("");
                    vGeneros.txtGenero.setText("");


                    Generosdao.save(Genero);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite un Id válido en el campo ID", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo ID está vacío", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource() == vGeneros.btnBuscar) {
            if (!vGeneros.txtId.getText().isEmpty()) {
                int txtId = Integer.parseInt(vGeneros.txtId.getText());
                Generos GeneroEncontrado = Generosdao.getGeneros(txtId);
                if (GeneroEncontrado == null) {
                    JOptionPane.showMessageDialog(null, "¡El Genero no está registrado!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "¡El Genero está registrado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite el ID del Genero", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vGeneros.btnEliminar) {

            int txtId = Integer.parseInt(vGeneros.txtId.getText());
            Generos generoEncontrado = Generosdao.getGeneros(txtId);
            try {
                if (generoEncontrado != null) {
                    Generosdao.delete(generoEncontrado);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite el ID del Genero", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vGeneros.btnActualizar) {
            if (!vGeneros.txtId.getText().isEmpty() && !vGeneros.txtGenero.getText().isEmpty()) {

                int txtId = Integer.parseInt(vGeneros.txtId.getText());
                Generos generoEncontrado = Generosdao.getGeneros(txtId);
                if (generoEncontrado != null) {

                    generoEncontrado.setGenero(vGeneros.txtGenero.getText());


                    Generosdao.update(generoEncontrado);
                    JOptionPane.showMessageDialog(null, "¡El Genero se ha actualizado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

                    vGeneros.txtId.setText("");
                    vGeneros.txtGenero.setText("");


                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el genero", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los campos para actualizar el genero", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vGeneros.btnListar) {
            List<Generos> generos = Generosdao.generos();
            vGeneros.mostrarGeneros(generos);
        }
        if (e.getSource() == vGeneros.btnFinalizar) {
            VPrincipal vistaMenu = new VPrincipal();
            vistaMenu.setVisible(true);
            MenuCtrl controladorMenu = new MenuCtrl(vistaMenu);
            controladorMenu.iniciar();
            vGeneros.setVisible(false);
        }
    }
}
