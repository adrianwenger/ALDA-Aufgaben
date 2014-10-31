package dictionary;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeMap;
import javax.swing.*;

/**
 *
 * @author adi
 */
public class DictionaryGUI extends JFrame {

    private final DictionaryProxy dic;

    /**
     *
     * @param tb
     */
    public DictionaryGUI(Dictionary<String, String> tb) {
        // Dictionary anlegen:
        dic = new DictionaryProxy();
        dic.setDic(tb);

        // Menuleiste einbauen:
        this.setJMenuBar(new DictionaryMenuBar(dic));

        // mainPanel mit Umrandung versehen und das
        // Einfuegen- und  SuchenLoeschenPanel einbauen:
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        this.setContentPane(mainPanel);
        mainPanel.add(new DictionaryWordInsertPanel(dic));
        mainPanel.add(new DictionarySearchErasePanel(dic));
        mainPanel.add(new PerformancePanel(dic));
        // Sonstige Eigenschaften des Hauptfenster setzen:
        this.setTitle("Dictionary");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Operation beim schliessen des Dictionarys
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                System.exit(0);
            }
        });

        this.setMinimumSize(new Dimension(485, 501));
        this.pack();
        this.setVisible(true);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new DictionaryGUI(new MapDictionary<>(new TreeMap<String, String>()));
    }
}
