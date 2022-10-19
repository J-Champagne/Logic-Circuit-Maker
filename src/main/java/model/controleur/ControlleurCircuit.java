package model.controleur;

import model.circuit.Circuit;
import model.circuit.ConstructeurElements;
import model.circuit.EditeurCircuit;
import model.circuit.ValidateurCircuit;
import model.commande.InstructionSet;
import model.instruction.Instruction;
import model.instruction.InstructionEnum;
import model.instruction.InverseurInstruction;
import model.elements.Element;
import model.elements.TypeElement;
import model.exception.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ControlleurCircuit {
    private Circuit circuit;
    private EditeurCircuit editeur;
    private ConstructeurElements constructeur;
    private InverseurInstruction inverseur;
    private InstructionSet inverse;

    public ControlleurCircuit() {
        this.circuit = new Circuit();
        this.editeur = new EditeurCircuit();
        this.constructeur = new ConstructeurElements();
        this.inverseur = new InverseurInstruction();
    }

    public ControlleurCircuit(Circuit circuit){
        this.circuit = circuit;
        this.editeur = new EditeurCircuit();
        this.constructeur = new ConstructeurElements();
        this.inverseur = new InverseurInstruction();
    }

    public ControlleurCircuit(Circuit circuit, EditeurCircuit editeur, ConstructeurElements constructeur,
                             InverseurInstruction inverseur) {
        this.circuit = circuit;
        this.editeur = editeur;
        this.constructeur = constructeur;
        this.inverseur = inverseur;
    }

    /**
    * Recoit une liste d'instructions à suivre pour la construction d'un circuit.
    * Extrait ces instructions et délègue la création des Elements ainsi que leurs connections.
    *
    * @param instructions   La liste d'instructions
     */
    public InstructionSet charger(ArrayList<Instruction> instructions) throws   MaxElementsException,
                                                                                        ExistingNameException,
                                                                                        LastElementException,
                                                                                        InvalidNameException,
                                                                                        InvalidConnectionException {
        for (Instruction inst : instructions) {
            InstructionEnum comm = inst.getInstruction();

            if (comm == InstructionEnum.AJOUTER) {
                TypeElement type = inst.getTypeElement();
                //Validation de la quantité d'éléments dans le circuit
                if(type == TypeElement.AND || type == TypeElement.OR || type == TypeElement.NOT){
                    ValidateurCircuit.maximumPortesAtteint(getNbrElemTablePortes());
                } else if (type == TypeElement.ENTREE){
                    ValidateurCircuit.maximumEntreesSortiesAtteint(getNbrElemTableEntrees());
                } else if (type == TypeElement.SORTIE){
                    ValidateurCircuit.maximumEntreesSortiesAtteint(getNbrElemTableSorties());
                }
                Element e = constructeur.construireElem(inst.getIdentification(), type.toString());
                inverse = inverseur.inverserAjouter(e);
                editeur.ajouter(e, getTable(type));

            } else if (comm == InstructionEnum.SUPPRIMER) {
                TypeElement type = inst.getTypeElement();
                //Validation de la quantité minimale d'entrées ou sorties
                if(type == TypeElement.ENTREE) {
                    ValidateurCircuit.estDernierEntreeOuSortie(getNbrElemTableEntrees());
                } else if (type == TypeElement.SORTIE){
                    ValidateurCircuit.estDernierEntreeOuSortie(getNbrElemTableSorties());
                }
                Element e = findElem(inst.getIdentification(), type);
                inverse = inverseur.inverserSupprimer(e);
                editeur.supprimer(e, getTable(type));

            } else if (comm == InstructionEnum.RELIER) {
                Element e1 = findElem(inst.getIdentification(), inst.getTypeElement());
                Element e2 = findElem(inst.getIdentification2(), inst.getTypeElement2());
                //Valide que e2 peut recevoir une autre source
                ValidateurCircuit.estConnexionValide(e2);
                inverse = inverseur.inverserRelier(e1, e2);
                editeur.connecter(e1, e2);

            } else if (comm == InstructionEnum.DECONNECTER) {
                Element e1 = findElem(inst.getIdentification(), inst.getTypeElement());
                Element e2 = findElem(inst.getIdentification2(), inst.getTypeElement2());
                inverse = inverseur.inverserDeconnecter(e1, e2);
                editeur.deconnecter(e1, e2);

            } else if (comm == InstructionEnum.DEFINIR) {
                //Validation du nom
                ValidateurCircuit.estNomValide(inst.getNom());
                ValidateurCircuit.estNomExistant(inst.getTypeElement(), getTable(inst.getTypeElement()), inst.getNom());
                Element e = findElem(inst.getIdentification(), inst.getTypeElement());
                inverse = inverseur.inverserDefinir(e);
                editeur.modifierNomElem(e, inst.getTypeElement(), inst.getNom());
            }
        }

        return inverse;
    }

    /**
    * Trouve un Element à partir de son identificateur et de son type.
    *
    * @param id     L'identificateur de l'Element
    * @param type   Le type de l'Element
    * @return       L'Element recherché
     */
    private Element findElem(String id, TypeElement type) {
        HashMap<String, Element> table = getTable(type);
        return table.get(id);
    }

    /**
     * Retourne le HashMap du circuit correspondant au type.
     *
     * @param type   Le type d'un Element associé au HashMap
     */
    private HashMap<String, Element> getTable(TypeElement type) {
        HashMap<String, Element> table;

        if (type == TypeElement.ENTREE) {table = circuit.getEntrees();}
        else if (type == TypeElement.SORTIE) {table = circuit.getSorties();}
        else {table = circuit.getPortes();}

        return table;
    }


    public HashMap<String, Element> getEntrees() {
        //faire cpie
        return circuit.getEntrees();
    }
    public HashMap<String, Element> getSorties() {
        return circuit.getSorties();
    }
    public HashMap<String, Element> getPortes() {
        return circuit.getPortes();
    }

    /**
    * Demande au circuit de générer les signals d'entrées dans la table de vérité et de la retourner.
    *
    * @return   La table de vérité
    */
    public Boolean[][] getTableVeriteIncomplete() {
        circuit.genererSignalsEntrees();
        return circuit.getTableVerite();
    }

    /**
     * Demande au circuit de générer sa table de vérité avec les signals des sorties et de la retourner.
     *
     * @return   La table de vérité
     */
    public Boolean[][] getTableVeriteComplete() throws InvalidCircuitException {
        ValidateurCircuit.estCircuitValide(circuit);
        circuit.genererTableVerite();
        return circuit.getTableVerite();
    }

    /**
     * Retourne le nombre d'Element du HashMap des entrées du circuit.
     *
     * @return   Le nombre d'entrées
     */
    public int getNbrElemTableEntrees() {
        return circuit.getEntrees().size();
    }

    /**
     * Retourne le nombre d'Element du HashMap des sorties du circuit.
     *
     * @return   Le nombre de sorties
     */
    public int getNbrElemTableSorties() {
        return circuit.getSorties().size();
    }

    /**
     * Retourne le nombre d'Element du HashMap des portes du circuit.
     *
     * @return   Le nombre de portes
     */
    public int getNbrElemTablePortes() {
        return circuit.getPortes().size();
    }
}
