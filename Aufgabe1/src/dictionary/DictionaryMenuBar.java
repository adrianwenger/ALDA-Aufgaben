package dictionary;

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian Wenger
 */
public class DictionaryMenuBar extends JMenuBar implements ActionListener {

    private final DictionaryProxy dic;
    private final JMenu datei;
    private final JMenuItem lesen;
    private final JMenuItem beenden;
    private JFileChooser fc;
    private final JMenu implArt;
    private final JMenuItem sortedArray;
    private final JMenuItem hash;
    private final JMenuItem tree;
    private final JMenuItem hashMap;
    private final JMenuItem treeMap;
    private final JMenuItem map;

    /**
     *
     * @param tb
     */
    public DictionaryMenuBar(DictionaryProxy tb) {
        dic = tb;

        // Menue anlegen
        datei = new JMenu("Datei");

        // Menue in menuBar einbauen
        // Unterpunkte in Menue anlegen  
        lesen = new JMenuItem("Wörterbuch lesen...");
        lesen.addActionListener(this);

        beenden = new JMenuItem("Wörterbuch beenden");
        beenden.addActionListener(this);

        datei.add(lesen);
        datei.add(new JSeparator());
        datei.add(beenden);
        // Menue hinzufuegen
        this.add(datei);

        implArt = new JMenu("ImplArt");

        sortedArray = new JRadioButtonMenuItem("Sorted Array");
        sortedArray.addActionListener(this);
        hash = new JRadioButtonMenuItem("Hash");
        hash.addActionListener(this);
        tree = new JRadioButtonMenuItem("Tree");
        tree.addActionListener(this);
        hashMap = new JRadioButtonMenuItem("Hash Map");
        hashMap.addActionListener(this);
        treeMap = new JRadioButtonMenuItem("Tree Map");
        treeMap.addActionListener(this);

        treeMap.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(sortedArray);
        group.add(hash);
        group.add(tree);
        group.add(hashMap);
        group.add(treeMap);
        map = new JMenu("Map");

        map.add(treeMap);
        map.add(hashMap);
        implArt.add(map);
        implArt.add(sortedArray);
        implArt.add(hash);
        implArt.add(tree);

        this.add(implArt);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // File Chooser anlegen
        this.fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        Object source = e.getSource();
        if (source == lesen) {
            read();
        } else if (source == hashMap) {
            Map<String, String> map = new HashMap<>();
            dic.setDic(new MapDictionary<>(map));
        } else if (source == treeMap) {
            Map<String, String> map = new TreeMap<>();
            dic.setDic(new MapDictionary<>(map));
        } else if (source == sortedArray) {
            Dictionary<String, String> sad = new SortedArrayDictionary<>();
            dic.setDic(sad);
        } else if (source == tree) {
        } else if (source == hash) {
            Dictionary<String, String> hd = new HashDictionary<>();
            dic.setDic(hd);
        } else {
            exit();
        }
    }

    private void read() {
        // Dialog zum Datei öffnen anzeigen
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            LineNumberReader in = null;
            try {
                in = new LineNumberReader(new FileReader(file));
                String line;
                while ((line = in.readLine()) != null) {
                    String[] sf = line.split(" ");
                    if (sf.length == 2) {
                        dic.getDic().insert(sf[0], sf[1]);
                    }
                }
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     *
     */
    public void exit() {
        System.exit(0);

    }
}
