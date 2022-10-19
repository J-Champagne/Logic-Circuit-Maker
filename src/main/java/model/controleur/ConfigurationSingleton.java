package model.controleur;

import java.awt.*;

/*
    Cette classe gère les éléments de configuration du board
 */
public class ConfigurationSingleton {

    // titre
    private final static String titre = "Système de modélisation et de calcul pour les circuits logiques";

    // position de départ de l'application
    private final static int x = 100;
    private final static int y = 100;


    private final static int tableVeriteX = 10;
    private final static int tableVeriteY = 10;

    // size de l'application
    private final static int width = 1260;
    private final static int heigth = 735;

    // espace entre les panels
    private final static int espace = 10;

    // ratio par rapport a l'application
    private final static double ratioColonneGaucheX = 0.25;
    private final static double ratioColonneGaucheY = 0.45;

    private final static double ratioColonneDroiteX = 0.72;
    private final static double ratioColonneDroiteY = 0.45;

    private final static double ratioInnerTableVeriteX = 0.98;
    private final static double ratioInnerTableVeriteY = 0.80;


    // Paneau
    private static InfoPanel board ;
    private static InfoPanel edition;
    private static InfoPanel telecommande;
    private static InfoPanel circuit;
    private static InfoPanel tableVerite;

    // Reference a l'instance de la classe
    private static ConfigurationSingleton instance;


    // Colors
    private static Color applicationBackgroundColor;

    // Images
    private static String frameBackgroundImage = "/images/background.jpg";



    /*
    Constructeur privé : Conception Singleton
     */
    private ConfigurationSingleton() {
    }


    public static String getTitre () {
        return titre;
    }
    /*

     */
    public Rectangle getBoardBounds() {
        return board.getBounds();
    }

    /*

     */
    public Rectangle getEditionBounds() {
        return edition.getBounds();
    }

    public Rectangle getTelecommandeBounds() {
        return telecommande.getBounds();
    }

    /*

     */
    public Rectangle getAffichageBounds() {
        return circuit.getBounds();
    }

    /*

     */
    public Rectangle getTableVeriteBounds() {
        return tableVerite.getBounds();
    }


    public Rectangle getInnerTableVeriteBounds() {
        return  new Rectangle( 0,0,
                (int) (tableVerite.getBounds().width * ratioInnerTableVeriteX),
                (int) (tableVerite.getBounds().height * ratioInnerTableVeriteY) ) ;
    }

    // Colors
    public Color getApplicationBackgroundColor () {
        return applicationBackgroundColor;
    }

    // Image
    public String getFrameBackgroundImage() {
        return frameBackgroundImage;
    }

    public String texteDepart () {
        char lf = 10;
        String ligneDepart = "// Bienvenue a l'éditeur UGU" + lf + lf;
//        ligneDepart += "// Déclaration des éléments" + lf ;
//        ligneDepart += "// [ <element> <id>] opt [: <nom>];" + lf + lf;
        ligneDepart += "@startugu" + lf + lf;
        ligneDepart += "// porte d'entree 1;" + lf;
        ligneDepart += "entree E1 :\"entree 1\";" + lf + lf;
        ligneDepart += "// porte d'entree 2;" + lf;
        ligneDepart += "entree E2 :\"entree 2\";" + lf + lf;

        ligneDepart += "// porte 1;" + lf;
        ligneDepart += "and P1 :\"porte1\";" + lf + lf;

        ligneDepart += "// porte de sortie 1;" + lf;
        ligneDepart += "sortie S1 :\"sortie 1\";" + lf + lf;

        ligneDepart += "// connection entre E1 et P1;" + lf;
        ligneDepart += "E1 >> P1;" + lf + lf ;

        ligneDepart += "// connection entre E2 et P1;" + lf;
        ligneDepart += "E2 >> P1;" + lf + lf ;

        ligneDepart += "// connection entre P1 et S1;" + lf;
        ligneDepart += "P1 >> S1;" + lf + lf ;

        ligneDepart += "@endugu";
        return ligneDepart;
    }

    public static ConfigurationSingleton getInstance() {
        if (instance == null) {
            instance = new ConfigurationSingleton();
            int posx = espace;
            int posy = espace;

            ConfigurationSingleton.applicationBackgroundColor = new Color(245, 245, 245);
            ConfigurationSingleton.board = new InfoPanel(new Rectangle(x, y, width, heigth), Color.LIGHT_GRAY);
            ConfigurationSingleton.edition = new InfoPanel(new Rectangle(posx, posy, (int) (width* ratioColonneGaucheX),espace + (int) (heigth * (ratioColonneGaucheY + ratioColonneDroiteY)) ), Color.LIGHT_GRAY);
            ConfigurationSingleton.telecommande = new InfoPanel(new Rectangle(posx + 40, posy, (int) (width* ratioColonneGaucheX),espace + (int) (heigth * (ratioColonneGaucheY + ratioColonneDroiteY)) ), Color.LIGHT_GRAY);
            ConfigurationSingleton.circuit  = new InfoPanel(new Rectangle(posx + espace + (int) (width* ratioColonneGaucheX),
                    posy,
                    (int) (width * ratioColonneDroiteX),
                    (int) (heigth * ratioColonneDroiteY)), Color.LIGHT_GRAY);

            ConfigurationSingleton.tableVerite  = new InfoPanel(new Rectangle(posx + espace + (int) (width * ratioColonneGaucheX) ,
                    posy + espace + (int) (heigth * ratioColonneGaucheY) ,
                    (int) (width * ratioColonneDroiteX) ,
                    (int) (heigth * ratioColonneDroiteY)), Color.LIGHT_GRAY);
        }
        return instance;
    }
}
