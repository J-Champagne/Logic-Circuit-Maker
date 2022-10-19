package model.circuit;

import model.elements.*;
import java.util.ArrayList;

public class ConstructeurElements {
    private String entree = TypeElement.ENTREE.toString();
    private String sortie = TypeElement.SORTIE.toString();
    private String and = TypeElement.AND.toString();
    private String or = TypeElement.OR.toString();
    private String not = TypeElement.NOT.toString();

    public ConstructeurElements() {}

    /*
    * Construit un Element à partir d'un identificateur et de son type.
    *
    * @param id L'identificateur
    * @param type Le type de l'élément à créer
    */
    public Element construireElem(String id, String type){
        ArrayList<Element> sources = new ArrayList<>();
        ArrayList<Element> sorties = new ArrayList<>();
        Element e = null;

        if (type.equals(entree)) {
            e = new EntreeCircuit(id, sources, sorties);
        }
        else if (type.equals(sortie)) {
            e = new SortieCircuit(id, sources, sorties);
        }
        else if (type.equals(and)) {
            e = new And(id, sources, sorties);
        }
        else if (type.equals(or)) {
            e = new Or(id, sources, sorties);
        }
        else if (type.equals(not)) {
            e = new Not(id, sources, sorties);
        }

        return e;
    }
}
