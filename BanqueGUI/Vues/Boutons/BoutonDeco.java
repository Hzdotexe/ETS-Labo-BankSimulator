package Vues.Boutons;

import Controleurs.ControleurDeconnexion;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * <p>Classe qui représente le bouton déconnexion
 * qui hérite de la classe JButton.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class BoutonDeco extends JButton {

    JFrame fenetre; // Référence de la fênetre PanneauTransaction

    /**
     * <p>Contructeur par défaut qui permet d'afficher et ajouter un
     * bouton à la fenêtre PanneauTransaction.</p>
     *
     * @param fenetre Instance de la fenêtre PanneauTransaction
     */
    public BoutonDeco(JFrame fenetre) {

        this.setText("Se deconnecter");
        this.fenetre = fenetre;
        //Création d'un nouveau écouteur pour le contrôle du bouton.
        this.addActionListener(new ControleurDeconnexion(fenetre));
    }
}