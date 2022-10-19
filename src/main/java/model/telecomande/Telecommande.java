package model.telecomande;

import model.commande.*;
import model.exception.*;
import model.instruction.Instruction;
import model.controleur.Board;
import model.controleur.ControlleurCircuit;
import model.controleur.controlleur_base.ControlleurChargement;
import model.controleur.controlleur_base.ControlleurNouveauFichier;
import model.controleur.controlleur_base.ControlleurSauvegarder;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeSupprimerElement;
import model.controleur.controlleur_telecommande.ControlleurUndo;
import model.controleur.controlleur_telecommande.ControlleurAfficherUndoRedo;
import model.controleur.controlleur_telecommande.ControlleurRedo;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeAjouterEntrer;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeAjouterPorte;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeAjouterSortie;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeAnnuler;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeDefinir;
import model.controleur.controlleur_telecommande.ControlleurTelecommandeRelierElement;
import model.elements.Element;
import model.elements.TypeElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Telecommande extends JLayeredPane implements ActionListener , Observable {

    private final ArrayList<Observateur> observateurs = new ArrayList<>();
    private TelecommandeEtatEnum state = TelecommandeEtatEnum.AUCUN;
    private InstructionSet sauvegarde;
    private boolean estModifier;
    private JButton nouveauFichierBouton;
    private JButton ouvrirFichierBouton;
    private JButton enregistrefichierBouton;
    private JButton entreeBouton;
    private JButton etBouton;
    private JButton ouBouton;
    private JButton nonBouton;
    private JButton sortieBouton;
    private JButton porteCustum1Bouton;
    private JButton porteCustum2Bouton;
    private JButton undoBouton;
    private JButton annulerBouton;
    private JButton redoBouton;
    private JButton supprimerBouton;
    private JButton relierBouton;
    private JButton definirBouton;
    private JButton afficherBouton;

    Body body;
    GestionID gid;

    private ArrayList<Instruction> listeInstruction;

    private ControlleurCircuit controlleurCircuit;
    private ConstructeurCommandes constructeurCommandes;

    public ControlleurCircuit getControleurCircuit() {
        return controlleurCircuit;
    }

    public Telecommande getSujetEditeur() {
        return this;
    }

    public ArrayList<Instruction> getInstructions() {
        return listeInstruction;
    }

    // Element
    private HashMap<String, Element> entreeCircuitMap;
    private HashMap<String, Element> sortieCircuitMap;
    private HashMap<String, Element> porteCircuitMap;

//    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void initialiser(){
        sauvegarde = new InstructionSet();
        controlleurCircuit = new ControlleurCircuit();
        constructeurCommandes = new ConstructeurCommandes();
        estModifier = false;
        gid = new GestionID();
        entreeCircuitMap = new HashMap<>();
        sortieCircuitMap = new HashMap<>();
        porteCircuitMap = new HashMap<>();
        state = TelecommandeEtatEnum.AUCUN;
    }

    public Telecommande() {
        initialiser();

        this.setBackground(Color.DARK_GRAY);
        this.setOpaque(true);
        Dimension boardSize = new Dimension(300, 600);
        this.setBounds(18,40,boardSize.width, boardSize.height);


        // création de l'entete de la télécommande
        Head head = new Head();
        head.setBounds(5,5,290, 40);

        // Création du body de la télécommande
        body = new Body(this);
        body.setBounds(5,50,290, 500);


        // Création du Bas de la Télécommande
        Foot foot = new Foot();
        foot.setBounds(5,555,290, 40);

        // Ajout des sections dans la télécommande
        this.add(head);
        this.add(body);
        this.add(foot);

        // Boutton action Listenner
        enregistrefichierBouton = body.getBoutonEnregistrerFichier();
        nouveauFichierBouton = body.getBoutonNouveauFichier();
        ouvrirFichierBouton = body.getBoutonOuvrirFichier();

        entreeBouton = body.getElementEntree();
        etBouton = body.getBoutonPorteEt();
        ouBouton = body.getBoutonPorteOu();
        nonBouton = body.getBoutonPorteNon();
        sortieBouton = body.getBoutonElementSortie();
        porteCustum1Bouton = body.getSpecial1Bouton();
        porteCustum2Bouton = body.getSpecial2Bouton();

        undoBouton = body.getBoutonUndo();
        annulerBouton = body.getBoutonCancel();
        redoBouton = body.getBoutonRedo();
        afficherBouton = body.getBoutonAfficher();

        supprimerBouton = body.getBoutonSupprimer();
        relierBouton = body.getBoutonRelier();
        definirBouton = body.getBoutonDefinir();



        // Initialisation des états des boutons
        initialiserBouton();
    }

    public boolean executeCommand(Commande commande) {
        try {
            if (commande.execute()) {
                // notification au observateur que le circuit est valide

                // transmet inforation des éléments a la console
                setEntrees(controlleurCircuit.getEntrees());
                setSorties(controlleurCircuit.getSorties());
                setPortes(controlleurCircuit.getPortes());
            }
            return true;
        }
        catch(MaxElementsException eMax) {
            body.afficherMessageErreur(eMax.getMessage());
        }
        catch(ExistingNameException eExistingName) {
            body.afficherMessageErreur(eExistingName.getMessage());
        }
        catch(LastElementException eLast) {
            body.afficherMessageErreur(eLast.getMessage());
        }
        catch(InvalidNameException eInvalidName) {
            body.afficherMessageErreur(eInvalidName.getMessage());
        }
        catch(InvalidConnectionException eConnection) {
            body.afficherMessageErreur(eConnection.getMessage());
        }
        //Si on vient ici c'est qu'une erreur à été levée
        return false;
    }

    private void intialiseCosoleConnection() {
        boolean e = body.getConnectionCourantEntree() != null;
        boolean s = body.getConnectionCourantSortie() != null;
        relierBouton.setEnabled(e && s);
    }

