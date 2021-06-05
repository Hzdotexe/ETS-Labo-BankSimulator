package baseDonnees.tables;

import java.util.Vector;

import baseDonnees.bases.Colonne;
import baseDonnees.modeles.Transaction;

/**
 * Représente une table d'une base de données avec une colonnes pour chaque 
 * attribut d'une transaction (compte cource, compte destination, montant 
 * et statut.
 *
 * @author Fred Simard | ETS
 * @version Automne 2020
 * @revision et commentaires Pierre Bélisle
 *
 */
public class TableTransactions {
	

	//  Les attributs d'une transaction.
	private Colonne noCompteSource;
	private Colonne noCompteDestination;
	private Colonne montants;
	private Colonne statut;
	
	
	/**
	 * Constructeur d'une tableau avec des colonnes vides.
	 */
	public TableTransactions() {
		
		noCompteSource = new Colonne();
		noCompteDestination = new Colonne();
		montants = new Colonne();
		statut = new Colonne();
	}
	
	/**
	 * Ajoute une transaction à la table.  
	 * 
	 * AUcune validation n'est réalisée ici.
	 * 
	 * @param transaction  La transaction à ajouter.
	 */
	public void ajouterUneTransaction(Transaction transaction) {
		
		this.noCompteSource.ajouterValeur(transaction.getNoCompteSource());
		this.noCompteDestination.ajouterValeur(transaction.getNoCompteDestination());
		this.montants.ajouterValeur(transaction.getMontant());
		this.statut.ajouterValeur(transaction.getStatut());
		
	}

	/**
	 * Retourne une collection Vector de copies des transactions associées à un 
	 * numéro de compte source ou un compte destination.
	 * 
	 * @param numeroDeCompte Le numéro de compte source cherchée.
	 * 
	 * @return Toutes les transactionssndu compte fourni à l'appel.
	 */
	public Vector<Transaction> obtenirTransactionsPourCompte(String numeroDeCompte) {
		
		// Le Vector à retourner.
		Vector<Transaction> vecteurDeTransaction = new Vector<Transaction>();
		
		
		try {
			
			// On parcrurt toute la table.
			for(int i=0;i<noCompteSource.getNbElements();i++) {
				
				// Chaque fois que les comptes concordent, on en ajoute
				// une copie au Vector.
				if(noCompteSource.obtenirValeur(i).equals(numeroDeCompte) ||
				   noCompteDestination.obtenirValeur(i).equals(numeroDeCompte)) {
					
					// ajoute la transaction au vecteur
					vecteurDeTransaction.add(new Transaction(
							(String)noCompteSource.obtenirValeur(i),
							(String)noCompteDestination.obtenirValeur(i),
							(Double)montants.obtenirValeur(i),
							(String)statut.obtenirValeur(i)));
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return vecteurDeTransaction;
	}
}
