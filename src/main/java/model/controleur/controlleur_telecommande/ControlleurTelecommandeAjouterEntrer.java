package model.controleur.controlleur_telecommande;

import model.commande.AjouterCommande;
import model.commande.Commande;
import model.commande.InstructionSet;
import model.elements.TypeElement;
import model.instruction.Instruction;
import model.instruction.InstructionEnum;
import model.telecomande.MessageTelecommandeEnum;
import model.telecomande.Telecommande;
import model.telecomande.TelecommandeEtatEnum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlleurTelecommandeAjouterEntrer extends ControlleurTelecommande implements ActionListener{

    public ControlleurTelecommandeAjouterEntrer(Telecommande telecommande){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ajouterEntre();
    }

    /**
     * Fonction qui ajoute une entré au circuit
     */
    public void ajouterEntre(){
        int id = telecommande.gestionID().getEntreID();
        identification = "E" + id;
        instruction = new Instruction(InstructionEnum.AJOUTER,identification,TypeElement.ENTREE);
        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        // execution de la commande ajouter
        Commande comm = new AjouterCommande(instructionSet, telecommande.getControleurCircuit());
        if(telecommande.executeCommand(comm)){
            telecommande.setEstModifier(true);
            telecommande.getSauvegarde().add(instruction);
            new ControlleurTelecommandeDefinir(telecommande).ajouterDefinitionTest(instruction);
            super.commandeFaite(comm, MessageTelecommandeEnum.MSG_AJOUTER.message + " : " + identification);
        }

    }

    /**
     * Fonction qui ajoute une entré à l'initialisation du circuit
     */
    public void ajouterEntreDepart(){
        int id = telecommande.gestionID().getEntreID();
        identification = "E" + id;
        instruction = new Instruction(InstructionEnum.AJOUTER,identification,TypeElement.ENTREE);
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
