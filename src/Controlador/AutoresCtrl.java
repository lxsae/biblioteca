package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import Dao.*;
import Model.Autores;
import Vista.*;

public class AutoresCtrl implements ActionListener {

    private VentanaAutores vAutores;
    private Autores Autorr;
    private AutoresDAO Autoresdao;

    public AutoresCtrl(VentanaAutores vAutores, Autores Autorr, AutoresDAO Autoresdao) {
        this.vAutores = vAutores;
        this.Autorr = Autorr;
        this.Autoresdao = new AutoresDAOimpl();

        this.vAutores.btnAgregar.addActionListener(this);
        this.vAutores.btnBuscar.addActionListener(this);
        this.vAutores.btnListar.addActionListener(this);
        this.vAutores.btnEliminar.addActionListener(this);
        this.vAutores.btnActualizar.addActionListener(this);
        this.vAutores.btnFinalizar.addActionListener(this);
    }

    public void iniciar() {
        vAutores.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vAutores.btnAgregar) {
            if (!vAutores.txtIdautores.getText().isEmpty()) {
                int idAutores = Integer.parseInt(vAutores.txtIdautores.getText());
                Autores autorEncontrado = Autoresdao.getAutores(idAutores);
                if (autorEncontrado != null) {
                    JOptionPane.showMessageDialog(null, "¡El Autor ya existe!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Autorr.setId(idAutores);
                    Autorr.setNombre(vAutores.txtNombre.getText());
                    Autorr.setApellido(vAutores.txtApellido.getText());
                    String telefonoText = vAutores.txtTelefono.getText();
                    int telefono = Integer.parseInt(telefonoText);
                    Autorr.setTelefono(telefono);
                    Autorr.setDireccion(vAutores.txtDireccion.getText());

                    vAutores.txtIdautores.setText("");
                    vAutores.txtNombre.setText("");
                    vAutores.txtApellido.setText("");
                    vAutores.txtTelefono.setText("");
                    vAutores.txtDireccion.setText("");

                    Autoresdao.save(Autorr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite un Id válido en el campo ID", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo ID está vacío", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource() == vAutores.btnBuscar) {
            if (!vAutores.txtIdautores.getText().isEmpty()) {
                int txtIdautores = Integer.parseInt(vAutores.txtIdautores.getText());
                Autores autorEncontrado = Autoresdao.getAutores(txtIdautores);
                if (autorEncontrado == null) {
                    JOptionPane.showMessageDialog(null, "¡El Autor no está registrado!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "¡El Autor está registrado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite el ID del Autor", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vAutores.btnEliminar) {

            int txtIdautores = Integer.parseInt(vAutores.txtIdautores.getText());
            Autores autorEncontrado = Autoresdao.getAutores(txtIdautores);
            try {
                if (autorEncontrado != null) {
                    Autoresdao.delete(autorEncontrado);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite el ID del Autor", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vAutores.btnActualizar) {
            if (!vAutores.txtIdautores.getText().isEmpty() && !vAutores.txtNombre.getText().isEmpty() && !vAutores.txtApellido.getText().isEmpty() && !vAutores.txtTelefono.getText().isEmpty() && !vAutores.txtDireccion.getText().isEmpty()) {

                int txtIdautores = Integer.parseInt(vAutores.txtIdautores.getText());
                Autores autorEncontrado = Autoresdao.getAutores(txtIdautores);
                if (autorEncontrado != null) {

                    autorEncontrado.setNombre(vAutores.txtNombre.getText());
                    autorEncontrado.setApellido(vAutores.txtApellido.getText());
                    autorEncontrado.setApellido(vAutores.txtTelefono.getText());
                    autorEncontrado.setDireccion(vAutores.txtDireccion.getText());

                    Autoresdao.update(autorEncontrado);
                    JOptionPane.showMessageDialog(null, "¡El Autor se ha actualizado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

                    vAutores.txtIdautores.setText("");
                    vAutores.txtNombre.setText("");
                    vAutores.txtApellido.setText("");
                    vAutores.txtTelefono.setText("");
                    vAutores.txtDireccion.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el autor", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los campos para actualizar el autor", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vAutores.btnListar) {
            List<Autores> autores = Autoresdao.autores();
            vAutores.mostrarAutores(autores);
        }
        if (e.getSource() == vAutores.btnFinalizar) {
            VPrincipal vistaMenu = new VPrincipal();
            vistaMenu.setVisible(true);
            MenuCtrl controladorMenu = new MenuCtrl(vistaMenu);
            controladorMenu.iniciar();
            vAutores.setVisible(false);
        }
    }
}
