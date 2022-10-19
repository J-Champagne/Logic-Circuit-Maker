package vue;


import model.controleur.Board;
import model.controleur.controlleur_base.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Interface implements ActionListener {

	private Application app;
	private Board board;


	private JPanel entreesLabels;
	private JPanel entreesIcons;
	private JPanel sortiesLabels;
	private JPanel sortiesIcons;

	private JLayeredPane panelCircuit;
	private JTable tableVerite;
	private JPanel panelPorte1;
	private JPanel panelPorte2;
	private JPanel panelPorte3;
	private JPanel panelPorte4;
	private JPanel panelPorte5;
	private JPanel panelPorte6;
	private JPanel panelPorte7;
	private JPanel panelPorte8;	
	private JPanel panelLiaison1;
	private JPanel panelLiaison2;
	private JPanel panelLiaison3;
	private JPanel panelLiaison4;
	private JPanel panelLiaison5;
	private JPanel panelLiaison6;
	private JPanel panelLiaison7;
	private JPanel panelLiaison8;
	private JPanel panelLiaison9;

	public JFrame frame;
	public JMenuItem sauvegarderCircuitButton;
	public JMenuItem quitterButton;
	public JMenuItem nouveauCircuitButton;
	public JMenuItem ouvrirCircuitButton;

	// Ajout des bouttons pour le menue
	public JMenuItem annulerOperationButton;
	public JMenuItem annulerToutesOperationsButton;
	public JMenuItem retablirOperationButton;


	public JButton ajouterEntreeButton;
	public JButton ajouterSortieButton;
	public JButton supprimerEntreeButton;
	public JButton supprimerSortieButton;
	public JButton definirNomEntreeButton;
	public JButton definirNomSortiebutton;
	public JButton ajouterPorteButton;
	public JButton supprimerPorteButton;
	public JButton relierEntreeButton;
	public JButton relierSortieButton;
	public JButton calculerTableButton;

	/**
	 * Créer l'application.
	 */
	public Interface()  {
		initialize();
	}

	/**
	 * Intialiser le contenu du Frame
	 */
	private void initialize() {

		// lancement de l'application

		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//					setLooAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		app = new Application();
		app.demarrer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}