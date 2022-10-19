package model.controleur.controlleur_telecommande;

import model.commande.Commande;
import model.commande.InstructionSet;
import model.commande.RelierCommande;
import model.elements.TypeElement;
import model.instruction.Instruction;
import model.instruction.InstructionEnum;
import model.telecomande.MessageTelecommandeEnum;
import model.telecomande.Telecommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlleurTelecommandeRelierElement extends ControlleurTelecommande implements ActionListener {

    public ControlleurTelecommandeRelierElement(Telecommande telecommande){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        relier();
    }
    
    /**
     * Fonction pour relier deux éléments
     */
    public void relier(){
        // RELIER

        // connection de la console connection
        identification = body.getConnectionCourantEntree();
        String identification2 = body.getConnectionCourantSortie();

        instruction = new Instruction(InstructionEnum.RELIER,
                identification, getTypeElement(identification.charAt(0)),
                identification2, getTypeElement(identification2.charAt(0)));
        InstructionSet instructionSet = new InstructionSet();
        instructionSet.add(instruction);

        // execute la commande relier
        Commande comm = new RelierCommande(instructionSet, telecommande.getCircuit());
        if(telecommande.executeCommand(comm)) {
            telecommande.setEstModifier(true);
            telecommande.getSauvegarde().add(instruction);
            super.commandeFaite(comm, MessageTelecommandeEnum.MSG_RELIER.message + " : " + identification + " --> " + identification2);
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
