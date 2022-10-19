package vue.dessin.section;

import vue.dessin.composite.GrapheSimple;

import java.awt.*;
import java.util.ArrayList;

public class SectionEntree extends GrapheSimple {

    ArrayList<GrapheSimple> listeEntree;

    public SectionEntree() {
    }

    @Override
    public void charge(ArrayList<GrapheSimple> listeElement) {
        listeEntree = listeElement;
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawRect(x, y, width, height);

        // dessine toutes les sortie
        int nombreElement = 1;
        int intervale = height / (listeEntree.size() + 1);
        for (GrapheSimple element : listeEntree) {
            element.setXYParameter(x + 20,y + (intervale * nombreElement) ,getSizeElement(), getSizeElement());
            element.paint(graphics);
            nombreElement ++;
        }
    }

}
