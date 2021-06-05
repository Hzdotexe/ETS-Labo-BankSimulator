package Vues.Boutons;

import javax.swing.*;

import Controleurs.ControleurConnexion;

/**
 * <p>Classe qui représente un bouton de connexion.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class BoutonConnexion extends JButton {
	
	/**
	 * Constructeur qui permet de construire le bouton avec son titre(texte)
	 * par défaut.
	 * 
	 * @param fenetre 	Le JFrame parent au composant dans lequel le bouton se 
	 * 					trouve.
	 * @param titre 	Le nom du bouton.
	 */
    public BoutonConnexion(JFrame fenetre, String titre) {
        this.setText(titre);
        this.addActionListener(new ControleurConnexion(fenetre));
    }
}
