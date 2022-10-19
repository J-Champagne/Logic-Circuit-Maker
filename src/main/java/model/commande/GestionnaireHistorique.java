package model.commande;

import model.telecomande.Historique;

public class GestionnaireHistorique {

    private static GestionnaireHistorique instance;
    private static Historique<InstructionSet> historique;

    private GestionnaireHistorique () {
    }

    public static GestionnaireHistorique getInstance() {
        if (instance == null) {
            instance = new GestionnaireHistorique();
            historique = new Historique();
        }
        return instance;
    }

    public Historique<InstructionSet> getHistorique () {
        return historique;
    }

    public void ajouter (InstructionSet instructionSet) {
        historique.ajouter(instructionSet);
    }

    public void ajouterRedo(InstructionSet instructionSet) {historique.pushRedo(instructionSet);}

    public void ajouterUndo(InstructionSet instructionSet) {historique.pushUndo(instructionSet);}

    public void clearRedo() {historique.clearRedo();}
    public void clearUndo() {historique.clearUndo();}

}
