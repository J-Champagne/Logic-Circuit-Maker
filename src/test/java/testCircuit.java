import model.controleur.ControlleurCircuit;
import model.instruction.Instruction;
import model.instruction.InstructionEnum;
import model.instruction.InverseurInstruction;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import java.util.HashMap;
import java.util.ArrayList;

import model.circuit.*;
import model.elements.*;

public class testCircuit {

    //Tests ConstruireElements
    @Test
    public void testConstruireEntree(){
        String id = "Bla";
        ConstructeurElements constructeur = new ConstructeurElements();
        Element e = constructeur.construireElem(id, TypeElement.ENTREE.toString());

        Boolean typeCheck = (e.getType() == TypeElement.ENTREE);
        Boolean idCheck = (e.getId().equals(id));
        assertTrue(typeCheck && idCheck);
    }

    @Test
    public void testConstruireSortie() {
        String id = "Bla";
        ConstructeurElements constructeur = new ConstructeurElements();
        Element e = constructeur.construireElem(id, TypeElement.SORTIE.toString());

        Boolean typeCheck = (e.getType() == TypeElement.SORTIE);
        Boolean idCheck = (e.getId().equals(id));
        assertTrue(typeCheck && idCheck);
    }

    @Test
    public void testConstruireAnd() {
        String id = "Bla";
        ConstructeurElements constructeur = new ConstructeurElements();
        Element e = constructeur.construireElem(id, TypeElement.AND.toString());

        Boolean typeCheck = (e.getType() == TypeElement.AND);
        Boolean idCheck = (e.getId().equals(id));
        assertTrue(typeCheck && idCheck);
    }

    @Test
    public void testConstruireOr() {
        String id = "Bla";
        ConstructeurElements constructeur = new ConstructeurElements();
        Element e = constructeur.construireElem(id, TypeElement.OR.toString());

        Boolean typeCheck = (e.getType() == TypeElement.OR);
        Boolean idCheck = (e.getId().equals(id));
        assertTrue(typeCheck && idCheck);
    }

    @Test
    public void testConstruireNot() {
        String id = "Bla";
        ConstructeurElements constructeur = new ConstructeurElements();
        Element e = constructeur.construireElem(id, TypeElement.NOT.toString());

        Boolean typeCheck = (e.getType() == TypeElement.NOT);
        Boolean idCheck = (e.getId().equals(id));
        assertTrue(typeCheck && idCheck);
    }

    // Tests EditeurCircuit
    @Test
    public void testAjouterElem(){
        boolean notNull;
        boolean checkId = false;

        String id = "test";
        ArrayList<Element> sources = new ArrayList<>();
        ArrayList<Element> sorties = new ArrayList<>();
        Element e = new EntreeCircuit(id, sources, sorties);
        EditeurCircuit editeur = new EditeurCircuit();
        HashMap<String, Element> table = new HashMap<>();

        editeur.ajouter(e, table);
        Element eAjout = table.get(id);
        notNull = (eAjout != null);
        if (notNull) {
            checkId = eAjout.getId().equals(id);
        }
        assertTrue (notNull && checkId);
    }

    @Test
    public void testConnecterElem(){
        boolean notNull;
        boolean notNull2;
        boolean checkId = false;
        boolean checkId2 = false;

        String id = "test";
        String id2 = "test2";
        ArrayList<Element> sources= new ArrayList<>();
        ArrayList<Element> sources2 = new ArrayList<>();
        ArrayList<Element> sorties = new ArrayList<>();
        ArrayList<Element> sorties2 = new ArrayList<>();
        Element e1 = new EntreeCircuit(id, sources, sorties);
        Element e2 = new SortieCircuit(id2, sources2, sorties2);
        EditeurCircuit editeur = new EditeurCircuit();

        editeur.connecter(e1, e2);
        Element eConnectDroit = e1.getSorties().get(0);
        Element eConnectGauche = e2.getSources().get(0);

        notNull = (eConnectDroit != null);
        notNull2 = (eConnectGauche != null);
        if (notNull && notNull2) {
            checkId = eConnectDroit.getId().equals(id2);
            checkId2 = eConnectGauche.getId().equals(id);
        }
        assertTrue (notNull && notNull2 && checkId && checkId2);
    }

    @Test
    public void testModifierNom(){
        String id = "test";
        String nom = "elem";
        ArrayList<Element> sources= new ArrayList<>();
        ArrayList<Element> sorties = new ArrayList<>();
        EntreeCircuit e = new EntreeCircuit(id, sources, sorties);
        EditeurCircuit editeur = new EditeurCircuit();
        HashMap<String, Element> table = new HashMap<>();

        editeur.modifierNomElem(e, e.getType(), nom);
        assertEquals(e.getNom(), nom);
    }

    @Test
    public void testControleurChargerAjouterEntree() {
        ControlleurCircuit controleur = new ControlleurCircuit();
        ArrayList<Instruction> instructions = new ArrayList<>();
        Instruction instruction = new Instruction(InstructionEnum.AJOUTER, "test", "elem",
                TypeElement.ENTREE);

        instructions.add(instruction);
        try{
            controleur.charger(instructions);
        } catch (Exception e){}

        assertEquals(controleur.getNbrElemTableEntrees(), 1);
    }

    @Test
    public void testControleurChargerAjouterSortie() {
        ControlleurCircuit controleur = new ControlleurCircuit();
        ArrayList<Instruction> instructions = new ArrayList<>();
        Instruction instruction = new Instruction(InstructionEnum.AJOUTER, "test", "elem",
                TypeElement.SORTIE);
        Instruction instruction2 = new Instruction(InstructionEnum.AJOUTER, "test2", "elem2",
                TypeElement.SORTIE);

        instructions.add(instruction);
        instructions.add(instruction2);
        try{
            controleur.charger(instructions);
        } catch (Exception e){}

        assertEquals(controleur.getNbrElemTableSorties(), 2);
    }

    @Test
    public void testControleurCircuitConnecter() {
        Circuit c = new Circuit();
        EditeurCircuit edit = new EditeurCircuit();
        ConstructeurElements construct = new ConstructeurElements();
        InverseurInstruction inverseur = new InverseurInstruction();
        ControlleurCircuit controleur = new ControlleurCircuit(c, edit, construct, inverseur);
        ArrayList<Instruction> instructions = new ArrayList<>();

        Instruction instruction = new Instruction(InstructionEnum.AJOUTER, "test", "elem",
                TypeElement.ENTREE);
        Instruction instruction2 = new Instruction(InstructionEnum.AJOUTER, "test2", "elem2",
                TypeElement.NOT);
        Instruction instruction3 = new Instruction(InstructionEnum.RELIER, "test", "elem",
                TypeElement.ENTREE, "test2", TypeElement.NOT);

        instructions.add(instruction);
        instructions.add(instruction2);
        instructions.add(instruction3);
        try{
            controleur.charger(instructions);
        } catch (Exception e){}

        Element e1 = c.getEntrees().get("test");
        Element e2 = c.getPortes().get("test2");

        boolean estConnectGaucheDroite = (e1.getSorties().get(0) == e2);
        boolean estConnectDroiteGauche = (e2.getSources().get(0) == e1);
        assertTrue(estConnectGaucheDroite && estConnectDroiteGauche);
    }
}
