package rzd.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by IntelliJ IDEA.
 * User: Евгений
 * Date: 03.10.2010
 * Time: 2:27:01
 * To change this template use File | Settings | File Templates.
 */
public class MakerDefaultTextInField implements FocusListener {
    private JTextField tf;
    private String defString;

    public MakerDefaultTextInField(String defaultText, JTextField tf) {
        this.tf = tf;
        this.defString = defaultText;
        tf.addFocusListener(this);
        focusLost(null);
    }

    public void focusGained(FocusEvent e) {
        if (tf.getText().trim().equals(defString)) {
            tf.setFont(new Font("Arial", 13, Font.PLAIN));
            tf.setForeground(Color.BLACK);
            tf.setText("");
        }
    }

    public void focusLost(FocusEvent e) {
        if (tf.getText().trim().equals("")) {
            tf.setFont(new Font("Arial", 13, Font.ITALIC));
            tf.setForeground(Color.GRAY);
            tf.setText(defString);
        }
    }
}
