package baseDonnees.modeles;


import java.util.Arrays;

import baseDonnees.utils.UtilitairesDB;

/**
* Premet de regrouper les infos d'un Utilisateur et de son mot de passe.
* 
*
* @author Fred Simard | ETS
* @version Automne 2020
* @revision et commentaires Pierre Bélisle
*
*/
public class Utilisateur {
	
	/*
	 * Stratégie : Le mot de passe utilise une technique de hashage et une de 
	 * salage pour assurer la confientialité des comptes.
	 */

	// Les info sd'un utilisateur.
	private String nomUtilisateur;
	private byte[] hashMotDePasse;
	private byte[] salt;
	private String numeroDeCompte;
	private double solde = 0;

	

	/**
	 * Constructeur par paramètre de l'utilisateur sans le hachage et le salage.
	 * 
	 * @param nomUtilisateur Le nom complet de l'utilisateur.
	 * @param motDePasse Le mot de passe 
	 * @param numeroDeCompte Le numéro de compte complet.
	 * @param solde Le solde du compte.
	 */
	public Utilisateur(String nomUtilisateur, 
			           String motDePasse, 
			           String numeroDeCompte, 
			           double solde){
		
		this.nomUtilisateur = nomUtilisateur;
		
		this.salt = UtilitairesDB.obtenirSalt();
		
		this.hashMotDePasse = 
				UtilitairesDB.hashMotDePasse(motDePasse, this.salt);
		
		this.numeroDeCompte = numeroDeCompte;
		
		this.solde = solde;
	}

	/**
	 * Constructeur par paramètre de l'utilisateur sans le hachage et le salage.
	 * 
	 * @param nomUtilisateur Le nom complet de l'utilisateur.
	 * @param hashMotDePasse Le mot de passe après hachage
	 * @param salt Le mot de passe après salage
	 * @param numeroDeCompte Le numéro de compte complet.
	 * @param solde Le solde du compte.
	 */
	public Utilisateur(String nomUtilisateur, 
			           byte[] hashMotDePasse, 
			           byte[] salt, 
			           String numeroDeCompte, 
			           double solde){
		
		this.nomUtilisateur = nomUtilisateur;
		this.hashMotDePasse = hashMotDePasse;
		this.salt = salt;
		this.numeroDeCompte = numeroDeCompte;
		this.solde = solde;
	}

	/**
	 * L'authentification de l'utilisateur par rapport au mot de passe attendu
	 * qui a été fourni au constructeur.
	 * 
	 * @param nomUtilisateur Le nom cherchée
	 * @param motDePasse Le mot de passe associé.
	 * @return Si l'utilisateur est authentifié ou non.
	 */
	public boolean authentifier(String nomUtilisateur, String motDePasse){

		boolean nomExiste = this.nomUtilisateur.equals(nomUtilisateur);
		byte[] hash = UtilitairesDB.hashMotDePasse(motDePasse, this.salt);
		
		
		boolean hashPareil = compare(this.hashMotDePasse, hash);
		
		if(nomExiste  && hashPareil) {
			
			return true;
		}

		return false;
	}
	
	/*
	 * FOnction locale qui compare un tableau de byte
	 */
	boolean compare(byte[] b1, byte[] b2) {
		
		
		boolean pareil = true;
		
		for (int i =0 ;i < b1.length && pareil;i++){
			pareil = pareil && b1[i] == b2[i];
		}
		
		return pareil;
	}
	
	

	/**
	 * Accesseur de nom de l'utilisateur.
	 * 
	 * @return Le nom de l'utilisateur.
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	/*
	 * Le mot de passe après hachage.
	 * @return un tableau de byte du mot de passe après hachage.
	 */
	public byte[] getHashMotDePasse() {
		return hashMotDePasse;
	}
	
    /**
     * Le mot de passe après salage.
     * 
     * @return un tableau de byte du mot de passe après salage.
     */
	public byte[] getSalt() {
		return salt;
	}

	/**
	 * Accesseur du numéro du compte de l'utilisateur.
	 * 
	 * @return Le numéro de compte de l'utilisateur.
	 */
	public String getNumeroDeCompte() {
		return numeroDeCompte;
	}


	/**
	 * Accesseur du solde de l'utilisateur.
	 * 
	 * @return Le solde de l'utilisateur.
	 */
	public double getSolde() {
		return solde;
	}

	
	/**
	 * Méthode mutatrice du solde de l'utilisateur.
	 * 
	 * param differentiel Le montant à ajouter au solde (peut être négatif).
	 */	
	public void transactionSurSolde(double differentiel) {
		this.solde += differentiel;
	}
	
	/**
	 * Retourne une version String du nom et du numéro de compte de l'utilisateur.
	 */
	public String toString(){
		return nomUtilisateur + ":" + numeroDeCompte;
	}
	
	/**
	 * Retourne si les infos de l'utilisateur reçu correscpondant aux infos de
	 * l'utilisateur actuel (this).  On dira que c'est le même utilisateur s'il 
	 * a le même numéro de compte.
	 * 
	 * @param utilisateur L'utilisateur de comparaison.
	 * @return Si l'utilisateur de comparaison a les mêmes infos que 
	 *        l'utilisateur actuel.
	 */
	public boolean equals(Utilisateur utilisateur){
		return this.numeroDeCompte.equals(utilisateur.numeroDeCompte);
	}
}
