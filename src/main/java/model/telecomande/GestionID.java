package model.telecomande;

import model.instruction.Instruction;

public class GestionID {
    private boolean[] listeEntre;
    private boolean[] listeSortie;
    private boolean[] listePorte;

    public GestionID(){ 
        listeEntre = new boolean[5];
        listeSortie = new boolean[5];
        listePorte = new boolean[50];
        for(int i = 0; i < listeEntre.length; i++){
            listeEntre[i] = true;
        }
        for(int i = 0; i < listeSortie.length; i++){
            listeSortie[i] = true;
        }
        for(int i = 0; i < listePorte.length; i++){
            listePorte[i] = true;
        }
    }

    public int getEntreID(){
        for(int i = 0; i < listeEntre.length; i++){
            if(listeEntre[i]){
                listeEntre[i] = false;
                return i + 1;
            }
        }
        return -1;
    }

    public int getSortieID(){
        for(int i = 0; i < listeSortie.length; i++){
            if(listeSortie[i]){
                listeSortie[i] = false;
                return i + 1;
            }
        }
        return -1;
    }

    public int getPorteID(){
        for(int i = 0; i < listePorte.length; i++){
            if(listePorte[i]){
                listePorte[i] = false;
                return i + 1;
            }
        }
        return -1;
    }

    public void mettreElementDisponible(Instruction instruction){
        String identification = instruction.getIdentification();
        String type = identification.substring(0,1);
        int nbElement = Integer.parseInt(identification.substring(1));
        if(instruction.estSupprimer())
        switch(type){
            case "E":
                listeEntre[nbElement - 1] = true;
                break;
            case "S":
                listeSortie[nbElement - 1] = true;
                break;
            default:
                listePorte[nbElement - 1] = true;
                break;
        }
    }

    public void vider(){
        for(int i = 0; i < listeEntre.length; i++){
            listeEntre[i] = true;
        }
        for(int i = 0; i < listeSortie.length; i++){
            listeSortie[i] = true;
        }
        for(int i = 0; i < listePorte.length; i++){
            listePorte[i] = true;
        }
    }

    public void mettreElementIndisponible(Instruction instruction){
        String identification = instruction.getIdentification();
        String type = identification.substring(0,1);
        int nbElement = Integer.parseInt(identification.substring(1));
        if(instruction.estAjouter()){
            switch(type){
                case "E":
                    listeEntre[nbElement - 1] = false;
                    break;
                case "S":
                    listeSortie[nbElement - 1] = false;
                    break;
                case "P":
                    listePorte[nbElement - 1] = false;
                    break;
            }
        }
    }
}
