package model.controleur.controlleur_telecommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.controleur.Board;

public class ControlleurAfficherUndoRedo extends ControlleurTelecommande implements ActionListener{
    private Board board;
    public ControlleurAfficherUndoRedo(Board board){
        super();
        this.board = board;
        this.telecommande = board.getTelecommande();
        body = telecommande.getBody();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        board.getVueFenetreHistorique().setVisible(true);
        
    }
    
}
