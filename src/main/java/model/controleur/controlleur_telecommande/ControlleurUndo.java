package model.controleur.controlleur_telecommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.commande.Commande;
import model.commande.GestionnaireHistorique;
import model.commande.InstructionSet;
import model.instruction.Instruction;
import model.telecomande.Telecommande;
import model.telecomande.TelecommandeEtatEnum;

public class ControlleurUndo extends ControlleurTelecommande implements ActionListener{

    public ControlleurUndo(Telecommande telecommande){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        undo();
    }

    /**
     * fonction pour revenir à l'étape précédente.
     */
    public void undo() {
        InstructionSet inverses = new InstructionSet();

        body.afficherMessageValidation("défaire une instruction");
        InstructionSet instructions = GestionnaireHistorique.getInstance().getHistorique().undo();

        if (instructions != null) {
            for (Instruction inst : instructions) {
                Commande comm = telecommande.getConstructeurCommandes().commFromInstruction(inst, telecommande.getCircuit());
                telecommande.executeCommand(comm);
                telecommande.getSauvegarde().add(inst);
                telecommande.gestionID().mettreElementDisponible(inst);
                telecommande.gestionID().mettreElementIndisponible(inst);
                InstructionSet inverse = comm.getInverse();
                if (inverse != null) {
                    inverses.add(inverse.get(0));
                }
            }
            GestionnaireHistorique.getInstance().ajouterRedo(inverses);
            telecommande.setState(TelecommandeEtatEnum.CIRCUIT_COURANT);
            body.rafraichirConsole(telecommande.getEntreeCircuitMap(), telecommande.getSortieCircuitMap(), telecommande.getPorteCircuitMap());
        }
    }
}
