package model.controleur.controlleur_base;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static javax.swing.JOptionPane.showMessageDialog;

import model.commande.AjouterCommande;
import model.commande.Commande;
import model.commande.GestionnaireHistorique;
import model.commande.InstructionSet;
import model.controleur.Board;
import model.instruction.Instruction;
import model.io.LectureFichier;
import model.telecomande.MessageTelecommandeEnum;
import model.telecomande.TelecommandeEtatEnum;

public class ControlleurChargement extends ControlleurSauvegarder {

    public ControlleurChargement(Board board) {
        super(board);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(telecommande.getEstModifier()){
            int choix = chargerUnFichier();
            switch(choix){
                case 0:
                    int reponse = super.enregistrer();
                    if(reponse == JFileChooser.APPROVE_OPTION)
                        chargerCircuit();
                    break;
                case 2:
                    chargerCircuit();
                    break;
                default:
                    break;
            }
        }
        else{
            chargerCircuit();
        }
        
    }

    /**
     * Fonction qui appel la fenetre pour ouvrir un circuit
     */
    public void chargerCircuit () {
        InstructionSet listeInstructions;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("XML", "xml");
        fileChooser.setDialogTitle("Ouvrir un fichier.");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filtre);
        int reponse = fileChooser.showOpenDialog(board);
        if(reponse == JFileChooser.APPROVE_OPTION){
            try{
                listeInstructions = LectureFichier.lire(fileChooser.getSelectedFile());
                ajouterCircuit(listeInstructions);
            }
            catch(Exception E){
                String message = E.getMessage();
                showMessageDialog(board, message);
            }
        }
    }

    /**
     * Fonction qui insère les instructions dans le circuit
     * @param instructionSet la liste d'instruction
     */
    private void ajouterCircuit(InstructionSet instructionSet){
        telecommande.initialiser();
        GestionnaireHistorique.getInstance().clearRedo();
        GestionnaireHistorique.getInstance().clearUndo();
        telecommande.getBody().rafraichirConsole(telecommande.getEntreeCircuitMap(), telecommande.getSortieCircuitMap(), telecommande.getPorteCircuitMap());
        for(Instruction instruction : instructionSet){
            telecommande.gestionID().mettreElementDisponible(instruction);
            telecommande.gestionID().mettreElementIndisponible(instruction);
            telecommande.getSauvegarde().add(instruction);
        }
        // execution de la commande ajouter
        Commande comm = new AjouterCommande(instructionSet, telecommande.getControleurCircuit());
        if(!telecommande.executeCommand(comm)){
            telecommande.getSauvegarde().clear();
        }
        telecommande.setState(TelecommandeEtatEnum.CIRCUIT_COURANT);

        //  Rafraichir la console
        telecommande.getBody().rafraichirConsole(telecommande.getEntreeCircuitMap(),
                                                 telecommande.getSortieCircuitMap(),
                                                 telecommande.getPorteCircuitMap());
        
        telecommande.getBody().afficherMessageValidation(MessageTelecommandeEnum.MSG_OUVRIR_FICHIER.message);
    }

    /**
     * Fonction qui ouvre une fenêtre pour choisir si on sauvegarder
     * le fichier et retourne le choix de l'utilisateur
     * @return Le choix de l'utilisateur
     */
    public int chargerUnFichier(){
        String[] option = {"Sauvegarder", "Annuler", "Ne pas sauvegarder"};
        return JOptionPane.showOptionDialog(board, "Voulez-vous vraiment charger un fichier? Vos données ne sont pas sauvegarder", "Sauvegarder?",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    null, option, option[2]);
        
    }
    
}
