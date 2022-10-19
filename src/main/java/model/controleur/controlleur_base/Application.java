package model.controleur.controlleur_base;

import model.controleur.Board;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import java.awt.event.WindowAdapter;

/**
 *
 * Cette classe permet de gérer les manipulations de l'usager
 *
 *
 */
public class Application {
    private Board board;

    /**
     * Fonction d'initialisation du Board
     */
    public void demarrer () {

        // ajout du board
        board = new Board();
        board.create();
        board.getMenu().ajouterListener(board);
        board.getTelecommande().ajouterListener(board);
        board.getTableVerite().ajouterListener(board);
        board.getFenetreAffichage().ajouterListener(board);
        board.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        new ControlleurNouveauFichier(board).ouvrirNouveauFichier();
        board.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent ev){
                fermeture();
            }
        });
    }
    
    /**
     * Fonction de fermeture
     */
    public void fermeture() {
        new ControlleurFermeture(board).fermer();
    }
}
