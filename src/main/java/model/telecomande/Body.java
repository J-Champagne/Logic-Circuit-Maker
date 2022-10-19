package model.telecomande;

import model.elements.Element;

import javax.swing.*;
import java.util.HashMap;

public class Body extends JPanel {


    BodySauvegarde circuit;
    BodyPorte porte;
    BodyHistorique historique;
    BodyConsole console;
    BodyExtraction extraction;
    BodyEcran screen;
    BodyDefinir definir;
    BodyPorteSpecialise specialiser;

    public Body (Telecommande context) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        screen = new BodyEcran();
        this.add(screen);

        circuit = new BodySauvegarde(context);
        this.add(circuit);

        porte = new BodyPorte(context);
        this.add(porte);

        specialiser = new BodyPorteSpecialise(context);
        this.add(specialiser);

        historique = new BodyHistorique(context);
        this.add(historique);

        definir = new BodyDefinir(context);
        this.add(definir);

        console = new BodyConsole(context);
        this.add(console);

        extraction = new BodyExtraction(context);
//        this.add(extraction);
    }

    // Circuit
    public BoutonCommande getBoutonNouveauFichier() {
        return circuit.getNouveau();
    }

    public BoutonCommande getBoutonEnregistrerFichier() {
        return circuit.getEnregistrer();
    }

    public BoutonCommande getBoutonOuvrirFichier() {
        return circuit.getOuvrir();
    }


    // Porte
    public BoutonCommande getElementEntree() {
        return porte.getEntree();
    }

    public BoutonCommande getBoutonPorteEt() {
        return porte.getEt();
    }

    public BoutonCommande getBoutonPorteOu() {
        return porte.getOu();
    }

    public BoutonCommande getBoutonPorteNon() {
        return porte.getNon();
    }

    public BoutonCommande getBoutonElementSortie() {
        return porte.getSortie();
    }

    // Historique
    public BoutonCommande getBoutonUndo() {
        return historique.getUndo();
    }

    public BoutonCommande getBoutonCancel() {
        return historique.getCancel();
    }

    public BoutonCommande getBoutonRedo() {
        return historique.getRedo();
    }

    public BoutonCommande getBoutonAfficher () {
        return historique.getAfficher();
    }

    // Console
    public BoutonCommande getBoutonSupprimer() {
        return console.getBoutonSupprimer();
    }

    public BoutonCommande getBoutonRelier() {
        return console.getBoutonRelier();
    }

    public BoutonCommande getBoutonDefinir() {
        return definir.getDefinirBouton();
    }

    public JTextArea getDefinirJTextArea() {
        return definir.getDefinirNomJTextArea();
    }


    public String getDefinirNom () {
        return definir.getDefinirNomTexte();
    }


    public void viderDefinirNom () {
        definir.getViderNomTexte();
    }

    public BoutonCommande getSpecial1Bouton() {
        return specialiser.getSpecial1Bouton();
    }

    public BoutonCommande getSpecial2Bouton() {
        return specialiser.getSpecial2Bouton();
    }



    // Extraire

    public BoutonCommande getExtraireCircuit () {
        return extraction.getCircuit();
    }

    public BoutonCommande getExtraireTableVerite () {
        return extraction.getTableVerite();
    }



    public JList<String> getMouseElement () {
        return console.getlisteEntree();
    }


    // Screen
    public void afficherMessageValidation(String str) {
        screen.validation(str);
    }

    public void afficherMessageErreur(String str) {
        screen.erreur(str);
    }


    public void rafraichirConsole(HashMap<String, Element> entree,
                                  HashMap<String, Element> sortie,
                                  HashMap<String, Element> porte) {

        console.setMode(entree, sortie, porte);
    }

    public String getElementCourant () {
        return console.getElementCourantConsole();
    }

    public String getConnectionCourantEntree() {
        return console.getConnectionCourantConsoleEntree();
    }

    public String getConnectionCourantSortie() {
        return console.getConnectionCourantConsoleSortie();
    }

    public int getConnectionCourantEntreeIndex() {
        return console.getConnectionCourantConsoleEntreeeIndex();
    }
    public int getConnectionCourantSortieIndex() {
        return console.getConnectionCourantConsoleSortieIndex();
    }


    public boolean getEstConnection () {
        return console.getEstConnection();
    }


}
