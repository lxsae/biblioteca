package Controlador;

import Model.Recursos;
import Model.Usuarios;
import Model.Autores;
import Model.Prestamos;
import Model.Generos;
import Dao.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vista.*;


public class MenuCtrl implements ActionListener {

    private VPrincipal vMenu;
    
    private VentanaUsuarios vUsuarios;
    private UsuariosDAO Usuariosdao;
    private Usuarios User;
    
    private VentanaRecursos vRecursos;
    private Recursos Recurss;
    private RecursosDAO Recursosdao;
    
    private Autores Autorr;
    private AutoresDAO Autoresdao;
    private VentanaAutores vAutores;
    
    private Prestamos Prestam;
    private PrestamosDAO Prestamosdao;
    private VentanaPrestamos vPrestamos;
    
    private Generos Generos;
    private GenerosDAO Generosdao;
    private VentanaGeneros vGeneros;
            
     

    public MenuCtrl(VPrincipal vistaMenu) {
        this.vMenu = vistaMenu;

        this.vMenu.btnUsuarios.addActionListener(this);

        this.vUsuarios = new VentanaUsuarios();
        this.User = new Usuarios(0,"Nombre","Rol");
        this.Usuariosdao = new UsuariosDAOimpl();

        this.vMenu.btnRecursos.addActionListener(this);

        this.vRecursos = new VentanaRecursos();
        this.Recurss = new Recursos("Nombre", "ISBN", "TIPO", "GENERO", "AUTOR");
        this.Recursosdao = new RecursosDAOimpl();
        
        this.vMenu.btnAutores.addActionListener(this);
        
        this.vAutores = new VentanaAutores();
        this.Autorr = new Autores(0,"Nombre","Apellido",0,"Direccion");
        this.Autoresdao = new AutoresDAOimpl();
        
        this.vMenu.btnPrestamos.addActionListener(this);
        
        this.vPrestamos = new VentanaPrestamos();
        this.Prestam = new Prestamos(0, "Fecha Registro", "Fecha Devolucion", "Estado");
        this.Prestamosdao = new PrestamosDAOimpl();
        
        this.vMenu.btnGeneros.addActionListener(this);
        
        this.vGeneros = new VentanaGeneros();
        this.Generos = new Generos(0, "Genero");
        this.Generosdao = new GenerosDAOimpl();

    }

    public void iniciar() {
        vMenu.setTitle("MENU");
        vMenu.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vMenu.btnUsuarios) {
            UsuariosCtrl controladorUsuarios = new UsuariosCtrl(vUsuarios, User, Usuariosdao);
            controladorUsuarios.iniciar();
            vUsuarios.setVisible(true);
            vMenu.setVisible(false);
        } else if (e.getSource() == vMenu.btnRecursos) {
            RecursosCtrl controladorRecursos = new RecursosCtrl(vRecursos, Recurss, Recursosdao, Autoresdao, Generosdao);
            controladorRecursos.iniciar();
            vRecursos.setVisible(true);
            vMenu.setVisible(false);
        } else if (e.getSource() == vMenu.btnAutores) {
            AutoresCtrl controladorAutores = new AutoresCtrl(vAutores, Autorr, Autoresdao);
            controladorAutores.iniciar();
            vAutores.setVisible(true);
            vMenu.setVisible(false);
        }else if (e.getSource()== vMenu.btnPrestamos){
            PrestamosCtrl ControladorPrestamos =new PrestamosCtrl(vPrestamos, Prestam, Prestamosdao, Recursosdao);
            ControladorPrestamos.iniciar();
            vPrestamos.setVisible(true);
            vMenu.setVisible(false);
        }
        else if (e.getSource()== vMenu.btnGeneros){
            GenerosCtrl ControladorGeneros =new GenerosCtrl(vGeneros, Generos, Generosdao);
            ControladorGeneros.iniciar();
            vGeneros.setVisible(true);
            vMenu.setVisible(false);
        }
    }
}

