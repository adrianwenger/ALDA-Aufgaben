package dictionary;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author adi
 */
public class PerformancePanel<K, V> extends JPanel implements ActionListener {

    private DictionaryProxy woerterBuch;
    private final JTextField tfRead;
    private final JTextField tfSuccess;
    private final JTextField tfFail;
    private final JButton startRead;
    private final JButton startSuccess;
    private final JButton startFail;
    private JFileChooser fc;
    long timeStart, timeEnd;
    double timeDiff;
    ArrayList<String> array = new ArrayList<>();
    private static final double UMRMS = 10e6;

    /**
     *
     * @param tb
     */
    public PerformancePanel(DictionaryProxy tb) {
        woerterBuch = tb;

        // Hauptpanel erstellen
        JPanel performance = new JPanel();
        performance.setBorder(BorderFactory.createTitledBorder("PerformanceTests"));
        performance.setLayout(new BoxLayout(performance, BoxLayout.X_AXIS));

        // JPanel einbauen 
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 1));
        panel1.add(new JLabel("read"));
        panel1.add(new JLabel("success"));
        panel1.add(new JLabel("fail"));

        // JPanel
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1));
        tfRead = new JTextField();
        tfSuccess = new JTextField();
        tfFail = new JTextField();
        tfRead.setEditable(false);
        tfSuccess.setEditable(false);
        tfFail.setEditable(false);
        panel2.add(tfRead);
        panel2.add(tfSuccess);
        panel2.add(tfFail);
        panel2.setPreferredSize(new Dimension(200, 20));



        // Anwenden Button erstellen
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(3, 1));
        startRead = new JButton("start");
        startSuccess = new JButton("start");
        startFail = new JButton("start");
        startRead.addActionListener(this);
        startSuccess.addActionListener(this);
        startFail.addActionListener(this);
        panel3.add(startRead);
        panel3.add(startSuccess);
        panel3.add(startFail);


        // Panel performance zusammenbauen
        performance.add(panel1);
        performance.add(panel2);
        performance.add(panel3);


        // Eigenschaften des Objekts festlegen
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(performance);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startRead) {
            // File Chooser anlegen
            this.fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            // Dialog zum Datei öffnen anzeigen
            int returnVal = fc.showOpenDialog(this);
            timeStart = System.nanoTime();
            read(fc.getSelectedFile());
            timeEnd = System.nanoTime();
            timeDiff = ((timeEnd - timeStart) / UMRMS);
            tfRead.setText(Double.valueOf(timeDiff).toString() + " ms");
        } else if (source == startSuccess) {
            timeStart = System.nanoTime();
            for (String key : array) {
                woerterBuch.getDic().search(key);
            }
            timeEnd = System.nanoTime();
            timeDiff = ((timeEnd - timeStart) / UMRMS);
            tfSuccess.setText(Double.valueOf(timeDiff).toString() + " ms");
        } else if (source == startFail) {
            ArrayList<String> array2 = new ArrayList<>(array);
            for (String key : array2) {
                key = key + "1";
            }
            timeStart = System.nanoTime();
            for (String key : array2) {
                woerterBuch.getDic().search(key);
            }
            timeEnd = System.nanoTime();
            timeDiff = ((timeEnd - timeStart) / UMRMS);
            tfFail.setText(Double.valueOf(timeDiff).toString() + " ms");
        }
    }

    private void read(File file) {
        LineNumberReader in = null;
        try {
            in = new LineNumberReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                String[] sf = line.split(" ");
                if (sf.length == 2) {
                    woerterBuch.getDic().insert(sf[0], sf[1]);
                    array.add(sf[0]);
                }
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
