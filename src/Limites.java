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
 * @email der.ketzer@gmail.com
 * @version 1.0
 */
public class Limites extends JApplet {
    GridBagLayout Layout = new GridBagLayout();
    Container Pantalla = getContentPane();
    GridBagConstraints GBC = new GridBagConstraints();

    JLabel fun1, fun2, fun3, fun4;
    String[] Encabezados;
    Double[] valoresX;

    JTable Tabla;
    TableModel Modelo;
    JScrollPane Scroll;

    int m=0, tam=0;
    double n=0.0, ini=0.0, fin=0.0, mitad=0.0, lim=0.0;

    Object Datos[][];

    String temp = "";

    public void init() {
        fun1 = new JLabel("f(x)=(x^2-4)/(x-2)");
        fun2 = new JLabel("f(x)=ln(x)");
        fun3 = new JLabel("f(x)=4/(1+x^2)");
        fun4 = new JLabel("f(x)=blu blu");

        Encabezados = new String[11];
        valoresX = new Double[11];

        ini = 1.0;
        fin = 3.0;
        mitad = (fin-ini);
        n = 0.0;

        Encabezados[10] = fin+"";
        valoresX[10] = fin;

        lim = mitad;

        for(m=0; m<10; m++){
            n += (lim-n)/2.0;
            valoresX[m] = n;
            temp = n+"";

            if(temp.length()>4)
                temp = temp.substring(0,4);

            Encabezados[m] = temp;

            if(m==5){
                Encabezados[5] = mitad+"";
                valoresX[5] = mitad;
                lim = fin;
                n = mitad;
            }
        }

        Datos = new Object[1][11];

        for(int i=0; i<1; i++){
            for(int j=0; j<11; j++){
                Datos[i][j] = "";
            }
        }

        Modelo = new AbstractTableModel(){
            public int getColumnCount(){
                return Encabezados.length;
            }
            public int getRowCount(){
                return 1;
            }
            public Object getValueAt(int row, int col){
                return Datos[0][col];
            }
            public String getColumnName(int c){
                return Encabezados[c];
            }
            public boolean isCellEditable(int row, int col){
                return false;
            }
            public void setValueAt(Object objeto, int col){
                Tabla.repaint();
                temp = (valoresX[col]*valoresX[col] + 2.0)+"";

                if(temp.length()>4)
                    temp = temp.substring(0,4);

                Datos[0][col] = temp;
            }
            public Class getColumnClass(int c){
                return getValueAt(0,c).getClass();
            }
        };

        Tabla = new JTable(Modelo);
        Scroll = new JScrollPane(Tabla);

        setLayout(Layout);

        buildConstraints(fun1, 0, 0, 1, 1, GridBagConstraints.NONE);
        buildConstraints(fun2, 1, 0, 1, 1, GridBagConstraints.NONE);
        buildConstraints(fun3, 2, 0, 1, 1, GridBagConstraints.NONE);
        buildConstraints(fun4, 3, 0, 1, 1, GridBagConstraints.NONE);
        buildConstraints(Scroll, 0, 1, 4, 1, GridBagConstraints.BOTH);

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
    
    private void limpiame(){
        for(int i=0; i<11; i++){
            Datos[0][i] = "";
        }

        Tabla.repaint();
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(10, 150, 780, 440);

        g.setColor(new Color(0, 0, 0));
        g.drawLine(10, 370, 790, 370);
        g.drawLine(400, 150, 400, 590);

        g.setColor(new Color(255, 0, 0));
        //g.drawString("Y", 410, 170);
        //g.drawString("X", 770, 350);

        g.setColor(new Color(0, 0, 0));
        //20 es 1 en X
        //780 es 3 en X

        //370 es 0 en Y
        //150 es max en Y
        //590 es min en Y
        //220 es dif en Y

        int pasoY = (int)(220/(valoresX[10]+2));

        for(int k=370; k>=150; k-=pasoY){
            g.drawString(((370-k)/pasoY)+"", 390, k);
        }

        for(int j=20; j<=780; j+=76){
            g.drawLine(j, 370, j, 380);
            g.drawString(Encabezados[(j-20)/76], j-8, 395);

            if(j>20){
                g.drawLine(
                        j-76,
                        (int)((370-((valoresX[(j-96)/76]+2)*pasoY))),
                        j,
                        (int)((370-((valoresX[(j-20)/76]+2)*pasoY)))
                );
            }
        }
    }
}