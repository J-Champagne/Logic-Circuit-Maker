package model.circuit;

import junit.framework.TestCase;
import model.elements.*;
import model.exception.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ValidateurCircuitTest extends TestCase {

    public void testMaximumPortesAtteint() {
        int erreurMax = 50;
        int validMax = 49;

        try {
            ValidateurCircuit.maximumPortesAtteint(erreurMax);
            fail();
        } catch (MaxElementsException e){
            assertTrue(true);
        }

        try {
            ValidateurCircuit.maximumPortesAtteint(validMax);
            assertTrue(true);
        } catch (MaxElementsException e){
            fail();
        }
    }

    public void testMaximumEntreesSortiesAtteint() {
        int erreurMax = 5;
        int validMax = 4;

        try {
            ValidateurCircuit.maximumEntreesSortiesAtteint(erreurMax);
            fail();
        } catch (MaxElementsException e){
            assertTrue(true);
        }

        try {
            ValidateurCircuit.maximumEntreesSortiesAtteint(validMax);
            assertTrue(true);
        } catch (MaxElementsException e){
            fail();
        }
    }

    public void testEstDernierEntreeOuSortie() {
        int erreurDernier = 1;
        int validDernier = 2;

        try {
            ValidateurCircuit.estDernierEntreeOuSortie(erreurDernier);
            fail();
        } catch (LastElementException e){
            assertTrue(true);
        }

        try {
            ValidateurCircuit.estDernierEntreeOuSortie(validDernier);
            assertTrue(true);
        } catch (LastElementException e){
            fail();
        }
    }

    public void testEstConnexionValide() {
        ArrayList<Element> sourcesErreur = new ArrayList<>();
        ArrayList<Element> sourcesValide = new ArrayList<>();
        Element source1 = new EntreeCircuit("entree1",null, null);
        Element source2 = new EntreeCircuit("entree2",null, null);
        sourcesErreur.add(source1);
        sourcesErreur.add(source2);
        sourcesValide.add(source1);

        Element elemValide = new And("valide",sourcesValide,null);
        Element elemErreur = new And("erreur",sourcesErreur,null);

        try {
            ValidateurCircuit.estConnexionValide(elemErreur);
            fail();
        } catch (InvalidConnectionException e){
            assertTrue(true);
        }

        try {
            ValidateurCircuit.estConnexionValide(elemValide);
            assertTrue(true);
        } catch (InvalidConnectionException e){
            fail();
        }
    }

    public void testEstNomValide() {
        String nomErreur = "Trop long";
        String nomValide = "Bon";

        try {
            ValidateurCircuit.estNomValide(nomErreur);
            fail();
        } catch (InvalidNameException e){
            assertTrue(true);
        }

        try {
            ValidateurCircuit.estNomValide(nomValide);
            assertTrue(true);
        } catch (InvalidNameException e){
            fail();
        }
    }

    public void testEstNomExistant() {
        TypeElement type = TypeElement.ENTREE;
        HashMap<String, Element> elements = new HashMap<>();
        EntreeCircuit entree = new EntreeCircuit("entree", null, null, "nom");
        elements.put("entree", entree);
        String nomErreur = "nom";
        String nomValide = "Valide";

        try {
            ValidateurCircuit.estNomExistant(type, elements, nomErreur);
            fail();
        } catch (ExistingNameException e){
            assertTrue(true);
        }

        try {
            ValidateurCircuit.estNomExistant(type, elements, nomValide);
            assertTrue(true);
        } catch (ExistingNameException e){
            fail();
        }
    }
}