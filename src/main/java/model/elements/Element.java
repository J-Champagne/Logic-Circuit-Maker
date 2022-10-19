package model.elements;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Classe abstraite qui englobe tous les éléments d'un circuit
 */
@XmlRootElement
public abstract class Element {

    private String id;
    private ArrayList<Element> sources;
    private ArrayList<Element> sorties;
    protected TypeElement type;
    protected int qteSourcesRequired;

    //Constructeur
    public Element(){}

    //Constructeur
    public Element(String id, ArrayList<Element> sources, ArrayList<Element> sorties){
        this.id = id;
        this.sources = sources;
        this.sorties = sorties;
    }

    //GETTERS ET SETTERS
    @XmlElement
    public String getId() {return this.id;}

    @XmlElement
    public ArrayList<Element> getSources() {return this.sources;}

    @XmlElement
    public ArrayList<Element> getSorties() {return this.sorties;}

    public void setSources(ArrayList<Element> sources) {this.sources = sources;}

    public void setSorties(ArrayList<Element> sorties) {this.sorties = sorties;}

    @XmlElement
    public TypeElement getType(){return this.type;}

    public int getQteSourcesRequired(){return this.qteSourcesRequired;}

    @XmlElement
    public abstract Boolean getSignal() throws NullPointerException;
    
    public abstract void setSignal(Boolean signal);

    public void setId(String id) {this.id = id;}

    public String getNom(){
        return "";
    }

}
