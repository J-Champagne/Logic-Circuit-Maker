package model.controleur.controlleur_base;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.controleur.Board;
import model.io.EcrireFichier;
import model.telecomande.MessageTelecommandeEnum;

public class ControlleurSauvegarder extends ControlleurApplication implements ActionListener{

    protected Board board;
    public ControlleurSauvegarder(Board board){
        this.board = board;
        this.telecommande = board.getTelecommande();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        enregistrer();
    }

    /**
     * Fonction qui ouvre la fenêtre pour sauvegarde un circuit
     */
    public int enregistrer() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("XML", "xml");
        fileChooser.setDialogTitle("Sauvegarder un fichier.");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filtre);
        int reponse = fileChooser.showSaveDialog(board);
        if(reponse == JFileChooser.APPROVE_OPTION){
            EcrireFichier.sauvegarder(telecommande.getSauvegarde(), ajouterExtension(fileChooser.getSelectedFile()));
            telecommande.getBody().afficherMessageValidation(MessageTelecommandeEnum.MSG_ENREGISTRER_FICHIER.message);
            telecommande.setEstModifier(false);
        }
        return reponse;
    }

    /**
     * Fonction qui ajoute la bonne extension au fichier
     * @param nomFichier Le fichier dont il faut modifier l'extension
     * @return Le fichier modifié
     */
    private File ajouterExtension(File nomFichier) {
        if(!nomFichier.getAbsolutePath().endsWith(".xml"))
        nomFichier = new File(nomFichier + ".xml");
        return nomFichier;
    }

    /**
     * Fonction qui ouvre une fenêtre pour choisir si on sauvegarder
     * le fichier et retourne le choix de l'utilisateur
     * @return Le choix de l'utilisateur
     */
    public int optionChoisi(){
        String[] option = {"Oui","Non"};
        return JOptionPane.showOptionDialog(board, "Voulez-vous sauvegarder?", "Sauvegarde",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    null, option, option[1]);
        
    }
    
}
