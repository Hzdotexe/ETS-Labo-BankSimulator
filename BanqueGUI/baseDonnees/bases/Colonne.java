package baseDonnees.bases;

import java.io.Serializable;
import baseDonnees.listes.ListeStatique;

/**
 * Cette classe définit une colonne de la base de données
 * 
 * La colonne offre plusieurs services permettant d'ajouter et d'accéder aux
 * éléments stocké dans la colonne.
 * 
 * Les services offerts:
 * 		- obtenirValeur
 * 		- obtenirIndex
 * 		- ajouterALaFin
 * 		- enleverValeur
 * 		- changerValeur
 *
 * @author Frédéric Simard | ETS
 * @version Automne 2020
 * @revision et commentaires Pierre Bélisle
 */

public class Colonne implements InterfaceColonne, Serializable{

	/*
	 * Stratégie : On utilise une liste statique qui double de taillle 
	 * lorsqu'elle est pleine et rétréci lorsque pas assez utilisé.
	 * 
	 * Chaque méthode n'aura la plupart du temps qu'à appeler une méthode
	 * de la liste.
	 */
	
	// Rendre cette constante disponible à la classe Colonne.
	public static final int ELEMENT_ABSENT = ListeStatique.ELEMENT_ABSENT;

	// Liste qui représente une colonne.
	private ListeStatique colonne;
	
	
	/**
	 * Constructeur qui crée une colonne innstanciée mais vide.
	 */
	public Colonne() {
		
		colonne = new ListeStatique();
	}

	/**
	 * Ajoute une valeur à la fin de la colonne
     *
	 * @param valeur La valeur à ajouter.
	 */
	public void ajouterValeur(Object valeur) {
		

		try {
	    	 
			colonne.insererFin(valeur);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Retourne la valeur à l'index.
	 * 
	 * @param index position de la valeur
	 * @return La valeur à la position de l'index.
	 * @throws Exception index<0 ou index>getNbElements()
	 */
	public Object obtenirValeur(int index) throws Exception{	

		
		return colonne.getElement(index);
	}

	
	/**
	 * Retourne la valeur à l'index reçue.
	 * 
	 * @param index position de la valeur à remplacer.
	 * @param valeur La valeur de remplacement
	 * @return La valeur à la position de l'index.
	 * @throws Exception index<0 ou index>getNbElements()
	 */
	public void remplacerValeur(Object valeur, int index) throws Exception{
		
		
		colonne.remplacerValeur(valeur, index);
	}

	
	/**
	 * Retourne le nombre d'éléments actuellement dans la liste.
	 */
	public int getNbElements() {
		
		return colonne.getNbElements();
	}

	
	@Override
	public void afficherContenu(){
		
		System.out.println("Affichage des données brutes");
		
		int nbElements = colonne.getNbElements();
		
		for(int i=0;i<nbElements;i++) {
			
			try {
				
				System.out.println(colonne.getElement(i).toString() + " ");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int obtenirIndex(Object valeur) {
		
		
		return colonne.obtenirIndex(valeur);
	}

	@Override
	public void supprimer(int index) throws Exception {
		
		colonne.supprimer(index);
		
	}
	
	/**
	 * Retourne le type en string
	 */
	public String toString() {
		
		return "Colonne";
	}

}
