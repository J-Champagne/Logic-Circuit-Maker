package model.commande;

import model.controleur.ControlleurCircuit;
import model.exception.*;

public abstract class Commande {
    protected InstructionSet instructionSet;
    protected InstructionSet inverse = null;
    protected ControlleurCircuit controleurCircuit;

    public Commande(InstructionSet instructionSet, ControlleurCircuit controleurCircuit) {
        this.instructionSet = instructionSet;
        this.controleurCircuit = controleurCircuit;
    }

    public InstructionSet getInverse(){
        return this.inverse;
    }

    public abstract boolean execute() throws MaxElementsException, ExistingNameException, LastElementException,
            InvalidNameException, InvalidConnectionException;
}
