package vue.dessin.composite;

import model.elements.Element;
import java.awt.*;
import java.util.ArrayList;

public class ElementEntree extends GrapheSimple {

//    private ElementSimple destination;

    public ElementEntree(ArrayList<Element> listeSource, String identification, int nb) {
        super(identification);
        this.setSource(listeSource);
        this.nombreDestination = nb;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        // texte
        Font font = graphics.getFont().deriveFont( fontSizeFlotant );
        graphics.setFont( font );
        graphics.drawString(identification, x - 5  , y + 3);

        // Rond
        graphics.drawRoundRect(x - 5  , y + 5, width, height, width, height );

        // Connection levante et baissante
        if (this.nombreDestination == 0) {
//            dessinerSortieFlotant(graphics);
            graphics.drawLine(x + width - 5 , y +  (3 * height / 4) , x + width + 5 , y  );

//            ImageIcon imageIcon =
//            new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/fault.png")));
//            graphics.drawImage(imageIcon.getImage(), x + width - 1 , y - 5 , 10, 10, null);

//            ImageIcon imageIcon2 =
//            new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/linkless.png")));
//            graphics.drawImage(imageIcon2.getImage(), x + width - 1 , y - 5 , 10, 10, null);


        } else {
//            dessinerSortie(graphics);
            positionSource1X = x + width + 10;
            positionSource1Y = y +  (3 * height / 4);

            graphics.drawLine(x + width - 5 , y +  (3 * height / 4) , positionSource1X , positionSource1Y );

//            ImageIcon imageIcon =
//            new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/valide.png")));
//            graphics.drawImage(imageIcon.getImage(), x + width + 5, y +  (3 * height / 4) - 4, 10, 10, null);

        }
    }
}
