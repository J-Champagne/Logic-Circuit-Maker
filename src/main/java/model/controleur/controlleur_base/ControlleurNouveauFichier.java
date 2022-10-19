package model.controleur.controlleur_base;

import model.commande.GestionnaireHistorique;
import model.controleur.Board;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeAjouterEntrer;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeAjouterSortie;
import model.telecomande.MessageTelecommandeEnum;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ControlleurNouveauFichier extends ControlleurSauvegarder{

    public ControlleurNouveauFichier(Board board) {
        super(board);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(telecommande.getEstModifier()){
            int choix = demanderUnNouveauFichier();
            switch(choix){
                case 0:
                    int reponse = super.enregistrer();
                    if(reponse == JFileChooser.APPROVE_OPTION)
                        ouvrirNouveauFichier();
                    break;
                case 2:
                    ouvrirNouveauFichier();
                    break;
                default:
                    break;
            }
        }
        else{
            ouvrirNouveauFichier();
        }
    }

    /**
     * Fonction qui va initialiser un nouveau fichier
     */
    public void ouvrirNouveauFichier(){
        telecommande.initialiser();
        GestionnaireHistorique.getInstance().clearRedo();
        GestionnaireHistorique.getInstance().clearUndo();
        telecommande.getBody().rafraichirConsole(telecommande.getEntreeCircuitMap(), telecommande.getSortieCircuitMap(), telecommande.getPorteCircuitMap());
        new ControlleurTelecommandeAjouterEntrer(telecommande).ajouterEntreDepart();
        new ControlleurTelecommandeAjouterEntrer(telecommande).ajouterEntreDepart();
        new ControlleurTelecommandeAjouterSortie(telecommande).ajouterSortieDepart();
        telecommande.getBody().afficherMessageValidation(MessageTelecommandeEnum.MSG_NOUVEAU_FICHIER.message);

    }

    /**
     * Fonction qui ouvre une fenêtre pour choisir si on sauvegarder
     * le fichier et retourne le choix de l'utilisateur
     * @return Le choix de l'utilisateur
     */
    public int demanderUnNouveauFichier(){
        String[] option = {"Sauvegarder", "Annuler", "Ne pas sauvegarder"};
        return JOptionPane.showOptionDialog(board, "Voulez-vous vraiment ouvrir un nouveau fichier? Vos données ne sont pas sauvegarder", "Sauvegarder?",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    null, option, option[2]);
        
    }
    
}
