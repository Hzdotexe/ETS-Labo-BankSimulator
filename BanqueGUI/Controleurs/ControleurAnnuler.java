package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * <p>Ce contrôleur permet de gérer les événements qui surviennent sur le
 * bouton d'annulation dans la fênetre de confirmation d'un payement qui
 * implémente la classe ActionListener.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class ControleurAnnuler implements ActionListener {

    private JDialog dialogueTransaction;

    /**
     * <p>Controleur par défaut qui ajoute l'instance de la boite de dialogue
     * à une variable locale privée.</p>
     *
     * @param dialogueTransaction Fenêtre dialogue de nouvelle transaction.
     */
    public ControleurAnnuler(JDialog dialogueTransaction) {
        this.dialogueTransaction = dialogueTransaction;
    }

    public void actionPerformed(ActionEvent e) {

        //Permet de supprimer la fênetre transaction.
        dialogueTransaction.dispose();
    }
}
