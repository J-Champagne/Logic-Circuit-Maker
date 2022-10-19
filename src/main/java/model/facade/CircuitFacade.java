package model.facade;

import model.controleur.ControlleurCircuit;
import model.elements.Element;
import model.elements.TypeElement;
import vue.dessin.composite.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CircuitFacade {

    private HashMap<String, Element> entreesCircuit;
    private HashMap<String, Element> sortiesCircuit;
    private HashMap<String, Element> portesCircuit;

    private ArrayList<GrapheSimple> listeEntree;
    private ArrayList<GrapheSimple> listeSortie;
    private ArrayList<GrapheSimple> listePorteFlottante;
    private ArrayList<GrapheSimple> listePorteSemiFlottante;
//    private ArrayList<ElementSimple> listeCircuit;


    public CircuitFacade (ControlleurCircuit controlleurCircuit) {
        entreesCircuit = controlleurCircuit.getEntrees();
        sortiesCircuit = controlleurCircuit.getSorties();
        portesCircuit = controlleurCircuit.getPortes();

        listeEntree = new ArrayList<>();
        listeSortie = new ArrayList<>();
        listePorteFlottante = new ArrayList<>();
        listePorteSemiFlottante = new ArrayList<>();
//        listeCircuit = new ArrayList<>();
    }


    private String porteConnecteToStr(ArrayList<Element> liste) {
        String str = "";
        for (Element element : liste) {
            str = str + " " + element.getId();
        }
        return str;
    }

    public ArrayList<GrapheSimple> getPorteSemiFlottante() {
        listePorteSemiFlottante.clear();

        portesCircuit.forEach ((key, value) -> {
            GrapheSimple nouveauElementPorte = null;

            String sources =  porteConnecteToStr(value.getSources()) ;
            String destination = porteConnecteToStr(value.getSorties()) ;
            String lienEntree = "";
            String lienSortie = "";


            if (sources.length() != 0) {
                 lienEntree = "(" + sources + ")-" ;
            }

            if (destination.length() != 0) {
                lienSortie = "-(" + destination + ")" ;
            }


            String id = lienEntree + value.getId() + lienSortie;


            if (value.getType() == TypeElement.AND) {

                nouveauElementPorte = new ElementET(
                    value.getSorties(), id,
                    value.getSources().size(),
                    value.getSorties().size());
            } else if (value.getType() == TypeElement.OR) {
                nouveauElementPorte = new ElementOU(
                    value.getSorties(), id,
                    value.getSources().size(),
                    value.getSorties().size());
            } else {
                nouveauElementPorte = new ElementNON(
                        value.getSorties(), id,
                        value.getSources().size(),
                        value.getSorties().size());
            }

            if (nouveauElementPorte.etatConnection() == ConnecteEnum.SEMI_CONNECTE)   {
//                System.out.println("1");
                if ((value.getSorties() == null) || (value.getSorties().isEmpty())) {
//                    System.out.println("2");
                    listePorteSemiFlottante.add(nouveauElementPorte);
//                    System.out.println("3");
                } else {
//                    System.out.println("4");
                    if (value.getSorties().get(0).getType() != TypeElement.SORTIE) {
//                        System.out.println("5");
                        listePorteSemiFlottante.add(nouveauElementPorte);
                    }
                }
            }
        });
        return listePorteSemiFlottante;
    }

    public ArrayList<GrapheSimple> getPorteFlottante() {
        listePorteFlottante.clear();
        portesCircuit.forEach ((key, value) -> {
            if (value.getSorties().size() == 0 && value.getSources().size() == 0) {
                GrapheSimple nouveauElementPorte;

                if (value.getType() == TypeElement.AND) {
                    nouveauElementPorte = new ElementET(
                            value.getSorties(), value.getId(),
                            value.getSources().size(),
                            value.getSorties().size());

                } else if (value.getType() == TypeElement.OR) {
                    nouveauElementPorte = new ElementOU(
                            value.getSorties(), value.getId(),
                            value.getSources().size(),
                            value.getSorties().size());

                } else {
                    nouveauElementPorte = new ElementNON(
                            value.getSorties(), value.getId(),
                            value.getSources().size(),
                            value.getSorties().size());
                }
                listePorteFlottante.add(nouveauElementPorte);
            }
        });
        return listePorteFlottante;
    }

    public ArrayList<GrapheSimple> entree() {
        listeEntree.clear();
        entreesCircuit.forEach ((key, value) -> {
            String nom = value.getNom();
            String nomDefini =  (nom.length() != 0) ? nom : value.getId();
            GrapheSimple nouveauElementEntree = new ElementEntree(
                    value.getSorties(), nomDefini, value.getSorties().size());

            listeEntree.add(nouveauElementEntree);
        });
        return listeEntree;

    }

    public ArrayList<GrapheSimple> sortie() {
        listeSortie.clear();
        sortiesCircuit.forEach ((key, value) -> {
            String nom = value.getNom();
            String nomDefini =  (nom.length() != 0) ? nom : value.getId();
            GrapheComposite elementRacineComposite = null;
//            ElementSimple elementSimple = null;

            if ( value.getSources() != null && value.getSources().size() == 1 ) {

                Element porte = value.getSources().get(0);

                // Construction des portes
                if (porte.getType() != TypeElement.ENTREE) {
                    if (porte.getType() == TypeElement.AND) {
                        elementRacineComposite = new ElementET(
                                value.getSorties(), porte.getId(),
                                value.getSources().size(),
                                value.getSorties().size());

                    } else if (porte.getType() == TypeElement.OR) {
                        elementRacineComposite = new ElementOU(
                                value.getSorties(), porte.getId(),
                                value.getSources().size(),
                                value.getSorties().size());

                    } else if (porte.getType() == TypeElement.NOT) {
                        elementRacineComposite = new ElementNON(
                                value.getSorties(), porte.getId(),
                                value.getSources().size(),
                                value.getSorties().size());
                    }
//                    else if (porte.getType() == TypeElement.ENTREE) {
//                        elementSimple = new ElementEntree(
//                                value.getSorties(), nomDefini, value.getSorties().size());
//                    }
                    // Inititalisation de la liste a vide
//                    elementRacineComposite.listeEnfant = null;

                    elementRacineComposite.ajouter(porte.getSources());
                }
            }
            GrapheSimple teteElementSortie = new ElementSortie(
                    value.getSorties(), nomDefini, value.getSources().size(), elementRacineComposite);

            listeSortie.add(teteElementSortie);
        });
        return listeSortie;
    }
}
