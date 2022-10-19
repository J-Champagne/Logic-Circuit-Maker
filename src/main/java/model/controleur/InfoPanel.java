package model.controleur;

import java.awt.*;

public class InfoPanel {

    public InfoPanel (Rectangle r, Color c ) {
        bounds = r;
        color = c;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Color getColor () {
        return color;
    }

    private final Rectangle bounds;
    private final Color color;
}