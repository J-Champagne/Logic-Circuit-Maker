package vue.dessin.composite;

public enum ConnecteEnum {
    FLOTTANT("Flottant"),
    CONNECTE("Connecte"),
    SEMI_CONNECTE("Porte");

    public String type;

    private ConnecteEnum(String type){
        this.type = type;
    }
}
