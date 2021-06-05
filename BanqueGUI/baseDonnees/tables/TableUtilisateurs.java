package baseDonnees.tables;

import java.io.Serializable;

import baseDonnees.bases.Colonne;
import baseDonnees.bases.ColonneIndexee;
import baseDonnees.modeles.Utilisateur;

/**
* Classe qui représente une table dans une base de données.  Elle est composée 
* de colonne de valeurs, indexée ou non.
* 
*
* @author Fred Simard | ETS
* @version Automne 2020
* adaptation et commentaires Pierre Bélisle
*
*/
public class TableUtilisateurs implements Serializable{

	/*
	 * Stratégie : Les colonne nomUtilisateurs et numeroDeCompote sont des 
	 * colonnes indexées puisqu'on recherche principalement dans ces 2
	 * colonnes.
	 */
	private ColonneIndexee nomUtilisateurs;
	private Colonne  hashMotDePasse;
	private Colonne  salt;
	private ColonneIndexee numeroDeCompte;
	private Colonne solde;
	
	
	/**
	 * Crée une table vide d'utilisateur.
	 * 
	 */
	public TableUtilisateurs(){
		
		nomUtilisateurs = new ColonneIndexee();
		hashMotDePasse = new Colonne();
		salt = new Colonne();
		numeroDeCompte = new ColonneIndexee();
		solde = new Colonne();
		
	}


	/**
	 * Ajojute une copie de l'utilisateur dans la table des utilisateurs à 
	 * raison d'un attribut par colonne.  Une modification à la référence
	 * utilisateur ne modifiera pas la table.
	 * 
	 * @param utilisateur L'utilisateur à ajouter dans la table,
	 * 
	 * @return Si l'ajout s'est bien effectuée. L'utilisateur doit être unique.
	 */
	public boolean ajouterUnUtilisateur(Utilisateur utilisateur) {
		
		
		// Évite pls appels à l'accesseur en mode débug
		String nom = utilisateur.getNomUtilisateur();
		
		// À retourner.
		boolean ajoutEffectue = false;
		
		// On s'assure de l'unicité.
		if(this.nomUtilisateurs.estUnique(nom)) {
			
			// Ajout dans chaque colonne.
			this.numeroDeCompte.ajouterValeur(utilisateur.getNumeroDeCompte());
			this.nomUtilisateurs.ajouterValeur(nom);
			
			this.hashMotDePasse.ajouterValeur(utilisateur.getHashMotDePasse());
			this.salt.ajouterValeur(utilisateur.getSalt());
			this.solde.ajouterValeur(utilisateur.getSolde());
			
			ajoutEffectue = true;
			
		}
	
		
		return ajoutEffectue;
	}
	
	
	/**
	 * Retourne un utilisateur avec les données des différentes colonnes
	 * de l'utilisateur correspondant au nom fourni lors de l'appel.
	 * *
	 * Retourne null s'il n'y a pas d'utilisateur avec ce nom.
	 *  
	 * @param nomUtilisateur Le nom de l'utilisateur voulu.
	 * 
	 * @return L'utilisateur correspondant au nom ou null 
	 *         (noté que c'est un clone).
	 */
	public Utilisateur obtenirUtilisateurParNom(String nomUtilisateur) {
		
		int indexElement = this.nomUtilisateurs.obtenirIndex(nomUtilisateur);
		
		// L'utilisateur à retourner ou nulL.
		Utilisateur utilisateur = null;
		
		// Si l'utilisatwur existe sinon indexElement est négatif.
		if(indexElement >= 0) {

			try {
				
				// Comme ce sont des colonnes d'Object, il faut transtyper.
				utilisateur = new Utilisateur((String)nomUtilisateur, 
						(byte[]) this.hashMotDePasse.obtenirValeur(indexElement), 
						(byte[]) this.salt.obtenirValeur(indexElement),
						(String) this.numeroDeCompte.obtenirValeur(indexElement),
						(Double) this.solde.obtenirValeur(indexElement));

			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		
		return utilisateur;
	}
	
	/**
	 * Retourne un utilisateur avec les données des différentes colonnes
	 * de l'utilisateur correspondant au numéroo de compte fourni lors de l'appel.
	 * 
	 * Retourne null s'il n'y a pas d'utilisateur avec ce numéro de compte.
	 * 
	 * @param numeroDeCompte Le numéro de compte de l'utilisateur voulu.
	 * @return L'utilisateur correspondant au numéro de compte ou null,
	 */
	public Utilisateur obtenirUtilisateurParCompte(String numeroDeCompte) {

		// L'utilisateur à retourner ou nulL.
		Utilisateur utilisateur = null;
				
		int indexElement = this.numeroDeCompte.obtenirIndex(numeroDeCompte);
		
		if(indexElement>=0) {
			
			try {

				// Comme ce sont des colonnes d'Object, il faut transtyper.
				utilisateur =  new Utilisateur((String)this.nomUtilisateurs
						                           .obtenirValeur(indexElement), 

						(byte[])this.hashMotDePasse.obtenirValeur(indexElement),
						(byte[])this.salt.obtenirValeur(indexElement), 
						(String) numeroDeCompte,
						(Double) this.solde.obtenirValeur(indexElement));

			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		
		return utilisateur;
	}

	/**
	 * Permet de mettre à jour le dole d'un compte utilisateur dans la table.
	 * 
	 * @param utilisateur
	 * @param solde
	 */
	public void mettreAJourSoldeUtilisateur(Utilisateur utilisateur, double solde) {

		int indexElement = 
			  this.numeroDeCompte.obtenirIndex(utilisateur.getNumeroDeCompte());
		
		try {
			
			System.out.println("[TableUtilisateur] Changement de solde: " + indexElement);
			this.solde.remplacerValeur(solde, indexElement);
			
		} catch (Exception e) {
			
			System.out.println("[TableUtilisateur] échec au changement de solde");
		}
		
	}
}
