package model.controleur.controlleur_telecommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.commande.AjouterCommande;
import model.commande.Commande;
import model.commande.InstructionSet;
import model.elements.TypeElement;
import model.instruction.Instruction;
import model.instruction.InstructionEnum;
import model.telecomande.MessageTelecommandeEnum;
import model.telecomande.Telecommande;

public class ControlleurTelecommandeAjouterPorte extends ControlleurTelecommande implements ActionListener{

    private TypeElement typeElement;
    public ControlleurTelecommandeAjouterPorte(Telecommande telecommande, TypeElement typeElement){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
        this.typeElement = typeElement;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        ajouterPorte();
    }

    /**
     * Fonction pour ajouter une porte
     */
    public void ajouterPorte(){

        // Construction de l'instruction
        int id = telecommande.gestionID().getPorteID();
        identification = "P" + id;
        instruction = new Instruction(InstructionEnum.AJOUTER,identification,typeElement);
        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        // execution de la commande ajouter
        Commande comm = new AjouterCommande(instructionSet, telecommande.getCircuit());
        if(telecommande.executeCommand(comm)) {
            telecommande.setEstModifier(true);
            telecommande.getSauvegarde().add(instruction);
            super.commandeFaite(comm, MessageTelecommandeEnum.MSG_AJOUTER.message + " : " + identification);
        }
    }
    
}
