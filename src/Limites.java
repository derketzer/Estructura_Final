/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JApplet;
import java.awt.Graphics;

/**
 *
 * @author Der Ketzer
 */
public class Limites extends JApplet {

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Hola Applet", 25, 50);
    }

    // TODO overwrite start(), stop() and destroy() methods

}
