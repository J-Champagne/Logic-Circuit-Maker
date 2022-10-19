package model.controleur.controlleur_telecommande;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.commande.Commande;
import model.commande.DefinirCommande;
import model.commande.InstructionSet;
import model.elements.TypeElement;
import model.instruction.Instruction;
import model.instruction.InstructionEnum;
import model.telecomande.MessageTelecommandeEnum;
import model.telecomande.Telecommande;

public class ControlleurTelecommandeDefinir extends ControlleurTelecommande implements ActionListener {

    public ControlleurTelecommandeDefinir(Telecommande telecommande){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        ajouterDefinition();        
    }

    /**
     * Fonction pour ajouter un nom à
     * un élément entré ou sorti du circuit
     */
    public void ajouterDefinition(){

        // Construction de l'instruction
        identification = body.getElementCourant();
        String nouveauNom = body.getDefinirNom();
        instruction = new Instruction(InstructionEnum.DEFINIR, identification, nouveauNom,
                getTypeElement(identification.charAt(0)));

        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        // execution de la commande ajouter
        Commande comm = new DefinirCommande(instructionSet, telecommande.getControleurCircuit());
        if(telecommande.executeCommand(comm)){
            telecommande.setEstModifier(true);
            telecommande.getSauvegarde().add(instruction);
            body.viderDefinirNom();
            super.commandeFaite(comm, MessageTelecommandeEnum.MSG_DEFINIR.message + " : " + identification);
        }

    }

    /**
     * Fonction pour ajouter un nom à
     * un élément entré ou sorti du circuit
     */
    public void ajouterDefinitionTest(Instruction inst){

        // Construction de l'instruction
        identification = inst.getIdentification();
        String nouveauNom = inst.getIdentification();
        instruction = new Instruction(InstructionEnum.DEFINIR, identification, nouveauNom,
                getTypeElement(identification.charAt(0)));

        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        // execution de la commande ajouter
        Commande comm = new DefinirCommande(instructionSet, telecommande.getControleurCircuit());
        if(telecommande.executeCommand(comm)){
            telecommande.setEstModifier(true);
            telecommande.getSauvegarde().add(instruction);
        }

    }

    /**
     * Fonction pour ajouter un nom à
     * un élément entré ou sorti du circuit
     */
    public void ajouterDefinitionCacherDepart(Instruction inst){

        // Construction de l'instruction
        identification = inst.getIdentification();
        String nouveauNom = inst.getIdentification();
        instruction = new Instruction(InstructionEnum.DEFINIR, identification, nouveauNom,
                getTypeElement(identification.charAt(0)));

        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        // execution de la commande ajouter
        Commande comm = new DefinirCommande(instructionSet, telecommande.getControleurCircuit());
        if(telecommande.executeCommand(comm)){
            telecommande.getSauvegarde().add(instruction);
        }

    }

    /**
     * Fonction pour connaitre le type d'élément à retourner
     * @param element L'élément à vérifier
     * @return Le type de l'élément
     */
    private TypeElement getTypeElement(char element) {
        TypeElement type;
    
        if (element == 'E') {
            type = TypeElement.ENTREE;
    
        } else if (element == 'S') {
            type = TypeElement.SORTIE;
        } else  {
            type = TypeElement.PORTE;
        }
        return type;
    }
}


