package model.commande;

import model.instruction.Instruction;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "ListeInstructionXML")
public class InstructionSet extends ArrayList<Instruction> {

    public InstructionSet () {
    }

    @XmlElementWrapper(name = "ListeInstruction")
    public InstructionSet getListe(){
        return this;
    }
    

    

}
