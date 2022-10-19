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
import model.telecomande.TelecommandeEtatEnum;

public class ControlleurTelecommandeAjouterSortie extends ControlleurTelecommande implements ActionListener{

    public ControlleurTelecommandeAjouterSortie(Telecommande telecommande){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ajouterSortie();
    }

    /**
     * Fonction pour ajouter une sortie
     */
    public void ajouterSortie(){
        int id = telecommande.gestionID().getSortieID();
        identification = "S" + id;
        instruction = new Instruction(InstructionEnum.AJOUTER,identification,TypeElement.SORTIE);
        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        Commande comm = new AjouterCommande(instructionSet, telecommande.getControleurCircuit());
        if(telecommande.executeCommand(comm)){
            telecommande.setEstModifier(true);
            telecommande.getSauvegarde().add(instruction);
            new ControlleurTelecommandeDefinir(telecommande).ajouterDefinitionTest(instruction);
            super.commandeFaite(comm, MessageTelecommandeEnum.MSG_AJOUTER.message + " : " + identification);
        }
    }

    /**
     * Fonction pour ajouter une sortie à l'initialisation du circuit
     */
    public void ajouterSortieDepart(){
        int id = telecommande.gestionID().getSortieID();
        identification = "S" + id;
        instruction = new Instruction(InstructionEnum.AJOUTER,identification,TypeElement.SORTIE);
        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        // execution de la commande ajouter
        Commande comm = new AjouterCommande(instructionSet, telecommande.getControleurCircuit());
        if(telecommande.executeCommand(comm)){
            telecommande.getSauvegarde().add(instruction);
            new ControlleurTelecommandeDefinir(telecommande).ajouterDefinitionCacherDepart(instruction);
            telecommande.setState(TelecommandeEtatEnum.CIRCUIT_COURANT);

            //  Rafraichir la console
            body.rafraichirConsole(telecommande.getEntreeCircuitMap(), telecommande.getSortieCircuitMap(), telecommande.getPorteCircuitMap());
    
            // Afficher la confirmation dans la télécommande
            body.afficherMessageValidation(MessageTelecommandeEnum.MSG_AJOUTER.message + " : " + identification);
        }
    }
}

