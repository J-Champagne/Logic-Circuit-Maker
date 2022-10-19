package model.telecomande;

import javax.swing.*;
import java.awt.*;

public class Foot extends JPanel {
    public Foot () {
        JLabel nomEquipe = new JLabel(MessageTelecommandeEnum.LABEL_PROJET.message);
        Font font = new Font("Courier", Font.BOLD,20);
        nomEquipe.setFont(font);
        this.add(nomEquipe);
    }
}
