package Controlador;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Model.Usuarios;
import Dao.UsuariosDAOimpl;
import Vista.*;
import Dao.UsuariosDAO;


public class UsuariosCtrl implements ActionListener{
    
    private VentanaUsuarios vUsuarios;
    private Usuarios Users;
    private UsuariosDAO Usuariosdao;
    
    
    public UsuariosCtrl (VentanaUsuarios vUsuarios, Usuarios Users, UsuariosDAO Usuariodao) {
        this.vUsuarios = vUsuarios;
        this.Users = Users;
        this.Usuariosdao = new UsuariosDAOimpl();
        
        
        this.vUsuarios.btnAgregar.addActionListener(this);
       
        this.vUsuarios.btnBuscar.addActionListener(this);
        this.vUsuarios.btnListar.addActionListener(this);
        this.vUsuarios.btnEliminar.addActionListener(this);
        this.vUsuarios.btnActualizar.addActionListener(this);
        this.vUsuarios.btnFinalizar.addActionListener(this);
    }
    public void iniciar() {

        vUsuarios.setLocationRelativeTo(null);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vUsuarios.btnAgregar){
        if (!vUsuarios.txtIdUsuario.getText().isEmpty()) {
        int idUsuario = Integer.parseInt(vUsuarios.txtIdUsuario.getText());
        Usuarios existeUser = Usuariosdao.getUsuarios(idUsuario);    
        if(existeUser != null){
        JOptionPane.showMessageDialog(null, "Ojo el usuario ya esta creado", "Advertencia", JOptionPane.ERROR_MESSAGE);
        return;
        } try {
            String estamentoSelecionado = (String) vUsuarios.combox.getSelectedItem();
                Users.setId(idUsuario);
                Users.setNombre(vUsuarios.txtNombre.getText());
                Users.setRol(estamentoSelecionado);
                vUsuarios.txtIdUsuario.setText("");
                vUsuarios.txtNombre.setText("");
                Usuariosdao.save(Users);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite un número válido en el campo Código", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo Código está vacío", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }   
        }
        if(e.getSource() == vUsuarios.btnBuscar){
            if(!vUsuarios.txtIdUsuario.getText().isEmpty()){
            int codigoUsuario = Integer.parseInt(vUsuarios.txtIdUsuario.getText());
            Usuarios usuarioEncontrado = Usuariosdao.getUsuarios(codigoUsuario);
            if(usuarioEncontrado == null){
                    JOptionPane.showMessageDialog(null, "¡El usuario no esta registrado!", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "¡El usuario esta registrado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Digite el numero de identificación", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == vUsuarios.btnEliminar){
            
            int codigoUsuario = Integer.parseInt(vUsuarios.txtIdUsuario.getText());
            Usuarios usuarioEncontrado = Usuariosdao.getUsuarios(codigoUsuario);
            try { Users.setId(Integer.parseInt(vUsuarios.txtIdUsuario.getText()));
                if(usuarioEncontrado != null){
                    Usuariosdao.delete(usuarioEncontrado);
                }
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Digite el numero de identificación", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == vUsuarios.btnActualizar){
            if(!vUsuarios.txtIdUsuario.getText().isEmpty() && !vUsuarios.txtNombre.getText().isEmpty()){
            int codigoUsuario = Integer.parseInt(vUsuarios.txtIdUsuario.getText());
            Usuarios usuarioEncontrado = Usuariosdao.getUsuarios(codigoUsuario);
                if (usuarioEncontrado != null) {
                    String estamentoSelecionado = (String) vUsuarios.combox.getSelectedItem();
                    usuarioEncontrado.setNombre(vUsuarios.txtNombre.getText());
                    Users.setRol(estamentoSelecionado);
                    Usuariosdao.uptade(usuarioEncontrado);
                    JOptionPane.showMessageDialog(null, "¡El usuario se ha actualizado!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    vUsuarios.txtIdUsuario.setText("");
                    vUsuarios.txtNombre.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el usuario", "Advertencia", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Llene todos los espacios para actualizar el usuario", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == vUsuarios.btnListar){
            List<Usuarios> usuarios = Usuariosdao.usuarios();
            vUsuarios.mostrarUsuario(usuarios);
        }
        if(e.getSource() == vUsuarios.btnFinalizar){
            VPrincipal vistaMenu = new VPrincipal();
            vistaMenu.setVisible(true);
            MenuCtrl controladorMenu = new MenuCtrl(vistaMenu);
            controladorMenu.iniciar();
            vUsuarios.setVisible(false);
        }
    }
}
