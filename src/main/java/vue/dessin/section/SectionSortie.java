package vue.dessin.section;

import vue.dessin.composite.ConnecteEnum;
import vue.dessin.composite.GrapheComposite;
import vue.dessin.composite.GrapheSimple;

import java.awt.*;
import java.util.ArrayList;

public class SectionSortie extends GrapheSimple {

    ArrayList<GrapheSimple> listeSortie;

    public SectionSortie() {
    }

    @Override
    public void charge(ArrayList<GrapheSimple> listeElement) {
        listeSortie = listeElement;
    }

    @Override
    public void paint(Graphics graphics) {
        // dessine le cadrage de la sortie
        graphics.drawRect(x , y, width, height);

        // dessine toutes les sortie
        int nombreElement = 1;
        int intervale = height / (listeSortie.size() + 1);
        for (GrapheSimple element : listeSortie) {
            element.setXYParameter(x + 20,y + (intervale * nombreElement) ,getSizeElement(), getSizeElement());

            // Affiche la sortie
            element.paint(graphics);

            // s'il y a un élément de connecté
            if (element.etatConnection() == ConnecteEnum.CONNECTE) {

                // positionne l'élément
                element.sourceEntree.setXYParameter(element.x - 60, element.y + 5, element.width, element.height);
                element.sourceEntree.setLienSortie(element.getPositionSource1X(),element.getPositionSource1Y());

//                print(graphics, element.sourceEntree);
                //
                element.sourceEntree.paint(graphics);



                if (element.sourceEntree.source != null) {
                    if (element.sourceEntree.source().size() != 0){
                        for (GrapheSimple simple : element.sourceEntree.source()) {
                            System.out.println(simple.identification);
                        }
                    }
                }

//                System.out.println("Liste d'enfant : " + element.sourceEntree.listeEnfant.size());
                if(element.sourceEntree.listeEnfant.size() != 0) {
//                    System.out.println("Liste d'enfant : " + element.sourceEntree.listeEnfant.get(0).identification);
                }

//                dessinerEnfant(graphics, element.sourceEntree, niveau);

            }

            nombreElement ++;
        }
    }

    private void dessinerEnfant(Graphics graphics, GrapheComposite composite, int niveau) {


//        System.out.println(" [dessinEnfant :" + niveau);
//        System.out.println(" -> enfant :" + composite.listeEnfant);
        if (!composite.listeEnfant.isEmpty()) {
//            System.out.println(" --- > niveau" + niveau + ": " + composite.identification);
//            System.out.println("1");
            for (GrapheSimple element : composite.listeEnfant) {
//                System.out.println("(");

                // Position
                element.setXYParameter(composite.x - 60,
                        composite.y , composite.width, composite.height);

                element.setLienSortie(composite.getPositionSource1X(),
                        composite.getPositionSource1Y());

                // Affiche l'élément
                element.paint(graphics);

//                System.out.println(">>>> Dessiner");
//                dessinerEnfant(graphics,element.sourceEntree , ++niveau);
//                System.out.println("<<<< Dessiner");


//                System.out.println("4");
                element.sourceEntree.paint(graphics);

//                System.out.println(")");
            }
        }
//        System.out.println("5");
//        System.out.println("dessinEnfant :" + niveau + "]");
    }
}
