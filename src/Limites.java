/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

/**
 *
 * @author Der Ketzer
 */
public class Limites extends JApplet {
    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
    */

    GridBagLayout Layout = new GridBagLayout();
    Container Pantalla = getContentPane();
    GridBagConstraints GBC = new GridBagConstraints();

    JLabel fun1, fun2, fun3, fun4;
    String[] Encabezados;

    JTable Tabla;
    TableModel Modelo;

    int m=0, tam=0;
    double n=0.0;

    Object Datos[][];

    public void init() {
        // TODO start asynchronous download of heavy resources
        fun1 = new JLabel("f(x)=(x^2-4)/(x-2)");
        fun2 = new JLabel("f(x)=ln(x)");
        fun3 = new JLabel("f(x)=4/(1+x^2)");
        fun4 = new JLabel("f(x)=blu blu");

        tam = 200/25;

        Encabezados = new String[tam+1];

        for(n=-1.0; n<=1.0; n+=0.25){
            Encabezados[m] = n+"";
            m++;
        }

        Datos = new Object[11][1];

        for(int i=0; i<1; i++){
            for(int j=0; j<1; j++){
                Datos[i][j] = "";
            }
        }

        setLayout(Layout);

        buildConstraints(fun1, 0, 0, 1, 1, GridBagConstraints.NONE);
        buildConstraints(fun2, 1, 0, 1, 1, GridBagConstraints.NONE);
        buildConstraints(fun3, 2, 0, 1, 1, GridBagConstraints.NONE);
        buildConstraints(fun4, 3, 0, 1, 1, GridBagConstraints.NONE);

        setSize(800,600);
    }

    private void buildConstraints(Component componente, int x, int y, int w, int h, int fill){
        GBC.gridx = x;
        GBC.gridy = y;
        GBC.gridwidth = w;
        GBC.gridheight = h;
        GBC.weightx = 1;
        GBC.weighty = 1;
        GBC.fill = fill;
        GBC.anchor = GridBagConstraints.CENTER;

        Layout.setConstraints(componente, GBC);
        Pantalla.add(componente);
    }

    // TODO overwrite start(), stop() and destroy() methods
}
