package vue.dessin.composite;

import model.elements.Element;
import java.awt.*;
import java.util.ArrayList;

public class GrapheSimple implements Tracable, Connectable {

    // identificationd de l'objet
    public String identification;

    public GrapheComposite sourceEntree;
//    protected GrapheComposite sourceEntree;
//    public GrapheComposite sourceEntree;


    // Constructeur

    public GrapheSimple() {
    }

    public GrapheSimple(String identification) {
        this.identification = identification;
    }


    // Connectable ------

    ArrayList<GrapheSimple> cSource;
    ArrayList<GrapheSimple> cDestination;

    public int nombreDestination;
    public int nombreSource;

    @Override
    public int nbSource() {
        return cSource.size();
    }

    @Override
    public int nbDestination() {
        return cDestination.size();
    }

    @Override
    public ArrayList<GrapheSimple> source() {
        return cSource;
    }

    @Override
    public ArrayList<GrapheSimple> destination() {
        return cDestination;
    }

    @Override
    public void charge(ArrayList<GrapheSimple> listeElement) {

    }



    // Tracable

    // dimension de l'objet
    public int x;
    public int y;
    public int width;
    public int height;

    public int positionSortieX;
    public int positionSortieY;

    public int positionSource1X;
    public int positionSource1Y;

    public int positionSource2X;
    public int positionSource2Y;

    public int distance = 20;
    public int sizeElement = 20;

    // font
    public float fontSizeFlotant = 9.0f;
    public float fontSizeTitre = 12.0f;

    // couleur de l'objet
//    public Color color;


    public int getPositionSource1X() {
        return positionSource1X;
    }

    public int getPositionSource1Y() {
        return positionSource1Y;
    }

    public int getPositionSource2X() {
        return positionSource2X;
    }

    public int getPositionSource2Y() {
        return positionSource2Y;
    }

    public void setXYParameter(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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

    @Override
    public void paint(Graphics graphics) {
    }

    @Override
    public void tracerElement(Graphics graphics) {

    }


    public void setLienSortie(int x, int y) {
        this.positionSortieX = x;
        this.positionSortieY = y;
    }

    // Liens avec autre objet
    protected boolean estFlottante = true;



    public GrapheSimple source = null;
    public GrapheSimple destination = null;

    private static int nbPalierEntree = 0;
    private static int nbPallierSortie = 0;


    private boolean selected = false;

    public static void initialiserPalier() {
        nbPallierSortie = 1;
    }

    public static void ajouterPalier() {
        nbPallierSortie++;
    }

    public static int getNbPalier() {
        return nbPalierEntree;
    }

    public int getSizeElement() {
        return sizeElement;
    };

    public void setPositionSource1(int x, int y) {
        this.positionSource1X = x;
        this.positionSource1Y = y;
    }

    public void paintFlotante(Graphics graphics) {
    }




//    @Override
//    public void move(int x, int y) {
//        this.x += x;
//        this.y += y;
//    }
//
//    @Override
//    public boolean isInsideBounds(int x, int y) {
//        return x > getX() && x < (getX() + getWidth()) &&
//                y > getY() && y < (getY() + getHeight());
//    }
//
//    @Override
//    public void select() {
//        selected = true;
//    }
//
//    @Override
//    public void unSelect() {
//        selected = false;
//    }
//
//    @Override
//    public boolean isSelected() {
//        return selected;
//    }
//
//    void enableSelectionStyle(Graphics graphics) {
//        graphics.setColor(Color.LIGHT_GRAY);
//
//        Graphics2D g2 = (Graphics2D) graphics;
//        float dash1[] = {2.0f};
//        g2.setStroke(new BasicStroke(1.0f,
//                BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_MITER,
//                2.0f, dash1, 0.0f));
//    }
//
//    void disableSelectionStyle(Graphics graphics) {
//        graphics.setColor(color);
//        Graphics2D g2 = (Graphics2D) graphics;
//        g2.setStroke(new BasicStroke());
//    }




    protected void setSource(ArrayList<Element> listeSource) {
    }
}
