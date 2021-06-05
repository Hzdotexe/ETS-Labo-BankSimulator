package baseDonnees.bases;

/**
 * Cette interface doit être implémentée par la classe Colonne.
 * 
 * 
 * @author Frédérick Simard
 * mise en page, commentaire et adaptation : Pierre Bélisle
 * @version Copyright(A2020)
 *
 */
@SuppressWarnings("rawtypes")
public interface InterfaceColonne {
	
	
	
	/**
	 * Ajoute la valeur à la fin de la colonne.
	 * 
	 * La colonne est dynamique, La limite de valeurs est dépendante 
	 * de la taille de la mémoire.
	 * 
	 * @param valeur La valeur à ajouter.
	 */
	public void ajouterValeur(Object valeur);
	
	/**
	 * Retourne la valeur située à l'index reçu.
	 * 
	 * L'index doit être valide.
	 * 
	 * @param index La position de la valeur voulue.
	 * @return La valeur situé à l'index, une copie reste dans la liste.
	 * @throws Exception index < 0 ou index >= getNbElements()
	 */
	public Object obtenirValeur(int index) throws Exception;
	
	/**
	 * Retourne la position de la valeur qui doit être Comparable et
	 * implémenter compareTo.  Si la valeur est absente, la fonction
	 * retourne ELEMENT_ABSENT
	 * 
	 * @param valeur La valeur cherchée
	 * @return La position de la valeur ou ELEMENT_ABSENT
	 */
	public int obtenirIndex(Object valeur);
	
	/**
	 * Permet de remplacer une valeur à la position reçue.
	 * 
	 * L'index doit être valide.
	 * 
	 * @param index
	 * @param valeur	 
	 * @throws Exception index < 0 ou index >= getNbElements()
	 */
	public void remplacerValeur(Object valeur, int index) throws Exception;
	
	/**
	 * Retourne le nombre d'éléments actuellement dans la liste
	 * 
	 * @return Le nombre d'éléments dans la liste.
	 */
	public int getNbElements();
	
	/**
	 * Sert principalement au débogage lors des tests.  Il affiche les
	 * valeurs de la liste dans la console.
	 */
	public void afficherContenu();
	
	
	/**
	 * Supprimer la valeur situé à la position reçue par l'index.
	 * 
	 * Si la moiité de la liste est inutilisée, elle est réduite du quart 
	 * de sa taille.
	 * 
	 * @param index La position à supprimer.
	 * @throws Exception index < 0 ou index >= getNbElements()
	 */
	public void supprimer(int index) throws Exception;
	
}
