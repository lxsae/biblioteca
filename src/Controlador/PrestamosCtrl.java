package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import Dao.*;
import Model.*;

import Vista.*;

public class PrestamosCtrl implements ActionListener {

    private VentanaPrestamos vPrestamos;
    private Prestamos Prestam;
    private PrestamosDAO Prestamosdao;
    private RecursosDAO Recursosdao;

    public PrestamosCtrl(VentanaPrestamos vPrestamos, Prestamos Prestam, PrestamosDAO Prestamosdao, RecursosDAO Recursosdao) {
        this.vPrestamos = vPrestamos;
        this.Prestam = Prestam;
        this.Prestamosdao = new PrestamosDAOimpl();
        this.Recursosdao = new RecursosDAOimpl();

        this.vPrestamos.btnAgregar.addActionListener(this);
        this.vPrestamos.btnBuscar.addActionListener(this);
        this.vPrestamos.btnListar.addActionListener(this);
        this.vPrestamos.btnEliminar.addActionListener(this);
        this.vPrestamos.btnActualizar.addActionListener(this);
        this.vPrestamos.btnFinalizar.addActionListener(this);
        this.vPrestamos.listVRecursos.addActionListener(this);
        this.vPrestamos.listVActivo.addActionListener(this);
        List<Recursos> recursos = Recursosdao.recursos();

        for (Recursos recurso : recursos) {
            vPrestamos.listVRecursos.addItem(recurso.getTitulo());
            vPrestamos.listVActivo.addItem(recurso.getAutor());
            vPrestamos.listVActivo.addItem(recurso.getAutor());

        }

    }

    public void iniciar() {
        vPrestamos.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vPrestamos.btnAgregar) {
            if (!vPrestamos.txtIdPrestamos.getText().isEmpty()) {
                int idPrestamos = Integer.parseInt(vPrestamos.txtIdPrestamos.getText());
                Prestamos PrestamoEncontrado = Prestamosdao.getPrestamos(idPrestamos);
                if (PrestamoEncontrado != null) {
                    JOptionPane.showMessageDialog(null, "¡El Prestamo ya existe!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Prestam.setId(idPrestamos);
                    Prestam.setFechaRegistro(vPrestamos.txtRegistro.getText());
                    Prestam.setFechaDevolucion(vPrestamos.txtDevolucion.getText());
                    Object selectedItem = vPrestamos.listVActivo.getSelectedItem();
                    String estado = selectedItem.toString();
                    Prestam.setEstado(estado);

                    vPrestamos.txtRegistro.setText("");
                    vPrestamos.txtDevolucion.setText("");
                    vPrestamos.txtIdPrestamos.setText("");

                    Prestamosdao.save(Prestam);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite un Id válido en el campo ID", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo ID está vacío", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource() == vPrestamos.btnBuscar) {
            if (!vPrestamos.txtIdPrestamos.getText().isEmpty()) {
                int txtIdPrestamos = Integer.parseInt(vPrestamos.txtIdPrestamos.getText());
                Prestamos prestamoEncontrado = Prestamosdao.getPrestamos(txtIdPrestamos);
                if (prestamoEncontrado == null) {
                    JOptionPane.showMessageDialog(null, "¡El Prestamo no está registrado!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "¡El Prestamo está registrado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite el ID del Prestamo", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vPrestamos.btnEliminar) {

            int txtIdautores = Integer.parseInt(vPrestamos.txtIdPrestamos.getText());
            Prestamos prestamoEncontrado = Prestamosdao.getPrestamos(txtIdautores);
            try {
                if (prestamoEncontrado != null) {
                    Prestamosdao.delete(prestamoEncontrado);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite el ID del Autor", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vPrestamos.btnActualizar) {
            if (!vPrestamos.txtIdPrestamos.getText().isEmpty() && !vPrestamos.txtRegistro.getText().isEmpty() && !vPrestamos.txtDevolucion.getText().isEmpty()) {

                int txtIdPrestamos = Integer.parseInt(vPrestamos.txtIdPrestamos.getText());
                Prestamos prestamoEncontrado = Prestamosdao.getPrestamos(txtIdPrestamos);
                if (prestamoEncontrado != null) {

                    prestamoEncontrado.setFechaRegistro(vPrestamos.txtRegistro.getText());
                    prestamoEncontrado.setFechaDevolucion(vPrestamos.txtDevolucion.getText());

                    Prestamosdao.update(prestamoEncontrado);
                    JOptionPane.showMessageDialog(null, "¡El Autor se ha actualizado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

                    vPrestamos.txtIdPrestamos.setText("");
                    vPrestamos.txtRegistro.setText("");
                    vPrestamos.txtDevolucion.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el autor", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Llene todos los campos para actualizar el autor", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vPrestamos.btnListar) {
            List<Prestamos> prestamos = Prestamosdao.prestamos();
            vPrestamos.mostrarPrestamos(prestamos);
        }
        if (e.getSource() == vPrestamos.btnFinalizar) {
            VPrincipal vistaMenu = new VPrincipal();
            vistaMenu.setVisible(true);
            MenuCtrl controladorMenu = new MenuCtrl(vistaMenu);
            controladorMenu.iniciar();
            vPrestamos.setVisible(false);
        }
    }
}
