package model.commande;

import model.controleur.ControlleurCircuit;
import model.instruction.Instruction;
import model.instruction.InstructionEnum;

public class ConstructeurCommandes {
    public Commande commFromInstruction(Instruction inst, ControlleurCircuit controleurCircuit) {
        Commande comm = null;
        InstructionSet set = new InstructionSet();
        InstructionEnum type = inst.getInstruction();

        set.add(inst);
        if (type == InstructionEnum.AJOUTER) {
            comm = new AjouterCommande(set, controleurCircuit);
        }
        else if (type == InstructionEnum.DEFINIR) {
            comm = new DefinirCommande(set, controleurCircuit);
        }
        else if (type == InstructionEnum.RELIER) {
            comm = new RelierCommande(set, controleurCircuit);
        }
        else if (type == InstructionEnum.SUPPRIMER) {
            comm = new SupprimerCommande(set, controleurCircuit);
        }
        else if (type == InstructionEnum.DECONNECTER) {
            comm = new DeconnecterCommande(set, controleurCircuit);
        }

        return comm;
    }
}
