package model.io;

import java.io.File;
import java.io.FileReader;

import model.commande.InstructionSet;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/**
 * Gestion de la lecture dans un fichier.
 * 
 */
public class LectureFichier {

    /**
     * 
     * @param fichierAOuvrir Fichier où lire l'information.
     * @return code Le code pour générer le circuit.
     */
    public static InstructionSet lire(File fichierAOuvrir) throws Exception{
        InstructionSet instructions = null;
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(InstructionSet.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            instructions = (InstructionSet) jaxbUnmarshaller.unmarshal(new FileReader(fichierAOuvrir));
            
        }
        catch(JAXBException jaxbException){
            throw new JAXBException("Fichier corrompu");
        }
        catch(Exception e){
            throw new Exception("Fichier corrompu");
        }
        return instructions;
    }
}
