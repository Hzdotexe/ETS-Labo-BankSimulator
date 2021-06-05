package banque;

import java.util.Vector;

import baseDonnees.BaseDonnees;
import baseDonnees.modeles.Transaction;
import baseDonnees.modeles.Utilisateur;
import baseDonnees.utils.SeedDB;

/**
 * Correspond à la partie dorsale de l'application (back end).  C'est la banque
 * qui contient la base de données des utilisateurs et des soldes.
 * 
 * Vous ne pouvez communiquer avec la banque qu'en obtenant l'intance qui est 
 * privée et unique.  Il ne sera pas possible de créer de nouvelle banque
 * ailleurs.
 * 
 * À noter que s'il y a des objets de type MonBserver qui ont été ajouté
 * (attacherObserver) à la classe MonObservable, ils seront avertit lors des 
 * modifications.
 * 
 * @author Frédéric Simard | ETS
 * @version Automne 2020
 * @revision et commentaires Pierre Bélisle
 *
 */
public class Banque{
	
	/*
	 * Stratégie : 
	 */

	// Les tables (Utilisateur et Transaction).
	BaseDonnees bd;
	
	// L'utilisateur actif.
	Utilisateur utilisateurActif;
	
	// Singleton 
	private static Banque instance = new Banque();
	
	/**
	 * Retourne la seule instance de Banque dans le projet.
	 * 
	 * @return L'instance de Banque qui contient la base de données,
	 */
	public static Banque getInstance() {
		
			return instance;

	};
	
	// Sera mis à true lorsqu'il y aura un utilisateur actif.
	private boolean connecte = false;
	
	
	/**
	 * Constructeur privée qui initialise la base de données.
	 */
	private Banque() {
		
		initBanque();
	}
	
	/*
	 * Bonne pratique de ne pas mettre de code directement dans un constructeur.
	 */
	private void initBanque() {
		
		bd = new BaseDonnees();		
		SeedDB.seed(bd);
	}

	/**
	 * Retourne si le nom et le mot de passe fourni sont authentifier auprès de
	 * l'utilisateur actif.
	 * 
	 * @param nomUtilisateur  Le nom à vérifier.
	 * @param motDePasse Le mot de passe associé au compte.
	 * @return
	 */
	public boolean verifier(String nomUtilisateur, String motDePasse) {
		
		/**
		 * On obtient l'utilisateur de la table des utilisateurs et s'il existe,
		 * on retourne le résultat de l'authentification faites par l'utilisateur.
		 */
		
		Utilisateur utilisateur = bd.obtenirUtilisateurParNom(nomUtilisateur);
		
		if(utilisateur != null) {
			
			System.out.println("v�rification de l'utilisateur");
			System.out.println(utilisateur.toString());
			
			return utilisateur.authentifier(nomUtilisateur, motDePasse);
		}
		
		return false;
	
	}
	
	/*
	 * Sous tâche pour soumettre une transaction
	 */
	private void accepterTransaction(Transaction nouvelleTransaction,
			                         Utilisateur source,
			                         Utilisateur destination) {
		
		nouvelleTransaction.setStatut(Transaction.ACCEPTE);
		
		bd.mettreAJourSoldeUtilisateur(source, 
				    source.getSolde()-nouvelleTransaction.getMontant());
		
		
		bd.mettreAJourSoldeUtilisateur(destination, 
				destination.getSolde()+nouvelleTransaction.getMontant());		
	}


	/**
	 * Soumet une transaction, ce qui revient à l'ajouter à la base de données,
	 * 
	 * Le statut de la transaction est mis à jour selon qu'il y a des
	 * erreurs ou non.  S'i; ny a pas d'erreur, la transaction est effectuée
	 * dans chacun des comptes et la base de données mise à jour.
	 *
	 * @param nouvelleTransaction
	 */
	public void soumettreTransaction(Transaction nouvelleTransaction) {
		
		// Les comptes de la transaction à obtenir de la bd.
		Utilisateur source;
		Utilisateur destination;
		
		// Obtenir les comptes.
		try {
			source = bd
					   .obtenirUtilisateurPourCompte(nouvelleTransaction
							                              .getNoCompteSource());
			
			
			destination = bd
					      .obtenirUtilisateurPourCompte(nouvelleTransaction
					     		                     .getNoCompteDestination());
		}catch(Exception E) {
			System.out.println(E);
			return;
		}
		
		//  Rien n'existe, la transaction est refusée.
		
		if(source == null || destination==null || source.equals(destination)) {
			nouvelleTransaction.setStatut(Transaction.REFUSE);
			
		}else {
			
			// Si le montant de la transaction est insuffisant ou négatif, refusée.
			if(nouvelleTransaction.getMontant() > source.getSolde() || 
		       nouvelleTransaction.getMontant() <= 0) {
				
			   nouvelleTransaction.setStatut(Transaction.REFUSE);
				
			// Sinon, on accepte et on ajoute à transaction en mettant les
		    // soldes des comptes à jour.
			}else {
				
				System.out.println("Transaction accepte");
				accepterTransaction(nouvelleTransaction, source, destination);
			}
		}
		
		bd.ajouterTransaction(nouvelleTransaction);
		
		rafraichirUtilisateurActif();
		
		
	}
	
	
	
	/**
	 * Méthode mutatrice de l'utilisateur actif.
	 * 
	 * @param nomUtilisateur Le nom du nouvel utilisateur actif.
	 */
	public void setUtilisateurActif(String nomUtilisateur) {
		
		utilisateurActif = bd.obtenirUtilisateurParNom(nomUtilisateur);
		
		System.out.println("activation de l'utilisateur");
		System.out.println(utilisateurActif);
		
		connecte = true;
	}
		
	
	/**
	 * Réactive un utilisateur actif
	 */
	public void rafraichirUtilisateurActif() {
		
		if(utilisateurActif!=null) {
			utilisateurActif = 
			  bd.obtenirUtilisateurParNom(utilisateurActif.getNomUtilisateur());
		}
	}
	
	/**
	 * Retourne la référence sur l'utilisateur actif.
	 * 
	 * @return L'utilisateur actif.
	 */
	public Utilisateur getUtilisateurActif() {
		return utilisateurActif;
	}

	/**
	 * Déconnecte l'utilisateur et les oberver sont avertis s'il y en a.
	 */
	public void deconnecterUtilisateur() {
		
		utilisateurActif = null;
		connecte = false;
		
	}
	
	/**
	 * Accesseur de l'état de connection de l'utilisateur actif.  
	 * 
	 * @return S'il y a un utilisateur actif de connecté,
	 */
	public boolean estConnecte() {
		return connecte;
	}

	/**
	 * Retourne toutes les transactions de l'utilisateur actif.
	 * 
	 * @return Une collecton de type Vector contenant les transaction.
	 */
	public Vector<Transaction> obtenirTransactionsPourCompte() {
		return bd
				.obtenirTransactionsPourCompte(utilisateurActif
						                                  .getNumeroDeCompte());
	}
}
