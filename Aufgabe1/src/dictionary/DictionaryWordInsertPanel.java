package dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

/**
 *
 * @author adi
 */
public class DictionaryWordInsertPanel extends JPanel implements ActionListener {

    private final DictionaryProxy dic;
    private final JTextField tfInsertDeutsch;
    private final JTextField tfInsertEnglisch;
    private final JButton buttonEinfuegen;

    /**
     *
     * @param tb
     */
    public DictionaryWordInsertPanel(DictionaryProxy tb) {
        dic = tb;

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(new JLabel("Deutsch"));
        panel1.add(new JLabel("Englisch"));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));
        tfInsertDeutsch = new JTextField(null, 20);
        panel2.add(tfInsertDeutsch);
        tfInsertEnglisch = new JTextField(null, 20);
        panel2.add(tfInsertEnglisch);

        Border border = BorderFactory.createTitledBorder("Einfügen");
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(panel1);
        this.add(panel2);
        buttonEinfuegen = new JButton("Einfügen");
        this.add(buttonEinfuegen);
        buttonEinfuegen.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ...
        Object source = e.getSource();
        if (source == buttonEinfuegen) {
            String answer = dic.getDic().insert(tfInsertDeutsch.getText(), tfInsertEnglisch.getText());
            if (answer != null) {
                JOptionPane.showMessageDialog(this, "gespeichert!");
            } else {
                JOptionPane.showMessageDialog(this, "Eintrag bereits vorhanden!");
            }
            tfInsertDeutsch.setText(null);
            tfInsertEnglisch.setText(null);
        }
    }
}
