package model.telecomande;

import javax.swing.*;
import java.awt.*;

public class Head extends JPanel {


    public Head () {
        JLabel sigle = new JLabel(MessageTelecommandeEnum.LABEL_CIE.message);
        sigle.setForeground(Color.BLUE);
        this.setLayout(new BorderLayout());
        this.add(sigle,BorderLayout.CENTER);
    }
}
