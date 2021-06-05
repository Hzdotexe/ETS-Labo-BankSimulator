package baseDonnees.bases;

import baseDonnees.listes.ListeBinaire;
import java.io.Serializable;

/**
 * Cette classe est une Colonne dont les données sont ordonnées pour accélérer 
 * la recherche.
 * 
 * On peut y ajouter, rechercher, ou supprimer une valeur comme pour une colonne.
 * 
 * @author Pierre Bélisle et Frédérick Simard.
 * @version Copyright A2020
 *
 */
@SuppressWarnings("rawtypes")
public class ColonneIndexee extends Colonne  implements Serializable{
	
	/**
	 * Stratégie : On utilise une ListeBinaire pour mettre les valeurs indexées
	 * et obtenir une fouille plus rapide..
	 */
	
	
	/**
	 * Classe interne qui permet de regrouper une valeur et sa position
	 * dans la colonne parent (super) qui n'est pas indexée.
	 * 
	 * L'élément doit être Comparable pour être insérée dans la liste binaire.
	 * 
	 */
	
	public class ValIndexee implements Comparable, Serializable{
		
		private int index;
		private Object valeur;
		
		public ValIndexee(int index, Object valeur) {
			this.index = index;
			this.valeur = valeur;
		}

		
		@SuppressWarnings("unchecked")
		public int  compareTo(Object valeur) {
		
			// On reçoit un objet de la classe ValIndexee, il faut 
			// le transtyper.
			ValIndexee valExterne = (ValIndexee)valeur;
		
			// Retourne simplement le résultat de l'évaluation du compareTo des
			// valeurs en cause.
		    return ((Comparable)this.valeur).compareTo(valExterne.valeur);
		}
	
	}
	
	
	public static final int PAS_UNIQUE = -1;
	public static final int ELEMENT_ABSENT = 0;
	
	// Retenir qu'il y a une liste aussi dans Colonne (super)
	private ListeBinaire listeOrdonnee;
	

	/**
	 * Constructeur qui crée une colonne simple et lui ajoute un index
	 * pour faciliter la recherche.
	 * 
	 */
	public ColonneIndexee() {
		
		super();
		
		listeOrdonnee = new ListeBinaire();
	}
	
	@Override
	public void ajouterValeur(Object valeur) {

		ajouterValeurComparable((Comparable)valeur);
	}

	/**
	 * Local privée, permet d'ajouter une valeur dans la colonne indexée qui
	 * sera de type Comparable par conversion de type au passage de paramètre.
	 */
	private void ajouterValeurComparable(Comparable valeur) {
	
		/**
		 * Stratégie : On ajoute la valeur dans la colonne parent à la fin 
		 * de la colonne, ce qui nous donne sa position.  On crée un objet
		 * de la classe interne pour regrouper la valeur et son index et on 
		 * l'ajoute à la liste binaire en ordre.
		 */
	
		ValIndexee valeuAAjouter;
		
		super.ajouterValeur(valeur);
		

		valeuAAjouter= new ValIndexee(super.getNbElements()-1,  valeur);

		try {

			listeOrdonnee.insererEnOrdre(valeuAAjouter);

		} catch (Exception e) {

			e.printStackTrace();
		}

		// DEBUG
		//afficherContenu();
				
	}
	
	@Override
	public int obtenirIndex(Object valeur)  {
		
		
		/**
		 * Stratégie : On crée une valeur indexée bidon pour retourver l'index 
		 * de la valeur dans la liste non ordonnée.  Ainsi on obtient l'index
		 * réel dans la liste non ordonnée.
		 */
		
		// Construit pour rechercher la valeur.
		ValIndexee valInterne = new ValIndexee(0, valeur);
		
		
		int colInd = listeOrdonnee.obtenirIndex(valInterne);
		
		ValIndexee val = null;
		
		if(colInd >= 0) {


			try {
				val = (ValIndexee)listeOrdonnee.getElement(colInd);

			} catch (Exception e) {

			}
		}
		
		// Opérateur ternaire pour retourner l'index ou ELEMENT_ABSENT selon 
		// que val est null ou non.
		return (val!=null)?val.index:ELEMENT_ABSENT;
	}
	
	
	
	@Override
	public void afficherContenu() {


		/*
		 * Stratégie : On affiche le contenu de la liste parent et le contenu 
		 * de la colonne indexée ensuite.  Les élément sont obtenus un à un de
		 * la liste sans les supprimer.
		 */
		
		super.afficherContenu();
		
		System.out.println("Affichage des données indexée");
		
		ValIndexee val =  null;
		
		for(int i=0; i< listeOrdonnee.getNbElements();i++) {
			
			
			try {
				val = (ValIndexee)listeOrdonnee.getElement(i);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
			System.out.println(val);
			
		}
		
	
		
	}

	/**
	 * On ne permet pas à une colonne indexée de remplacer de valeur.
	 * @throws On ne peut remplacer dans une colonne indexée.
	 */
	public void remplacerValeur(Object valeur, int index) throws Exception{
		
		
		throw new Exception("Il n'y a pas de modification possible " +
				            "dans cette liste");
	}
	
	
	@Override
	public void supprimer(int index) throws Exception {
		
		/**
		 * Stratégie:  On obtient l'index de la valeur dans la liste parent non
		 * ordonnée pour pouvoir retrouver la position de la valeur dans la 
		 * liste binaire et on supprime la valeur dans les 2 listes.
		 */
		
		Object valeur = super.obtenirValeur(index);
		
		super.supprimer(index);
		
		
		// On utilise le paramèetre comme variable puisque sa valeur n'est plus
		// utile.
		index = obtenirIndex(valeur);
		
		listeOrdonnee.supprimer(index);
		
	}
	
	/**
	 * cette méthode détermine si la valeur reçue est déjà dans le tableau.
	 * Retourne false, si elle est présente, et true, si absente, parce qu'elle 
	 * répond à la question: est-ce que la valeur est unique?
	 * 
	 * @param valeur Dont ont veut connaitre l'unicité dans la liste
	 * 
	 * @return  Si cette valeur existe c'est qu'elle n'est pas unique.
	 */
	public boolean estUnique(Object valeur) {
		
		/*
		 * Stratégie : On obtient l'index de la valeur dans la liste ordonnée. 
		 * Si l'index obtenu est négatif, la valeur n'est pas présente, on 
		 * retourne true  et si elle est positive ou nulle, c'est qu'elle 
		 * existe donc on retourne false.
		 */
		
		return listeOrdonnee.obtenirIndex(valeur) < 0;
	}
	
}
