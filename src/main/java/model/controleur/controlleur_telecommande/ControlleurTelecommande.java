package model.controleur.controlleur_telecommande;

import model.commande.Commande;
import model.commande.GestionnaireHistorique;
import model.commande.InstructionSet;
import model.instruction.Instruction;
import model.telecomande.Body;
import model.telecomande.Telecommande;
import model.telecomande.TelecommandeEtatEnum;

public abstract class ControlleurTelecommande {
    protected Telecommande telecommande;
    protected Instruction instruction;
    protected String identification;
    protected Body body;

    /**
     * Fonction qui informe modifie et rafraichi la console et l'historique
     * @param comm La commande a inverser
     * @param message Le message a afficher
     */
    protected void commandeFaite(Commande comm, String message) {

        InstructionSet inverse = comm.getInverse();
        if (inverse != null) {
            GestionnaireHistorique.getInstance().ajouterUndo(inverse);
            GestionnaireHistorique.getInstance().clearRedo();
            telecommande.setState(TelecommandeEtatEnum.CIRCUIT_COURANT);
        }
        //  Rafraichir la console
        body.rafraichirConsole(telecommande.getEntreeCircuitMap(), telecommande.getSortieCircuitMap(), telecommande.getPorteCircuitMap());

        // Afficher la confirmation dans la télécommande
        body.afficherMessageValidation(message);
    }
}
