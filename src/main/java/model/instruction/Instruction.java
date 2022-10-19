package model.instruction;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.elements.TypeElement;

/**
 * Cette classe permet d'identifier l'instruction du circuit qui a été selection dans l'interface utilisateur
 */
@XmlRootElement(name = "Instruction")
public class Instruction {
    private InstructionEnum typeInstruction;
    private String identification;
    private String identification2 = "";
    private String nom;
    private TypeElement typeElement;
    private TypeElement typeElement2 = null;

    public Instruction(){
        
    }

    public Instruction(InstructionEnum typeInstruction, String identification, String nom, TypeElement typeElement) {
        this.typeInstruction = typeInstruction;
        this.identification = identification;
        this.nom = nom;
        this.typeElement = typeElement;
    }

    public Instruction(InstructionEnum typeInstruction, String identification, TypeElement typeElement) {
        this.typeInstruction = typeInstruction;
        this.identification = identification;
        this.typeElement = typeElement;
    }

    public Instruction(InstructionEnum typeInstruction, String identification, String nom, TypeElement typeElement,
                       String identification2, TypeElement typeElement2) {
        this.typeInstruction = typeInstruction;
        this.identification = identification;
        this.nom = nom;
        this.typeElement = typeElement;

        this.identification2 = identification2;
        this.typeElement2 = typeElement2;
    }

    public Instruction(InstructionEnum typeInstruction, String identification, TypeElement typeElement,
                       String identification2, TypeElement typeElement2) {
        this.typeInstruction = typeInstruction;
        this.identification = identification;
        this.typeElement = typeElement;
        this.identification2 = identification2;
        this.typeElement2 = typeElement2;
    }

    // Type Instruction
    public boolean estAjouter() {
        return  typeInstruction == InstructionEnum.AJOUTER;
    }

    public boolean estSupprimer() {
        return  typeInstruction == InstructionEnum.SUPPRIMER;
    }

    public boolean estDefinir() {
        return  typeInstruction == InstructionEnum.DEFINIR;
    }

    public boolean estRelier() {
        return  typeInstruction == InstructionEnum.RELIER;
    }


    // Type Element
    //
    public boolean estEntree () {
        return typeElement == TypeElement.ENTREE;
    }

    public boolean estSortie () {
        return typeElement == TypeElement.SORTIE;
    }

    public boolean estPorte () {
        return (typeElement == TypeElement.NOT) ||
                (typeElement == TypeElement.OR) ||
                (typeElement == TypeElement.AND);
    }

    // Getter
    //
    @XmlElement(name = "typeInstruction")
    public InstructionEnum getInstruction() {return this.typeInstruction;}

    @XmlElement(name = "nom")
    public String getNom() {return this.nom;}

    @XmlElement(name = "identification")
    public String getIdentification () {return this.identification;}

    @XmlElement(name = "identification2")
    public String getIdentification2 () {return this.identification2;}

    @XmlElement(name = "typeElement")
    public TypeElement getTypeElement() {return this.typeElement;}

    @XmlElement(name = "typeElement2")
    public TypeElement getTypeElement2() {return this.typeElement2;}

    // Setter
    //
    public void setInstruction(InstructionEnum ie) {this.typeInstruction = ie;}

    public void setNom(String nom) {this.nom = nom;}

    public void setIdentification (String identification) {this.identification = identification;}

    public void setIdentification2 (String identification2) {this.identification2 = identification2;}

    public void setTypeElement(TypeElement te) {this.typeElement = te;}

    public void setTypeElement2(TypeElement te) {this.typeElement2 = te;}


    @Override
    public String toString() {

        return "'" + typeInstruction + '\'' +
                ", id=" + identification +
                ", element=" + typeElement +
                '}';
    }


    public boolean estEt() { return this.typeElement == TypeElement.AND; }
    public boolean estOu() { return this.typeElement == TypeElement.OR; }
    public boolean estNon() { return this.typeElement == TypeElement.NOT; }

}
