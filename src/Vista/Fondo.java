
package Vista;



import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel; 


class Fondo extends JPanel{
         ImageIcon imagen;
         String nombre;
    public  Fondo(String nombre){
        this.nombre = nombre;
     }
    @Override
    public void paint(Graphics g){

        imagen = new ImageIcon(getClass().getResource(nombre));
        g.drawImage(imagen.getImage(),0,0, getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
