package model.controleur.controlleur_base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.controleur.Board;
import model.io.EcrireTableVerite;
import model.telecomande.MessageTelecommandeEnum;

public class ControlleurTableVerite implements ActionListener{

    private Board board;
    public ControlleurTableVerite(Board board){
        this.board = board;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        exporterTableVerite();
    }
    
    /**
     * Fonction qui ouvre une fenêtre pour exporter la table de vérité
     */
    public void exporterTableVerite() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("TEXT", "txt");
        fileChooser.setDialogTitle("Exporter la table de vérité..");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filtre);
        int reponse = fileChooser.showSaveDialog(board);
        if(reponse == JFileChooser.APPROVE_OPTION){
            EcrireTableVerite.sauvegarder(board.getTableVerite().getEnteteTableVeriteInterface(), board.getTableVerite().getDonneeTableVeriteInterface(),ajouterExtension(fileChooser.getSelectedFile()));
            board.getTelecommande().getBody().afficherMessageValidation(MessageTelecommandeEnum.MSG_EXTRACTION_TABLE_VERITE.message);

        }
    }

    /**
     * Fonction qui ajoute la bonne extension au fichier
     * @param nomFichier Le fichier dont il faut modifier l'extension
     * @return Le fichier modifié
     */
    private File ajouterExtension(File nomFichier) {
        if(!nomFichier.getAbsolutePath().endsWith(".txt"))
        nomFichier = new File(nomFichier + ".txt");
        return nomFichier;
    }
    
}
