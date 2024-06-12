import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.ArrayList;

public class Gui extends JFrame implements Runnable{
    Client cl;
    JFrame f;
    JPanel bedienelemente;
    JPanel inputArea;
    JButton weiter;
    JButton zurueck;
    JButton speichern;
    JTextArea inputTextArea;
    JLabel seitenAnzeige;
    int seite = 0;
    int maxSize;
    boolean stillRunning = true;
    public Gui() {
        prepareFrame();
        cl = new Client();
        try {
            new Thread() {
                @Override
                public void run() {
                    String s = null;
                    while (s == null) {
                        try {
                            s = cl.readNotiz(0);
                            if ("0".equals(s)) s = null;
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    inputTextArea.setText(s);
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareFrame() {
        f = new JFrame("Jonas ist Kacke");
        f.setSize(800, 400);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        f.setLocationRelativeTo(null);
        f.setAlwaysOnTop(true);

        bedienelemente = new JPanel(new FlowLayout());
        inputArea = new JPanel(new FlowLayout());

        seitenAnzeige = new JLabel(Integer.toString(seite));
        weiter = new JButton("Weiter");
        zurueck = new JButton("Zurueck");
        speichern = new JButton("Speichern");
        inputTextArea = new JTextArea();
        inputTextArea.setPreferredSize(new Dimension(200, 200));

        speichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I was clicked!");
                cl.saveNotiz(seite, inputTextArea.getText());
            }
        });

        weiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seite >= 0) {
                    seite++;
                    try {
                        inputTextArea.setText(cl.readNotiz(seite));
                        seitenAnzeige.setText(Integer.toString(seite));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        zurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seite > 0) {
                    seite--;
                    try {
                        inputTextArea.setText(cl.readNotiz(seite));
                        seitenAnzeige.setText(Integer.toString(seite));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        bedienelemente.add(weiter);
        bedienelemente.add(zurueck);
        bedienelemente.add(speichern);
        bedienelemente.add(seitenAnzeige);
        inputArea.add(inputTextArea);

        f.add(bedienelemente, BorderLayout.NORTH);
        f.add(inputArea, BorderLayout.CENTER);

        inputTextArea.setText("");
        try
        {
            Thread.sleep(200);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        f.setVisible(true);
    }
    @Override
    public void run(){
        while(stillRunning){
            maxSize = cl.requestNoteBookSize();
        }
    }
}