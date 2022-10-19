package model.elements;

import java.util.ArrayList;

/**
 * Classe qui gère l'entrée d'un circuit
 */
public class EntreeCircuit extends Element{

    private Boolean signal;
    private String nom = "";

    //Constructeur avec nom
    public EntreeCircuit(String id, ArrayList<Element> sources, ArrayList<Element> sorties, String nom){
        super(id, sources, sorties);
        this.type = TypeElement.ENTREE;
        this.nom = nom;
        this.qteSourcesRequired = 0;
    }

    //Constructeur sans nom
    public EntreeCircuit(String id, ArrayList<Element> sources, ArrayList<Element> sorties){
        super(id, sources, sorties);
        this.type = TypeElement.ENTREE;
        this.qteSourcesRequired = 0;
    }

    //GETTERS ET SETTERS
    @Override
    public String getNom(){return this.nom;}

    public void setNom(String nom){this.nom = nom;}

    public Boolean getSignal(){return this.signal;}

    public void setSignal(Boolean signal){this.signal = signal;}
}
