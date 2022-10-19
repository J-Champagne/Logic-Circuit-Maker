package model.telecomande;

public enum MessageTelecommandeEnum {
    MSG_OUVRIR_FICHIER("Le fichier a été ouvert"),
    MSG_NOUVEAU_FICHIER("Créer un nouveau fichier"),
    MSG_ENREGISTRER_FICHIER("Le circuit a été enregister"),
    MSG_AJOUTER("Ajout de"),
    MSG_SUPPRIMER("Supression de"),
    MSG_DEFINIR("Définition de"),
    MSG_RELIER("Connection de"),
    MSG_CUSTUM("Porte spécialiée"),
    MSG_ALLUMER_TELECOMMANDE("Bienvenue sur UGU"),
    MSG_FERMER_TELECOMMANDE("Aurevoir !"),
    MSG_ANNULER_HITORIQUE("Annuler Historique"),
    MSG_EXTRACTION_CIRCUIT("Le fichier a été extrait"),
    MSG_EXTRACTION_TABLE_VERITE("La table de vérité a été extraite"),
    TITRE_AJOUTER("Ajouter un élément"),
    TITRE_DEFINIR("Définir un nom"),
    TITRE_ELEMENT("Choisir un élément"),
    TITRE_CONNECTION("Relier les éléments"),
    TITRE_TABLE_DE_VERITE("Table de vérité"),
    TITRE_CIRCUIT("Circuit Logique"),
    LABEL_BOUTON_CALCULER("Calculer"),
    LABEL_PROJET("Circuit UGU"),
    LABEL_CIE(" UQAM");

    public String message;
    private MessageTelecommandeEnum(String message){
        this.message = message;
    }
}
