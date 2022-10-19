package model.circuit;

import model.elements.*;
import java.util.HashMap;
import java.util.ArrayList;

public class EditeurCircuit {
    public EditeurCircuit () {}

    /**
    * Ajoute un Element dans le HashMap correspondant du circuit.
    *
    * @param e  L'élément à ajouter
    */
    public void ajouter(Element e, HashMap<String, Element> table){
        table.put(e.getId(), e);
    }

    /**
    * Supprime un Element du circuit. Le fait en éliminant toutes les références à cet Element.
    * Les références supprimées sont les sources des sorties de 'e', les sorties des sources de 'e',
    * et l'entrée de 'e' dans le HashMap du circuit correspondant à son type.
    *
    * @param e  L'élément à supprimer
    */
    public void supprimer(Element e, HashMap<String, Element> table) {
        ArrayList<Element> sources = e.getSources();
        ArrayList<Element> sorties = e.getSorties();

        for (int i = 0; i < sources.size(); i++) {
            ArrayList<Element> sourceSorties = sources.get(i).getSorties();
            for (int j = 0; j < sourceSorties.size(); j++) {
                if (e == sourceSorties.get(j)) {
                    sourceSorties.remove(j);
                }
            }
        }

        for (int i = 0; i < sorties.size(); i++) {
            ArrayList<Element> sortieSources = sorties.get(i).getSources();
            for (int j = 0; j < sortieSources.size(); j++) {
                if (e == sortieSources.get(j)) {
                    sortieSources.remove(j);
                }
            }
        }

        table.remove(e.getId());
    }

    /**
    * Connecte 2 Element ensemble. 'e2' fera partie des sorties de 'e1' et 'e1' fera partie
    * des sources de 'e2' (e1 -> e2).
    *
    * @param e1 L'Element de gauche à connecter
    * @param e2 L'Element de droite à connecter
    */
    public void connecter(Element e1, Element e2){
        e1.getSorties().add(e2);
        e2.getSources().add(e1);
    }

    /**
     * Déconnecte un Element d'un autre. E1 doit être une source de e2 et e2 doit être une sortie de e1.
     * (e1 -> e2).
     *
     * @param e1    La source de e2
     * @param e2    La sortie de e1
     */
    public void deconnecter(Element e1, Element e2){
        ArrayList<Element> sorties = e1.getSorties();
        ArrayList<Element> sources = e2.getSources();

        for (int i = 0; i < sorties.size(); i++) {
            Element sortie = sorties.get(i);
            if (e2 == sortie) {
                sorties.remove(i);
            }
        }

        for (int i = 0; i < sources.size(); i++) {
            Element source = sources.get(i);
            if (e1 == source) {
                sources.remove(i);
            }
        }

    }

    /**
    * Modifie le nom d'une EntreeCircuit ou d'une SortieCircuit.
    *
    * @param e      L'Element à modifier
    * @param type   Le type de l'Element
    * @param nom    Le nouveau nom
    */
    public void modifierNomElem(Element e, TypeElement type, String nom){
        if (type == TypeElement.ENTREE) {
            EntreeCircuit entree = (EntreeCircuit) e;
            entree.setNom(nom);
        } else if (type == TypeElement.SORTIE) {
            SortieCircuit sortie = (SortieCircuit) e;
            sortie.setNom(nom);
        }
    }
}
