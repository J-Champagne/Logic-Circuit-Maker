package model.elements;

import java.util.ArrayList;

/**
 * Classe qui gère l'opérateur 'And' d'un circuit
 */
public class And extends PorteLogique {

    //Constructeur
    public And(String id, ArrayList<Element> sources, ArrayList<Element> sorties){
        super(id, sources, sorties);
        this.type = TypeElement.AND;
        this.qteSourcesRequired = 2;
    }

    /**
     * Calcul le signal selon le signal de ses sources
     */
    @Override
    public void calculerSignal() {
        if(getSources().size() != 2){
            System.out.println("Erreur: Un opérateur AND doit avoir exactement 2 sources");
        } else {
            setSignal(getSources().get(0).getSignal() && getSources().get(1).getSignal());
        }
    }
}
