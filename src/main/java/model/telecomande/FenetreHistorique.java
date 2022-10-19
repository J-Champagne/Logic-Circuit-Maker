package model.telecomande;

import model.commande.GestionnaireHistorique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Cette classe nous permet d'afficher le circuit
 *
 */
public class FenetreHistorique extends JLayeredPane implements Observateur {

    private final Telecommande sujetObservable;

    JList<String> undo;
    DefaultListModel<String> modelUndo = new DefaultListModel<>();
    JList<String> redo;
    DefaultListModel<String> modelRedo = new DefaultListModel<>();

    public FenetreHistorique(Telecommande e, Rectangle r, Color c) {
        this.sujetObservable = e;

        // souscription
        this.sujetObservable.ajouterObservateur(this);

        this.setBackground(c);
        this.setBounds(r);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEtchedBorder(1));

        this.setLayout(new BorderLayout());

        Dimension boardSize = new Dimension(200, 500);
        this.setBounds(305,100,boardSize.width, boardSize.height);
//        this.setBounds(0,0,boardSize.width, boardSize.height);

        JPanel undoPanel = new JPanel();
        undoPanel.setPreferredSize(new Dimension(180, 240));

        undo = new JList<>();
        undo.setModel(modelUndo);
        undo.setBorder(BorderFactory.createLineBorder(Color.blue));
//        undo.setPreferredSize(new Dimension(180, 210));
        JLabel undoLabel = new JLabel("Undo");
        undoPanel.add(undoLabel);
        JScrollPane scrollPaneUndo = new JScrollPane(undo);
        scrollPaneUndo.setPreferredSize(new Dimension(180, 210));
        undoPanel.add(scrollPaneUndo);


        JPanel redoPanel = new JPanel();
        redoPanel.setPreferredSize(new Dimension(180, 240));
        redo = new JList<>();
        redo.setModel(modelRedo);
        redo.setBorder(BorderFactory.createLineBorder(Color.blue));

        JLabel redoLabel = new JLabel("Redo");
        redoPanel.add(redoLabel);
        JScrollPane scrollPaneRedo = new JScrollPane(redo);
        scrollPaneRedo.setPreferredSize(new Dimension(180, 210));
        redoPanel.add(scrollPaneRedo);

        this.add(undoPanel,BorderLayout.NORTH);
        this.add(redoPanel,BorderLayout.SOUTH);

    }

    @Override
    public void update() {
        if (sujetObservable.getState() == TelecommandeEtatEnum.CIRCUIT_COURANT  || sujetObservable.getState() == TelecommandeEtatEnum.HISTORIQUE) {

            // Undo
            ArrayList<String> undoList = GestionnaireHistorique.getInstance().getHistorique().getEtatUndo();
            modelUndo.removeAllElements();
            for (String s : undoList) {
                modelUndo.addElement(s);
            }

            //Redo
            ArrayList<String> redoList = GestionnaireHistorique.getInstance().getHistorique().getEtatRedo();
            modelRedo.removeAllElements();
            for (String s : redoList) {
                modelRedo.addElement(s);
            }
        }
    }
}
