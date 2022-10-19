package vue.historique;

import javax.swing.JFrame;

import model.telecomande.FenetreHistorique;

import javax.swing.*;
import java.awt.*;

public class VueFenetreHistorique extends JFrame {

    public VueFenetreHistorique(FenetreHistorique fenetreHistorique){
        // Construction du frame
        super("Opérations");
        setSize(300, 520);
        this.getContentPane().setLayout(null);
        this.setResizable(false);
        this.getContentPane().setEnabled(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout( new BorderLayout());
        getContentPane().add(fenetreHistorique);
    }
}
