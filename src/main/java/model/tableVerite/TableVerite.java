package model.tableVerite;

import model.exception.InvalidCircuitException;
import model.instruction.Instruction;
import model.controleur.Board;
import model.controleur.ControlleurCircuit;
import model.controleur.controlleur_base.ControlleurTableVerite;
import model.telecomande.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Cette classe affiche le résultat de la table de vérité d'un circuit
 *
 * - Met ajout les données de la table de vérité au moyen du update() de l'observer
 * - Affiche les donneés de la table de vérité
 *
 */
public class TableVerite extends JLayeredPane implements ActionListener, Observateur {

//    private boolean estDonneeTable = false;

    // Conteneur pour les informations du circuit
    private ArrayList<String> enteteTableVeriteInterface = new ArrayList<>();
    private ArrayList<ArrayList<String>> donneeTableVeriteInterface = new ArrayList <>();

    // elements
    private final JTable table;
    //    private final JLabel message ;
    private final JButton buttonCalcul;
    private JButton buttonExtraire;

    // Couleur des éléments du tableau
    private final Color rowSelected = Color.GREEN;
    private final Color rowEven = Color.gray;
    private final Color rowOdd = Color.LIGHT_GRAY;

    // Information du sujet Edition (observable)
    private final Telecommande sujetObservable;

    ControlleurCircuit circuit;

    /**
     * Constructeur
     *
     */
    public TableVerite(Telecommande e, Rectangle r, Color c) {
        buttonExtraire = new JButton();

        this.sujetObservable = e;

        // souscription
        this.sujetObservable.ajouterObservateur(this);

        this.setBackground(c);
        this.setBounds(r);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEtchedBorder(1));

        this.setLayout(new BorderLayout());

        // Conteneur pour la class Jtable
        String[] entete = {};
        String[][] donnes = {};
        DefaultTableModel model = new DefaultTableModel(donnes, entete);

        table = new JTable (model);
        Border thickBorder = new LineBorder(Color.black, 1);
        table.setPreferredScrollableViewportSize(new Dimension(450,200));
        table.setFillsViewportHeight(true);

        table.setBorder(thickBorder);

        // Table de verite
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

//        // Message
//        message = new JLabel("Aucune donnée disponible");
//        this.add(message, BorderLayout.PAGE_START);

        // affiche le message ou le tableaud de verite
//        afficheTableouMessage();

        // Bouton Calculer
        buttonCalcul = new JButton(MessageTelecommandeEnum.LABEL_BOUTON_CALCULER.message);
        this.add(buttonCalcul, BorderLayout.PAGE_END);
//        buttonCalcul.setEnabled(estDonneeTable);
        buttonCalcul.setToolTipText("Calculer la table de vérité");
        buttonCalcul.addActionListener(this);


        TitreLabel label = new TitreLabel(MessageTelecommandeEnum.TITRE_TABLE_DE_VERITE.message);

        BoutonCommande jpeg = new BoutonCommande(TypeBoutonCommandeEnum.TEXT);
        jpeg.setToolTipText("Extraire la table de vérité en fichier texte");
        jpeg.addActionListener(this);

        buttonExtraire = jpeg;

        jpeg.setPreferredSize(new Dimension(50,30));
        JPanel impresstion = new JPanel();

        impresstion.setLayout(new BorderLayout());
        impresstion.add(jpeg, BorderLayout.EAST);
        impresstion.add(label, BorderLayout.CENTER);

        this.add(impresstion,BorderLayout.NORTH);
    }

    private void rafraichirTableInterface() {
        // Utilisation du DefaultTableModel
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        // vide la table
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        // remplis les entetes
        int i;

        for (i = 0; i < enteteTableVeriteInterface.size(); i++) {
            dtm.addColumn(enteteTableVeriteInterface.get(i));
        }

        // Rempli la table
        for (ArrayList<String> circuitDonnee : donneeTableVeriteInterface) {
            Vector v = new Vector();
            for ( i=0; i < circuitDonnee.size(); i++) {
                v.add(circuitDonnee.get(i));
            }
            dtm.addRow(v);
        }

        table.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCalcul) {
            sujetObservable.setState(TelecommandeEtatEnum.TABLE_VERITE);
        }
    }

    private ArrayList<ArrayList<String>> convertir(Boolean[][] table) {

        ArrayList<ArrayList<String>> liste = new ArrayList<>();

        for(int i = 0; i < table.length; i++){
            ArrayList<String> ligne = new ArrayList<>();

            for(int j = 0; j < table[i].length; j++){
                String donnee;
                if(table[i][j] == null){
                    donnee = "N/D";
                } else {
                    donnee = (table[i][j]) ? "1" : "0";
                }
                ligne.add(donnee);
            }
            liste.add(ligne);
        }
        return liste;
    }

    @Override
    public void update() {

        if (sujetObservable.getState() == TelecommandeEtatEnum.TABLE_VERITE) {

            // vider la liste précédente d'entete
            enteteTableVeriteInterface.clear();

            // Remplisage des entetes : entrées
            sujetObservable.getEntree().forEach ((key, value) -> {
                enteteTableVeriteInterface.add(key);
                    }
            );

            // Remplisage des entetes : sorties
            sujetObservable.getSorties().forEach ((key, value) -> {
                        enteteTableVeriteInterface.add(key);
                    }
            );

            // Récupération du circuit
            circuit = sujetObservable.getCircuit();
            // Calcul de la table de vérité
            try {
                Boolean[][] donneeTableVerite = circuit.getTableVeriteComplete();
                // efface la table dans l'interface
                donneeTableVeriteInterface.clear();
                // convertie le donnée de la table de vérite pour l'interface
                donneeTableVeriteInterface = convertir(donneeTableVerite);

                rafraichirTableInterface();
            } catch (InvalidCircuitException e){
                sujetObservable.getBody().afficherMessageErreur(e.getMessage());
            }
        }

        if (this.sujetObservable.getState() == TelecommandeEtatEnum.CIRCUIT_COURANT) {

            // vider la liste précédente d'entete
            enteteTableVeriteInterface.clear();

            // Remplisage des entetes : entrées
            sujetObservable.getEntree().forEach ((key, value) -> {
                        enteteTableVeriteInterface.add(key);
                    }
            );

            // Remplisage des entetes : sorties
            sujetObservable.getSorties().forEach ((key, value) -> {
                        enteteTableVeriteInterface.add(key);
                    }
            );

            circuit = sujetObservable.getCircuit();
            if(circuit.getEntrees().size() > 0) {
                Boolean[][] donneeTableVerite = circuit.getTableVeriteIncomplete();

                // efface la table dans l'interface
                donneeTableVeriteInterface.clear();
                // convertie le donnée de la table de vérite pour l'interface
                donneeTableVeriteInterface = convertir(donneeTableVerite);

                rafraichirTableInterface();
            }
        }

    }

    // Ajoute un listener sur le bouton extraire pour exporter la able de vérité
    public void ajouterListener(Board board){
        buttonExtraire.addActionListener(new ControlleurTableVerite(board));
    }

    public ArrayList<ArrayList<String>> getDonneeTableVeriteInterface(){
        return donneeTableVeriteInterface;
    }

    public ArrayList<String> getEnteteTableVeriteInterface(){
        return enteteTableVeriteInterface;
    }
}
