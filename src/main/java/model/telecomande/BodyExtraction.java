package model.telecomande;

import javax.swing.*;

public class BodyExtraction extends JPanel {

    BoutonCommande circuit;
    BoutonCommande tableverite;

    public BodyExtraction(Telecommande context) {
        circuit = new BoutonCommande(TypeBoutonCommandeEnum.XML,"circuit");
        circuit.addActionListener(context);

        tableverite = new BoutonCommande(TypeBoutonCommandeEnum.XML,"table de vérité");
        tableverite.addActionListener(context);

        this.add(circuit);
        this.add(tableverite);
    }

    public BoutonCommande getCircuit () {
        return circuit;
    }

    public BoutonCommande getTableVerite () {
        return tableverite;
    }


}
