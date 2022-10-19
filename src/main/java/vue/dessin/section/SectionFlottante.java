package vue.dessin.section;

import vue.dessin.composite.GrapheSimple;

import java.awt.*;
import java.util.ArrayList;

public class SectionFlottante extends GrapheSimple {

    ArrayList<GrapheSimple> listeFlottante;


    public SectionFlottante() {
    }

    @Override
    public void charge(ArrayList<GrapheSimple> listeElement) {
        listeFlottante = listeElement;
    }

    @Override
    public void paint(Graphics graphics) {
        // dessine le cadrage de la sortie

        super.paint(graphics);
        graphics.drawRect(x , y, width, height);

//        // texte
        Font font = graphics.getFont().deriveFont( fontSizeTitre );
        graphics.setFont( font );

        graphics.drawString("Porte flottante", x + 15  , y + (height / 2) + 5);


        // dessine toutes les sortie
        int nombreElement = 1;
//        int intervale = height / (listeSortie.size() + 1);


        for (GrapheSimple element : listeFlottante) {
            element.setXYParameter(x + 90 + (nombreElement * 30),y + 15 ,getSizeElement(), getSizeElement());
            element.paintFlotante(graphics);
            nombreElement ++;
        }
    }



}
