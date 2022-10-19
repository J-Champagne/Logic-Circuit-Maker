package model.commande;

import model.controleur.ControlleurCircuit;
import model.exception.*;

public class SupprimerCommande extends Commande{

    public SupprimerCommande(InstructionSet instructionSet, ControlleurCircuit controleurCircuit) {
        super(instructionSet, controleurCircuit);
    }

    @Override
    public boolean execute() throws MaxElementsException, ExistingNameException, LastElementException,
            InvalidNameException, InvalidConnectionException {

        // commande invalide
        if (instructionSet == null || instructionSet.isEmpty()) return false;
        this.inverse = controleurCircuit.charger(instructionSet);
        return true;
    }
}
