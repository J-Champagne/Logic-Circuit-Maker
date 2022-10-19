package vue.dessin.section;

import vue.dessin.composite.GrapheSimple;

import java.awt.*;
import java.util.ArrayList;

public class SectionCircuit extends GrapheSimple {

    private ArrayList<GrapheSimple> listeCircuit;

    public SectionCircuit() {
    }

    @Override
    public void charge(ArrayList<GrapheSimple> listeElement) {
        listeCircuit = listeElement;
    }

    @Override
    public void paint(Graphics graphics) {
        // dessine le cadrage de la sortie
        graphics.drawRect(x, y, width, height);

        // dessine toutes les sortie
//        listeSortie.dessiner(graphics);
    }
}
