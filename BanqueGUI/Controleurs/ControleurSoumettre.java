package Controleurs;

import Vues.Fenetres.FenetreTransactions;
import banque.Banque;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * <p>Ce contrôleur permet de gérer les événements qui surviennent sur le
 * bouton Soumettre sur la boite d'authentification grâce à l'implémentation de
 * l'interface ActionListener et ses méthodes.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */

public class ControleurSoumettre implements ActionListener {

    JFrame fenetre;
    JDialog auth;
    JTextField nomUtilisateur;
    JPasswordField pwMotDePasse;
    JLabel msgErreur;

    /*
     * STRATÉGIE : Cette classe a pour but d'authentifier un usager
     * et d'ouvrir une nouvelle fenêtre permettant d'afficher les
     * informations de son compte.
     */

    /**
     * <p>Constructeur qui permet d'assigner les paramètres aux variables
     * de cette classe.</p>
     *
     * @param fenetre        Instance de la fenêtre principale
     * @param auth           Instance de la fenêtre d'authentification
     * @param nomUtilisateur Nom d'utilisateur que l'utilisateur inscrit
     * @param pwMotDePasse   Mot de passe que l'utilisateur inscrit
     * @param msgErreur      Message d'erreur qui s'affiche si les informations
     *                       de connexion sont incorecte
     */

    public ControleurSoumettre(JFrame fenetre, JDialog auth,
                               JTextField nomUtilisateur,
                               JPasswordField pwMotDePasse, JLabel msgErreur) {
        this.fenetre = fenetre;
        this.auth = auth;
        this.nomUtilisateur = nomUtilisateur;
        this.pwMotDePasse = pwMotDePasse;
        this.msgErreur = msgErreur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Nom d'utilisateur et Mot de passe en format String
        String utilisateur = nomUtilisateur.getText();
        String motDePasse = new String(pwMotDePasse.getPassword());

        Banque banque = Banque.getInstance();
        Boolean valide = banque.verifier(utilisateur, motDePasse);

        /*Si les informations de connexion sont incorrectes,
        un message d'erreur s'affiche.*/
        if (valide) {
            banque.setUtilisateurActif(utilisateur);

            fermerFenetres();
            afficherNouvelleFenetre();
        } else { //Sinon l'interface principale s'affiche.
            afficherMessageErreur();
        }
    }

    /**
     * <p>Fonction qui annule les processus des fenêtres :
	 * Accueil et Authentification</p>
     */
    private void fermerFenetres() {
        fenetre.dispose();
        auth.dispose();
    }

    /**
     * <p>Fonction qui crée une nouvelle instance de la fenêtre principale.</p>
     */
    private void afficherNouvelleFenetre() {
        FenetreTransactions principal = new FenetreTransactions();
        principal.initialiser();
    }

    /**
     * <p>Fonction qui affiche le message d'erreur, si les informations
	 * de connexion sont incorrectes</p>
     */
    private void afficherMessageErreur() {
        msgErreur.setVisible(true);
    }
}