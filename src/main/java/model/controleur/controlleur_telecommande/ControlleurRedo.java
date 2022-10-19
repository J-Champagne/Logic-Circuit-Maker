package model.controleur.controlleur_telecommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.commande.Commande;
import model.commande.GestionnaireHistorique;
import model.commande.InstructionSet;
import model.instruction.Instruction;
import model.telecomande.Telecommande;
import model.telecomande.TelecommandeEtatEnum;

public class ControlleurRedo extends ControlleurTelecommande implements ActionListener{
    public ControlleurRedo(Telecommande telecommande){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        redo();
    }

    /**
     * Fonction pour revenir annuler le dernier Undo
     */
    public void redo(){
        InstructionSet inverses = new InstructionSet();

        body.afficherMessageValidation("refaire une instruction");
        InstructionSet instructions = GestionnaireHistorique.getInstance().getHistorique().redo();

        if (instructions != null) {
            for (int i = instructions.size() - 1; i >= 0; i--) {
                Commande comm = telecommande.getConstructeurCommandes().commFromInstruction(instructions.get(i), telecommande.getCircuit());
                telecommande.executeCommand(comm);
                telecommande.getSauvegarde().add(instructions.get(i));
                telecommande.gestionID().mettreElementIndisponible(instructions.get(i));
                telecommande.gestionID().mettreElementIndisponible(instructions.get(i));
                InstructionSet inverse = comm.getInverse();
                if (inverse != null) {
                    inverses.add(0, inverse.get(0));
                }
            }
            GestionnaireHistorique.getInstance().ajouterUndo(inverses);
            telecommande.setState(TelecommandeEtatEnum.CIRCUIT_COURANT);
            body.rafraichirConsole(telecommande.getEntreeCircuitMap(), telecommande.getSortieCircuitMap(), telecommande.getPorteCircuitMap());
        }
    }
}
