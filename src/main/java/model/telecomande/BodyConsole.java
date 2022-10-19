package model.telecomande;

import model.elements.Element;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BodyConsole extends JPanel {


    // Element courant des consoles
    // Console des éléments
    private int elementCircuitCourantIndex;
    private String elementCircuitCourant = null;

    // Console connection des entrées
    private String connectionCourantConsoleEntree = null;
    private int connectionCourantconsoleEntreeIndex;

    // Console connection des sorties
    private String connectionCourantConsoleSortie = null;
    private int connectionCourantConsoleSortieIndex;




    // Console pour la liste des éléments
    JList<String> listeElementCircuit = new JList<>();
    DefaultListModel<String> modelElementCircuit = new DefaultListModel<>();

    // Console pour la liste de connection entree
    JList<String> listeConnectionEntree = new JList<>();
    DefaultListModel<String> modelConnectionEntree = new DefaultListModel<>();

    // Console pour la liste de connection entree
    JList<String> listeConnectionSortie = new JList<>();
    DefaultListModel<String> modelConnectionSortie = new DefaultListModel<>();



    // bouton
    BoutonCommande boutonSupprimer;
    BoutonCommande boutonRelier;

    boolean estConnection;

    public BodyConsole (Telecommande context) {
        this.setLayout(new BorderLayout());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Panneau gauche et droit
        JPanel panelGauche = new JPanel();
        panelGauche.setBorder(new EmptyBorder(5,5, 5, 5));
        panelGauche.setBackground(Color.LIGHT_GRAY);
//        panelGauche.setBackground(new Color(225,254,224));



        JPanel panelDroit = new JPanel();
        panelDroit.setBorder(new EmptyBorder(5,5, 5, 5));
        panelDroit.setBackground(Color.LIGHT_GRAY);
//        panelDroit.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));


        this.add(panelGauche);
        panelGauche.setPreferredSize(new Dimension(140, 180));
        this.add(panelDroit);
        panelDroit.setPreferredSize(new Dimension(140, 180));

        // liste des éléments
        JScrollPane scrollPane = new JScrollPane(listeElementCircuit);

        listeElementCircuit.setBorder(BorderFactory.createLineBorder(Color.green));
        listeElementCircuit.setModel(modelElementCircuit);
//        listeElementCircuit.setBorder(new EmptyBorder(10,10, 10, 10));

        // Lorsque l'usager sélection un élément
        listeElementCircuit.getSelectionModel().addListSelectionListener(e -> {

            // élément courant
            elementCircuitCourant = listeElementCircuit.getSelectedValue();
            elementCircuitCourantIndex = listeElementCircuit.getSelectedIndex();
//            boutonSupprimer.setEnabled(elementCircuitCourant != null);
            context.initialiseConsoleElement();
            context.initialiseDefinir();

//            context.body.getElementCourant();

//            // Détermine la liste des connections pour l'élément courant
//            ArrayList<String> connectionsPossible = calculConnectionPossible();
//            modelConnectionPossible.removeAllElements();
//            for (String connection : connectionsPossible) {
//                // ajout des connections dans la console
//                modelConnectionPossible.addElement(connection);
//            }
//            estConnection = (modelConnectionPossible.size() != 0);
        });

        // détect le mouvement de souris
//        listeElementCircuit.addMouseListener(context);

        // Panneau de gauche
        panelGauche.setLayout(new BorderLayout());
        boutonSupprimer = new BoutonCommande(TypeBoutonCommandeEnum.SUPPRIMER_ELEMENT);
        boutonSupprimer.setEnabled(elementCircuitCourant != null);
        boutonSupprimer.setToolTipText("Supprimer un élément");
        boutonSupprimer.addActionListener(context);

        TitreLabel titreElelement = new TitreLabel(MessageTelecommandeEnum.TITRE_ELEMENT.message);
        panelGauche.add(titreElelement, BorderLayout.NORTH);
        panelGauche.add(scrollPane, BorderLayout.CENTER);
        panelGauche.add(boutonSupprimer, BorderLayout.SOUTH);



        // Panel Connection



        // Liste des connections entrees
        JScrollPane scrollPaneConnectionEntree = new JScrollPane(listeConnectionEntree);
        scrollPaneConnectionEntree.setPreferredSize(new Dimension(65, 180));
        listeConnectionEntree.setBorder(BorderFactory.createLineBorder(Color.blue));
        listeConnectionEntree.setModel(modelConnectionEntree);
//        listeConnectionEntree.setBorder(new EmptyBorder(10,10, 10, 10));
        // Parcours des connections possible
        listeConnectionEntree.getSelectionModel().addListSelectionListener(e -> {
            connectionCourantConsoleEntree = listeConnectionEntree.getSelectedValue();
            connectionCourantconsoleEntreeIndex = listeConnectionEntree.getSelectedIndex();
            boutonRelier.setEnabled( (connectionCourantConsoleEntree != null) &&
                    (connectionCourantConsoleSortie != null));

        });


        // Liste des connections sortie
        JScrollPane scrollPaneConnectionSortie = new JScrollPane(listeConnectionSortie);
        scrollPaneConnectionSortie.setPreferredSize(new Dimension(65, 180));
        listeConnectionSortie.setBorder(BorderFactory.createLineBorder(Color.blue));
        listeConnectionSortie.setModel(modelConnectionSortie);
//        listeConnectionEntree.setBorder(new EmptyBorder(10,10, 10, 10));
        // Parcours des connections possible
        listeConnectionSortie.getSelectionModel().addListSelectionListener(e -> {
            connectionCourantConsoleSortie = listeConnectionSortie.getSelectedValue();
            connectionCourantConsoleSortieIndex = listeConnectionSortie.getSelectedIndex();
            boutonRelier.setEnabled( (connectionCourantConsoleEntree != null) &&
                    (connectionCourantConsoleSortie != null));

        });


        JPanel connectionPanel = new JPanel();
        connectionPanel.setLayout(new BorderLayout());
//        connectionPanel.setBorder(new EmptyBorder(10,10, 10, 10));
        connectionPanel.add(scrollPaneConnectionEntree, BorderLayout.WEST);
        connectionPanel.add(scrollPaneConnectionSortie, BorderLayout.EAST);



        // panneau de droit
        panelDroit.setLayout(new BorderLayout());
        boutonRelier = new BoutonCommande(TypeBoutonCommandeEnum.RELIER);
        boutonRelier.setEnabled( (connectionCourantConsoleEntree != null) &&
                                (connectionCourantConsoleSortie != null));
        boutonRelier.setToolTipText("Relier deux éléments");
        boutonRelier.addActionListener(context);

        TitreLabel titreConnection = new TitreLabel(MessageTelecommandeEnum.TITRE_CONNECTION.message);
        panelDroit.add(titreConnection, BorderLayout.NORTH);


        panelDroit.add(connectionPanel, BorderLayout.CENTER);
        panelDroit.add(boutonRelier, BorderLayout.SOUTH);
    }

    public ArrayList<String> calculConnectionPossible() {

        String element = elementCircuitCourant;
        ArrayList<String> listeDesConnections = new ArrayList<>();


        for (int i = 0; i < modelElementCircuit.size(); i++ ) {
            String lienStr = "";
            String key = modelElementCircuit.get(i);
            if ((element != key) && !element.contains("-")) {
                if (!key.contains("-")) {
                    if (element.charAt(0) == 'E') {
                        if (key.charAt(0) == 'P') {
                            lienStr = element + " -> " + key;
                        }
                    } else if (element.charAt(0) == 'S') {
                        if (key.charAt(0) == 'P') {
                            lienStr = key + " -> " + element;
                        }
                    } else {
                        if (key.charAt(0) == 'E') {
                            lienStr = key + " -> " + element;
                        } else {
                            lienStr = element + " -> " + key;
                        }
                    }
                    if (lienStr.length() != 0) {
                        listeDesConnections.add(lienStr);
                    }
                }
            }
        }

        Collections.sort(listeDesConnections);
        return listeDesConnections;
    }

    public BoutonCommande getBoutonSupprimer() {
        return boutonSupprimer;
    }

    public BoutonCommande getBoutonRelier() {
        return boutonRelier;
    }

    public JList<String>  getlisteEntree() {
        return listeElementCircuit;
    }

    public String getElementCourantConsole () {
        return elementCircuitCourant;
    }

    public String getConnectionCourantConsoleEntree () {
        return connectionCourantConsoleEntree;
    }
    public String getConnectionCourantConsoleSortie () {
        return connectionCourantConsoleSortie;
    }

    public int getConnectionCourantConsoleEntreeeIndex() {
        return connectionCourantconsoleEntreeIndex;
    }
    public int getConnectionCourantConsoleSortieIndex() {
        return connectionCourantConsoleSortieIndex;
    }

    public boolean getEstConnection (){
        return estConnection;
    }

    public void setMode (HashMap<String, Element> entreeMap,
                         HashMap<String, Element> sortieMap,
                         HashMap<String, Element> porteMap) {
        HashMap<String, Element>  listeConsoleElement = new HashMap<>();
        HashMap<String, Element>  listeConsoleConnectionEntree = new HashMap<>();
        HashMap<String, Element>  listeConsoleConnectionSortie = new HashMap<>();


        // Ajoute les entrees dans la liste des éléments
        entreeMap.forEach ((key, value) -> {
                    listeConsoleElement.put(key,value);
                    listeConsoleConnectionEntree.put(key,value);
                }
        );

        //ajoute les sorties dans la liste des éléments
        sortieMap.forEach ((key, value) -> {
                    listeConsoleElement.put(key,value);
                    listeConsoleConnectionSortie.put(key,value);
                }
        );

                // ajoute les portes dans la liste des éléments
        porteMap.forEach ((key, value) -> {
                    listeConsoleElement.put(key,value);
                    listeConsoleConnectionEntree.put(key,value);
                    listeConsoleConnectionSortie.put(key,value);
                }
        );

        // Console : Element
        ArrayList<String> sort_ListElement = new ArrayList<>();
        listeConsoleElement.forEach ((key, value) -> sort_ListElement.add(value.getId()));
        Collections.sort(sort_ListElement);
        // remplissage de la console des éléments
        modelElementCircuit.removeAllElements();
        for (String s : sort_ListElement) {
            modelElementCircuit.addElement(s);
        }

        // Console : Connection entrée
        ArrayList<String> sort_ListConsoleEntree = new ArrayList<>();
        listeConsoleConnectionEntree.forEach ((key, value) -> sort_ListConsoleEntree.add(value.getId()));
        Collections.sort(sort_ListConsoleEntree);
        // remplissage de la console des éléments
        modelConnectionEntree.removeAllElements();
        for (String s : sort_ListConsoleEntree) {
            modelConnectionEntree.addElement(s);
        }

        // Console : Connection sortie
        ArrayList<String> sort_ListConsoleSortie = new ArrayList<>();
        listeConsoleConnectionSortie.forEach ((key, value) -> sort_ListConsoleSortie.add(value.getId()));
        Collections.sort(sort_ListConsoleSortie);
        // remplissage de la console des éléments
        modelConnectionSortie.removeAllElements();
        for (String s : sort_ListConsoleSortie) {
            modelConnectionSortie.addElement(s);
        }







//        listeElementCircuit.repaint();
    }

}
