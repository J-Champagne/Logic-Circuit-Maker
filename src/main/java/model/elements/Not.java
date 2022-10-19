package model.elements;

import java.util.ArrayList;

/**
 * Classe qui gère l'opérateur 'Not' d'un circuit
 */
public class Not extends PorteLogique {

    //Constructeur
    public Not(String id, ArrayList<Element> sources, ArrayList<Element> sorties){
        super(id, sources, sorties);
        this.type = TypeElement.NOT;
        this.qteSourcesRequired = 1;
    }

    /**
     * Calcul le signal selon le signal de sa source
     */
    @Override
    public void calculerSignal() {
        if(getSources().size() != 1){
            System.out.println("Erreur: Un operateur NOT doit avoir qu'une source");
        } else {
            setSignal(!getSources().get(0).getSignal());
        }
    }
}
