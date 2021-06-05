package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import banque.Banque;
import baseDonnees.modeles.Transaction;

/**
 * <p>
 * Ce contrôleur permet de gérer les événements qui surviennent sur l'item menu
 * "Sauvegarder l'utilisateur actif (.csv)" dans la fenêtre de transactions
 * grâce à l'implémentation de l'interface ActionListener et ses méthodes.
 * </p>
 * 
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class ControleurSauvegarde implements ActionListener {
	/*
	 * Contiendra l'instance de la banque où on pourra récupérer les données à
	 * sauvegarder
	 */
    Banque banque;
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        banque = Banque.getInstance();
        String nomUtilisateur = banque.getUtilisateurActif()
                .getNomUtilisateur();
        String repertoire = "src/transactions/";
        String nomFichier = nomUtilisateur + "_" + getTempsCourant();

        sauvegarderTransactions(repertoire, nomFichier);
    }

    /**
     * Retourne le temps courant.
     *
     * @return Le temps courant.
     */
    private String getTempsCourant() {
        DateTimeFormatter formateurDeTemps = DateTimeFormatter
                .ofPattern("dd-MM-yyyy HH-mm-ss");
        LocalDateTime tempsCourant = LocalDateTime.now();
        String temps = formateurDeTemps.format(tempsCourant);

        return temps;
    }
    
    /**
     * Permet de sauvegarder les transactions de l'utilisateur actif dans un
     * fichier répertorié à un chemin spécifique.
     * 
     * @param repertoire	Emplacement dans l'explorateur de fichier où on 
	 * 						cherche à sauvegarder l'information.
	 * @param nomFichier 	Le nom du fichier qu'on cherche à sauvegarder.
     */
    private void sauvegarderTransactions(String repertoire, String nomFichier) {
        sauvegarderEnCSV(repertoire, nomFichier);
        SauvegarderEnFichierBinaire(repertoire, nomFichier);
    }

	/**
	 * Permet de sauvegarder les transactions dans un fichier 
	 * d'extension ".csv".
	 * 
	 * @param repertoire	Emplacement dans l'explorateur de fichier où on 
	 * 						cherche à sauvegarder l'information.
	 * @param nomFichier 	Le nom du fichier qu'on cherche à sauvegarder.
	 */
    private void sauvegarderEnCSV(String repertoire, String nomFichier) {
        String extension = ".csv";
        String chemin = repertoire + nomFichier + extension;

        /**
         * STRATÉGIE : On crée un fichier et on écrit les transactions à
         * l'intérieur
         */
        creerFichierCSV(chemin);
        ecrireTransactionsDansCSV(chemin);
    }

    /**
     * Crée un fichier d'extension .csv dans un chemin spécifique.
     *
     * @param chemin Chemin complet où le fichier sera dans l'explorateur 
     * 		  		 de fichiers.
     */
    private void creerFichierCSV(String chemin) {
        try {
            File fichier = new File(chemin);
            String msgSuccess = "Le fichier " + fichier.getName()
                    + " à été crée dans " + fichier.getPath();
            String msgExisteDeja = "Le fichier " + fichier.getName()
                    + "existe déjà.";

            if (fichier.createNewFile()) {
                System.out.println(msgSuccess);
            } else {
                System.out.println(msgExisteDeja);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Écrit toutes les transactions de l'utilisateur actif dans un fichier
     * d'extenstion .csv répertorié dans un chemin spécifique.
     *
     * @param chemin Chemin complet où le fichier sera dans l'explorateur 
     * 		  		 de fichiers.
     */
    private void ecrireTransactionsDansCSV(String chemin) {
        try {
            FileWriter writer = new FileWriter(chemin, true);

            for (Transaction transaction : banque
                    .obtenirTransactionsPourCompte()) {
                writer.write(getDonneesTransaction(transaction) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Une erreur est survenue.");
            e.printStackTrace();
        }
    }

	/**
	 * Retourne les données d'une transaction.
	 *
	 * @param transaction	Objet de type transaction qui contient les données 
	 * 						de transaction de l'utilisateur actif dans 
	 * 						la banque.
	 * @return Les données d'une transaction
	 */
    private String getDonneesTransaction(Transaction transaction) {
        String source = transaction.getNoCompteSource();
        String montant = "" + transaction.getMontant();
        String destination = transaction.getNoCompteDestination();
        String statut = transaction.getStatut();

        return banque.getUtilisateurActif().getNomUtilisateur() + ";" + source
                + ";" + montant + ";" + destination + ";" + statut;
    }

    /**
     * Permet de sauvegarder les transactions dans un fichier 
	 * d'extension ".bin".
	 * 
	 * @param repertoire	Emplacement dans l'explorateur de fichier où on 
	 * 						cherche à sauvegarder l'information.
	 * @param nomFichier 	Le nom du fichier qu'on cherche à sauvegarder.
     */
    private void SauvegarderEnFichierBinaire(String repertoire,
                                             String nomFichier) {
        String extension = ".bin";
        String chemin = repertoire + nomFichier + extension;
  
        /**
         * STRATÉGIE : On crée un fichier et ensuite on écrit les transactions à
         * l'intérieur.
         */
        creerEtEcrireTransactionsDansFichierBinaire(chemin);
    }

	/**
	 * Crée un fichier d'extension .csv dans un chemin spécifique, puis y écrit
	 * toutes les transactions de l'utilisateur actif.
	 * 
	 * @param chemin Chemin complet où le fichier sera dans l'explorateur 
     * 		  		 de fichiers.
	 */
    private void creerEtEcrireTransactionsDansFichierBinaire(String chemin) {
        try {
            FileOutputStream fileOS = new FileOutputStream(chemin);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOS);
            for (Transaction transaction : banque
                    .obtenirTransactionsPourCompte()) {
                outputStream.writeUTF(getDonneesTransaction(transaction));
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
