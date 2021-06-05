package baseDonnees.modeles;


/**
 * Premet de regrouper les infos d'une transaction qui consiste au numéro de 
 * compte source, no de compte destination , le montant et le statut de la 
 * transaction qui peut étre ACCEPE REFUSE OU A_DETERMINE.
 * 
 *
*  @author Fred Simard | ETS
 * @version Automne 2020
 * @revision et commentaires Pierre Bélisle
 *
 */
public class Transaction {
	
	// Les états possibles du status.
	public static final String ACCEPTE = "Accepte";
	public static final String REFUSE = "Refuse";
	public static final String A_DETERMINER = "A determiner";

	// Les infos d'une transaction,
	private String noCompteSource;
	private String noCompteDestination;
	private double montant;
	private String statut;
	
	/**
	 * Constructeur par paramétres qui affecte chacun des paramétres à
	 * son attribut correspondant.
	 * 
	 * @param noCompteSource Le compte d'où vient l'argent.
	 * @param noCompteDestination Le compte où va l'argent.
	 * @param montant Le montant de la transaction.
	 * @param status Le statut de la transaction (ACCEPTE-REFUSE-A_DETERMINER)
	 */
	public Transaction(String noCompteSource, 
			           String noCompteDestination, 
			           double montant, 
			           String statut) {
		
		this.noCompteSource = noCompteSource;
		this.noCompteDestination = noCompteDestination;
		this.montant = montant;
		this.statut = statut;
	}

	/**
	 * Constructeur par paramètres qui affecte chacun des paramètres à
	 * son attribut correspondant.  Le statut est A_DETERMINER par défaut.
	 * 
	 * @param noCompteSource Le compte d'où vient l'argent.
	 * @param noCompteDestination Le compte où va l'argent.
	 * @param montant Le mongtant de la transaction.
	 */
	public Transaction(String noCompteSource, String noCompteDestination, double montant) {
		
		this.noCompteSource = noCompteSource;
		this.noCompteDestination = noCompteDestination;
		this.montant = montant;
		this.statut = A_DETERMINER;
	}

	/**
	 * Accessseur du compte source.
	 * 
	 * @return Le compte source associé à la transaction.
	 */
	public String getNoCompteSource() {
		return noCompteSource;
	}

	/**
	 * Accessseur du compte destination.
	 * 
	 * @return Le compte destination associé à la transaction.
	 */
	public String getNoCompteDestination() {
		return noCompteDestination;
	}

	/**
	 * Accesseur du montant.
	 * 
	 * @return Le montant de la transaction.
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * Accesseur du statut qui retourne ACCEPTE, REFUSE ou A_DETERMINER
	 * 
	 * @return Le statut de la transaction ().
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * Mutateur du status.  Aucune validation si la valeur n'est pas ACCEPTE, 
	 * REFUSE ou A_DETERMINER"
	 * 
	 * @param statut Le nouveau statut
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
}
