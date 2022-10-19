package model.telecomande;

import javax.swing.*;

public class BodyPorteSpecialise extends JPanel {

    BoutonCommande p1;
    BoutonCommande p2;

    public BodyPorteSpecialise(Telecommande contexte) {
        p1 = new BoutonCommande(TypeBoutonCommandeEnum.PORTE_SPECIALISE);
        p2 = new BoutonCommande(TypeBoutonCommandeEnum.PORTE_SPECIALISE);
        p1.addActionListener(contexte);
        p2.addActionListener(contexte);
        this.add(p1);
        this.add(p2);
    }

    public BoutonCommande getSpecial1Bouton() {
        return p1;
    }
    public BoutonCommande getSpecial2Bouton() {
        return p2;
    }

}
