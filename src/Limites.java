import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.table.*;

/**
 *
 * @author Der Ketzer
 * @email der.ketzer@gmail.com
 * @version 1.0
 *
 */

public class Limites extends JApplet {
    GridBagLayout Layout = new GridBagLayout();
    Container Pantalla = getContentPane();
    GridBagConstraints GBC = new GridBagConstraints();

    JLabel fun1, fun2, fun3, fun4;
    String[] Encabezados;
    Double[] valoresX, valoresY;

    JTable Tabla;
    TableModel Modelo;
    JScrollPane Scroll;

    int m=0, tam=0;
    double n=0.0, ini=0.0, fin=0.0, mitad=0.0, lim=0.0;
    double y1=0, y2=0;

    Object Datos[][];

    String temp = "";

    JRadioButton rad_Fun1, rad_Fun2, rad_Fun3, rad_Fun4;
    ButtonGroup grp_Funs;

    int iniY, finY, iniX, finX, ceroY, ceroX, funcion;
    
    public void init() {
        Icon im1 = new ImageIcon("fun1.jpg");
        Icon im2 = new ImageIcon("fun2.jpg");
        Icon im3 = new ImageIcon("fun3.jpg");
        Icon im4 = new ImageIcon("fun4.jpg");

        fun1 = new JLabel(im1);
        fun2 = new JLabel(im2);
        fun3 = new JLabel(im3);
        fun4 = new JLabel(im4);

        grp_Funs = new ButtonGroup();

        rad_Fun1 = new JRadioButton("", true);
        rad_Fun2 = new JRadioButton("", false);
        rad_Fun3 = new JRadioButton("", false);
        rad_Fun4 = new JRadioButton("", false);

        grp_Funs.add(rad_Fun1);
        grp_Funs.add(rad_Fun2);
        grp_Funs.add(rad_Fun3);
        grp_Funs.add(rad_Fun4);

        Encabezados = new String[11];
        valoresX = new Double[11];
        valoresY = new Double[11];

        Datos = new Object[1][11];

        for(int i=0; i<11; i++){
            Datos[0][i] = "";
        }
        
        genera_valores(1.0, 3.0);
        funcion = 1;

        for(m=0; m<11; m++){
            valoresY[m] = valoresX[m]+2.0;
        }

        Modelo = new AbstractTableModel(){
            public int getColumnCount(){
                /*
                 * @return regresa la cantidad de columnas
                 */
                return Encabezados.length;
            }
            public int getRowCount(){
                /*
                 * @return Regresa la cantidad de filas
                 */
                return 1;
            }
            public Object getValueAt(int row, int col){
                /* @param row pide la fila
                 * @param col pide la columna
                 * @return devuelve el valor en la fila y columna especificada
                 */
                return Datos[0][col];
            }
            public String getColumnName(int c){
                /*
                 * @param c pide la columna
                 * @return devulve el encabezado de la columna
                 */
                return Encabezados[c];
            }
            public boolean isCellEditable(int row, int col){
                /*
                 * @param row fila
                 * @param col columna
                 * @return devuelve si la fila/columna es editable
                 */
                Datos[0][col] = valoresY[col];
                setValueAt(Datos,col);

                return false;
            }
            public void setValueAt(Object objeto, int col){
                /*
                 * @param objeto con los datos
                 * @param col columna
                 * @return nadap
                 */
                Tabla.repaint();
            }
            public Class getColumnClass(int c){
                /*
                 * @param c columna
                 * @return Regresa el tipo de columna
                 */
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
        
        buildConstraints(rad_Fun1, 0, 1, 1, 1, GridBagConstraints.NONE);
        buildConstraints(rad_Fun2, 1, 1, 1, 1, GridBagConstraints.NONE);
        buildConstraints(rad_Fun3, 2, 1, 1, 1, GridBagConstraints.NONE);
        buildConstraints(rad_Fun4, 3, 1, 3, 1, GridBagConstraints.NONE);

        buildConstraints(Scroll, 0, 2, 4, 1, GridBagConstraints.BOTH);

        rad_Fun1.addActionListener(new Eventos());
        rad_Fun2.addActionListener(new Eventos());
        rad_Fun3.addActionListener(new Eventos());
        rad_Fun4.addActionListener(new Eventos());

        setSize(800,600);
    }

    private void buildConstraints(Component componente, int x, int y, int w, int h, int fill){
        /*
         * @param x posicion x
         * @param y posicion y
         * @param w ancho
         * @param h y alto, respecto a la cantidad de elementos en los nieveles superiores
         * @param fill dice si se llena todo el campo o no
         * @return nadap
         */

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
        /*
         * @param g es el inicializador de graficos, que por cierto lo odie. Es tan primitivo que no me lo creo.
         */

        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g.setColor(new Color(255, 255, 255));
        iniY = 190;
        finY = 400;
        iniX = 10;
        finX = 780;
        ceroY = iniY+finY/2;
        ceroX = ((finX-iniX)/2)+iniX;

        g.fillRect(iniX, iniY, finX, finY);

        g.setColor(new Color(0, 0, 0));
        g.drawLine(iniX, ceroY, finX+iniX, ceroY);
        g.drawLine(ceroX, iniY, ceroX, iniY+finY);

        g.setColor(new Color(255, 0, 0));
        g.drawString("Y", ceroX+10, iniY+15);
        g.drawString("X", finX, ceroY+15);

        g.setColor(new Color(0, 0, 0));
        int maxY = 0;
        for(int j=0; j<11; j++){
            if((int)(Math.ceil(valoresY[j])) > maxY)
                maxY = (int)(Math.ceil(valoresY[j]));
        }

        int pasoY = (ceroY-iniY)/maxY;
        int pasoX = (finX-iniX)/10;

        for(int j=maxY; j>=0; j--){
            g.drawLine(ceroX-10, j*pasoY+iniY, ceroX+10, j*pasoY+iniY);
            g.drawString(maxY-j+"", ceroX-15, j*pasoY+iniY+15);
        }

        for(int j=iniX; j<=finX; j+=pasoX){
            g.drawLine(j, ceroY-10, j, ceroY+10);
            g.drawString(Encabezados[j/pasoX], j-8, ceroY+25);
        }
        
        g.setColor(new Color(0, 0, 0));

        Line2D line;

        double bla = (((ceroY-iniY)/maxY)*(10.0/maxY))/(finX-iniX);
        double ble = ((iniY+finY)/(10.0/maxY))/maxY;
        double turu = 0.0;

        for(int j=iniX+1; j<=finX; j++){
            switch(funcion){
                case 1:
                    y1 = ((j-11.0))*bla+ble*2.0;
                    y2 = (j-10.0)*bla+ble*2.0;
                break;
                case 2:
                    y1 = Math.log(j-11.0)*ble/2.0;
                    y2 = Math.log(j-10.0)*ble/2.0;
                break;
                case 3:
                    if(j<ceroX)
                        turu = ceroX-j;
                    else
                        turu = ceroX-j;

                    y1 = ble*4.0/(bla*1.0+((turu-11.0)*bla*(turu-11.0)));
                    y2 = ble*4.0/(bla*1.0+((turu-10.0)*bla*(turu-10.0)));
                break;
                case 4:
                    if(j<ceroX){
                        y1 = ble*0.6-((j-11.0)*bla*1.3);
                        y2 = ble*0.6-((j-10.0)*bla*1.3);
                    }else{
                        y1 = ((j-11.0))*bla+ble*2.38;
                        y2 = (j-10.0)*bla+ble*2.38;
                    }
                break;
            }

            line = new Line2D.Double(j-1, ceroY-y1, j, ceroY-y2);
            g2.draw(line);
        }
    }

    public void genera_valores(double ini, double fin){
        /*
         * @param ini es el valor inicial del rango
         * @param fin es el valor final del rango
         */
        mitad = (fin-ini)/2+ini;
        n = ini;

        Encabezados[0] = ini+"";
        valoresX[0] = ini;
        Encabezados[10] = fin+"";
        valoresX[10] = fin;

        lim = mitad;

        for(m=1; m<10; m++){
            n += (lim-n)/2.0;
            valoresX[m] = n;
            temp = n+"";

            if(temp.length()>4 && n>0)
                temp = temp.substring(0,4);
            else if(temp.length() > 4 && n < 0)
                temp = temp.substring(0,5);

            Encabezados[m] = temp;

            if(m==5){
                Encabezados[5] = mitad+"";
                valoresX[5] = mitad;
                lim = fin;
                n = mitad;
            }
        }
    }

    public class Eventos implements ActionListener{
        public void actionPerformed(ActionEvent e){
            /*
             * @param e variable que cacha quien manda a llamar al evento
             */

            if(e.getSource() == rad_Fun1){
                genera_valores(1.0, 3.0);
                funcion = 1;
                for(m=0; m<11; m++){
                    valoresY[m] = valoresX[m]+2.0;
                    Tabla.getColumnModel().getColumn(m).setHeaderValue(Encabezados[m]);
                }
                repaint();
                limpiame();
            }else if(e.getSource() == rad_Fun2){
                genera_valores(0.0, 1000.0);
                funcion = 2;
                for(m=0; m<11; m++){
                    valoresY[m] = Math.log(valoresX[m]);
                    Tabla.getColumnModel().getColumn(m).setHeaderValue(Encabezados[m]);
                }
                repaint();
                limpiame();
            }else if(e.getSource() == rad_Fun3){
                genera_valores(-2.0, 2.0);
                funcion = 3;
                for(m=0; m<11; m++){
                    valoresY[m] = 4.0/(1.0+valoresX[m]*valoresX[m]);
                    Tabla.getColumnModel().getColumn(m).setHeaderValue(Encabezados[m]);
                }
                repaint();
                limpiame();
            }else if(e.getSource() == rad_Fun4){
                genera_valores(0.0, 2.0);
                funcion = 4;
                for(m=0; m<11; m++){
                    if(valoresX[m]<1)
                        valoresY[m] = 1.0-valoresX[m]*valoresX[m];
                    else
                        valoresY[m] = valoresX[m]+4.0;
                    Tabla.getColumnModel().getColumn(m).setHeaderValue(Encabezados[m]);
                }
                repaint();
                limpiame();
            }
        }
    }
}