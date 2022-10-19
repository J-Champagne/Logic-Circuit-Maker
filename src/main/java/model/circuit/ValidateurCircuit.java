package model.circuit;

import model.elements.Element;
import model.elements.EntreeCircuit;
import model.elements.SortieCircuit;
import model.elements.TypeElement;
import model.exception.*;

import java.util.HashMap;
import java.util.Map;

public class ValidateurCircuit {

    private static int maxPortes = 50;
    private static int maxEntreesSorties = 5;

    /**
     * Valide la quantité de portes dans le circuit
     * @param qtePortes     Nombre de portes
     * @throws MaxElementsException
     */
    public static void maximumPortesAtteint(int qtePortes) throws MaxElementsException{
        if(qtePortes >= maxPortes) throw new MaxElementsException();
    }

    /**
     * Valide la quantité d'entrées ou de sorties dans le circuit
     * @param qteElements     Nombre de sorties ou entrées
     * @throws MaxElementsException
     */
    public static void maximumEntreesSortiesAtteint(int qteElements) throws MaxElementsException{
        if(qteElements >= maxEntreesSorties) throw new MaxElementsException();
    }

    /**
     * Valide qu'il reste au moins un entrée ou sortie dans le circuit
     * @param qteElements   Nombre de sorties ou entrées
     * @throws LastElementException
     */
    public static void estDernierEntreeOuSortie(int qteElements) throws LastElementException {
        if(qteElements <= 1) throw new LastElementException();
    }

    /**
     * Valide qu'un élément peut recevoir une autre source
     * @param elem
     * @throws InvalidConnectionException
     */
    public static void estConnexionValide(Element elem) throws InvalidConnectionException {
        if(elem.getQteSourcesRequired() <= elem.getSources().size()) throw new InvalidConnectionException();
    }

    /**
     * Valide la taille du nom
     * @param nom   Le nom
     * @throws InvalidNameException
     */
    public static void estNomValide(String nom) throws InvalidNameException{
        if(nom.length() > 5) throw new InvalidNameException();
    }

    /**
     * Valide si une sortie ou une entrée porte déjà le nom choisi
     *
     * @param type  Le type de l'Élément
     * @param nom   Le nouveau nom
     * @throws ExistingNameException
     */
    public static void estNomExistant(TypeElement type, HashMap<String, Element> list, String nom) throws ExistingNameException {
        for (Map.Entry<String, Element> elem : list.entrySet()) {
            if (type == TypeElement.ENTREE) {
                EntreeCircuit entree = (EntreeCircuit) elem.getValue();
                if(entree.getNom().equals(nom) && !entree.getNom().equals("")){
                    throw new ExistingNameException();
                }
            } else if (type == TypeElement.SORTIE) {
                SortieCircuit sortie = (SortieCircuit) elem.getValue();
                if(sortie.getNom().equals(nom) && !sortie.getNom().equals("")){
                    throw new ExistingNameException();
                }
            }
        }
    }

    /**
     * Valide le circuit pour des elements flottants ou manquants
     *
     * @param circuit   Le circuit
     * @throws InvalidCircuitException
     */
    public static void estCircuitValide(Circuit circuit) throws InvalidCircuitException{
        if(circuit.getEntrees().size() < 1){
            throw new InvalidCircuitException("Entree manquante");
        } else if (circuit.getSorties().size() < 1){
            throw new InvalidCircuitException("Sortie manquante");
        } else if (circuit.getPortes().size() < 1){
            throw new InvalidCircuitException("Porte manquante");
        }

        //Test les entrees
        for (Map.Entry<String, Element> entry : circuit.getEntrees().entrySet()) {
            Element entree = entry.getValue();
            if(entree.getSources().size() > 0){
                throw new InvalidCircuitException("Source invalide pour Entree " + entree.getId());
            }
            if(entree.getSorties().size() < 1){
                throw new InvalidCircuitException("Entree " + entree.getId() + " flottante");
            }
        }
        //Test les portes
        for (Map.Entry<String, Element> entry : circuit.getPortes().entrySet()) {
            Element porte = entry.getValue();
            if(porte.getSources().size() == 0){
                throw new InvalidCircuitException("Manque sources pour Porte " + porte.getId());
            } else if(porte.getSorties().size() == 0){
                throw new InvalidCircuitException("Manque sorties pour Porte " + porte.getId());
            }

            if(porte.getSources().size() < porte.getQteSourcesRequired()){
                throw new InvalidCircuitException("Trop de sources pour Porte " + porte.getId());
            } else if (porte.getSources().size() > porte.getQteSourcesRequired()){
                throw new InvalidCircuitException("Manque sources pour Porte " + porte.getId());
            }
        }
        //Test les sorties
        for (Map.Entry<String, Element> entry : circuit.getSorties().entrySet()) {
            Element sortie = entry.getValue();
            if(sortie.getSorties().size() > 0){
                throw new InvalidCircuitException("Sortie invalide pour Sortie " + sortie.getId());
            }
            if(sortie.getSources().size() < 1){
                throw new InvalidCircuitException("Sortie " + sortie.getId() + " flottante");
            } else if (sortie.getSources().size() > 1){
                throw new InvalidCircuitException("Trop de sources pour Sortie " + sortie.getId());
            }
        }
    }
}
