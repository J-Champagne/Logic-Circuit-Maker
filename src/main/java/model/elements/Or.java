package model.elements;

import java.util.ArrayList;

/**
 * Classe qui gère l'opérateur 'Or' d'un circuit
 */
public class Or extends PorteLogique{

    //Constructeur
    public Or(String id, ArrayList<Element> sources, ArrayList<Element> sorties){
        super(id, sources, sorties);
        this.type = TypeElement.OR;
        this.qteSourcesRequired = 2;
    }

    /**
     * Calcul le signal selon le signal de ses sources
     */
    @Override
    public void calculerSignal() {
        if(getSources().size() != 2){
            System.out.println("Erreur: Un operateur OR doit avoir que 2 sources");
        } else {
            setSignal(getSources().get(0).getSignal() || getSources().get(1).getSignal());
        }
    }
}
