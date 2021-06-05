package Vues.Dialogues;

import javax.swing.JDialog;

import Vues.Fenetres.FenetreTransactions;
import Vues.Panneaux.PanneauNouvTransaction;

/**
 * <p>Classe qui représente le bouton "Nouvelle Transaction"
 * qui hérite de la classe JButton.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class DialogTransaction extends JDialog {

    // Dimensions du dialogue
    public static final int LONGUEUR = 350;
    public static final int HAUTEUR = 200;

    private FenetreTransactions fenetre;

    /**
     * <p>Constructeur par défaut qui permet d'initialiser tout les paramèetres
     * de la boite de dialogue DialogTransaction.</p>
     *
     * @param fenetre Fenêtre parent PanneauTransactions.
     */
    public DialogTransaction(FenetreTransactions fenetre) {
        this.fenetre = fenetre;
        initComposants();
    }

    /**
     * <p>Peremt d'initialiser tous les composants de la boite de dialogue, dont
     * la taille, le titre et les paramètres de fermeture.</p>
     */
    private void initComposants() {

        // Configuration des parametres du dialogue
        this.setTitle("Ajouter une transaction");
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(LONGUEUR, HAUTEUR);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.ajouterPanneauTransaction();

        this.setVisible(true);
    }

    /**
     * <p>Fonction permettant d'ajouter la
     * boite de dialogue au PanneauTransaction.</p>
     */
    public void ajouterPanneauTransaction() {
        this.add(new PanneauNouvTransaction(fenetre, this));
    }
}