package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vues.Dialogues.DialogTransaction;
import Vues.Fenetres.FenetreTransactions;

/**
 * <p>Ce contrôleur permet de crée une nouvelle boite de dialogue
 * permettant à l'usager de faire nouvelle transaction. Cette classe
 * implémente la classe ActionListener.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class ControleurTransaction implements ActionListener {

    private FenetreTransactions fenetre;

    /**
     * <p>Controleur par défaut qui ajoute l'instance de la fenêtre transaction
     * à une variable locale privée.</p>
     *
     * @param fenetre PanneauTransaction du programme.
     */
    public ControleurTransaction(FenetreTransactions fenetre) {
        this.fenetre = fenetre;
    }

    public void actionPerformed(ActionEvent e) {

        /* Création d'une nouvelle boite de dialogue lors du clic sur
         * le bouton "Nouvelle Transaction"
         */
        DialogTransaction dlgTransaction = new DialogTransaction(fenetre);
    }
}