//    private void intialiseCosoleElement() {
//        supprimerBouton.setEnabled(body.getElementCourant() != null);
//    }


    private void intialiseHistorique() {
        boolean undoEmpty = GestionnaireHistorique.getInstance().getHistorique().undoEmpty();
        undoBouton.setEnabled(undoEmpty);
        boolean redoEmpty = GestionnaireHistorique.getInstance().getHistorique().redoEmpty();
        redoBouton.setEnabled(redoEmpty);

        // undo ou redo n'est pas vide
        annulerBouton.setEnabled( !undoEmpty || redoEmpty );

    }

    public void initialiseConsoleElement() {
        boolean estElementSelection = body.getElementCourant() != null;
        supprimerBouton.setEnabled(estElementSelection);
    }

    public void initialiseDefinir() {
        boolean estElementSelection = body.getElementCourant() != null;

        boolean elementIO = estElementSelection && (body.getElementCourant().charAt(0) == 'E' || body.getElementCourant().charAt(0) == 'S' ) ;

        body.getDefinirJTextArea().setEnabled(elementIO);
        definirBouton.setEnabled(elementIO);
    }


    /**
     * Initialise les boutons
     *
     */
    private void initialiserBouton() {

        initialiseConsoleElement();
        intialiseCosoleConnection();
        intialiseHistorique();
        initialiseDefinir();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== porteCustum1Bouton) {
            body.afficherMessageValidation(MessageTelecommandeEnum.MSG_CUSTUM.message);

        } else if (e.getSource()== porteCustum2Bouton) {
            body.afficherMessageValidation(MessageTelecommandeEnum.MSG_CUSTUM.message);

        }
    }

    @Override
    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void supprimerObservateur(Observateur observateur) {
    }

    @Override
    public void notifierObservateur() {
        for (Observateur observateur : observateurs) {
            observateur.update();
        }
    }

    @Override
    public TelecommandeEtatEnum getState() {
        return state;
    }

    @Override
    public void setState(TelecommandeEtatEnum state)  {
        this.state = state;
        notifierObservateur();
    }

    public void setEntrees(HashMap<String, Element> entrees) {
        this.entreeCircuitMap = entrees;
    }

    public void setSorties(HashMap<String, Element> sorties) {
        this.sortieCircuitMap = sorties;
    }

    public void setPortes(HashMap<String, Element> portes) {
        this.porteCircuitMap = portes;
    }

    public HashMap<String, Element> getSorties() {
        return this.sortieCircuitMap;
    }

    public ControlleurCircuit getCircuit() {
        return controlleurCircuit;
    }

    public Body getBody(){
        return this.body;
    }
    public HashMap<String, Element> getEntreeCircuitMap(){
        return entreeCircuitMap;
    }
    public HashMap<String, Element> getSortieCircuitMap(){
        return sortieCircuitMap;
    }
    public HashMap<String, Element> getPorteCircuitMap(){
        return porteCircuitMap;
    }

    public void init() {
        boolean elementSelection = body.getElementCourant() != null;
        boolean elementIO = elementSelection && (body.getElementCourant().charAt(0) == 'E' || body.getElementCourant().charAt(0) == 'S' ) ;

        // supprimer
        supprimerBouton.setEnabled(elementSelection);

        // Définir
        definirBouton.setEnabled(elementIO);
        body.getDefinirJTextArea().setEnabled(elementIO);

    }
    public HashMap<String, Element> getPortes() {
        return this.porteCircuitMap;
    }

    public HashMap<String, Element> getEntree() {
        return this.entreeCircuitMap;
    }

    public InstructionSet getSauvegarde(){
        return sauvegarde;
    }

    public void ajouterListener(Board board){
        enregistrefichierBouton.addActionListener(new ControlleurSauvegarder(board));
        nouveauFichierBouton.addActionListener(new ControlleurNouveauFichier(board));
        ouvrirFichierBouton.addActionListener(new ControlleurChargement(board));
        entreeBouton.addActionListener(new ControlleurTelecommandeAjouterEntrer(this));
        sortieBouton.addActionListener(new ControlleurTelecommandeAjouterSortie(this));
        etBouton.addActionListener(new ControlleurTelecommandeAjouterPorte(this, TypeElement.AND));
        ouBouton.addActionListener(new ControlleurTelecommandeAjouterPorte(this, TypeElement.OR));
        nonBouton.addActionListener(new ControlleurTelecommandeAjouterPorte(this, TypeElement.NOT));
        supprimerBouton.addActionListener(new ControlleurTelecommandeSupprimerElement(this));
        relierBouton.addActionListener(new ControlleurTelecommandeRelierElement(this));
        definirBouton.addActionListener(new ControlleurTelecommandeDefinir(this));
        annulerBouton.addActionListener(new ControlleurTelecommandeAnnuler(this));
        undoBouton.addActionListener(new ControlleurUndo(this));
        redoBouton.addActionListener(new ControlleurRedo(this));
        afficherBouton.addActionListener(new ControlleurAfficherUndoRedo(board));
    }

    public boolean getEstModifier(){
        return estModifier;
    }

    public void setEstModifier(boolean modifier){
        estModifier = modifier;
    }

    public GestionID gestionID(){
        return gid;
    }

    public ConstructeurCommandes getConstructeurCommandes(){
        return constructeurCommandes;
    }
}