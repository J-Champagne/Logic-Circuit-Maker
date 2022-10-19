package model.circuit;

import model.elements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "circuit")
public class Circuit {
    private HashMap<String, Element> entrees = new HashMap<>();
    private HashMap<String, Element> sorties = new HashMap<>();
    private HashMap<String, Element> portes = new HashMap<>();
    private Boolean[][] tableVerite;

    public Circuit() {}

    /**
     * Générer la table de vérité
     */
    public void genererTableVerite() {
        //initialise table de vérité
        genererSignalsEntrees();

        int rows = tableVerite.length;

        //Parcour chaque ligne de la table de vérité pour calculer les sorties
        for(int i = 0; i < rows; i++) {
            int entreeNum = 0;
            //Initialise les signals des entrées
            for (Map.Entry<String, Element> entry : entrees.entrySet()) {
                String key = entry.getKey();
                Element value = entry.getValue();

                value.setSignal(tableVerite[i][entreeNum]);
                entreeNum++;
                //Mise à jour du hashmap
                entrees.replace(key,value);
            }

            int sortieNum = 0;
            //Calcule le signal de chaque sortie
            for (Map.Entry<String, Element> entry : sorties.entrySet()) {
                Element value = entry.getValue();

                //Devrait seulement en avoir un
                Element source = value.getSources().get(0);
                tableVerite[i][entrees.size() + sortieNum] = trouverSignal(source);
                sortieNum++;
            }
        }
    }

    /**
     * Trouver le signal par récursion dans les sources associées
     * @param source    L'élément dont il faut trouver le signal
     * @return          Le signal
     */
    private Boolean trouverSignal(Element source){
        try{
            return source.getSignal();
        } catch (NullPointerException e){
            trouverSignal(source.getSources().get(0));
            if(source.getSources().size() > 1){
                trouverSignal(source.getSources().get(1));
            }
        }
        return source.getSignal();
    }

    /**
     * Générer tous les signals d'entrées possibles dans la table de vérité
     */
    public void genererSignalsEntrees(){
        this.tableVerite = new Boolean[(int) Math.pow(2, entrees.size())][entrees.size() + sorties.size()];
        for(int i = 0; i < entrees.size(); i++){
            // #Rows/(2 * currentColumn)
            int interval = tableVerite.length/(2*(i+1));

            boolean signal = false;
            int k = interval;
            for(int j = 0; j < tableVerite.length; j++) {
                if(k <= 0) {
                    signal = !signal;
                    k = interval;
                }
                k--;
                tableVerite[j][i] = signal;
            }
        }
    }

    //GETTERS ET SETTERS
    @XmlElementWrapper
    public HashMap<String, Element> getEntrees() {
        return entrees;
    }

    @XmlElementWrapper
    public HashMap<String, Element> getSorties() {
        return sorties;
    }

    @XmlElementWrapper
    public HashMap<String, Element> getPortes() {
        return portes;
    }

    public Boolean[][] getTableVerite() {
        return tableVerite;
    }

}
