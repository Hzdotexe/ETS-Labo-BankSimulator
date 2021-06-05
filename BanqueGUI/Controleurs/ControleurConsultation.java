package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Vues.Fenetres.FenetreTransactions;

/**
 * <p>
 * Ce contrôleur permet de gérer les événements qui surviennent sur l'item menu
 * "Consulter un fichier utilisateur" dans la fenêtre de transactions grâce à
 * l'implémentation de l'interface ActionListener et ses méthodes.
 * </p>
 * 
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class ControleurConsultation implements ActionListener {
	/* Répertoire dans lequel se trouve les transactions. */
	public static final String REPERTOIRE = "src/transactions";

	/* Fenetre dans laquelle on doit présenter les informations. */
	FenetreTransactions fenetre;

	/* Le fichier contenant les transactions de l'utilisateur */
	File fichier;

	/**
	 * Constructeur qui reçoit l'instance du JFrame dans laquelle on doit
	 * présenter les informations.
	 * 
	 * @param fenetre
	 */
	public ControleurConsultation(FenetreTransactions fenetre) {
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> listeTransactions;

		fichier = choisirFichier(REPERTOIRE);

		listeTransactions = getTransactions(getExtension(fichier));
	}

	/**
	 * Retourne la liste des transactions contenue dans le fichier de
	 * transactions choisi.
	 * 
	 * @param extension L'extension du fichier qu'on a choisi.
	 * @return Liste de données de transactions.
	 */
	private ArrayList<String> getTransactions(String extension) {

		ArrayList<String> listeTransactions;

		switch (extension) {
			case "csv" :
				listeTransactions = getTransactionsDeCSV();
				break;
			case "bin" :
				listeTransactions = getTransactionsDeBinaire();
				break;
			default :
				listeTransactions = new ArrayList<>();
				break;
		}

		return listeTransactions;
	}

	/**
	 * Retourne l'extension d'un fichier quelconque.
	 * 
	 * @param fichier Le dont on veut connaître l'extension.
	 * @return L'extension d'un certain fichier.
	 */
	public String getExtension(File fichier) {
		String nomFichier = fichier.getName();

		return nomFichier.substring(nomFichier.lastIndexOf(".") + 1);
	}

	/**
	 * Ouvre un dialogue similaire à l'explorateur de fichier afin de permettre
	 * à l'utilisateur de sélectionner le fichier de transaction qu'il désire
	 * faire afficher dans le panneau de la fenêtre de transaction.
	 * 
	 * @param repertoire	Emplacement par défaut dans l'explorateur de fichiers qu'on
	 *            			présente à l'utilisateur pour récupérer son fichier de
	 *            			transactions.
	 * @return Le fichier choisi par l'utilisateur.
	 */
	public File choisirFichier(String repertoire) {
		JFileChooser selecteurDeFichier = new JFileChooser(repertoire);
		FileNameExtensionFilter filtreurExtention = new FileNameExtensionFilter(
				"Fichiers CSV et Binaires", "csv", "bin");
		selecteurDeFichier.setDialogTitle("Consulter un fichier utilisateur");
		selecteurDeFichier.setAcceptAllFileFilterUsed(true);
		selecteurDeFichier.addChoosableFileFilter(filtreurExtention);
		selecteurDeFichier.setFileFilter(filtreurExtention);
		int statut = selecteurDeFichier.showOpenDialog(null);
		if (statut == JFileChooser.APPROVE_OPTION) {
			return selecteurDeFichier.getSelectedFile();
		}

		return null;
	}

	/**
	 * Retourne la liste des données de transaction contenue dans le fichier de
	 * transaction d'extension ".csv" choisi par l'utilisateur.
	 * 
	 * @return	La liste des données de transaction contenue dans le fichier 
	 * 			de transaction d'extension ".csv".
	 */
	private ArrayList<String> getTransactionsDeCSV() {
		ArrayList<String> listeTransactions = new ArrayList<String>();

		try {
			Scanner lecteur = new Scanner(fichier);
			while (lecteur.hasNextLine()) {
				String ligne = lecteur.nextLine();

				String transaction = getTransaction(ligne);

				listeTransactions.add(transaction);
			}
			lecteur.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return listeTransactions;
	}

	/**
	 * Retourne la liste des données de transaction contenue dans le fichier de
	 * transaction d'extension ".bin" choisi par l'utilisateur.
	 * 
	 * @return 	La liste des données de transaction contenue dans le fichier 
	 * 			de transaction d'extension ".bin".
	 */
	private ArrayList<String> getTransactionsDeBinaire() {
		ArrayList<String> listeTransactions = new ArrayList<String>();

		try {
			FileInputStream fileIS = new FileInputStream(fichier.getPath());
			ObjectInputStream inputStream = new ObjectInputStream(fileIS);

			while (inputStream.available() > 0) {
				String ligne = inputStream.readUTF();

				String transaction = getTransaction(ligne);

				listeTransactions.add(transaction);
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listeTransactions;
	}

	/**
	 * Retourne la transaction contenue dans une ligne d'un fichier de
	 * transaction.
	 * 
	 * @param ligne	La ligne d'un fichier de transaction.
	 * @return La donnée contenue dans la ligne du fichier de transaction
	 *         formatée.
	 */
	private String getTransaction(String ligne) {
		String separateur = ";";

		return getUtilisateur(ligne) + separateur + getSource(ligne)
				+ separateur + getDestination(ligne) + separateur
				+ getMontant(ligne) + separateur + getStatut(ligne);
	}

	/**
	 * Retourne l'utilisateur contenu dans une ligne d'un fichier de
	 * transaction.
	 * 
	 * @param ligne
	 *            La ligne d'un fichier de transaction.
	 * @return La donnée "utilisateur" contenue dans la ligne du fichier de
	 *         transaction formatée.
	 */
	private String getUtilisateur(String ligne) {
		return fragmenterLigneDonneeTransaction(ligne)[0];
	}

	/**
	 * Retourne la source contenu dans une ligne d'un fichier de transaction.
	 * 
	 * @param ligne La ligne d'un fichier de transaction.
	 * @return La donnée "source" contenue dans la ligne du fichier de
	 *         transaction formatée.
	 */
	private String getSource(String ligne) {
		return fragmenterLigneDonneeTransaction(ligne)[1];
	}

	/**
	 * Retourne le montant contenu dans une ligne d'un fichier de transaction.
	 * 
	 * @param ligne La ligne d'un fichier de transaction.
	 * @return La donnée "montant" contenue dans la ligne du fichier de
	 *         transaction formatée.
	 */
	private double getMontant(String ligne) {
		return Double.parseDouble(fragmenterLigneDonneeTransaction(ligne)[2]);
	}

	/**
	 * Retourne la destination contenue dans une ligne d'un fichier de
	 * transaction.
	 * 
	 * @param ligne La ligne d'un fichier de transaction.
	 * @return La donnée "destination" contenue dans la ligne du fichier de
	 *         transaction formatée.
	 */
	private String getDestination(String ligne) {
		return fragmenterLigneDonneeTransaction(ligne)[3];
	}

	/**
	 * Retourne le statut contenue dans une ligne d'un fichier de transaction.
	 * 
	 * @param ligne La ligne d'un fichier de transaction.
	 * @return La donnée "statut" contenue dans la ligne du fichier de
	 *         transaction formatée.
	 */
	private String getStatut(String ligne) {
		return fragmenterLigneDonneeTransaction(ligne)[4];
	}

	/**
	 * Retourne un tableau avec les informations contenues dans une ligne d'un
	 * fichier de transaction séparées.
	 * 
	 * @param ligne La ligne d'un fichier de transaction.
	 * @return Le tableau avec les informations d'un ligne séparées 
	 * 		   par leur séparateur.
	 */
	private String[] fragmenterLigneDonneeTransaction(String ligne) {
		return ligne.split(";");
	}
}
