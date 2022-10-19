package vue.dessin.composite;

import model.elements.Element;

import java.awt.*;
import java.util.ArrayList;

public class GrapheComposite extends GrapheSimple {

    public ArrayList<GrapheSimple> listeEnfant;


    public GrapheComposite(String identification) {
        super(identification);
        listeEnfant = new ArrayList<>();

    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    public void ajouter (ArrayList<Element> element) {

        if (element == null ) {
            // ne rien faire
        } else if (element.isEmpty())  {
            // ne rien faire
        } else {
        }
    }
}
