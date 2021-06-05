package Vues.Panneaux;

import java.awt.Color;

import javax.swing.*;

import Controleurs.ControleurSoumettre;

/**
 * PanneauAuthetification est un panneau qui apparait lorsque l'utilisateur
 * cherche a se connecter. Il permet à celui-ci d'entrer ses informations, puis
 * va passer ces informations au ControleurSoumettre afin qu'elles puissent 
 * être validées. 
 * 
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class PanneauAuthentification extends JPanel {
	/* CONSTANTES */
	/* Couleurs */
	public static final Color COULEUR_FOND = Color.decode("#0F141F");
	public static final Color LIBEL_COULEUR = Color.WHITE;
	public static final Color LIBEL_STATUT_COULEUR = Color.RED;
	/* Dimensions libelé */
	public static final int LIBEL_LONGUEUR = 165;
	public static final int LIBEL_HAUTEUR = 50;
	/* Dimentions des champs de saisie */
	public static final int SAISIE_LONGUEUR = 165;
	public static final int SAISIE_HAUTEUR = 50;
	
	// Instance de la fenetre parent.
	JFrame fenetre;
	
	// Instance du Dialogue dans lequel le panneau se trouve.
	JDialog auth;
	
	// Champs qui contiennent les données de l'utilisateur.
	JTextField txtNomUtilisateur;
	JPasswordField pwMotDePasse;
	
	// Bouton servant a authentifier les informations fournies.
	JButton soumettre;
	
	// Message apparraissant lorsque les informations sont mauvaises.
	JLabel msgErreur;


	/**
	 * Constructeur recevant la fenetre de dialogue sur laquelle sera posé
	 * le panneau, et la fenetre qui sera modifiée (supprimée) apres 
	 * l'authetification.
	 * 
	 * @param fenetre	La fenetre qui sera modifiée après l'authentification
	 * @param auth		Le dialogue sur lequel le panneau sera posé
	 * 
	 */
	public PanneauAuthentification(JFrame fenetre, JDialog auth) {
		this.setBackground(COULEUR_FOND);
		this.fenetre = fenetre;
		this.auth = auth;
		initComposants();
	}

	
	/**
	 * Fontion qui fait la mise en place du panneau en ajoutant les composants
	 * nécéssaires et applicant la couleur désirée
	 */
	private void initComposants() {
		ajouterChampUtilisateur();
		ajouterChampMotDePasse();
		ajouterBoutonSoumettre();
		ajouterMessageErreur();
		ajouterEcouteurSoumettre();
	}
	
	/**
	 * Ajoute le champ utilisateur (Le libellé et le champ texte) au panneau.
	 */
	private void ajouterChampUtilisateur() {
		creerLibellee("Nom utilisateur :", LIBEL_COULEUR, true);
		creerChampTexte(20);
	}

	/**
	 * Ajoute le champ de mot de passe (Le libellé et le champ texte) 
	 * au panneau.
	 */
	private void ajouterChampMotDePasse() {
		creerLibellee("Mot de passe :", LIBEL_COULEUR, true);
		creerChampMotDePasse(20);
	}

	/**
	 * Ajoute un bouton qui sert à soumettre les informations 
	 * d'authentification.
	 */
	private void ajouterBoutonSoumettre() {
		//creation du bouton et instantion de ses parametres
		soumettre = new JButton("Soumettre");
		soumettre.setBounds(45, 150, 150, 25);
		
		//ajout du bouton au panneau d'authentification
		this.add(soumettre);
	}
	
	
	/**
	 * Methode qui crée un message d'erreur qui sera affiché si les informations
	 * de l'utilisateur ne sont pas valides.
	 */
	private void ajouterMessageErreur() {
		creerLibellee("Erreur, veuillez ressayer de nouveau.", 
				LIBEL_STATUT_COULEUR, false);
	}

	/**
	 * Methode qui ajoute l'ecouteur approprié au bouton de soumission.
	 */
	private void ajouterEcouteurSoumettre(){
		soumettre.addActionListener(new ControleurSoumettre(fenetre, auth,
				txtNomUtilisateur, pwMotDePasse, msgErreur));
	}
	
	/**
	 * Crée un libelé et l'ajoute au panneau.
	 * 
	 * @param titre		Le titre du libelle.
	 * @param couleur	La couleur du texte du libelé
	 * @param visible	Si le libellee est visible ou pas par défaut
	 */
	private void creerLibellee(String titre, Color couleur, boolean visible) {
		JLabel lbl = new JLabel(titre);
		lbl.setSize(LIBEL_LONGUEUR, LIBEL_HAUTEUR);
		lbl.setForeground(couleur);
		
		lbl.setVisible(visible);
		
		this.add(lbl);
	}
	
	/**
	 * Crée un champ texte et l'ajoute au panneau.
	 * 
	 * @param colonnes	Le nombre de colonnes du champ texte.
	 */
	private void creerChampTexte(int colonnes) {
		txtNomUtilisateur = new JTextField(colonnes);
		txtNomUtilisateur.setSize(SAISIE_LONGUEUR, SAISIE_HAUTEUR);
		
		this.add(txtNomUtilisateur);
	}
	
	/**
	 * Crée un champ de mot de passe et l'ajoute au panneau.
	 * 
	 * @param colonnes	Le nombre de colonnes du champ texte.
	 */
	private void creerChampMotDePasse(int colonnes) {
		pwMotDePasse = new JPasswordField(colonnes);
		pwMotDePasse.setSize(SAISIE_LONGUEUR, SAISIE_HAUTEUR);
		pwMotDePasse.setEchoChar('•');

		this.add(pwMotDePasse);
	}
}