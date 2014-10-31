package dictionary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author adi
 */
public class DictionarySearchErasePanel extends JPanel implements ActionListener {

    private final DictionaryProxy dic;
    private final JTextField tfdeutsch;
    private final JButton buttonAnwenden;
    private final JComboBox auswBox;
    private final JTextArea ausFeld;

    /**
     *
     * @param tb
     */
    public DictionarySearchErasePanel(DictionaryProxy tb) {
        dic = tb;

        // Hauptpanel erstellen
        JPanel suchenLoeschen = new JPanel();
        suchenLoeschen.setBorder(BorderFactory.createTitledBorder("Suchen/Löschen"));
        suchenLoeschen.setLayout(new BoxLayout(suchenLoeschen, BoxLayout.X_AXIS));

        // JPanel einbauen für JLabel Name, Zusatz
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 1));
        panel1.add(new JLabel("Deutsch"));

        // JPanel für TextField Name, Zusatz
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 1));
        tfdeutsch = new JTextField();
        panel2.add(tfdeutsch);
        panel2.setPreferredSize(new Dimension(200, 20));

        // Input-Dialog mit ComboBox
        String options[] = {"Suchen", "Löschen"};
        // ComboBox auswBox erstellen
        auswBox = new JComboBox(options);
        // Standardwert auf Prefix-Suche (index 1 von options[])
        auswBox.setSelectedIndex(1);
        auswBox.addActionListener(this);

        // Anwenden Button erstellen
        buttonAnwenden = new JButton("Anwenden");
        buttonAnwenden.addActionListener(this);

        // Ausgabetextfeld erstellen
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBorder(BorderFactory.createTitledBorder("Ausgabe"));
        ausFeld = new JTextArea();
        ausFeld.setEditable(false);
        panel3.setPreferredSize(new Dimension(500, 500));
        panel3.add(ausFeld, BorderLayout.CENTER);
        JScrollPane jScrollPane = new JScrollPane(ausFeld);
        panel3.add(jScrollPane);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Panel suchenLoeschen zusammenbauen
        suchenLoeschen.add(panel1);
        suchenLoeschen.add(panel2);
        suchenLoeschen.add(auswBox);
        suchenLoeschen.add(buttonAnwenden);

        // Eigenschaften des Objekts festlegen
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(suchenLoeschen);
        this.add(panel3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String resultat;
        if (source == buttonAnwenden) {
            ausFeld.setText(null);
            int index = auswBox.getSelectedIndex();
            // index 0 = Suchen
            if (index == 0) {
                resultat = dic.getDic().search(tfdeutsch.getText());
                if (tfdeutsch.getText().isEmpty()) {
                    ausFeld.setText(dic.getDic().toString());
                } else if (resultat == null) {
                    // Eintrag nicht gefunden 
                    JOptionPane.showMessageDialog(this, "Eintrag nicht gefunden");
                } else {
                    // Eintrag gefunden, Ausgabe in ausFeld
                    ausFeld.setText(dic.getDic().search(tfdeutsch.getText()).toString());                   //ausFeld.setText(resultat.toString());
                }

            } else {
                // index 1 = Löschen
                if (dic.getDic().remove(tfdeutsch.getText()) != null) {
                    // Löschen  erfolgreich
                    JOptionPane.showMessageDialog(this, "Löschen war erfolgreich");
                } else {
                    // Löschen nicht erfolgreich
                    JOptionPane.showMessageDialog(this, "Löschen war nicht erfolgreich!");
                }
            }
        }
    }
}
