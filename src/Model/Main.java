
package Model;

import Controlador.MenuCtrl;

import Vista.*;




public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        VPrincipal view = new VPrincipal ();
        MenuCtrl m = new MenuCtrl (view);
        m.iniciar();
        view.setVisible(true);
    }
    
}
    

