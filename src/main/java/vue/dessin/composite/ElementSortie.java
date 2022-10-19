package vue.dessin.composite;

import model.elements.Element;

import java.awt.*;
import java.util.ArrayList;

public class ElementSortie extends GrapheSimple {

    public ElementSortie(ArrayList<Element> listeSource, String identification, int nb, GrapheComposite elementComposite) {
        super(identification);
        this.setSource(listeSource);
//        this.identification = identification;
        this.nombreSource = nb;
        this.sourceEntree = elementComposite;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        tracerElement(graphics);
    }

    @Override
    public void tracerElement(Graphics graphics) {
        // texte
        Font font = graphics.getFont().deriveFont( fontSizeFlotant );
        graphics.setFont( font );
        graphics.drawString(identification, x  , y + 4);

        // Rond
        graphics.fillRoundRect(x - 4, y + 5, width, height, width, height );

        positionSource1X = x - 20;
        positionSource1Y = y +  (3 * height / 4);
        // Connection levante et baissante
        if (this.nombreSource == 0) {
            graphics.drawLine(x , y +  (3 * height / 4) , x - 10 , y  );
        } else {
            graphics.drawLine(x , y +  (3 * height / 4) , positionSource1X , positionSource1Y );
        }

    }

    @Override
    public ConnecteEnum etatConnection() {
        if (nombreSource == 0) {
            return ConnecteEnum.FLOTTANT;
        } else if (nombreSource == 1) {
            return ConnecteEnum.CONNECTE;
        } else {
            return ConnecteEnum.SEMI_CONNECTE;
        }
    }
}
