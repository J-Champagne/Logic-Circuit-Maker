package model.telecomande;

/**
 * Classe enum qui décrit les types de bouton
 */
public enum TypeBoutonCommandeEnum {

    ENTREE("entree"),
    SORTIE("sortie"),
    AND("et"),
    OR("ou"),
    NOT("not"),
    PORTE_SPECIALISE("porteS1"),
    DEFINIR("definir"),
    RELIER("relier"),
    SUPPRIMER_LIEN("sLien"),
    SUPPRIMER_ELEMENT("sElement"),
    UNDO("undo"),
    REDO("redo"),
    CANCEL("cancel"),
    NOUVEAU("nouveau"),
    OUVRIR("ouvrir"),
    XML("xml"),
    ENREGISTRER("enregistrer"),
    POWER_OFF("powerOff"),
    POWER_ON("powerOn"),
    TEXT("text"),
    PNG("png"),
    JPEG("jpeg"),
    AFFICHER("afficher");

    public String type;

    private TypeBoutonCommandeEnum(String type){
        this.type = type;
    }
}
