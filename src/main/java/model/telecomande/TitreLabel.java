package model.telecomande;

import javax.swing.*;
import java.awt.*;

public class TitreLabel extends JLabel {

    public TitreLabel (String text) {
        super( text );
        this.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font("Helvetica", Font.PLAIN,12);
        this.setFont(font);
        this.setForeground(new Color(2,21,89));
    }
}
