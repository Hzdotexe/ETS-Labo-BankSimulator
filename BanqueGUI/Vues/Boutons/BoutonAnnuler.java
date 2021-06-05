package Vues.Boutons;

import Controleurs.ControleurAnnuler;

import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * <p>Classe qui représente le bouton annuler qui hérite de la
 * classe JButton.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class BoutonAnnuler extends JButton {

    /**
     * <p>Contructeur par défaut qui permet d'afficher et ajouter un
     * bouton à la boite de dialogue.</p>
     *
     * @param dialogueTransaction Instance de la boite de dialogue
     */
    public BoutonAnnuler(JDialog dialogueTransaction) {

        this.setText("Annuler");
        //Création d'un nouveau écouteur pour le contrôle du bouton.
        this.addActionListener(new ControleurAnnuler(dialogueTransaction));
    }
}