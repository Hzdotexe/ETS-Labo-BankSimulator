package baseDonnees.listes;

@SuppressWarnings("rawtypes")

/**
 * Classe qui redéfinit les méthodes : inserer, remplacerValeur et obtenirIndex.
 * Les données sont insérées dans l'ordre pour utiliser la fouille binaire 
 * (O(Log(n))
 * 
 * @author Pierre Bélisle
 * @version Copyright (A2020).
 *
 */
public class ListeBinaire extends ListeStatique{

	
	/**
	 * Constructeur d'une liste d'une capacité de ListeStatique.MAX_ELEMENTS
	 */
	public ListeBinaire() {

		super();  
	}


	/**
	 * Constructeur d'une liste de la capacité reçue.
	 * 
	 * @param capcacite taille maximum de la liste au départ.
	 */
	public ListeBinaire(int capacite) {

		super(capacite);
	}

	//� optimiser avec recherche binaire
	@SuppressWarnings("unchecked")
	public int obtenirIndex(Comparable valeur) {

		/*
		 * Stratégie : Implémentation de l'algorithme de fouillle binaire pour 
		 * retourner la position d'uinsertion.
		 */


		// L'indice cherchée si la valeur existe.
		int milieu = 0;

		// Mis à true lorsque l'élément est trouvé pour sortir de la boucle.
		boolean trouve = false;

		// Les indices de parcours.
		int debut = 0;
		int fin = getNbElements() -1;
		int resultatComparaison = 0;

		// Optimisation pour ne pas chercher inutilement.
		if(nbElements == 0) {
			milieu = 0;
		}

		// Fouille binaire
		else {


			while(debut <= fin && !trouve) {

				milieu = (debut + fin) / 2;

				/*
				 * Exemple d'utilisation de l'attribut protected de la 
				 * classe parent.
				 */
				resultatComparaison = ((Comparable)liste[milieu]).compareTo(valeur);

				// La valeur dans le tableau est plus grande que la valeur 
				// cherchée.
				if ( resultatComparaison > 0) {
					fin = milieu - 1;

				}

				// La valeur dans le tableau est plus petite que la valeur 
				// cherchée.
				else if(resultatComparaison  < 0) {

					debut = milieu + 1;
				}

				//  On a trouvé.
				else {

					trouve = true;	
				}
				
			}
			
			/* Dernier petit cas spécial. Lorsque milieu est 0, il se peut 
		       qu'on doive indérer après si la valeur est plus grande que 
			   celle à l'indice 0. C'est le prix à payer p our retourner 
			   correctement -milieu-1 à la fin.
			   */
			/*if(milieu == 0 && resultatComparaison  < 0) {
				milieu = 1;
			}*/

		}
		
	

		// Si la valeur est absente, on retourne l'inverse de la position où 
		// elle devrait se trouver - 1 (l'inverse == -milieu)
		return  (trouve) ?milieu:-debut-1;
	}
	/**
	 * Insèere la valeur à sa position en ordre croissant dans la 
	 * liste.
	 * 
	 * @param valeur La valeur à insérer à son rang.
	 * @throws Exception
	 * @SuppressWarnings("rawtypes") 
	 */
	@SuppressWarnings("unchecked")
	public void insererEnOrdre(Comparable valeur) throws Exception  {


		/*
		 * Stratégie : On gère les cas limites en premier pour sauver du temps
		 * et on utilise obtenirIndex (fouille binaire) pour
		 * obtenir la position d'insertion.  Ensuite, on l'insère à la bonne 
		 * position.
		 */


		// Liste vide ou valeur la plus petite de toute, on insère en avant.
		// On accèede à liste qui est de visibilité protected.
		if (nbElements == 0 || valeur.compareTo(liste[0]) < 0){

			super.inserer(valeur, 0);
		}

		// Si la valeur est la plus grande de toute, on insèere à la fin.
		else if(valeur.compareTo(liste[nbElements-1]) > 0) {

			super.insererFin(valeur);
		}

		else {
			int position = obtenirIndex(valeur);


			// Si l'élément est absent, on incrémente la position avant d'obtenir 
			// sa valeur absolue pour retrouver la position d'insertion.
			// (voir binarySearch java.utils.Arrays 
			if (position < 0) {

				position = Math.abs(position + 1);
			}

			super.inserer(valeur, position);

		}


	}



}
