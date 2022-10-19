package model.instruction;

import model.commande.InstructionSet;
import model.elements.Element;
import model.elements.EntreeCircuit;
import model.elements.SortieCircuit;
import model.elements.TypeElement;

import java.util.ArrayList;

public class InverseurInstruction {
    public InverseurInstruction() {}

    /**
     * Inverse l'instruction Ajouter.
     *
     * @param e     L'element qui avait été ajouté
     * @return      L'instruction inverse Supprimmer
     */
    public InstructionSet inverserAjouter(Element e){
        InstructionSet inverse = new InstructionSet();
        inverse.add(new Instruction(InstructionEnum.SUPPRIMER, e.getId(), e.getType()));
        return inverse;
    }

    /**
     * Inverse l'instruction Supprimer.
     *
     * @param e L'element qui avait été supprimé
     * @return  Les instructions inverse (Ajouter, Definir, et Relier)
     */
    public InstructionSet inverserSupprimer(Element e){
        InstructionSet inverse = new InstructionSet();
        String id = e.getId();
        TypeElement type = e.getType();

        inverse.add(new Instruction(InstructionEnum.AJOUTER, e.getId(), type));

        if (type == TypeElement.ENTREE) {
            EntreeCircuit entree = (EntreeCircuit) e;
            inverse.add(new Instruction(InstructionEnum.DEFINIR, entree.getId(), entree.getNom(), type));

        } else if (type == TypeElement.SORTIE) {
            SortieCircuit sortie = (SortieCircuit) e;
            inverse.add(new Instruction(InstructionEnum.DEFINIR, sortie.getId(), sortie.getNom(), type));
        }

        for (Element source : e.getSources()) {
            inverse.add(new Instruction(InstructionEnum.RELIER, source.getId(), source.getType(),
                        id, type));
        }

        for (Element sortie : e.getSorties()) {
            inverse.add(new Instruction(InstructionEnum.RELIER, id, type,
                        sortie.getId(), sortie.getType()));
        }

        return inverse;
    }

    /**
     * Inverse l'instruction Relier.
     *
     * @param e1    L'element à gauche qui avait été relié
     * @param e2    L'element à droite qui avait été relié
     * @return      L'instruction inverse Deconnecter(e1, e2)
     */
    public InstructionSet inverserRelier(Element e1, Element e2){
        InstructionSet inverse = new InstructionSet();
        inverse.add(new Instruction(InstructionEnum.DECONNECTER, e1.getId(), e1.getType(), e2.getId(), e2.getType()));
        return inverse;
    }

    /**
     * Inverse l'instruction Deconnecter.
     *
     * @param e1 L'element à gauche qui avait été deconnecté
     * @param e2 L'element à droite qui avait été deconnecté
     * @return   L'instruction inverse Relier(e1, e2)
     */
    public InstructionSet inverserDeconnecter(Element e1, Element e2){
        InstructionSet inverse = new InstructionSet();
        inverse.add(new Instruction(InstructionEnum.RELIER, e1.getId(), e1.getType(), e2.getId(), e2.getType()));
        return inverse;
    }

    /**
     * Inverse l'instruction Definir.
     *
     * @param e L'element qui avait été renommé
     * @return  L'instruction inverse Definir avec l'ancien nom
     */
    public InstructionSet inverserDefinir(Element e){
        InstructionSet inverse = new InstructionSet();
        TypeElement type = e.getType();
        String nom = "";

        if (type == TypeElement.ENTREE) {
            EntreeCircuit entree = (EntreeCircuit) e;
            nom = entree.getNom();

        } else if (type == TypeElement.SORTIE) {
            SortieCircuit sortie = (SortieCircuit) e;
            nom = sortie.getNom();
        }

        inverse.add(new Instruction(InstructionEnum.DEFINIR, e.getId(), nom, type));
        return inverse;
    }
}
