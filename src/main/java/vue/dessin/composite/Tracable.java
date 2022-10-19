package vue.dessin.composite;

import java.awt.*;

public interface Tracable {
    int getX();
    int getY();
    int getWidth();
    int getHeight();


//    void move(int x, int y);
//    boolean isInsideBounds(int x, int y);
//    void select();
//    void unSelect();
//    boolean isSelected();
    void paint(Graphics graphics);
    void tracerElement(Graphics graphics);


}

