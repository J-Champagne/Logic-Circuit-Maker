package model.controleur.controlleur_telecommande;

import model.commande.Commande;
import model.commande.InstructionSet;
import model.commande.SupprimerCommande;
import model.elements.TypeElement;
import model.instruction.Instruction;
import model.instruction.InstructionEnum;
import model.telecomande.MessageTelecommandeEnum;
import model.telecomande.Telecommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlleurTelecommandeSupprimerElement extends ControlleurTelecommande implements ActionListener{

    public ControlleurTelecommandeSupprimerElement(Telecommande telecommande){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        supprimerElement();
    }

    /**
     * Fonction pour supprimer un élément
     */
    public void supprimerElement(){
        // SUPPRIMER
        identification = body.getElementCourant();
        System.out.println("(" + identification + ")");

        // construction de l'instruction
        instruction = new Instruction(InstructionEnum.SUPPRIMER,identification,
                getTypeElement(identification.charAt(0)));

        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        // execution de la commande supprimer
        Commande comm = new SupprimerCommande(instructionSet, telecommande.getCircuit());
        if(telecommande.executeCommand(comm)) {
            telecommande.setEstModifier(true);
            telecommande.gestionID().mettreElementDisponible(instruction);
            telecommande.getSauvegarde().add(instruction);
            super.commandeFaite(comm, MessageTelecommandeEnum.MSG_SUPPRIMER.message + " : " + identification);
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
