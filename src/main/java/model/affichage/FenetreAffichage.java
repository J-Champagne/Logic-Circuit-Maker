package model.affichage;

import model.controleur.Board;
import model.controleur.controlleur_base.ControlleurExporterDessin;
import model.telecomande.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Cette classe nous permet d'afficher le circuit
 *
 */
public class FenetreAffichage extends JLayeredPane implements Observateur {

    private final Telecommande sujetObservable;
    private Affichage dessin;
    private BoutonCommande jpeg;

    public FenetreAffichage(Telecommande e, Rectangle r, Color c) {
        this.sujetObservable = e;

        // souscription
        this.sujetObservable.ajouterObservateur(this);

        this.setBackground(c);
        this.setBounds(r);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEtchedBorder(1));

        this.setLayout(new BorderLayout());

        // Table de verite
        dessin = new Affichage(sujetObservable);
        this.add(dessin, BorderLayout.CENTER);

        TitreLabel label = new TitreLabel(MessageTelecommandeEnum.TITRE_CIRCUIT.message);

        jpeg = new BoutonCommande(TypeBoutonCommandeEnum.PNG);
        jpeg.setToolTipText("Extraire une image");

        jpeg.setPreferredSize(new Dimension(50,30));
        JPanel impresstion = new JPanel();

        impresstion.setLayout(new BorderLayout());
        impresstion.add(jpeg, BorderLayout.EAST);
        impresstion.add(label, BorderLayout.CENTER);

        this.add(impresstion,BorderLayout.NORTH);
    }

    /**
     * Fonction pour retourner le panneau d'affichage
     * @return Le panneau d'affichage
     */
    public Affichage getAffichage(){
        return dessin;
    }

    /**
     * Fonction pour ajouter le ActionListener au bouton
     * @param board La fenêtre principale
     */
    public void ajouterListener(Board board){
        jpeg.addActionListener(new ControlleurExporterDessin(board));
    }

    @Override
    public void update() {
    }
}
