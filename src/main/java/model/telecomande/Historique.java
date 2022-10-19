package model.telecomande;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Cette classe permet la gestion du undo et redo
 *
 * @param <T>
 */
public class Historique  <T> {

    private final Stack<T> pileUndo;
    private final Stack<T> pileRedo;

    public  Historique() {
        pileUndo = new Stack<>();
        pileRedo = new Stack<>();
    }

    /**
     * Empile une instruction dans l'historique
     *
     * @param e
     */
    public void ajouter(T e) {
        pileUndo.push(e);
        if (!pileRedo.isEmpty()) {
            // efface le redo
            pileRedo.clear();
        }
    }

    /**
     *  Depile une instruction de l'historique, le retourne et l'empile dans la pile du redo.
     *
     * @return
     */
    public T undo () {
        T instruction = null;

        // si la pile n'est pas vide
        if (!pileUndo.isEmpty()) {
            // depile l'historique
            instruction = pileUndo.pop();
        }
        return instruction;
    }

    /**
     *  Depile une instruction de l'historique et la retourne et l'empile dans la pile du redo.
     * @return
     */
    public T redo () {
        T instruction = null;

        // si la pile n'est pas vide
        if (!pileRedo.isEmpty()) {
            // depile l'historique
            instruction = pileRedo.pop();
        }
        return instruction;
    }

    public void pushRedo(T elem) {
        pileRedo.push(elem);
    }

    public void pushUndo(T elem) {
        pileUndo.push(elem);
    }

    public void clearRedo() {
        pileRedo.clear();
    }

    public void clearUndo() {
        pileUndo.clear();
    }

    /**
     * 
     */
    public void reset () {
        if (!pileUndo.isEmpty()) pileUndo.clear();
        if (!pileRedo.isEmpty()) pileRedo.clear();
    }

    public boolean undoEmpty() {
        return pileUndo.empty();
    }

    public boolean redoEmpty() {
        return pileRedo.empty();
    }

    public ArrayList<String> getEtatUndo () {
        ArrayList<String> list = new ArrayList<>();
        for (T t : pileUndo) {
            list.add(t.toString());
        }
        return list;
    }

    public ArrayList<String> getEtatRedo () {
        ArrayList<String> list = new ArrayList<>();
        for (T t : pileRedo) {
            list.add(t.toString());
        }
        return list;
    }


}
