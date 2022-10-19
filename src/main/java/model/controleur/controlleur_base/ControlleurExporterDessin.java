package model.controleur.controlleur_base;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import model.controleur.Board;
import model.telecomande.MessageTelecommandeEnum;

public class ControlleurExporterDessin extends ControlleurSauvegarder {
    
    public ControlleurExporterDessin(Board board) {
        super(board);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        exporterDessin();
    }

    /**
     * Fonction qui ouvre la fenêtre pour exporter un dessin du circuit
     */
    public void exporterDessin () {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("PNG", "png");
        fileChooser.setDialogTitle("Exporter un fichier.");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filtre);
        int reponse = fileChooser.showSaveDialog(board);
        if(reponse == JFileChooser.APPROVE_OPTION){
            try {
                ImageIO.write(genererImage(), "png", ajouterExtension(fileChooser.getSelectedFile()));
                board.getTelecommande().getBody().afficherMessageValidation(MessageTelecommandeEnum.MSG_EXTRACTION_CIRCUIT.message);
            } catch (IOException e) {
                e.printStackTrace();
            }    
            catch (Exception e) {
                e.printStackTrace();
            }                       
        }
    }

    /**
     * Fonction qui génère une image du circuit et la retourne
     * @return L'image du circuit
     */
    private BufferedImage genererImage(){
        BufferedImage bi = new BufferedImage(board.getFenetreAffichage().getAffichage().getSize().width,
                                             board.getFenetreAffichage().getAffichage().getSize().height,
                                             BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        board.getFenetreAffichage().getAffichage().paint(g);  //this == JComponent
        g.dispose();
        return bi;
    }

    /**
     * Fonction qui ajoute la bonne extension à la fin du fichier
     * @param nomFichier Le fichier dont il faut modifier l'extension
     * @return Retourne le fichier avec la bonne extension
     */
    private File ajouterExtension(File nomFichier) {
        if(!nomFichier.getAbsolutePath().endsWith(".png"))
        nomFichier = new File(nomFichier + ".png");
        return nomFichier;
    }
}
