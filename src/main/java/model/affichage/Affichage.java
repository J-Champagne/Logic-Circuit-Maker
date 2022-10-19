package model.affichage;

import model.facade.CircuitFacade;
import model.telecomande.Telecommande;
import model.telecomande.Observateur;
import vue.dessin.composite.GrapheSimple;
import vue.dessin.section.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Cette classe permet de dessiner le graphique
 *
 */
public class Affichage extends JPanel implements Observateur {

    // Panel Affichage
    private final int x = 1;
    private final int y = 1;
    private final int width = 900;
    private final int height = 290;

    // partition de l'affichage
    double ratioHeightCircuit = 0.7;
    double ratioHeightFlottant = 0.15;
    double ratioHeightSemiFlottant = 0.15;

    // porteFlottante
    private final int porteFlottanteX = x;
    private final int porteFlottanteY = y;
    private final int porteFlottanteWidth = width;
    private final int porteFlottanteHeight = (int) (height * ratioHeightFlottant);

    // PorteSortie
    private final int porteSortieWidth = 50;
    private final int porteSortieHeight = (int) (height * ratioHeightCircuit);
    private final int porteSortieX =x + width - porteSortieWidth;
    private final int porteSortieY = y + porteFlottanteHeight;

    // PorteCircuit
    private final int porteEntreeX = x;
    private final int porteEntreeY = y + porteFlottanteHeight;
    private final int porteEntreeWidth = porteSortieWidth;
    private final int porteEntreeHeight = (int) (height * ratioHeightCircuit);

    // PorteCircuit
    private final int porteCircuitX = x + porteEntreeWidth;
    private final int porteCircuitY = y + porteFlottanteHeight;
    private final int porteCircuitWidth = width - porteEntreeWidth - porteSortieWidth;
    private final int porteCircuitHeight = (int) (height * ratioHeightCircuit);;

    // PorteCircuit
    private final int porteSemiFlottanteX = x;
    private final int porteSemiFlottanteY = y + porteFlottanteHeight + porteCircuitHeight;
    private final int porteSemiFlottanteWidth = width;
    private final int porteSemiFlottanteHeight = (int) (height * ratioHeightSemiFlottant);;

    // Observateur
    private final Telecommande sujetObservable;

    // Facade
    CircuitFacade circuitFacade;

    // Section des dessins
    private SectionFlottante flottantes;
    private SectionSortie sorties;
    private SectionEntree entrees;
    private SectionCircuit circuits;
    private SectionSemiFlottante semiFlottantes;

    public Affichage(Telecommande e) {
        // attache l'observateur
        sujetObservable = e;
        this.sujetObservable.ajouterObservateur(this);

        // partition de la hauteur de l'affichage
        double ratioHeightCircuit = 0.8;

        // instanciation des sections
        sorties = new SectionSortie();
        entrees = new SectionEntree();
        flottantes = new SectionFlottante();
        circuits = new SectionCircuit();
        semiFlottantes = new SectionSemiFlottante();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // Dessin des sections de l'affichage

        // Sortie
        ArrayList<GrapheSimple> sortie = circuitFacade.sortie();
        sorties.charge(sortie);
        sorties.setXYParameter(porteSortieX, porteSortieY, porteSortieWidth, porteSortieHeight);
        sorties.paint(graphics);

        //Porte Flottante
        ArrayList<GrapheSimple>  flottante = circuitFacade.getPorteFlottante();
        flottantes.charge(flottante);
        flottantes.setXYParameter(porteFlottanteX, porteFlottanteY, porteFlottanteWidth, porteFlottanteHeight);
        flottantes.paint(graphics);

        // Entree
        ArrayList<GrapheSimple>  entree = circuitFacade.entree();
        entrees.charge(entree);
        entrees.setXYParameter(porteEntreeX, porteEntreeY, porteEntreeWidth, porteEntreeHeight);
        entrees.paint(graphics);

//        // Circuit
//        ArrayList<ElementSimple>  circuit = circuitFacade.circuit();
//        circuits.charge(circuit);
//        circuits.setXYParameter(porteCircuitX, porteCircuitY, porteCircuitWidth, porteCircuitHeight);
//        circuits.paint(graphics);

        // SemiFlottante
        ArrayList<GrapheSimple>  semiFlottante = circuitFacade.getPorteSemiFlottante();
        semiFlottantes.charge(semiFlottante);
        semiFlottantes.setXYParameter(porteSemiFlottanteX, porteSemiFlottanteY, porteSemiFlottanteWidth, porteSemiFlottanteHeight);
        semiFlottantes.paint(graphics);

    }

    @Override
    public void update() {
        // Création de la facade avec circuit courant

        circuitFacade = new CircuitFacade(sujetObservable.getControleurCircuit());
        this.repaint();
    }
}