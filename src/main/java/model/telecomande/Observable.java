package model.telecomande;

import javax.swing.text.BadLocationException;

/**
 *
 * Interface Observable
 *
 */
public interface Observable {
    public void ajouterObservateur(Observateur observateur);
    public void supprimerObservateur(Observateur observateur);
    public void notifierObservateur();


    public TelecommandeEtatEnum getState();

    public void setState(TelecommandeEtatEnum state) throws BadLocationException;
}
