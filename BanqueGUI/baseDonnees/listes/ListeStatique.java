package baseDonnees.listes;

/**
 * Dans le cadre du tp2Inf111A2020
 * 
 * Classe qui implémente une liste dynaique qui s'agrandit lorsque
 * pleine et rétrécit lorsque peu utiisée.
 * 
 * En tout temps les opérations se font par rapport à une position fournie en 
 * paramètre (index).
 *
 *
 * Une exception pour supprimer est levée si la liste est vide.
 * 
 *  En tout temps l'index doit être valide. Donc s'il y a une erreur sur un 
 *  index, une exception est également levée.
 *    
 * @author  Pierre Bélisle
 * @version Copyright(A2020)
 */

public class ListeStatique {

	public static final int ELEMENT_ABSENT = -1;

	/*
	 * STRATéGIE : 
	 *
	 * L'implémentation est dans un tableau statique qui est doublé 
	 * lorsque plein.  On le diminue lorsqu'il a moins de la moitié des valeur
	 * lors d'une suppression.
	 *
	 * Les statégie pour chaque méthodes sont décrites dans leur commentaire
	 * de stratégie respectif.
	 */

	//Nombre d'éléments possibles au maximum par défaut
	public static final int MAX_ELEMENTS = 100;

	//La liste avec les objets
	protected Object [] liste;

	//Maintenu à jour après une insertion ou une suppresion.
	protected int nbElements;


	/**
	 * Crée une liste vide d'une capacité de MAX_ELEMENTS au départ.
	 */
	public ListeStatique(){

		/**
		 * Stratgie : On utilise le constructeur qui suit (bonne pratique).
		 */

		this(MAX_ELEMENTS);

	}

	/**
	 * Crée une liste vide de la capacité reçue au départ.
	 */
	public ListeStatique(int taille){

		/**
		 * Stratégie : On initialise explicitement les valeurs plutôt en créant 
		 * le tableau et en s'assurant que nbElements est à 0.  
		 */

		liste = new Object[taille];
	
		nbElements = 0;

	}



	/**
	 * Retourne si la liste est vide.
	 *
	 * Antécédent : Aucun.
	 * Conséquent : Aucun.
	 *
	 * @return true si la liste est vide et false autrement.
	 */
	public boolean estVide(){

		/*
		 * Stratégie : On retourne simplement l'évaluation booléenne de la
		 *  comparaison du nombre d'éléments avec 0.
		 */
		return nbElements == 0;
	}


	
	/**
	 * Décale les données d'un tableau d'une case vers la droite pour les cases
	 * de début à fin.  Début et fin sont considérés comme valides.
	 */
	private void decalerDroite(Object[] tab, int debut, int fin){

		for(int i = fin; i >= debut;i--){
			tab[i+1] = tab[i];
		}

	}


	/**
	 * Décale les données d'un tableau d'une case vers la gauche pour les cases
	 * de début à fin.  Début et fin sont considérés comme valides.
	 */
	private void decalerGauche(Object[] tab, int debut, int fin){

		for(int i = debut; i <= fin;i++){
			tab[i-1] = tab[i];
		}

	}
	
	
	/*
	 * Simple copie d'un tableau dans l'autre.
	 * @dest : Le tableau qui reçoit la copie.
	 * src : Le tableau source.
	 */
	private void copierTab(Object[] dest, Object[] src) {
		
		for(int i = 0; i < nbElements; i++) {
			dest[i] = src[i];
		}
		
	}


	/*
     * Procédure privée qui redimensionne et recopie le tableau.
	 */
	private void ajusterTab(int taille) {
		
		Object[] copie = new Object[taille];
		
		// Copier la liste dans le nouveau tableau.
		copierTab(copie, liste);
		
		// On modifie la liste priginale qui est maintenant doublée.
		liste = copie;
		
		
	}

