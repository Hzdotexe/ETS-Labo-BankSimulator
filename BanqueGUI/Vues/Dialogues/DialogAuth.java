package Vues.Dialogues;

import javax.swing.*;

import Vues.Panneaux.PanneauAuthentification;

/**
 * DialogAuth est un dialogue qui s'ouvre lorsque l'utilisateur tente de se 
 * connecter. Il servira d'interface pour l'authentification.
 * 
 * 
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 *
 */
public class DialogAuth extends JDialog {
	
	// Dimensions du dialogue. 
	public static final int LONGUEUR = 250;
	public static final int HAUTEUR = 250;

	// Fenetre parent du dialogue, qui sera supprimée après l'authentification.
	JFrame fenetre;

	/**
	 * Constructeur du dialogue d'authentification.
	 * 
	 * @param fenetre	Fenetre parent qui sera supprimée apres 
	 * 					l'authentification.
	 * @param titre		Titre du dialogue qui sera affiché.
	 * 
	 */
	public DialogAuth(JFrame fenetre, String titre) {
		this.fenetre = fenetre;
		initComposants(titre);
	}
	
	/**
	 * Methode qui configure les parametres du dialogue et ajoute les composants
	 * nécéssaires.
	 * 
	 * @param titre		Titre du dialogue qui sera affiché.
	 */	
	private void initComposants(String titre) {
		// Configure les paramètres du dialogue,
		this.setTitle(titre);
		this.setModal(true);
		this.setSize(LONGUEUR, HAUTEUR);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//Ajoute le panneau d'authenfication au dialogue.
		ajouterPanneauAuth();
		
		this.setVisible(true);
	}

	
	/*
	 * Methode qui ajoute le panneau d'authenfication sur le dialogue.
	 */
	private void ajouterPanneauAuth() {
		this.add(new PanneauAuthentification(fenetre, this));
	}
}
