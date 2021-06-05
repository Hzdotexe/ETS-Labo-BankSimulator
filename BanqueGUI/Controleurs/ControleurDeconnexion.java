package Controleurs;

import Vues.Fenetres.FenetreAccueil;
import banque.Banque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * <p>Ce contrôleur permet de gérer les événements qui surviennent sur le
 * bouton "Se Deconnecter" dans la fênetre principale Transaction. De plus,
 * cette classe implémente la classe ActionListener.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class ControleurDeconnexion implements ActionListener {

    //Instance privée de la fenêtre Transaction
    private JFrame fenetre;
    //Instance unique de la banque
    private Banque banque = Banque.getInstance();

    /**
     * <p>Controleur par défaut qui ajoute l'instance de la boite de dialogue
     * à une variable locale privée.</p>
     *
     * @param fenetre PanneauTransaction du programme.
     */
    public ControleurDeconnexion(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    public void actionPerformed(ActionEvent e) {

        //Déconnection de l'usager sur la base de données de la banque
        banque.deconnecterUtilisateur();
        //Suppression de la fênetre PanneauTransaction
        fenetre.dispose();

        //Affichage du menu d'accueil
        afficherAcceuil();
    }

    /**
     * <p>Cette fonction permet de crée une instance de la fênetre Accueil
     * et de l'afficher.</p>
     */
    public void afficherAcceuil() {
        FenetreAccueil acceuil = new FenetreAccueil();
        acceuil.initialiser();
    }
}
