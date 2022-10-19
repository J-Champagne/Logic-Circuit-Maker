package model.io;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import model.commande.InstructionSet;

/**
 * Gestion de l'ecriture dans un fichier.
 * 
 */
public class EcrireFichier {

    /**
     * Methode pour gérer l'ecriture dans un fichier.
     * 
     * @param fichierDeSauvegarde Fichier où sauvegarder l'information.
     * @param circuit L'information à sauvegarder.
     */
    public static void sauvegarder(InstructionSet is, File fichierDeSauvegarde){
        try{
            
            JAXBContext jaxbContext = JAXBContext.newInstance(InstructionSet.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(is, fichierDeSauvegarde);
        }
        catch(JAXBException jaxbException){
            jaxbException.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
