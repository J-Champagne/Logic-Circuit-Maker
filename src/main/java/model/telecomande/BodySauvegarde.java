package model.telecomande;

import javax.swing.*;

public class BodySauvegarde extends JPanel {
    BoutonCommande nouveau;
    BoutonCommande ouvrir;
    BoutonCommande enregistrer;

    public BodySauvegarde(Telecommande context) {
        nouveau = new BoutonCommande(TypeBoutonCommandeEnum.NOUVEAU);
        nouveau.setToolTipText("Nouveau circuit");
        nouveau.addActionListener(context);

        ouvrir = new BoutonCommande(TypeBoutonCommandeEnum.OUVRIR);
        ouvrir.setToolTipText("Charger un circuit");
        ouvrir.addActionListener(context);

        enregistrer = new BoutonCommande(TypeBoutonCommandeEnum.ENREGISTRER);
        enregistrer.setToolTipText("Enregistrer un circuit");
        enregistrer.addActionListener(context);

        this.add(nouveau);
        this.add(ouvrir);
        this.add(enregistrer);
    }

    public BoutonCommande getNouveau() {
        return nouveau;
    }

    public BoutonCommande getEnregistrer() {
        return enregistrer;
    }

    public BoutonCommande getOuvrir() {
        return ouvrir;
    }


}
