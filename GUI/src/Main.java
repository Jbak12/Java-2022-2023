import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;


class SimpleGUI {
    JButton przycisk1;
    JButton przycisk2;
    JLabel etykietka;
    JFrame ramka;

    public static void main( String[] argv ) {
        SimpleGUI g = new SimpleGUI();
        g.work();
    }

    class KlasaWewnetrzna implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            etykietka.setText( "Kliknieto przycisk 1" );
        }
    }

    private void work() {
        ramka = new JFrame( "Moja ramka do zabawy z GUI" );
        ramka.setSize( 500, 100 );

        przycisk1 = new JButton( "Przycisk 1" );
        przycisk1.addActionListener( new KlasaWewnetrzna() );

        przycisk2 = new JButton( "Przycisk 2" );
        przycisk2.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                etykietka.setText( "Kliknieto przycisk 2" );
            }
        } );

        etykietka = new JLabel( "Tutaj etykietka" );

        ramka.getContentPane().add( BorderLayout.WEST, przycisk1 );
        ramka.getContentPane().add( BorderLayout.EAST, przycisk2 );
        ramka.getContentPane().add( BorderLayout.CENTER, etykietka );

        ramka.setVisible( true );
        ramka.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
}

 class JFrameResizing extends JFrame implements ComponentListener {

    JLabel label;

     Graphics2D g2d = new Graphics();


    JFrameResizing(){
        label = new JLabel();
        getContentPane().add(label);
        getContentPane().addComponentListener(this);
    }

    public void componentResized(ComponentEvent ce) {
        int height = this.getHeight();
        int width = this.getWidth();
        label.setText("Size: " + height + "x" + width);
        g2d.drawLine(int x1, int y1, int x2, int y2)
    }

     @Override
     public void componentMoved(ComponentEvent e) {

     }

     @Override
     public void componentShown(ComponentEvent e) {

     }

     @Override
     public void componentHidden(ComponentEvent e) {

     }

     ;

    public static void main(String[] arguments) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrameResizing frame = new JFrameResizing();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("JFrame Resizing Example");
        frame.setSize(300,150);
        frame.setVisible(true);

    }
}

class CalcManager() {
    Point CalculatePoint(x)
}

class Point {
    int x;
    int y;
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
