import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Beschreiben Sie hier die Klasse Gui.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Gui extends JFrame
{
    Client cl;
    JFrame f;
    JPanel bedienelemente;
    JButton weiter;
    JButton zurueck;
    public Gui(){
        prepareFrame();
        cl = new Client();
    }
    private void prepareFrame(){
        f = new JFrame("Jonas ist Kacke");
        f.setSize(800,400);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setVisible(true);
        setLocationRelativeTo(null);
        f.setAlwaysOnTop(true);
        //f.pack();
        bedienelemente = new JPanel();
        weiter = new JButton("Weiter");
        zurueck = new JButton("Zurueck");
        bedienelemente.add(weiter);
        bedienelemente.add(zurueck);
        
    }
}
