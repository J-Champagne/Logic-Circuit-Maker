package model.controleur;

import javax.swing.*;

import model.controleur.controlleur_base.Application;
import model.controleur.controlleur_base.ControlleurChargement;
import model.controleur.controlleur_base.ControlleurExporterDessin;
import model.controleur.controlleur_base.ControlleurFermeture;
import model.controleur.controlleur_base.ControlleurNouveauFichier;
import model.controleur.controlleur_base.ControlleurSauvegarder;
import model.controleur.controlleur_base.ControlleurTableVerite;
import model.controleur.controlleur_telecommande.ControlleurAfficherUndoRedo;
import model.controleur.controlleur_telecommande.ControlleurRedo;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeAnnuler;
import model.controleur.controlleur_telecommande.ControlleurUndo;

/**
 * Cette gere le menu de l'application
 *
 */
public class Menu extends JMenuBar {

    public JMenuItem sauvegarderCircuitButton;
    public JMenuItem quitterButton;
    public JMenuItem nouveauCircuitButton;
    public JMenuItem ouvrirCircuitButton;

    public JMenuItem exporterCircuit;
    public JMenuItem exporterTableVerite;

    // Ajout des bouttons pour le menu
    public JMenuItem annulerOperationButton;
    public JMenuItem annulerToutesOperationsButton;
    public JMenuItem retablirOperationButton;
    public JMenuItem afficherOperation;

    public Menu() {

        JMenu menuFichier = new JMenu("Fichier");
        this.add(menuFichier);

        // Fichier
        nouveauCircuitButton = new JMenuItem("Nouveau circuit");
        menuFichier.add(nouveauCircuitButton);

        ouvrirCircuitButton = new JMenuItem("Ouvrir circuit");
        menuFichier.add(ouvrirCircuitButton);

        JSeparator separator_2 = new JSeparator();
        menuFichier.add(separator_2);

        sauvegarderCircuitButton = new JMenuItem("Sauvegarder circuit");
        menuFichier.add(sauvegarderCircuitButton);

        // Menu Quitter
        quitterButton = new JMenuItem("Quitter");
        menuFichier.add(quitterButton);

        JSeparator separator = new JSeparator();
        menuFichier.add(separator);

        exporterCircuit = new JMenuItem("Exporter circuit logique");
        menuFichier.add(exporterCircuit);

        exporterTableVerite = new JMenuItem("Exporter table de v??rit??");
        menuFichier.add(exporterTableVerite);

        JSeparator separator_1 = new JSeparator();
        menuFichier.add(separator_1);

        // Edition
        JMenu menuEdition = new JMenu("??dition");
        this.add(menuEdition);

        afficherOperation = new JMenuItem("Afficher les tables Undo/Redo");
        menuEdition.add(afficherOperation);

        // Menu annuler derniere operation
        annulerOperationButton = new JMenuItem("Annuler derni??re op??ration");
        menuEdition.add(annulerOperationButton);

        // Menu annuler toutes les op??rations
        annulerToutesOperationsButton = new JMenuItem("Annuler toutes les op??rations");
        menuEdition.add(annulerToutesOperationsButton);

        // Menu r??tablir derniere operation
        retablirOperationButton = new JMenuItem("R??tablir derni??re op??ration");
        menuEdition.add(retablirOperationButton);
    }

    /**
     * Ajoute les ??v??nements pour les boutons du menu
     * @param board
     */
    public void ajouterListener(Board board){
        sauvegarderCircuitButton.addActionListener(new ControlleurSauvegarder(board));
        ouvrirCircuitButton.addActionListener(new ControlleurChargement(board));
        nouveauCircuitButton.addActionListener(new ControlleurNouveauFichier(board));
        quitterButton.addActionListener(new ControlleurFermeture(board));
        exporterTableVerite.addActionListener(new ControlleurTableVerite(board));
        annulerToutesOperationsButton.addActionListener(new ControlleurTelecommandeAnnuler(board.getTelecommande()));
        annulerOperationButton.addActionListener(new ControlleurUndo(board.getTelecommande()));
        retablirOperationButton.addActionListener(new ControlleurRedo(board.getTelecommande()));
        exporterCircuit.addActionListener(new ControlleurExporterDessin(board));
        afficherOperation.addActionListener(new ControlleurAfficherUndoRedo(board));
    }
}
