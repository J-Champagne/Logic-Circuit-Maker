package vue.dessin.composite;

import java.util.ArrayList;

public interface Connectable {


    public int nbSource();

    public int nbDestination();

    public ArrayList<GrapheSimple> source ();

    public ArrayList<GrapheSimple> destination ();

    public ConnecteEnum etatConnection();

    public void charge(ArrayList<GrapheSimple> listeElement);


}
