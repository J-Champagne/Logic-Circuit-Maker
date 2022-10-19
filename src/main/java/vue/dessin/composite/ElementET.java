package vue.dessin.composite;

import model.elements.Element;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ElementET extends GrapheComposite {

    public ElementET(ArrayList<Element> listeSource, String identification, int nbSource, int nbDestination) {
        super(identification);
        this.setSource(listeSource);
        this.nombreSource = nbSource;
        this.nombreDestination = nbDestination;
    }

    @Override
    public void paint(Graphics graphics) {
//        super.paint(graphics);

//        if (this.etatConnection() == ConnecteEnum.FLOTTANT) {
//            paintFlotante(graphics);
//        } else {
            tracerElement(graphics);
//        }

    }

    @Override
    public void tracerElement(Graphics graphics) {
        graphics.drawArc (x, y, width,height,-90,180);
        graphics.drawLine(x, y, x + (width / 2) , y  );
        graphics.drawLine(x, y + height, x + (width / 2) , y + height  );
        graphics.drawLine(x , y, x , y + height );

//        // connection entree
        graphics.drawLine(x + width  , y + (height / 2), positionSortieX , positionSortieY);
//
        positionSource1X = x - 20;
        positionSource1Y = y + (height / 3);

        positionSource2X = x - 20;
        positionSource2Y = y + (height * 2 / 3);

//        // connection sortie
        graphics.drawLine(x  , y + (height / 3), positionSource1X , positionSource1Y);
        graphics.drawLine(x  , y + (height * 2 / 3), positionSource2X , positionSource2Y);

        graphics.setColor(Color.darkGray);
        Font font = graphics.getFont().deriveFont( 8.0f );
        graphics.setFont( font );
        graphics.drawString(identification, x + (int)(width * 0.2) , y + (int )(width * 0.6) );
        graphics.setColor(Color.BLACK);


    }

    @Override
    public void paintFlotante(Graphics graphics) {
        // texte
        Font font = graphics.getFont().deriveFont( fontSizeFlotant );
        graphics.setFont( font );
        graphics.drawString(identification, x , y);

        // image
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/andgateorange.png")));
        graphics.drawImage(imageIcon.getImage(), x - 8, y, 30, 30, null);

    }

    @Override
    public ConnecteEnum etatConnection() {
        if (nombreDestination == 0 && nombreSource == 0) {
            return ConnecteEnum.FLOTTANT;
        } else if (nombreDestination == 1 && nombreSource == 2) {
            return ConnecteEnum.CONNECTE;
        } else {
            return ConnecteEnum.SEMI_CONNECTE;
        }
    }
}
