package model.elements;

import java.util.ArrayList;

/**
 * Classe qui gère la sortie d'un circuit
 */
public class SortieCircuit extends Element{

    private Boolean signal;
    private String nom = "";

    //Constructeur avec nom
    public SortieCircuit(String id, ArrayList<Element> sources, ArrayList<Element> sorties, String nom){
        super(id, sources, sorties);
        this.type = TypeElement.SORTIE;
        this.nom = nom;
        this.qteSourcesRequired = 1;
    }

    //Constructeur sans nom
    public SortieCircuit(String id, ArrayList<Element> sources, ArrayList<Element> sorties){
        super(id, sources, sorties);
        this.type = TypeElement.SORTIE;
        this.qteSourcesRequired = 1;
    }

    //GETTERS ET SETTERS
    @Override
    public String getNom(){return this.nom;}

    public void setNom(String nom){this.nom = nom;}

    public void setSignal(Boolean signal){this.signal = signal;}

    public Boolean getSignal(){return this.signal;}

}