	/**
	 * Insére l'élément reçu à la position de l'index aprés avoir déplacé
	 * tous les éléments vers la droite d'une case.
	 * 
	 * Il est impossible d'insérer après le dernier élément avec cette méthode.
	 * Veuillez utiliser insererFIn.
	 * 
	 * On ne se préoccupe pas de valider l'index si le nombre d'éléments 
	 * est à 0.
	 *
	 * @param element L'élément à insérer à la position de l'index.
	 * 
	 * @throws Si on insère dans une liste pleine.
	 * @throws Si index < 0 ou index >= getNbElements()
	 *
	 */
	public void inserer(Object element, int index) throws Exception{
		
		
		/*
		 * Stratégie : On utilise le nombre d'éléments pour tester. 
		 * 
		 * S'il le tableau est plein, on le double avec la procdure prévue.
		 * Ensuite, on décale les données à droite à
		 * 
		 * l'aide de la peocédure locale et on met l'élément à la position 
		 * de l'index.
		 * 
		 * 
		 */


		if(nbElements != 0) {

			validerIndex(index);
			
			// Si la liste est pleine, on la double.
			if(nbElements == liste.length){
				ajusterTab(nbElements * 2);
			}

			// On décale jusqu'à l'avant dernier indice vers la droite.
			decalerDroite(liste, index, nbElements-1);


			// Insertion de l'élément s'il n'y a pas eu d'exception.
			liste[index] = element;
		}
		
		else {

			// Liste vide, Insertion de l'élément à la position 0.
			liste[0] = element;
		}



		// Ajuste le nombre d'éléemnt (Un élément de plus).
		nbElements++;
			

	}
	
	


	
	/**
	 * Supprime l'élément à l'index reçu.
	 * 
	 * @param taille 
	 *
	 * @throws Si on supprime dans une liste vide.
	 * @throws Si index < 0 ou index >= getNbElements()
	 */
	public void supprimer(int index) throws Exception{

		
		/*
		 * Stratégie : On utilise la fonction estVide() pour tester s'il y a
		 * des données àa supprimer.   Si c'est le cas, on décale les données 
		 * à gauche à l'aide de la  fonction locale, on décrémente le nombre 
		 * d'éléments et on vérifie s'il faut ajuster le tableau.
		 */

		if(estVide()){
			
			throw new Exception("La liste est vide");
		}

		validerIndex(index);
		
		decalerGauche(liste, index+1, nbElements -1);

		// On met la dernière case significatives à null en ajustant 
		// le nombre d'éléments d'abord.
		liste[--nbElements] = null;
	
		// Si le nombre d'éléments devient moins de la moitié du tableau, 
		// on libère de l'espace.
		if(nbElements < liste.length  / 2) {
			
			ajusterTab((int) (liste.length * .75));
		}
		
	}


	/**
	 * Retourne l'élément à l'index reçu.
	 *
	 * Antécédent : La liste ne doit pas être vide.
	 *
	 * Conséquent : Aucun
	 *
	 * @return L'élément à l'index reçu.
	 */
	public Object getElement(int index) throws Exception{

		/*Stratégie : Retourne simplement l'élément à la
		 * position de index après l'avoir validé.
		 */
		validerIndex(index);
		
		return liste[index];
	}

	/*
	 * Fonction privée qui valide que l'index est dans la bonne plage.
	 * 
	 * Une exception est levée dans le cas contraire.
	 */
	private void validerIndex(int index) throws Exception {
		
		if (index < 0 || index > nbElements) {

			throw new Exception("L'index n'est pas valide" + 
			                    index + " " + 
					            nbElements);
		}
		
	}

	/**
	 * Retourne le nombre d'éléments actuellement dans la liste.
	 *
	 * Antécédent : aucun.
	 * Conséquent : aucun.
	 *
	 * @return Le nombre d'éléments de la liste.
	 */
	public int getNbElements(){

		return nbElements;
	}



	/**
	 * 
	 * Retourne la position de la valeur si elle existe
	 * sinon elle retourne ListeStatique.ELEMENT_ABSENT.
	 * 
	 * @param valeur La valeur cherchée
	 * 
	 * @return L'index de la valeur si elle si trouve ou 
	 *         ListeStatique.ELEMENT_ABSENT.
	 *         
	 */
	public int obtenirIndex(Object valeur) {

		
		/*
		 * Stratégie : Fouille linéaire non ordonnéee.
		 */
		int indexARetourner = ELEMENT_ABSENT;
		
		int i=0;

		while(i<nbElements && indexARetourner == ELEMENT_ABSENT) {
			
			if(liste[i].equals(valeur)){
				 
				indexARetourner = i;
			}
			
			i++;
		}

		return indexARetourner;
	}
	

	/**
	 * Remplace la valeur à l'index reçu.
	 * 
	 * @param valeur
	 * @param index
	 * @throws Exception Si index < 0 ou index >= getNbElements()
	 */
	public void remplacerValeur(Object valeur, int index) throws Exception{
		
		validerIndex(index);
		
		liste[index] = valeur;
		
	
	}
	
	
	/**
	 * Insère à la suite du dernier élément.  Si le tableau est plein, 
	 * il est agrandi.
	 * 
	 * @param valeur La valeur à insérer à la fin.
	 */
	public void insererFin(Object valeur) {


		//Si la liste est pleine, oni l'ajuste.
		if(nbElements == liste.length){
			ajusterTab(liste.length * 2);
		}		

		liste[nbElements] = valeur;

		//un élément de plus
		nbElements++;
		
	}

}