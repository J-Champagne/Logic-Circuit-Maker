package model.controleur;

import model.affichage.FenetreAffichage;
import model.telecomande.FenetreHistorique;
import model.telecomande.Telecommande;
import vue.historique.VueFenetreHistorique;
import model.tableVerite.TableVerite;

import javax.swing.*;
import java.awt.*;

/**
 * Cette nous permet de construire le board
 *
 */
public class Board extends JFrame{

    Telecommande telecommande;
    FenetreAffichage panelAffichage;
    TableVerite panelTableVerite;
    Menu menuBar;
    Telecommande sujetObserve;
    FenetreHistorique fenetreHistorique;
    VueFenetreHistorique vfh;

    public Board () {

        // Construction du frame
        this.getContentPane().setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(ConfigurationSingleton.getInstance().getApplicationBackgroundColor());
        this.getContentPane().setEnabled(false);
        this.setTitle(ConfigurationSingleton.getTitre());
        this.setBounds(ConfigurationSingleton.getInstance().getBoardBounds());
        this.setLayout( new BorderLayout());
        this.setContentPane(new JLabel(new ImageIcon(getClass().getResource(ConfigurationSingleton.getInstance().getFrameBackgroundImage()))));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void create() {
        ConfigurationSingleton configurationSingleton = ConfigurationSingleton.getInstance();

        // Edition
//        panelEdition = new Edition(configurationSingleton.getEditionBounds(),Color.LIGHT_GRAY );
        telecommande = new Telecommande();

        this.getContentPane().add(telecommande);


        // Modele de conception Observer
        // EditeurSujet est le sujet que la TableVerite va observer
//        sujetObserve = panelEdition.getContenu().getSujet();
        sujetObserve = telecommande.getSujetEditeur();


        // Affichage Circuit
        panelAffichage = new FenetreAffichage(sujetObserve, configurationSingleton.getAffichageBounds(),Color.LIGHT_GRAY );
        this.getContentPane().add(panelAffichage);


        // Table de vérite
        panelTableVerite = new TableVerite(sujetObserve, configurationSingleton.getTableVeriteBounds(),Color.LIGHT_GRAY );
        this.getContentPane().add(panelTableVerite);


        // Table de vérite
        fenetreHistorique = new FenetreHistorique(sujetObserve, configurationSingleton.getTableVeriteBounds(),Color.LIGHT_GRAY );
        //this.getContentPane().add(fenetreHistorique);
        vfh = new VueFenetreHistorique(fenetreHistorique);

        // Menu
        menuBar = new Menu();
        this.setJMenuBar(menuBar);

    }

    /**
     * Fonction qui retourne le controlleur du circuit
     * @return Le controlleur du circuit
     */
    public ControlleurCircuit getControlleurCircuit(){
        return telecommande.getControleurCircuit();
    }

    /**
     * Fonction qui retourne le panneau telecommande
     * @return La panneau telecommande
     */
    public Telecommande getTelecommande(){
        return telecommande;
    }

    /**
     * Fonction qui retourne le panneau de la table de vérité
     * @return La panneau table de vérité
     */
    public TableVerite getTableVerite(){
        return panelTableVerite;
    }

    /**
     * Fonction qui retourne le panneau d'affichage
     * @return La panneau affichage
     */
    public FenetreAffichage getFenetreAffichage(){
        return panelAffichage;
    }

    /**
     * Fonction qui retourne la barre de menu
     * @return La barre de menu
     */
    public Menu getMenu(){
        return menuBar;
    }

    /**
     * Fonction qui retourne la barre de menu
     * @return La barre de menu
     */
    public VueFenetreHistorique getVueFenetreHistorique(){
        return vfh;
    }
}