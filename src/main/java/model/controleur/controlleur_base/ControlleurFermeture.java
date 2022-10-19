package model.controleur.controlleur_base;

import model.controleur.Board;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ControlleurFermeture extends ControlleurSauvegarder {

    public ControlleurFermeture(Board board) {
        super(board);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        fermer();
    }

    /**
     * Fonction qui gère la fermeture avec une sauvegarde du fichier circuit
     */
    public void fermer(){
        if(telecommande.getEstModifier()){
            int choix = demanderAQuitter();
            switch(choix){
                case 0:
                    int reponse = super.enregistrer();
                    if(reponse == JFileChooser.APPROVE_OPTION)
                        System.exit(0);
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    break;


            }
        }
        else{
            System.exit(0);
        }
    }

    /**
     * Fonction qui ouvre une fenêtre pour choisir si on sauvegarder
     * le fichier et retourne le choix de l'utilisateur
     * @return Le choix de l'utilisateur
     */
    public int demanderAQuitter(){
        String[] option = {"Sauvegarder","Annuler", "Ne pas sauvegarder"};
        return JOptionPane.showOptionDialog(board, "Voulez-vous vraiment quitter? Vos données ne sont pas sauvegarder", "Sauvegarder?",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    null, option, option[2]);
        
    }

    
}
