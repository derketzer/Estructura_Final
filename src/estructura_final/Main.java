/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package estructura_final;

import java.awt.Graphics;
import javax.swing.JApplet;

/**
 *
 * @author Der Ketzer
 * @email der.ketzer@gmail.com
 * @version 1.0
 */
public class Main extends JApplet{

    /**
     * @param args the command line arguments
     */
    public void paint(Graphics g) {
        // TODO code application logic here

        //Prueba applet
        super.paint(g);
        g.drawString("Hola Applet", 25, 50);
    }

}
