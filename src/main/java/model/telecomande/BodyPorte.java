package model.telecomande;

import javax.swing.*;
import java.awt.*;

public class BodyPorte extends JPanel {

    BoutonCommande entree;
    BoutonCommande et;
    BoutonCommande ou;
    BoutonCommande non;
    BoutonCommande sortie;

    public BodyPorte (Telecommande context) {

        JPanel panelAjouter = new JPanel();
        panelAjouter.setLayout(new BorderLayout());
        this.add(panelAjouter);


        TitreLabel titreAjouter = new TitreLabel(MessageTelecommandeEnum.TITRE_AJOUTER.message);
        panelAjouter.add(titreAjouter, BorderLayout.NORTH);


        entree = new BoutonCommande(TypeBoutonCommandeEnum.ENTREE);
        entree.setToolTipText("Ajouter une 'ENTRÉE'");
        entree.addActionListener(context);

        sortie = new BoutonCommande(TypeBoutonCommandeEnum.SORTIE);
        sortie.setToolTipText("Ajouter une 'SORTIE'");
        sortie.addActionListener(context);

        et = new BoutonCommande(TypeBoutonCommandeEnum.AND);
        et.setToolTipText("Ajouter une 'Porte ET'");
        et.addActionListener(context);

        ou = new BoutonCommande(TypeBoutonCommandeEnum.OR);
        ou.setToolTipText("Ajouter une 'Porte OU'");
        ou.addActionListener(context);

        non = new BoutonCommande(TypeBoutonCommandeEnum.NOT);
        non.setToolTipText("Ajouter une 'Porte NON'");
        non.addActionListener(context);


        JPanel panelPorte = new JPanel();

        panelAjouter.add(panelPorte, BorderLayout.CENTER);

        panelPorte.add(entree);
        panelPorte.add(et);
        panelPorte.add(ou);
        panelPorte.add(non);
        panelPorte.add(sortie);
    }

    public BoutonCommande getEntree () {
        return entree;
    }

    public BoutonCommande getSortie () {
        return sortie;
    }

    public BoutonCommande getEt () {
        return et;
    }

    public BoutonCommande getOu () {
        return ou;
    }

    public BoutonCommande getNon () {
        return non;
    }






}
