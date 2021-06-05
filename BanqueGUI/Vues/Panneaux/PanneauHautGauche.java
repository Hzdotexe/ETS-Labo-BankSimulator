package Vues.Panneaux;

import Vues.Fenetres.FenetreTransactions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>Panneau qui permet l'affichage des informations du client connecté à
 * son compte bancaire. Ceux-ci inclus son nom, son solde et son numéro de
 * compte. De plus, cette classe hérite de la classe JPanel.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class PanneauHautGauche  extends JPanel {

	/* Dimensions de l'image dans le panneau d'accueil. */
	public static final int LONGUEUR_IMAGE = 200;
	public static final int HAUTEUR_IMAGE = 200;

	/**
	 * <p>Constructeur par défaut qui initialise les paramètres du
	 * panneau, incluant la couleur et du logo de la banque.</p>
	 */
	public PanneauHautGauche () {
		this.setBackground(FenetreTransactions.COULEUR_FOND);

		// Emplacement de l'image de la fenêtre accueil.
		String repertoire = "/images/";
		String nomImage = "logotp3.png";

		//Ajout de l'image au panneau.
		ajouterImage(repertoire, nomImage);
	}
	
	/**
	 * Ajoute une image au panneau principal.
	 *
	 * @param repertoire Repertoire dans lequel l'image se trouve.
	 * @param nomImage   Le nom de l'image avec son extension
	 */
	public void ajouterImage(String repertoire, String nomImage) {
		try {
			BufferedImage bfLogo;
			ImageIcon imgLogoBanque;
			JLabel logoBanque;

			String cheminImg = repertoire + nomImage;

			bfLogo = ImageIO.read(this.getClass().getResource(cheminImg));
			imgLogoBanque = new ImageIcon(bfLogo);
			Image image = imgLogoBanque.getImage();
			Image newimg = image.getScaledInstance(LONGUEUR_IMAGE,
					HAUTEUR_IMAGE, Image.SCALE_SMOOTH);
			imgLogoBanque = new ImageIcon(newimg);

			logoBanque = new JLabel(imgLogoBanque);

			this.add(logoBanque);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


