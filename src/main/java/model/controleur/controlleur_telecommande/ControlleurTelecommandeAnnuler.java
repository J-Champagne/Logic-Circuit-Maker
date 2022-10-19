package model.controleur.controlleur_telecommande;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.commande.GestionnaireHistorique;
import model.telecomande.MessageTelecommandeEnum;
import model.telecomande.Telecommande;
import model.telecomande.TelecommandeEtatEnum;

public class ControlleurTelecommandeAnnuler extends ControlleurTelecommande implements ActionListener {

    public ControlleurTelecommandeAnnuler(Telecommande telecommande){
        super();
        this.telecommande = telecommande;
        body = telecommande.getBody();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        annuler();
    }

    /**
     * Fonction pour vider l'historique
     */
    public void annuler(){
        body.afficherMessageValidation(MessageTelecommandeEnum.MSG_ANNULER_HITORIQUE.message);

        GestionnaireHistorique.getInstance().getHistorique().reset();

        // notification aux observateurs
        telecommande.setState(TelecommandeEtatEnum.HISTORIQUE);
    }
    
}
