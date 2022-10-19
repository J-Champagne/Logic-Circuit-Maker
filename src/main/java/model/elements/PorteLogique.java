package model.elements;

import java.util.ArrayList;

/**
 * Classe parente des opérateurs d'un circuit
 */
public class PorteLogique extends Element {

    private Boolean signal;

    //Constructeur
    public PorteLogique(String id, ArrayList<Element> sources, ArrayList<Element> sorties){
        super(id, sources, sorties);
    }

    /**
     * Classe qui est 'ovverridée' par ses enfants
     */
    public void calculerSignal() throws NullPointerException{}

    //GETTERS ET SETTERS
    public void setSignal(Boolean signal){this.signal = signal;}

    public Boolean getSignal(){
        calculerSignal();
        return signal;
    }

}
