package vue.dessin.composite;

import model.elements.Element;
import model.elements.TypeElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ElementNON extends GrapheComposite {

    private int position;

    private GrapheSimple source;
    private GrapheSimple sortie;


    public ElementNON(ArrayList<Element> listeSource, String identification, int nbSource, int nbDestination) {
        super(identification);
        this.setSource(listeSource);
//        this.identification = identification;
        this.nombreSource = nbSource;
        this.nombreDestination = nbDestination;
        this.estFlottante = (nbSource == 0) && (nbDestination == 0);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
//        System.out.println(identification);
//        graphics.fillRect(x - 1, y - 1, getWidth(), getHeight());

//        graphics.fillRect(1, 1, 100, 100);
        // texte

        if (this.etatConnection() == ConnecteEnum.FLOTTANT) {
            paintFlotante(graphics);
        } else {
            tracerElement(graphics);
        }
    }

    @Override
    public void tracerElement(Graphics graphics) {

        // bar vertical
        graphics.drawLine(  x , y, x , y + height );
        // bar diagonale haut a milieu
        graphics.drawLine(x , y, x + width  , y + (height / 2) );
        // bar diagonale bas a milieu
        graphics.drawLine(x , y + height, x + width  , y + (height / 2) );

        int dimensionCercle = (int) (width * 0.25);
        graphics.drawRoundRect(x + width  , y + (int)(width * 0.38) , dimensionCercle, dimensionCercle, dimensionCercle, dimensionCercle );

//        positionSourceY = y + (width / 2);
//        if (this.source == null) {
//            psourceX = x - 30;
//            pSourceY = y + (width / 2);
//        } else {
//            pSourceX = ElementSimple.positionEntreeX;
//            pSourceY = y + (width / 2);
//        }

//        int xDestination = x + width + dimensionCercle + 30;
        int yDestination = y + (width / 2);

        // connection entree
        graphics.drawLine(x + width + dimensionCercle  , y + (width / 2), positionSortieX , positionSortieY);


        positionSource1X = x - 20;
        positionSource1Y = y + (height / 2);

        // connection sortie
        graphics.drawLine(x  , y + (height / 2), positionSource1X , positionSource1Y);

        graphics.setColor(Color.darkGray);
        Font font = graphics.getFont().deriveFont( 8.0f );
        graphics.setFont( font );
        graphics.drawString(identification, x + (int)(width * 0.1) , y + (int )(width * 0.6) );
        graphics.setColor(Color.BLACK);

    }

    @Override
    public void paintFlotante(Graphics graphics) {
        Font font = graphics.getFont().deriveFont( fontSizeFlotant );
        graphics.setFont( font );
        graphics.drawString(identification, x , y);

        // image
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/notgatepink.png")));
        graphics.drawImage(imageIcon.getImage(), x - 8, y, 30, 30, null);

    }

    @Override
    public ConnecteEnum etatConnection() {
        if (nombreDestination == 0 && nombreSource == 0) {
            return ConnecteEnum.FLOTTANT;
        } else if (nombreDestination == 1 && nombreSource == 1) {
            return ConnecteEnum.CONNECTE;
        } else {
            return ConnecteEnum.SEMI_CONNECTE;
        }
    }

    @Override
    public void ajouter (ArrayList<Element> listeSource) {
        super.ajouter(listeSource);
        inserer(listeSource);

    }

    private void inserer (ArrayList<Element> listeSource) {

        // Liste enfants
        if (!listeSource.isEmpty()) {

            GrapheSimple grapheSimple = null;
            for (Element element : listeSource) {

                if (element.getType() == TypeElement.ENTREE) {
                    String nom = element.getNom();
                    String nomDefini =  (nom.length() != 0) ? nom : element.getId();
                    grapheSimple = new ElementEntree(
                            element.getSorties(), nomDefini,
                            element.getSorties().size());

                } else if (element.getType() == TypeElement.AND) {
                        grapheSimple = new ElementET(
                                element.getSorties(), element.getId(),
                                element.getSources().size(),
                                element.getSorties().size());

                } else if (element.getType() == TypeElement.OR) {
                        grapheSimple = new ElementOU(
                                element.getSorties(), element.getId(),
                                element.getSources().size(),
                                element.getSorties().size());

                } else if (element.getType() == TypeElement.NOT) {
                        grapheSimple = new ElementNON(
                                element.getSorties(), element.getId(),
                                element.getSources().size(),
                                element.getSorties().size());
                }
                    //elementComposite.ajouter(porte.getSources());
                //element.getSources();
//                inserer(element.getSources());

                listeEnfant.add(grapheSimple);
            }
        }
    }


}
