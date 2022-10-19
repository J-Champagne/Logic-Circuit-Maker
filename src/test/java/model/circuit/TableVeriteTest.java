package model.circuit;

import junit.framework.TestCase;
import model.elements.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TableVeriteTest extends TestCase {

    private ArrayList<Element> entree1Sorties = new ArrayList<>();
    private ArrayList<Element> entree2Sorties = new ArrayList<>();
    private ArrayList<Element> entree3Sorties = new ArrayList<>();
    private Element entree1 = new EntreeCircuit("E1",null,entree1Sorties, "Entree1");
    private Element entree2 = new EntreeCircuit("E2",null,entree2Sorties,"Entree2");
    private Element entree3 = new EntreeCircuit("E3",null,entree3Sorties, "Entree3");
    private ArrayList<Element> porteAnd1Sources = new ArrayList<Element>(){
        {
            add(entree1);
            add(entree2);
        }
    };
    private ArrayList<Element> porteAnd1Sorties = new ArrayList<>();
    private Element porteAnd1 = new And("P1",porteAnd1Sources,porteAnd1Sorties);
    private ArrayList<Element> porteOrSources = new ArrayList<Element>(){
        {
            add(entree2);
            add(entree3);
        }
    };
    private ArrayList<Element> porteOrSorties = new ArrayList<>();
    private Element porteOr = new Or("P2",porteOrSources,porteOrSorties);
    private ArrayList<Element> porteNotSources = new ArrayList<Element>(){
        {
            add(porteAnd1);
        }
    };
    private ArrayList<Element> porteNotSorties = new ArrayList<>();
    private Element porteNot = new Not("P3",porteNotSources,porteNotSorties);
    private ArrayList<Element> porteAnd2Sources = new ArrayList<Element>(){
        {
            add(porteNot);
            add(porteOr);
        }
    };
    private ArrayList<Element> porteAnd2Sorties = new ArrayList<>();
    private Element porteAnd2 = new And("P4",porteAnd2Sources,porteAnd2Sorties);

    private ArrayList<Element> sortie1Sources = new ArrayList<>();
    private Element sortie1 = new SortieCircuit("S1",sortie1Sources,null, "Sortie1");

    {
        entree1Sorties.add(porteAnd1);
        entree2Sorties.add(porteAnd1);
        entree2Sorties.add(porteOr);
        entree3Sorties.add(porteOr);
        porteAnd1Sorties.add(porteNot);
        porteNotSorties.add(porteAnd2);
        porteOrSorties.add(porteAnd2);
        porteAnd2Sorties.add(sortie1);

        entree1.setSorties(entree1Sorties);
        entree2.setSorties(entree2Sorties);
        entree3.setSorties(entree3Sorties);
        porteAnd1.setSources(porteAnd1Sources);
        porteAnd1.setSorties(porteAnd1Sorties);
        porteOr.setSources(porteOrSources);
        porteOr.setSorties(porteOrSorties);
        porteNot.setSources(porteNotSources);
        porteNot.setSorties(porteNotSorties);
        porteAnd2.setSources(porteAnd2Sources);
        porteAnd2.setSorties(porteAnd2Sorties);

        sortie1Sources.add(porteAnd2);
        sortie1.setSources(sortie1Sources);
    }

    @Test
    public void testGenererTableVerite() {
        Circuit circuit = new Circuit();

        circuit.getEntrees().put("E1",entree1);
        circuit.getEntrees().put("E2",entree2);
        circuit.getEntrees().put("E3",entree3);
        circuit.getSorties().put("S1",sortie1);

        circuit.genererTableVerite();

        Boolean[][] expectedTableVerite = {
                {false,false,false,false},
                {false,false,true,true},
                {false,true,false,true},
                {false,true,true,true},
                {true,false,false,false},
                {true,false,true,true},
                {true,true,false,false},
                {true,true,true,false}
        };

        for(int i = 0; i < expectedTableVerite.length; i++){
            for(int j = 0; j < expectedTableVerite[0].length; j++){
                assertEquals(expectedTableVerite[i][j],circuit.getTableVerite()[i][j]);
            }
        }
    }
}