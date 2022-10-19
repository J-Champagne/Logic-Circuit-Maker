package model.telecomande;

import javax.swing.*;
import java.awt.*;

public class BodyEcran extends JPanel {
    JLabel message;
    JPanel messagePanel;

    public BodyEcran() {
        messagePanel = new JPanel();
        this.add(messagePanel);
        message = new JLabel("Bienvenue");
        messagePanel.setPreferredSize(new Dimension(250, 30));
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        messagePanel.add(message);
        Font font = new Font("Monospaced", Font.BOLD,12);
        message.setFont(font);
    }

    public void validation(String str) {
        messagePanel.setBackground(new Color(233,253,223));
        message.setText(str.toUpperCase());
    }

    public void erreur(String str) {
        messagePanel.setBackground(new Color(255,214,214));
        message.setText(str.toUpperCase());
    }
}
