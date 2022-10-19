package model.elements;

/**
 * Classe enum qui décrit les types d'éléments
 */
public enum TypeElement {

    ENTREE("Entree"),
    SORTIE("Sortie"),
    PORTE("Porte"),
    AND("And"),
    OR("Or"),
    NOT("Not");

    public String type;

    private TypeElement(String type){
        this.type = type;
    }
}
