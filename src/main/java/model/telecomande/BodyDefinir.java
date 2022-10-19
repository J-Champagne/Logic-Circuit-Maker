package model.telecomande;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BodyDefinir extends JPanel {

    JTextArea definirNom;
    BoutonCommande definir;

    public BodyDefinir (Telecommande context) {


        JPanel panelDefinir = new JPanel();
        panelDefinir.setLayout(new BorderLayout());

        panelDefinir.setPreferredSize(new Dimension(280, 50));
        panelDefinir.setBorder(new EmptyBorder(5,5, 5, 5));
        panelDefinir.setBackground(Color.LIGHT_GRAY);

        definir = new BoutonCommande(TypeBoutonCommandeEnum.DEFINIR);
        definir.setEnabled(false);
        definir.setToolTipText("Modifier le nom d'un élément");
        definir.addActionListener(context);

        definirNom = new JTextArea();
        definirNom.setEnabled(false);
        definirNom.setPreferredSize(new Dimension(170, 20));
        definirNom.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        definirNom.addKeyListener(new KeyAdapter() {

//            @Override
//            public void keyTyped(KeyEvent e) {
//                String nomTexte = definirNom.getText().trim();
//                nomTexte.replaceAll("\\s+$", "");
//
//                boolean estValideTexte = (  !nomTexte.contains(" ") && definirNom.getText().trim().length() != 0 ) ;
//
//                definir.setEnabled(estValideTexte);
//
//            }


            @Override
            public void keyReleased(KeyEvent e) {
                String nomTexte = definirNom.getText().trim();
                nomTexte.replaceAll("\\s+$", "");

                boolean estValideTexte = (  !nomTexte.contains(" ") && definirNom.getText().trim().length() != 0 ) ;

                definir.setEnabled(estValideTexte);

            }


        });

        this.add(panelDefinir);


        TitreLabel definirLabel = new TitreLabel(MessageTelecommandeEnum.TITRE_DEFINIR.message);


        panelDefinir.add(definir, BorderLayout.WEST);
        panelDefinir.add(definirNom, BorderLayout.CENTER);
        panelDefinir.add(definirLabel, BorderLayout.NORTH);

    }

    public BoutonCommande getDefinirBouton() {
        return definir;
    }

    public String getDefinirNomTexte() {
        String nomTexte = definirNom.getText().trim();
        nomTexte.replaceAll("\\s+$", "");
        return nomTexte;
    }

    public void getViderNomTexte() {
        definirNom.setText("");
    }

    public JTextArea getDefinirNomJTextArea() {
        return definirNom;
    }
}
