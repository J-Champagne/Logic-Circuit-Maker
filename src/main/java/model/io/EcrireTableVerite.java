package model.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EcrireTableVerite {

    /**
     * 
     */
    public static void sauvegarder(ArrayList<String> enteteTable, ArrayList<ArrayList<String>> donneeTableVeriteInterface, File fichierDeSauvegarde){
        try {
            FileWriter fileWriter = new FileWriter(fichierDeSauvegarde);
            fileWriter.write(composerTable(enteteTable, donneeTableVeriteInterface));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     */
    public static String composerTable(ArrayList<String> enteteTable, ArrayList<ArrayList<String>> donneeTableVeriteInterface){
        String texte = "";
        for(int i = 0; i < enteteTable.size()-1; i++){
            texte = texte + enteteTable.get(i) + "\t";
        }
        texte = texte + enteteTable.get(enteteTable.size()-1) + "\n";
        for(ArrayList<String> liste :  donneeTableVeriteInterface){
            for(String valeur : liste){
                texte = texte + valeur + "\t";
            }
            texte += "\n";
        }

        return texte;
    }
    
}
