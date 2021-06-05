package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;

import Vues.Fenetres.FenetreTransactions;
import banque.Banque;
import baseDonnees.modeles.Transaction;

/**
 * <p>Ce contrôleur permet de crée une nouvelle boite de dialogue
 * permettant à l'usager de faire nouvelle transaction. Cette classe
 * implémente la classe ActionListener.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class ControleurConfirmer implements ActionListener {

    //Instance unique de la banque
    private Banque banque = Banque.getInstance();

    //Permet de crée une nouvelle transaction
    private Transaction nouvelleTransaction;

    //Les informations necéssaire à une Transaction
    private JTextField txtFieldCompteDestination, txtFieldMontant;
    private String noCompteSource, noCompteDestination;
    private double montant;

    // Référence  à la boite de dialogue Transaction
    // et à la fênetre Transaction
    private JDialog dialogueTransaction;
    private FenetreTransactions fenetre;

    /**
     * <p>Controleur par défaut qui permet d'initialiser les variables
     * privée avec la fenêtre Transaction, le comptre de destination, le
     * montant à transférer et la référence vers la boite de dialogue.</p>
     *
     * @param fenetre           Référence de la fenêtre PanneauTransaction
     * @param compteDestination Compte de destionnation vers la quelle
     *                          la transaction doit être effectuer
     * @param montant           Le montant à transférer
     * @param dialogue          Référence de la boite de dialogue Transaction.
     */
    public ControleurConfirmer(FenetreTransactions fenetre,
                               JTextField compteDestination, JTextField montant,
                               JDialog dialogue) {
        this.fenetre = fenetre;
        txtFieldCompteDestination = compteDestination;
        txtFieldMontant = montant;
        dialogueTransaction = dialogue;
    }

    public void actionPerformed(ActionEvent e) {

        /* Permet d'enregistrer dans une variable
        locale privée le numéro de compte source */
        noCompteSource = banque.getUtilisateurActif().getNumeroDeCompte();

        /* Permet d'enregistrer dans une variable
        locale privée le numéro du compte de destination */
        noCompteDestination = txtFieldCompteDestination.getText();

        /* Permet d'enregistrer dans une variable
        locale privée le montant du transfert */
        montant = Double.valueOf(txtFieldMontant.getText());

        //Création d'une nouvelle transaction
        nouvelleTransaction = new Transaction(noCompteSource,
                noCompteDestination, montant);

        //Soumet la transaction à la banque et retourne le résultat
        banque.soumettreTransaction(nouvelleTransaction);
        
        fenetre.mettreAJourFenetre();

        //Dispose la fenêtre transaction
        dialogueTransaction.dispose();
    }
}