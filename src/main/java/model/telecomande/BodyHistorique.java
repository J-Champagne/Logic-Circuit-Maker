package model.telecomande;

import javax.swing.*;

public class BodyHistorique extends JPanel {
    BoutonCommande undo;
    BoutonCommande cancel;
    BoutonCommande redo;
    BoutonCommande afficher;

    public BodyHistorique(Telecommande context) {
        undo = new BoutonCommande(TypeBoutonCommandeEnum.UNDO);
        undo.setToolTipText("UNDO");
        undo.addActionListener(context);


        redo = new BoutonCommande(TypeBoutonCommandeEnum.REDO);
        redo.setToolTipText("REDO");
        redo.addActionListener(context);

        cancel = new BoutonCommande(TypeBoutonCommandeEnum.CANCEL);
        cancel.setToolTipText("Vider l'historique");
        cancel.addActionListener(context);

        afficher = new BoutonCommande(TypeBoutonCommandeEnum.AFFICHER);
        cancel.setToolTipText("Afficher Undo/Redo");
        cancel.addActionListener(context);

        this.add(undo);
        this.add(cancel);
        this.add(redo);
        this.add(afficher);
    }

    public BoutonCommande getUndo () {
        return undo;
    }

    public BoutonCommande getCancel () {
        return cancel;
    }

    public BoutonCommande getRedo () {
        return redo;
    }

    public BoutonCommande getAfficher () {
        return afficher;
    }
}
